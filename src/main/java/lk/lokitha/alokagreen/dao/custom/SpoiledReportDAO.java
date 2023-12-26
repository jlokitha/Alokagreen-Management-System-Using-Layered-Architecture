package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.SpoiledProductReport;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface SpoiledReportDAO extends CrudUtil<SpoiledProductReport> {
    boolean UpdateSpoiledReport(final String... data) throws SQLException;
}
