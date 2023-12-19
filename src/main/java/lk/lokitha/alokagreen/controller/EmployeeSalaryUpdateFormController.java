package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.EmployeeSalaryDto;
import lk.lokitha.alokagreen.model.EmployeeAttendanceModel;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.model.EmployeeSalaryModel;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EmployeeSalaryUpdateFormController implements Initializable {

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
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAdd;

    private String date;

    public static String id;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if ( validateSalary() ) {
            EmployeeSalaryDto eSDto = new EmployeeSalaryDto();

            eSDto.setSalary_Id(id);
            eSDto.setEmployee_Id(cmbEmpId.getSelectionModel().getSelectedItem());
            double base = Double.parseDouble( txtBaseSalary.getText() );
            double bonus = Double.parseDouble( txtBonus.getText() );
            eSDto.setTotal_Salary(base + bonus);
            eSDto.setWorked_Days(Integer.parseInt(txtDayCount.getText()));
            eSDto.setBonus(Double.parseDouble(txtBonus.getText()));

            boolean isSaved = EmployeeSalaryModel.updateEmpSalary(eSDto);

            if (isSaved) {
                Navigation.closePane();
                EmployeeSalaryManageFormController.controller.getAllId();
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
        String name = EmployeeModel.getNameOfId(id);
        String yearMonth = getYearMonth(date);
        int days = EmployeeAttendanceModel.getEmployeeWorkDayCount(id, yearMonth);

        txtName.setText(name);
        txtDayCount.setText(String.valueOf(days));
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
            btnUpdateOnAction(event);
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

    public String getYearMonth(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        int year = localDate.getYear();
        int month = localDate.getMonthValue();

        return year + "-" + month;

    }

    private void setData() {

        EmployeeSalaryDto data = EmployeeSalaryModel.getData(id);
        String name = EmployeeModel.getNameOfId(data.getEmployee_Id());

        id = data.getSalary_Id();
        date = data.getDate();
        cmbEmpId.setValue(data.getEmployee_Id());
        txtName.setText(name);
        txtDayCount.setText(String.valueOf(data.getWorked_Days()));
        txtBonus.setText(String.valueOf(data.getBonus()));
        txtBaseSalary.setText(String.valueOf(data.getTotal_Salary() - data.getBonus()));
        txtTotalSalary.setText(String.valueOf(data.getTotal_Salary()));

    }

    private void setCmb() {
        cmbEmpId.getItems().addAll(EmployeeModel.getAllId());
        cmbEmpId.setStyle("-fx-font-size: 16;");
    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
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
        setCmb();
        setData();
    }
}
