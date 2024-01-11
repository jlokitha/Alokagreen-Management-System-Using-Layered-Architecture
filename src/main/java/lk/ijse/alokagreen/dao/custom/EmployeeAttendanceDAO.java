package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.Attendance;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAttendanceDAO extends CrudUtil<Attendance> {
    int getEmployeeWorkDayCount(final String id, final String yearMonth) throws SQLException;
    ArrayList<String> getAttendanceForDate(final String date) throws SQLException;
}
