package lk.ijse.alokagreen.bo.custom.impl;

import lk.ijse.alokagreen.bo.custom.MaterialBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.MaterialDAO;
import lk.ijse.alokagreen.dao.custom.impl.MaterialDAOImpl;
import lk.ijse.alokagreen.dto.MaterialDto;
import lk.ijse.alokagreen.entity.MaterialList;
import lk.ijse.alokagreen.util.NewId;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialBOImpl implements MaterialBO {
    private final MaterialDAO materialDAO = (MaterialDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.MATERIAL );

    @Override
    public boolean saveMaterial(MaterialDto dto) throws SQLException {
        return materialDAO.save( new MaterialList(
                dto.getMaterial_Code(),
                dto.getDescription()
        ) );
    }

    @Override
    public ArrayList<String> getAllMaterialIds() throws SQLException {
        return materialDAO.getAllId();
    }

    @Override
    public boolean deleteMaterial(String id) throws SQLException {
        return materialDAO.delete( id );
    }

    @Override
    public MaterialDto getMaterialData(String id) throws SQLException {
        MaterialList data = materialDAO.getData( id );

        return new MaterialDto(
                data.getMaterialCode(),
                data.description
        );
    }

    @Override
    public boolean updateMaterial(MaterialDto dto) throws SQLException {
        return materialDAO.update( new MaterialList(
                dto.getMaterial_Code(),
                dto.getDescription()
        ));
    }

    @Override
    public String generateNewMaterialId() throws SQLException {
        return NewId.newMaterialCode();
    }
}
