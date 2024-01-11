package lk.ijse.alokagreen.bo.custom;

import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.SpoiledReportDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SpoiledReportBO extends SuperBO {
    boolean saveSpoiledReport( SpoiledReportDto dto ) throws SQLException;
    String getProductId(String desc) throws SQLException;
    ArrayList<String> getAllProductDesc() throws SQLException;
    String getProductDesc(String id) throws SQLException;
    boolean updateSpoiledReport(String... data) throws SQLException;
    SpoiledReportDto getSpoiledReportDetails(String id) throws SQLException;
    ArrayList<String> getAllSpoiledReportIds() throws SQLException;
}
