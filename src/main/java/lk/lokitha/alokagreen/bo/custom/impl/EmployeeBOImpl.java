package lk.lokitha.alokagreen.bo.custom.impl;

import com.google.zxing.WriterException;
import lk.lokitha.alokagreen.bo.custom.EmployeeBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.EmployeeDAO;
import lk.lokitha.alokagreen.dao.custom.impl.EmployeeDAOImpl;
import lk.lokitha.alokagreen.dto.EmployeeDto;
import lk.lokitha.alokagreen.entity.Employee;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.GenerateQrCode;
import lk.lokitha.alokagreen.util.NewId;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    private final EmployeeDAO employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.EMPLOYEE );

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, IOException, WriterException {
        String id = NewId.newEmployeeId( );

        boolean isSaved = employeeDAO.save( new Employee(
                id,
                dto.getFirst_Name( ),
                dto.getLast_Name( ),
                dto.getNic( ),
                dto.getHouse_No( ),
                dto.getStreet( ),
                dto.getCity( ),
                dto.getMobile( ),
                dto.getEmail( ),
                dto.getRole( ),
                DateTime.dateNow( )
        ) );

        if ( isSaved ) {
            GenerateQrCode.generateQr( id );
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete( id );
    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO.update( new Employee(
                dto.getEmployee_Id(),
                dto.getFirst_Name(),
                dto.getLast_Name(),
                dto.getNic(),
                dto.getHouse_No(),
                dto.getStreet(),
                dto.getCity(),
                dto.getMobile(),
                dto.getEmail(),
                dto.getRole(),
                dto.getDate()
        ) );
    }

    @Override
    public EmployeeDto getEmployeeData(String id) throws SQLException {
        Employee data = employeeDAO.getData( id );

        return new EmployeeDto(
                data.getEmployeeId(),
                data.getFirstName(),
                data.getLastName(),
                data.getNic(),
                data.getHouseNo(),
                data.getStreet(),
                data.getCity(),
                data.getMobile(),
                data.getEmail(),
                data.getRole(),
                data.getDate()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        return employeeDAO.getAllId();
    }

    @Override
    public ArrayList<String> getEmployeeRoles() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Manager");
        roles.add("System Manager");
        roles.add("Field Staff");
        roles.add("Shop Staff");
        roles.add("Other");

        return roles;
    }

    @Override
    public String calculateWorkingDays(LocalDate startDate) {
        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(startDate, currentDate);

        if (period.getYears() > 0) {
            return period.getYears() + " years";
        } else if (period.getMonths() > 0) {
            return period.getMonths() + " months";
        } else {
            return period.getDays() + " Days";
        }
    }
}
