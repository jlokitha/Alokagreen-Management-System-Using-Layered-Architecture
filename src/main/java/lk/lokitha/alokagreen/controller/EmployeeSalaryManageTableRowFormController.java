package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.EmployeeSalaryDto;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.model.EmployeeSalaryModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;

public class EmployeeSalaryManageTableRowFormController {

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSalary;

    @FXML
    private Label lblDate;

    @FXML
    private ImageView imgView;

    @FXML
    private ImageView imgUpdate;

    @FXML
    private ImageView imgDelete;

    private String id;

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Salary");
        alert.setContentText("Are you sure you want to delete this payment?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            EmployeeSalaryModel.deleteSalary(id);
            EmployeeSalaryManageFormController.controller.getAllId();
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
            EmployeeSalaryUpdateFormController.id = id;
            Navigation.popupPane("EmployeeSalaryUpdateForm.fxml");
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

    @FXML
    void imgViewOnMouseClicked(MouseEvent event) {
        try {
            EmployeeSalaryViewFormController.id = id;
            Navigation.popupPane("EmployeeSalaryViewForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgViewOnMouseEntered(MouseEvent event) {
        imgView.setImage(new Image("/assets/icon/view_blue.png"));
    }

    @FXML
    void imgViewOnMouseExited(MouseEvent event) {
        imgView.setImage(new Image("/assets/icon/view_default.png"));
    }

    public void setData(String id) {

        EmployeeSalaryDto data = EmployeeSalaryModel.getData(id);
        String name = EmployeeModel.getNameOfId(data.getEmployee_Id());

        this.id = data.getSalary_Id();
        lblEmpId.setText(data.getEmployee_Id());
        lblName.setText(name);
        lblSalary.setText(String.valueOf(data.getTotal_Salary()));
        lblDate.setText(data.getDate());

    }

}
