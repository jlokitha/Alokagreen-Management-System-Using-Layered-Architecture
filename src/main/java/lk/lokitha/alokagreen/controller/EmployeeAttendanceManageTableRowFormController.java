package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.model.EmployeeAttendanceModel;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;

public class EmployeeAttendanceManageTableRowFormController {

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private ImageView imgUpdate;

    @FXML
    private ImageView imgDelete;

    private String id;

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Employee Attendance");
        alert.setContentText("Are you sure you want to delete this attendance?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            EmployeeAttendanceModel.deleteEmployeeAttendance(id);
            EmployeeAttendanceManageFormController.controller.getAllId();
        }
    }

    @FXML
    void imgDeleteOnMouseEntered(MouseEvent event) {
        imgDelete.setImage(new Image("/assets/icon/delete_red_row.png"));
    }

    @FXML
    void imgDeleteOnMouseExited(MouseEvent event) {
        imgDelete.setImage(new Image("/assets/icon/delete_default.png"));
    }

    @FXML
    void imgUpdateOnMouseClicked(MouseEvent event) {
        try {
            EmployeeAttendanceUpdateFormController.id = id;
            Navigation.popupPane("EmployeeAttendanceUpdateForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgUpdateOnMouseEntered(MouseEvent event) {
        imgUpdate.setImage(new Image("/assets/icon/update_green.png"));
    }

    @FXML
    void imgUpdateOnMouseExited(MouseEvent event) {
        imgUpdate.setImage(new Image("/assets/icon/update.png"));
    }

    public void setData(String id) {

        EmployeeAttendanceDto data = EmployeeAttendanceModel.getData(id);
        String name = EmployeeModel.getNameOfId(data.getEmployee_Id());

        this.id = data.getAttendance_Id();
        lblEmpId.setText(data.getEmployee_Id());
        lblName.setText(name);
        lblDate.setText(data.getDate());
        lblTime.setText(data.getTime());

    }

}
