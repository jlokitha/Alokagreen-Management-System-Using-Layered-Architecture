package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.EmployeeSalaryDto;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.model.EmployeeSalaryModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeSalaryViewFormController implements Initializable {

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblEmpName;

    @FXML
    private Label lblWorkedDays;

    @FXML
    private Label lblBonus;

    @FXML
    private Label lblBase;

    @FXML
    private Label lblTotal;

    @FXML
    private JFXButton btnCancel;

    public static String id;

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
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmployeeSalaryDto data = EmployeeSalaryModel.getData(id);
        String name = EmployeeModel.getNameOfId(data.getEmployee_Id());

        double base = data.getTotal_Salary() - data.getBonus();

        lblEmpId.setText(data.getEmployee_Id());
        lblEmpName.setText(name);
        lblWorkedDays.setText(String.valueOf(data.getWorked_Days()));
        lblBonus.setText(String.valueOf(data.getBonus()));
        lblBase.setText(String.valueOf(base));
        lblTotal.setText(String.valueOf(data.getTotal_Salary()));
    }
}
