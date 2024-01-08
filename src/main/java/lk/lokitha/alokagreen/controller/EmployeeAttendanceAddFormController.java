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
import lk.lokitha.alokagreen.bo.custom.AttendanceBO;
import lk.lokitha.alokagreen.bo.custom.impl.AttendanceBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeAttendanceAddFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private Label lblId;

    public static String id;

    private final AttendanceBO attendanceBO = (AttendanceBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.ATTENDANCE );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        id = null;
        Navigation.closePane();
    }

    @FXML
    void btnMarkOnAction(ActionEvent event) {

        if (cmbEmpId.getSelectionModel().getSelectedItem() != null) {
            try {
                boolean isSaved = attendanceBO.saveAttendance( new EmployeeAttendanceDto(
                        null,
                        cmbEmpId.getSelectionModel( ).getSelectedItem( ),
                        null,
                        null
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    if ( EmployeeAttendanceManageFormController.controller != null) {
                        EmployeeAttendanceManageFormController.controller.getAllId();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Attendance Not Marked !").show();
                }

            } catch (SQLException e) {
                throw new RuntimeException( e );
            }

        } else {
            lblId.setText("Please select an employee ID");
        }

    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {
        try {
            txtName.setText(attendanceBO.getEmployeeName(cmbEmpId.getSelectionModel().getSelectedItem()));
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
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
        try {
            cmbEmpId.getItems().addAll(attendanceBO.getAllEmployeeIds ());

            if ( id != null ) {
                cmbEmpId.setValue(id);
                txtName.setText(attendanceBO.getEmployeeName(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

}
