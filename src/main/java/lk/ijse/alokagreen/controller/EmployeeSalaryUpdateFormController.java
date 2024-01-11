package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SalaryBO;
import lk.ijse.alokagreen.bo.custom.impl.SalaryBOImpl;
import lk.ijse.alokagreen.dto.EmployeeSalaryDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
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

    private final SalaryBO salaryBO = (SalaryBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.SALARY );

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if ( validateSalary() ) {
            try {
                double base = Double.parseDouble( txtBaseSalary.getText() );
                double bonus = Double.parseDouble( txtBonus.getText() );

                boolean isSaved = salaryBO.updateSalary( new EmployeeSalaryDto(
                        id,
                        cmbEmpId.getSelectionModel( ).getSelectedItem( ),
                        (base + bonus),
                        Integer.parseInt( txtDayCount.getText( ) ),
                        bonus,
                        null,
                        null
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    EmployeeSalaryManageFormController.controller.getAllId();
                }

            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {

        try {
            lblEmpId.setText(null);
            String name = salaryBO.getEmployeeName( id );
            String days = salaryBO.employeeWorkedDayCount(id);

            txtName.setText(name);
            txtDayCount.setText(days);
            txtBonus.requestFocus();

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtBaseSalaryOnAction(ActionEvent event) {
        lblBaseSalary.setText(null);

        String baseSalary = txtBaseSalary.getText();

        if (Regex.money(baseSalary)) {
            lblBaseSalary.setText("Should contain integer or decimal");

        } else {
            double bonus = Double.parseDouble(txtBonus.getText());
            double base = Double.parseDouble(txtBaseSalary.getText());
            txtTotalSalary.setText(salaryBO.calculateTotalSalary( base, bonus ));
            btnUpdateOnAction(event);
        }
    }

    @FXML
    void txtBonusOnAction(ActionEvent event) {
        lblBonus.setText(null);

        String bonusSalary = txtBonus.getText();

        if (Regex.money(bonusSalary)) {
            lblBonus.setText("Should contain integer or decimal");

        } else {
            double bonus = Double.parseDouble(txtBonus.getText());
            double baseSalary = Double.parseDouble(txtBaseSalary.getText());
            txtTotalSalary.setText(salaryBO.calculateTotalSalary( baseSalary, bonus ));
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

    private void setData() {
        try {
            EmployeeSalaryDto data = salaryBO.getSalaryData( id );
            String name = salaryBO.getEmployeeName(data.getEmployee_Id());

            id = data.getSalary_Id();
            date = data.getDate();
            cmbEmpId.setValue(data.getEmployee_Id());
            txtName.setText(name);
            txtDayCount.setText(String.valueOf(data.getWorked_Days()));
            txtBonus.setText(String.valueOf(data.getBonus()));
            txtBaseSalary.setText(String.valueOf(data.getTotal_Salary() - data.getBonus()));
            txtTotalSalary.setText(String.valueOf(data.getTotal_Salary()));

        } catch ( SQLException e ) {
            e.printStackTrace();
        }


    }

    private void setCmb() {
        try {
            cmbEmpId.setStyle("-fx-font-size: 16;");
            cmbEmpId.getItems().addAll(salaryBO.getAllEmployeeId());
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnAdd );
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnAdd );
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCmb();
        setData();
    }
}
