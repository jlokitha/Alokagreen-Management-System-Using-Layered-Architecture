package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.model.EmployeeAttendanceModel;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeAttendanceUpdateFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAdd;

    public static String id;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if (cmbEmpId.getSelectionModel().getSelectedItem() != null) {
            String cId = cmbEmpId.getSelectionModel().getSelectedItem();
            boolean isSaved = EmployeeAttendanceModel.updateEmployeeAttendance(id, cId);

            if (isSaved) {
                Navigation.closePane();
                EmployeeAttendanceManageFormController.controller.getAllId();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select an employee ID").show();
        }
    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {
        txtName.setText(EmployeeModel.getNameOfId(cmbEmpId.getSelectionModel().getSelectedItem()));
    }

    private void setData() {

        EmployeeAttendanceDto data = EmployeeAttendanceModel.getData(id);
        String name = EmployeeModel.getNameOfId(data.getEmployee_Id());

        cmbEmpId.setValue(data.getEmployee_Id());
        txtName.setText(name);
    }


    private void setCmb() {
        cmbEmpId.getItems().addAll(EmployeeModel.getAllId());
        cmbEmpId.setStyle("-fx-font-size: 16;");
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
        setCmb();
        setData();
    }
}
