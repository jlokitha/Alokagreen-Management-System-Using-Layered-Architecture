package lk.ijse.alokagreen.bo.custom.impl;

import lk.ijse.alokagreen.bo.custom.SignInBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.EmployeeDAO;
import lk.ijse.alokagreen.dao.custom.UserDAO;
import lk.ijse.alokagreen.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.alokagreen.dao.custom.impl.UserDAOImpl;
import lk.ijse.alokagreen.util.SendEmail;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class SignInBOImpl implements SignInBO {
    private final UserDAO userDAO = (UserDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.USER );
    private final EmployeeDAO employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.EMPLOYEE );

    @Override
    public boolean checkPassword(String user, String password) throws SQLException {
        return userDAO.checkPassword ( user, password );
    }

    @Override
    public String getEmployeeId(String user) throws SQLException {
        return userDAO.getEmployeeId ( user );
    }

    @Override
    public String getEmployeeEmail(String id) throws SQLException {
        return employeeDAO.getEmailOfId ( id );
    }

    @Override
    public void sendEmail ( String... data) {
        SendEmail sendEmail = new SendEmail ();
        new Thread(()->{
            try {
                sendEmail.sendEmail(data[0], data[1], data[2], data[3]);
            } catch ( MessagingException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public boolean updateUserPassword(String user, String password) throws SQLException {
        return userDAO.updatePassword ( user, password );
    }
}
