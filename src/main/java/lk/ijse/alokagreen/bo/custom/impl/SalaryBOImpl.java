package lk.ijse.alokagreen.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.alokagreen.bo.custom.SalaryBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.alokagreen.dao.custom.EmployeeDAO;
import lk.ijse.alokagreen.dao.custom.EmployeeSalaryDAO;
import lk.ijse.alokagreen.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.alokagreen.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.alokagreen.dao.custom.impl.EmployeeSalaryDAOImpl;
import lk.ijse.alokagreen.dto.EmployeeSalaryDto;
import lk.ijse.alokagreen.entity.Salary;
import lk.ijse.alokagreen.util.DateTime;
import lk.ijse.alokagreen.util.NewId;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    private final EmployeeSalaryDAO employeeSalaryDAO = (EmployeeSalaryDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.SALARY );
    private final EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.ATTENDANCE );
    private final EmployeeDAO employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.EMPLOYEE );

    @Override
    public boolean saveSalary(EmployeeSalaryDto dto) throws SQLException {
        return employeeSalaryDAO.save( new Salary(
                NewId.newSalaryId(),
                dto.getEmployee_Id(),
                dto.getTotal_Salary(),
                dto.getWorked_Days(),
                dto.getBonus(),
                DateTime.dateNow(),
                DateTime.timeNow()
        ) );
    }

    @Override
    public String employeeWorkedDayCount(String id) throws SQLException {
        String yearDate = LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM"));

        return String.valueOf( employeeAttendanceDAO.getEmployeeWorkDayCount( id, yearDate ) );
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getNameOfId( id );
    }

    @Override
    public String calculateTotalSalary(double baseSalary, double bonus) {
        return String.valueOf( (baseSalary + bonus) );
    }

    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException {
        return employeeDAO.getAllId();
    }

    @Override
    public ArrayList<String> getSalaryForMonth(String yearMonth) throws SQLException {
        return employeeSalaryDAO.getSalaryForMonth( yearMonth );
    }

    @Override
    public ArrayList<String> getAllSalaryIds() throws SQLException {
        return employeeSalaryDAO.getAllId();
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException {
        return employeeSalaryDAO.delete( id );
    }

    @Override
    public EmployeeSalaryDto getSalaryData(String id) throws SQLException {
        Salary data = employeeSalaryDAO.getData( id );

        return new EmployeeSalaryDto(
                data.getSalaryId(),
                data.getEmployeeId(),
                data.getTotalSalary(),
                data.getWorkedDayCount(),
                data.getBonus(),
                data.getDate(),
                data.getTime()
        );
    }

    @Override
    public boolean updateSalary(EmployeeSalaryDto dto) throws SQLException {
        return employeeSalaryDAO.update( new Salary(
                dto.getSalary_Id( ),
                dto.getEmployee_Id(),
                dto.getTotal_Salary(),
                dto.getWorked_Days(),
                dto.getBonus(),
                dto.getDate(),
                dto.getTime()
        ) );
    }

    @Override
    public ObservableList<String> setYears() {
        int currentYear = LocalDate.now().getYear();
        int pastYear = 2010;

        List<String> years = new ArrayList<>();
        for (int year = currentYear; year >= pastYear; year--) {
            years.add(String.valueOf(year));
        }

        return FXCollections.observableArrayList( years );
    }

    @Override
    public String mapMonthToNumber(String month) {
        if ( month != null ) {
            switch (month) {
                case "January":
                    return "01";
                case "February":
                    return "02";
                case "March":
                    return "03";
                case "April":
                    return "04";
                case "May":
                    return "05";
                case "June":
                    return "06";
                case "July":
                    return "07";
                case "August":
                    return "08";
                case "September":
                    return "09";
                case "October":
                    return "10";
                case "November":
                    return "11";
                case "December":
                    return "12";
            }
        }
        return "20";
    }
}
