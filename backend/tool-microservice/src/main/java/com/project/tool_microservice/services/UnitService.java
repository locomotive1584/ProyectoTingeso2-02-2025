package com.project.tool_microservice.services;

import com.project.tool_microservice.entities.UnitEntity;
import com.project.tool_microservice.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    @Autowired
    UnitRepository unitRepository;

    @Autowired
    ToolService toolService;

    public List<UnitEntity> getAllUnits() {
        return unitRepository.findAll();
    }

    public UnitEntity getUnitById(long id) {
        return unitRepository.findById(id);
    }

    public List<UnitEntity> getUnitsByToolId(long id) {
        return unitRepository.findByToolId(id);
    }

    public List<UnitEntity> getUnitsByState(String state){
        return unitRepository.findByState(state);
    }

    public boolean checkStates(UnitEntity unit){
        boolean isNotNull = unit.getState() != null && unit.getState().length() > 0;
        if(isNotNull){
            boolean isAAllowedStates = unit.getState().equals("disponible") || unit.getState().equals("prestada")
                    || unit.getState().equals("en reparacion") || unit.getState().equals("dada de baja");
            return isAAllowedStates;
        }
        return false;
    }

    public boolean isAnAddingState(String state){
        return state.equals("disponible");
    }

    public boolean isASubstractingState(String state){
        return state.equals("prestada") || state.equals("en reparacion") || state.equals("dada de baja");
    }

    public UnitEntity saveUnit(UnitEntity unit) throws Exception{
        if (unit == null){
            throw new Exception("La unidad no puede ser nulo");
        }
        else if (checkStates(unit)) {
            if (isAnAddingState(unit.getState())) {
                toolService.addStock(unit.getToolId(), 1);
            }
            unitRepository.save(unit);
        }
        throw new Exception("La unidad no tiene datos permitidos (OBSERVAR ESTADO)");
    }

    public boolean deleteUnit(long id) throws Exception{
        try{
            if (unitRepository.getById(id) != null){
                if (isAnAddingState(unitRepository.getById(id).getState())) {
                    toolService.addStock(unitRepository.getById(id).getToolId(), -1);
                }
                unitRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception("La unidad no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UnitEntity updateUnit(UnitEntity unit) throws Exception{
        try {
            return unitRepository.save(unit);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la unidad");
        }
    }

    public void setStates(UnitEntity unit, String state) throws Exception{
        try {
            if (unit != null){

                if (isAnAddingState(state)){
                    toolService.addStock(unit.getToolId(), 1);
                    unit.setState(state);
                    updateUnit(unit);
                }

                else if (isASubstractingState(state)){
                    toolService.addStock(unit.getToolId(), -1);
                    unit.setState(state);
                    updateUnit(unit);
                }

                else {
                    throw new Exception("Estado no valido; state: " + state);
                }
            }
            else {
                throw new Exception("La unidad no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UnitEntity getUnitByToolIdAndState(long toolId, String state){
        return unitRepository.findByToolIdAndState(toolId, state).get(0);
    }

    public UnitEntity getAvaliableUnit(long toolId) throws Exception {
        try{
            if (toolService.getToolById(toolId) != null) {
                if (toolService.getToolById(toolId).getStock() > 0) {
                    return getUnitByToolIdAndState(toolId, "disponible");
                }
                else {
                    throw new Exception("Herramienta no tiene stock disponible");
                }
            }
            else {
                throw new Exception("Herramienta no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al conseguir unidad de la herramienta");
        }
    }
}
