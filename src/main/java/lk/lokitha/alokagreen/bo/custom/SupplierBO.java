package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    boolean saveSupplier(SupplierDto dto) throws SQLException;
    boolean deleteSupplier(String id) throws SQLException;
    boolean updateSupplier(SupplierDto dto) throws SQLException;
    SupplierDto getSupplierData(String id) throws SQLException;
    ArrayList<String> getAllSupplierIds() throws SQLException;
}