package lk.lokitha.alokagreen.bo.custom.impl;

import lk.lokitha.alokagreen.bo.custom.SignInBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.EmployeeDAO;
import lk.lokitha.alokagreen.dao.custom.UserDAO;
import lk.lokitha.alokagreen.dao.custom.impl.EmployeeDAOImpl;
import lk.lokitha.alokagreen.dao.custom.impl.UserDAOImpl;
import lk.lokitha.alokagreen.util.SendEmail;

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

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getNameOfId ( id );
    }

    @Override
    public String getUserName(String user) throws SQLException {
        return userDAO.getUserName ( user );
    }
}
