package lk.lokitha.alokagreen.bo.custom.impl;

import lk.lokitha.alokagreen.bo.custom.SignUpBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.EmployeeDAO;
import lk.lokitha.alokagreen.dao.custom.UserDAO;
import lk.lokitha.alokagreen.dao.custom.impl.EmployeeDAOImpl;
import lk.lokitha.alokagreen.dao.custom.impl.UserDAOImpl;
import lk.lokitha.alokagreen.dto.UserDto;
import lk.lokitha.alokagreen.entity.User;
import lk.lokitha.alokagreen.util.SendEmail;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    private final EmployeeDAO employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.EMPLOYEE );
    private final UserDAO userDAO = (UserDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.USER );

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getNameOfId ( id );
    }

    @Override
    public String getUserName(String user) throws SQLException {
        return userDAO.getUserName ( user );
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
    public String getEmployeeId(String user) throws SQLException {
        return userDAO.getEmployeeId ( user );
    }

    @Override
    public boolean saveUser( UserDto dto ) throws SQLException {
        return userDAO.save ( new User (
                dto.getUsername (),
                dto.getPassword (),
                dto.getEmpId ()
        ) );
    }
}
