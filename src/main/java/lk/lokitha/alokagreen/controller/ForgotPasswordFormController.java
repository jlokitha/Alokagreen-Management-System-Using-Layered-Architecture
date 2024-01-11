package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SignInBO;
import lk.lokitha.alokagreen.bo.custom.impl.SignInBOImpl;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.OTPGenerator;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.Style;

import java.io.IOException;
import java.sql.SQLException;

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

    private final SignInBO signInBO = (SignInBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SIGN_IN );

    public ForgotPasswordFormController() {
        subject = "Password Reset Verification Code";
        otp = OTPGenerator.generateOTP();
    }

    @FXML
    void btnRequesOtpOnAction(ActionEvent event) {
        if ( validateUserName() ) {
            try {
                if ( signInBO.getEmployeeId(txtUsername.getText()) != null ) {
                    String employeeId = signInBO.getEmployeeId(txtUsername.getText());
                    String email = signInBO.getEmployeeEmail(employeeId);

                    try {
                        SignInVerifyOtpFormController.otp = otp;
                        userName = txtUsername.getText();
                        Navigation.switchLoginPage("SignInVerifyOtpForm.fxml");

                        signInBO.sendEmail ( email, subject, "ForgotPasswordEmail.html", otp );

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    lblUserName.setText("User does not exists");
                }
            } catch ( SQLException e ) {
                e.printStackTrace ();
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
        Style.btnOnMouseEntered ( btnReqOtp );
    }

    @FXML
    public void btnReqOtpOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExited ( btnReqOtp );
    }

    @FXML
    public void btnCancelOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    public void btnCancelOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }
}
