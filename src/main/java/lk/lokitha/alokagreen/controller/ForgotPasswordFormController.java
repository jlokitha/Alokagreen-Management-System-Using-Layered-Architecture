package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.model.UserModel;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.OTPGenerator;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;

public class ForgotPasswordFormController {
    @FXML
    public JFXButton btnReqOtp;

    @FXML
    public JFXButton btnCancel;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private Label lblUserName;

    private String subject;
    private String otp;
    public static String userName;

    public ForgotPasswordFormController() {
        subject = "Password Reset Verification Code";
        otp = OTPGenerator.generateOTP();
    }

    @FXML
    void btnRequesOtpOnAction(ActionEvent event) {
        if ( validateUserName() ) {
            if ( UserModel.getEmployeeId(txtUsername.getText()) != null ) {
                SendEmail sendEmail = new SendEmail();
                String employeeId = UserModel.getEmployeeId(txtUsername.getText());
                String email = EmployeeModel.getEmailOfId(employeeId);

                try {
                    SignInVerifyOtpFormController.otp = otp;
                    userName = txtUsername.getText();
                    Navigation.switchLoginPage("SignInVerifyOtpForm.fxml");

                    new Thread(()->{
                        try {
                            sendEmail.sendEmail(email, subject, "ForgotPasswordEmail.html", otp);
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                    }).start();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                lblUserName.setText("User does not exists");
            }
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            Navigation.switchLoginPage("SignInForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtUserNameOnMouseClicked(MouseEvent event) {
        lblUserName.setText(null);
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        String userName = txtUsername.getText();

        if (Regex.userName(userName)) {
            lblUserName.setText("Please enter valid username");
        } else {
            lblUserName.setText(null);
            btnRequesOtpOnAction(event);
        }
    }

    public boolean validateUserName() {
        String userName = txtUsername.getText();

        if (Regex.userName(userName)) {
            lblUserName.setText("Please enter valid username");
            return false;
        }

        return true;
    }

    @FXML
    public void btnReqOtpOnMouseEntered(MouseEvent mouseEvent) {
        btnReqOtp.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnReqOtpOnMouseExited(MouseEvent mouseEvent) {
        btnReqOtp.setStyle(
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
