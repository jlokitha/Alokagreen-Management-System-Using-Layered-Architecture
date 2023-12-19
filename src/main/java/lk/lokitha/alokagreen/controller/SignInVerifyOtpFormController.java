package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;

import java.io.IOException;

public class SignInVerifyOtpFormController {

    @FXML
    public JFXButton btnVerify;

    @FXML
    public JFXButton btnBack;

    @FXML
    private JFXTextField txtOtp;

    @FXML
    private Label lblOtp;

    public static String otp;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            Navigation.switchLoginPage("ForgotPasswordForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) {
        if ( validateOtp() ) {
            if (txtOtp.getText().equals(otp)) {
                try {
                    Navigation.switchLoginPage("ResetPasswordForm.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                lblOtp.setText("Invalid OTP !");
            }
        }
    }

    @FXML
    void txtOtpOnMouseClicked(MouseEvent event) {
        lblOtp.setText(null);
    }

    @FXML
    void txtOtpOnAction(ActionEvent event) {
        String otp = txtOtp.getText();

        if (Regex.otp(otp)) {
            lblOtp.setText("OTP should contain 6 numbers");
        } else {
            lblOtp.setText(null);
            btnVerifyOnAction(event);
        }
    }

    public boolean validateOtp() {
        String otp = txtOtp.getText();

        if (Regex.otp(otp)) {
            lblOtp.setText("OTP should contain 6 numbers");
            return false;
        }

        return true;
    }

    @FXML
    public void btnVerifyOnMouseEntered(MouseEvent mouseEvent) {
        btnVerify.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnVerifyOnMouseExited(MouseEvent mouseEvent) {
        btnVerify.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnBackOnMouseEntered(MouseEvent mouseEvent) {
        btnBack.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    public void btnBackOnMouseExited(MouseEvent mouseEvent) {
        btnBack.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }
}
