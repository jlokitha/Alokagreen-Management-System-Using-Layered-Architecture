package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.ProfileBO;
import lk.lokitha.alokagreen.bo.custom.impl.ProfileBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeDto;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileFormController implements Initializable {

    @FXML
    public JFXButton btnDelete;

    @FXML
    public JFXButton btnReset;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEmpId;

    @FXML
    private ImageView imgClose;

    private final ProfileBO profileBO = (ProfileBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PROFILE );

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Navigation.closePane();
            Navigation.popupPane("DeleteUserForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        try {
            Navigation.closePane();
            Navigation.popupPane("ChangeCredentialForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgCloseOnMouseClicked(MouseEvent event) {
        Navigation.closePane();
    }

    @FXML
    void imgCloseOnMouseEntered(MouseEvent event) {
        imgClose.setImage(new Image("/assets/icon/close_red.png"));
    }

    @FXML
    void imgCloseOnMouseExited(MouseEvent event) {
        imgClose.setImage(new Image("/assets/icon/close.png"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String employeeId = profileBO.getEmployeeId( GlobalFormController.user );
            EmployeeDto detail = profileBO.getEmployeeDetails(employeeId);

            lblUserName.setText(GlobalFormController.user);
            lblEmpId.setText(detail.getEmployee_Id());
            lblName.setText(detail.getFirst_Name() + " " + detail.getLast_Name());
            lblEmail.setText(detail.getEmail());

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnDeleteOnMouseEntered(MouseEvent mouseEvent) {
        btnDelete.setStyle(
                "-fx-background-color: #FFC4C4;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #f44949;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill: #f44949;");
    }

    @FXML
    public void btnDeleteOnMouseExited(MouseEvent mouseEvent) {
        btnDelete.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #f44949;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill: #f44949;");
    }

    @FXML
    public void btnResetOnMouseEntered(MouseEvent mouseEvent) {
        btnReset.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    public void btnResetOnMouseExited(MouseEvent mouseEvent) {
        btnReset.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }
}
