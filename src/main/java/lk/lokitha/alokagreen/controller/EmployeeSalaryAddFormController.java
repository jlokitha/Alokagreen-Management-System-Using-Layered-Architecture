package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.EmployeeSalaryDto;
import lk.lokitha.alokagreen.model.EmployeeAttendanceModel;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.model.EmployeeSalaryModel;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EmployeeSalaryAddFormController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXTextField txtDayCount;

    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtBonus;

    @FXML
    private JFXTextField txtBaseSalary;

    @FXML
    private JFXTextField txtTotalSalary;

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblBonus;

    @FXML
    private Label lblBaseSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if ( validateSalary() ) {
            EmployeeSalaryDto eSD = new EmployeeSalaryDto();

            eSD.setSalary_Id(NewId.newSalaryId());
            eSD.setEmployee_Id(cmbEmpId.getSelectionModel().getSelectedItem());
            double base = Double.parseDouble( txtBaseSalary.getText() );
            double bonus = Double.parseDouble( txtBonus.getText() );
            eSD.setTotal_Salary(base + bonus);
            eSD.setWorked_Days(Integer.parseInt(txtDayCount.getText()));
            eSD.setBonus(Double.parseDouble(txtBonus.getText()));
            eSD.setDate(DateTime.dateNow());
            eSD.setTime(DateTime.timeNow());

            boolean isSaved = EmployeeSalaryModel.saveEmpSalary(eSD);

            if (isSaved) {
                Navigation.closePane();
                EmployeeSalaryManageFormController.controller.getAllId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Salary Payment Not Added !").show();
            }
        }

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {
        lblEmpId.setText(null);
        String id = cmbEmpId.getSelectionModel().getSelectedItem();
        String yearDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        txtName.setText(EmployeeModel.getNameOfId(id));
        txtDayCount.setText(String.valueOf(EmployeeAttendanceModel.getEmployeeWorkDayCount(id, yearDate)));
        txtBonus.requestFocus();
    }

    @FXML
    void txtBaseSalaryOnAction(ActionEvent event) {
        lblBaseSalary.setText(null);

        String baseSalary = txtBaseSalary.getText();

        if (Regex.money(baseSalary)) {
            lblBaseSalary.setText("Should contain integer or decimal");

        } else {
            double bonus = Double.parseDouble(txtBonus.getText());
            double baseSalary1 = Double.parseDouble(txtBaseSalary.getText());
            txtTotalSalary.setText(String.valueOf(bonus + baseSalary1));
            btnAddOnAction(event);
        }
    }

    @FXML
    void txtBonusOnAction(ActionEvent event) {
        lblBonus.setText(null);

        String bonus = txtBonus.getText();

        if (Regex.money(bonus)) {
            lblBonus.setText("Should contain integer or decimal");

        } else {
            double bonus1 = Double.parseDouble(txtBonus.getText());
            double baseSalary = Double.parseDouble(txtBaseSalary.getText());
            txtTotalSalary.setText(String.valueOf(bonus1 + baseSalary));
            txtBaseSalary.requestFocus();
        }
    }

    @FXML
    void txtBonusOnMouseClicked(MouseEvent event) {
        lblBonus.setText(null);
    }

    @FXML
    void txtBaseSalaryOnMouseClicked(MouseEvent event) {
        lblBaseSalary.setText(null);
    }

    @FXML
    void cmbEmpIdOnMouseClicked(MouseEvent event) {
        lblEmpId.setText(null);
    }

    public boolean validateSalary() {
        if (cmbEmpId.getSelectionModel().getSelectedItem() == null) {
            lblEmpId.setText("Please select a employee Id");
            return false;
        }

        String bonus = txtBonus.getText();

        if (Regex.money(bonus)) {
            lblBonus.setText("Should contain integer or decimal");
            return false;
        }

        String baseSalary = txtBaseSalary.getText();

        if (Regex.money(baseSalary)) {
            lblBaseSalary.setText("Should contain integer or decimal");
            return false;
        }

        return true;
    }

    public void setComboBox() {
        cmbEmpId.setStyle("-fx-font-size: 16;");
        cmbEmpId.getItems().addAll(EmployeeModel.getAllId());
    }

    public void setTextFields() {
        txtBaseSalary.setText("36000.00");
        txtTotalSalary.setText(String.valueOf(Double.parseDouble(txtBaseSalary.getText())));
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboBox();
        setTextFields();
    }
}
