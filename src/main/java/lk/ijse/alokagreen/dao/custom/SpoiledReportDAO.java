package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.SpoiledProductReport;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface SpoiledReportDAO extends CrudUtil<SpoiledProductReport> {
    boolean UpdateSpoiledReport(final String... data) throws SQLException;
}
