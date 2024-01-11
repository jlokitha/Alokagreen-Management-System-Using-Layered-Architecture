package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

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
        Style.btnOnMouseEntered ( btnVerify );
    }

    @FXML
    public void btnVerifyOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExited ( btnVerify );
    }

    @FXML
    public void btnBackOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEnteredWithBorder ( btnBack );
    }

    @FXML
    public void btnBackOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExitedWithBorder ( btnBack );
    }
}
