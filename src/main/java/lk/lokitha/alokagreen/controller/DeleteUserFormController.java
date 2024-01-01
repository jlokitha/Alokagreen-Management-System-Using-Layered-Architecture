package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.ProfileBO;
import lk.lokitha.alokagreen.bo.custom.impl.ProfileBOImpl;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteUserFormController implements Initializable {

    @FXML
    private Label lblUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private Label lblOtp;

    private final ProfileBO profileBO = (ProfileBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PROFILE );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if ( validatePassword() ) {
            String password = txtPassword.getText();

            try {
                if ( profileBO.checkPassword(GlobalFormController.user, password) ) {
                    String employeeId = profileBO.getEmployeeId(lblUserName.getText());
                    String email = profileBO.getEmployeeEmail(employeeId);
                    boolean deleted = profileBO.deleteUser(lblUserName.getText());

                    if ( deleted ) {
                        profileBO.sendAccountDeletionEmail( email );

                        SignInFormController.stage.close();
                        Navigation.switchNavigation("GlobalLoginForm.fxml", event);
                    }
                } else {
                    lblOtp.setText("Please enter correct password");
                }
            } catch ( SQLException | IOException e ) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void txtPasswordOnMouseClicked(MouseEvent event) {
        lblOtp.setText(null);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        lblOtp.setText(null);

        String password = txtPassword.getText();

        if (Regex.password(password)) {
            lblOtp.setText("Password should contain at least 6 characters");
        } else {
            btnDeleteOnAction(event);
        }
    }

    public boolean validatePassword() {
        String password = txtPassword.getText();

        if (Regex.password(password)) {
            lblOtp.setText("Password should contain at least 6 characters");
            return false;
        }

        return true;
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #EEEEEE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
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
        lblUserName.setText(GlobalFormController.user);
    }

    public void btnDeleteOnMouseEntered(MouseEvent mouseEvent) {
        btnDelete.setStyle(
                "-fx-background-color: #ff6a6a;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    public void btnDeleteOnMouseExited(MouseEvent mouseEvent) {
                btnDelete.setStyle(
                "-fx-background-color:  #f44930;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }
}
