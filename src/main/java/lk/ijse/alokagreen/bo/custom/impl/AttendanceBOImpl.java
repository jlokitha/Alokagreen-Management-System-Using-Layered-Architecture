package lk.ijse.alokagreen.bo.custom.impl;

import lk.ijse.alokagreen.bo.custom.AttendanceBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.alokagreen.dao.custom.EmployeeDAO;
import lk.ijse.alokagreen.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.alokagreen.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.alokagreen.dto.EmployeeAttendanceDto;
import lk.ijse.alokagreen.entity.Attendance;
import lk.ijse.alokagreen.util.DateTime;
import lk.ijse.alokagreen.util.NewId;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {
    private final EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.ATTENDANCE );
    private final EmployeeDAO employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.EMPLOYEE );
    @Override
    public boolean saveAttendance(EmployeeAttendanceDto dto) throws SQLException {
        return employeeAttendanceDAO.save( new Attendance(
                NewId.newAttendanceId(),
                dto.getEmployee_Id(),
                DateTime.dateNow(),
                DateTime.timeNow()
        ) );
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getNameOfId( id );
    }

    @Override
    public ArrayList<String> getAllAttendanceIds() throws SQLException {
        return employeeAttendanceDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAttendanceForDate(String date) throws SQLException {
        return employeeAttendanceDAO.getAttendanceForDate( date );
    }

    @Override
    public boolean deleteAttendance(String id) throws SQLException {
        return employeeAttendanceDAO.delete( id );
    }

    @Override
    public EmployeeAttendanceDto getAttendanceData(String id) throws SQLException {
        Attendance data = employeeAttendanceDAO.getData( id );

        return new EmployeeAttendanceDto(
                data.getAttendanceId(),
                data.getEmployeeId(),
                data.getDate(),
                data.getTime()
        );
    }

    @Override
    public boolean updateAttendance(EmployeeAttendanceDto dto) throws SQLException {
        return employeeAttendanceDAO.update( new Attendance(
                dto.getAttendance_Id(),
                dto.getEmployee_Id(),
                dto.getDate(),
                dto.getTime()
        ) );
    }

    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException {
        return employeeDAO.getAllId();
    }
}
