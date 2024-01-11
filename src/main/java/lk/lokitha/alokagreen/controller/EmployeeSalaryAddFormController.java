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
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SalaryBO;
import lk.lokitha.alokagreen.bo.custom.impl.SalaryBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeSalaryDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
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

    private final SalaryBO salaryBO = (SalaryBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.SALARY );

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if ( validateSalary() ) {
            double base = Double.parseDouble( txtBaseSalary.getText() );
            double bonus = Double.parseDouble( txtBonus.getText() );

            try {
                boolean isSaved = salaryBO.saveSalary( new EmployeeSalaryDto(
                        null,
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
                } else {
                    new Alert(Alert.AlertType.ERROR, "Salary Payment Not Added !").show();
                }

            } catch ( SQLException e ) {
                throw new RuntimeException( e );
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
            String id = cmbEmpId.getSelectionModel().getSelectedItem();
            txtName.setText(salaryBO.getEmployeeName( id ));
            txtDayCount.setText(salaryBO.employeeWorkedDayCount( id ));
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
            txtTotalSalary.setText(salaryBO.calculateTotalSalary( bonus, base ));
            btnAddOnAction(event);
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

    public void setComboBox() {
        try {
            cmbEmpId.setStyle("-fx-font-size: 16;");
            cmbEmpId.getItems().addAll(salaryBO.getAllEmployeeId());
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public void setTextFields() {
        txtBaseSalary.setText("36000.00");
        txtTotalSalary.setText(String.valueOf(Double.parseDouble(txtBaseSalary.getText())));
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnAdd );
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
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
        setComboBox();
        setTextFields();
    }
}
