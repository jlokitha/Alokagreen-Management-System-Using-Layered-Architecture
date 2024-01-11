package lk.ijse.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.EmployeeBO;
import lk.ijse.alokagreen.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.alokagreen.dto.EmployeeDto;
import lk.ijse.alokagreen.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeManageRowFormController {

    @FXML
    private HBox hbox;

    @FXML
    private ImageView imgCheck;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblEmail;

    @FXML
    private ImageView imgView;

    @FXML
    private ImageView imgUpdate;

    @FXML
    private ImageView imgDelete;

    private boolean isSelected;

    public EmployeeManageRowFormController() {
        isSelected = false;
    }

    private final EmployeeBO employeeBO = (EmployeeBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.EMPLOYEE );

    @FXML
    void imageCheckBoxOnMouseClicked(MouseEvent event) {
        if ( !isSelected ) {
            isSelected = true;
            rawSelected();
            EmployeeManageFormController.ids.add(lblId.getText());
        } else {
            isSelected = false;
            rawUnSelected();
            EmployeeManageFormController.ids.remove(lblId.getText());
        }
    }

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Employee");
        alert.setContentText("Are you sure you want to delete this employee?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                employeeBO.deleteEmployee( lblId.getText() );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            EmployeeManageFormController.controller.getAllId();
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
            EmployeeUpdateFormController.id = lblId.getText();
            Navigation.popupPane("EmployeeUpdateForm.fxml");
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
            EmployeeViewFormController.id = lblId.getText();
            Navigation.popupPane("EmployeeViewForm.fxml");
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

    public void rawSelected() {
        hbox.setStyle("-fx-background-color: #CEFFE1;" +
                "-fx-border-color: #139547;" +
                "-fx-border-width: 0 0 1 0;");
        imgCheck.setImage(new Image("assets/icon/check_box_select.png"));
    }

    public void rawUnSelected() {
        hbox.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #A8A9AB;" +
                "-fx-border-width: 0 0 1 0;");
        imgCheck.setImage(new Image("assets/icon/check_box_default.png"));
    }


    public void setData(String id) {

        EmployeeDto data = null;
        try {
            data = employeeBO.getEmployeeData( id );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if ( data != null ) {
            lblId.setText(data.getEmployee_Id());
            lblName.setText(data.getFirst_Name() + " " + data.getLast_Name());
            lblRole.setText(data.getRole());
            lblEmail.setText(data.getEmail());
            lblMobile.setText(data.getMobile());
        }
    }

}
