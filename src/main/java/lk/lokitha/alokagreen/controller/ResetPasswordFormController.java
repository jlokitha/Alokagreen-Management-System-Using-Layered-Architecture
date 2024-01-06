package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SignInBO;
import lk.lokitha.alokagreen.bo.custom.impl.SignInBOImpl;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;

import java.io.IOException;
import java.sql.SQLException;

public class ResetPasswordFormController {

    @FXML
    public JFXButton btnConfirm;

    @FXML
    public JFXButton btnCancel;

    @FXML
    private JFXTextField txtNewPassword;

    @FXML
    private JFXPasswordField txtComfirmNewPassword;

    @FXML
    private Label lblNewPass;

    @FXML
    private Label lblConPass;

    private final SignInBO signInBO = (SignInBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SIGN_IN );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        try {
            Navigation.switchLoginPage("SignInForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) {
        if ( validatePassword() ) {
            if (txtNewPassword.getText().equals(txtComfirmNewPassword.getText())) {
                String userName = ForgotPasswordFormController.userName;

                try {
                    if (signInBO.updateUserPassword (userName, txtComfirmNewPassword.getText())) {
                        try {
                            Navigation.switchNavigation("GlobalForm.fxml", event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch ( SQLException e ) {
                    e.printStackTrace ();
                }
            } else {
                lblConPass.setText("Password Does Not Match !");
            }
        }
    }

    @FXML
    void txtConfirmOnMouseClicked(MouseEvent event) {
        lblConPass.setText(null);
    }

    @FXML
    void txtConfirmOnAction(ActionEvent event) {
        lblConPass.setText(null);
        String password = txtComfirmNewPassword.getText();

        if (Regex.password(password)) {
            lblConPass.setText("Password should have at least 6 and less than 20 characters");
        } else {
            btnResetPasswordOnAction(event);
        }
    }

    @FXML
    void txtNewOnMouseClicked(MouseEvent event) {
        lblNewPass.setText(null);
    }

    @FXML
    void txtNewOnAction(ActionEvent event) {
        lblNewPass.setText(null);
        String password = txtNewPassword.getText();

        if (Regex.password(password)) {
            lblNewPass.setText("Password should have at least 6 and less than 20 characters");
        } else {
            txtComfirmNewPassword.requestFocus();
        }
    }

    public boolean validatePassword() {
        String password = txtNewPassword.getText();

        if (Regex.password(password)) {
            lblNewPass.setText("Password should have at least 6 and less than 20 characters");
            return false;
        }

        return true;
    }

    @FXML
    public void btnConfirmOnMouseEntered(MouseEvent mouseEvent) {
        btnConfirm.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnConfirmOnMouseExited(MouseEvent mouseEvent) {
        btnConfirm.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnCancelOnMouseEntered(MouseEvent mouseEvent) {
        btnCancel.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    public void btnCancelOnMouseExited(MouseEvent mouseEvent) {
        btnCancel.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }
}
