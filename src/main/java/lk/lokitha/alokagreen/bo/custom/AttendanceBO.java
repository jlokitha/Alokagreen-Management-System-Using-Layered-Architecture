package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    boolean saveAttendance(EmployeeAttendanceDto dto) throws SQLException;
    String getEmployeeName(String id) throws SQLException;
    ArrayList<String> getAllAttendanceIds() throws SQLException;
    ArrayList<String> getAttendanceForDate(String date) throws SQLException;
    boolean deleteAttendance(String id) throws SQLException;
    EmployeeAttendanceDto getAttendanceData(String id) throws SQLException;
    boolean updateAttendance(EmployeeAttendanceDto dto) throws SQLException;
    ArrayList<String> getAllEmployeeIds() throws SQLException;
}
