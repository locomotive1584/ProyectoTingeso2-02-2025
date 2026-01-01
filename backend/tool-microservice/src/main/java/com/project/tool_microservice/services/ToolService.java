package com.project.tool_microservice.services;

import com.project.tool_microservice.entities.ToolEntity;
import com.project.tool_microservice.entities.UnitEntity;
import com.project.tool_microservice.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {
    @Autowired
    ToolRepository toolRepository;
    @Autowired
    private UnitService unitService;

    public List<ToolEntity> getTools() {
        return toolRepository.findAll();
    }

    public ToolEntity getToolById(long id) {
        return toolRepository.findById(id);
    }

    public List<ToolEntity> getToolsByCategory(String category) {
        return toolRepository.findByCategory(category);
    }

    public boolean checkBasicData(ToolEntity tool) {
        boolean name = tool.getName() != null && tool.getName().length() > 0;
        boolean category = tool.getCategory() != null && tool.getCategory().length() > 0;
        return name && category;
    }

    public ToolEntity saveTool(ToolEntity tool) throws Exception{
        if(tool == null){
            throw new Exception("Herramienta es nula");
        }
        else if (checkBasicData(tool)) {
            toolRepository.save(tool);
        }
        throw new Exception("Herramienta tiene datos basicos nulos");
    }

    public boolean deleteTool(long id) throws Exception {
        try{
            if (toolRepository.getById(id).getStock() == 0) {
                toolRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception("Herramienta tiene unidades activas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ToolEntity updateTool(ToolEntity tool) throws Exception{
        try {
            return toolRepository.save(tool);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la herramienta");
        }
    }

    public void addStock(long id, int stock) throws Exception {
        ToolEntity tool = toolRepository.findById(id);
        if (tool == null) {
            throw new Exception("Herramienta innexistente");
        }
        else {
            int oldStock = tool.getStock();
            tool.setStock(oldStock + stock);
            updateTool(tool);
        }
    }

    public boolean toolExist(long id) {
        return toolRepository.getById(id) != null;
    }

    public boolean toolHasStock(long id) throws Exception {
        try {
            return toolRepository.getById(id).getStock() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UnitEntity getAvaliableUnit(long id) throws Exception {
        try{
            if (toolRepository.getById(id) != null) {
                if (toolRepository.getById(id).getStock() > 0) {
                    return unitService.getUnitByToolIdAndState(id, "disponible");
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
