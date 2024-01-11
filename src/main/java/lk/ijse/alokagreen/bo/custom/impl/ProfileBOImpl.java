package lk.ijse.alokagreen.bo.custom.impl;

import lk.ijse.alokagreen.bo.custom.ProfileBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.EmployeeDAO;
import lk.ijse.alokagreen.dao.custom.UserDAO;
import lk.ijse.alokagreen.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.alokagreen.dao.custom.impl.UserDAOImpl;
import lk.ijse.alokagreen.dto.EmployeeDto;
import lk.ijse.alokagreen.entity.Employee;
import lk.ijse.alokagreen.util.SendEmail;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class ProfileBOImpl implements ProfileBO {
    private final UserDAO userDAO = (UserDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.USER );
    private final EmployeeDAO employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.EMPLOYEE );

    @Override
    public String getEmployeeId(String username) throws SQLException {
        return userDAO.getEmployeeId( username );
    }

    @Override
    public EmployeeDto getEmployeeDetails(String id) throws SQLException {
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
    public boolean checkPassword(String user, String password) throws SQLException {
        return userDAO.checkPassword( user, password );
    }

    @Override
    public String getEmployeeEmail(String id) throws SQLException {
        return employeeDAO.getEmailOfId( id );
    }

    @Override
    public boolean deleteUser(String user) throws SQLException {
        return userDAO.delete( user );
    }

    @Override
    public void sendAccountDeletionEmail(String email) {
        SendEmail sendEmail = new SendEmail();
        String subject = "Account Deletion Confirmation";
        String htmlPath = "DeleteUserEmail.html";

        new Thread(() -> {
            try {
                sendEmail.sendEmail(email, subject, htmlPath);

            } catch ( MessagingException e) {}
        }).start();
    }

    @Override
    public boolean updatePassword(String user, String password) throws SQLException {
        return userDAO.updatePassword( user, password );
    }

    @Override
    public void sendPasswordChangeEmail(String email) {
        new Thread(() -> {
            SendEmail sendEmail = new SendEmail();

            String subject = "Password Change Confirmation";
            String htmlPath = "ChangePasswordEmail.html";
            try {
                sendEmail.sendEmail(email, subject, htmlPath);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
