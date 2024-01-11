package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.AttendanceBO;
import lk.lokitha.alokagreen.bo.custom.impl.AttendanceBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
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

    private final AttendanceBO attendanceBO = (AttendanceBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.ATTENDANCE );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if (cmbEmpId.getSelectionModel().getSelectedItem() != null) {
            String eId = cmbEmpId.getSelectionModel().getSelectedItem();

            try {
                boolean isSaved = attendanceBO.updateAttendance( new EmployeeAttendanceDto(
                        id,
                        eId,
                        null,
                        null
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    EmployeeAttendanceManageFormController.controller.getAllId();
                }

            } catch (SQLException e) {
                throw new RuntimeException( e );
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select an employee ID").show();
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

    private void setData() {
        try {
            EmployeeAttendanceDto data = attendanceBO.getAttendanceData( id );
            String name = attendanceBO.getEmployeeName(data.getEmployee_Id());

            cmbEmpId.setValue(data.getEmployee_Id());
            txtName.setText(name);
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }

    }


    private void setCmb() {
        try {
            cmbEmpId.getItems().addAll(attendanceBO.getAllEmployeeIds());
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
        cmbEmpId.setStyle("-fx-font-size: 16;");
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
        setCmb();
        setData();
    }
}
