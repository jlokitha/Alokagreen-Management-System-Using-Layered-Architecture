package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.EmployeeBO;
import lk.lokitha.alokagreen.bo.custom.impl.EmployeeBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeDto;
import lk.lokitha.alokagreen.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
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
        btnCancel.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
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
