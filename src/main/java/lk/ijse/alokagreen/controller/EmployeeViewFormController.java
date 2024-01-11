package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.EmployeeBO;
import lk.ijse.alokagreen.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.alokagreen.dto.EmployeeDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeViewFormController implements Initializable {

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNic;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblWorkingFor;

    @FXML
    private JFXButton btnCancel;

    public static String id;

    private final EmployeeBO employeeBO = (EmployeeBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.EMPLOYEE );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    private String calculateWorkingDuration(LocalDate startDate) {
        return employeeBO.calculateWorkingDays( startDate );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            EmployeeDto detail = employeeBO.getEmployeeData( id );

            String adderess = detail.getHouse_No() + ", " + detail.getStreet() + ", " + detail.getCity();

            lblId.setText(detail.getEmployee_Id());
            lblName.setText(detail.getFirst_Name() + " " + detail.getLast_Name());
            lblRole.setText(detail.getRole());
            lblMobile.setText(detail.getMobile());
            lblEmail.setText(detail.getEmail());
            lblNic.setText(detail.getNic());
            lblAddress.setText(adderess);
            lblWorkingFor.setText(calculateWorkingDuration(LocalDate.parse(detail.getDate())));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
