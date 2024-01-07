package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SignUpBO;
import lk.lokitha.alokagreen.bo.custom.impl.SignUpBOImpl;
import lk.lokitha.alokagreen.util.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class EmployeeVerificationFormController {

    @FXML
    public JFXButton btnSignIn;

    @FXML
    public Pane paneShutDown;

    @FXML
    public ImageView imgQr;

    @FXML
    public JFXButton btnQr;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXButton btnRequest;

    private String subject;

    private String otp;

    public static String empID;

    private String email;

    @FXML
    private Label lblID;

    private final SignUpBO signUpBO = (SignUpBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SIGN_UP );

    public EmployeeVerificationFormController() {
        subject = "New Employee Registration";
        otp = OTPGenerator.generateOTP();
        email = "alokagreenofficial@gmail.com";
    }

    @FXML
    void btnRequestOtpOnAction(ActionEvent event) {
        if ( validateId() ) {
            try {
                if (signUpBO.getEmployeeName (txtEmployeeId.getText()) != null) {
                    if (signUpBO.getUserName(txtEmployeeId.getText()) == null) {
                        try {
                            SignUpVerifyOtpFormController.otp = otp;
                            empID = txtEmployeeId.getText();
                            String name = signUpBO.getEmployeeName (empID);
                            Navigation.switchLoginPage("SignUpVerifyOtpForm.fxml");

                            signUpBO.sendEmail ( email, subject, "SignUpEmail.html", otp, name, empID );

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        lblID.setText("Employee already has a account");
                    }
                } else {
                    lblID.setText("Employee does not exists");
                }
            } catch ( SQLException e ) {
                e.printStackTrace ();
            }
        }
    }

    @FXML
    void txtEmpIdOnMouseClicked(MouseEvent event) {
        lblID.setText(null);
    }

    public boolean validateId() {
        String id = txtEmployeeId.getText();

        if (Regex.employee(id)) {
            lblID.setText("Please enter valid employee id");
            return false;
        }

        return true;
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        try {
            Navigation.switchLoginPage("SignInForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRequestOnMouseEntered(MouseEvent event) {
        btnRequest.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnRequestOnMouseExited(MouseEvent event) {
        btnRequest.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void imgShutDownOnMouseClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void btnSignInOnMouseEntered(MouseEvent mouseEvent) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.WHITE);
        btnSignIn.setEffect(dropShadow);
    }

    @FXML
    public void btnSignInOnMouseExited(MouseEvent mouseEvent) {
        btnSignIn.setEffect(null);
    }

    @FXML
    public void imgShutDownOnMouseEntered(MouseEvent mouseEvent) {
        paneShutDown.setVisible(true);
    }

    @FXML
    public void imgShutDownOnMouseExited(MouseEvent mouseEvent) {
        paneShutDown.setVisible(false);
    }

    @FXML
    public void txtIdOnAction(ActionEvent event) {
        lblID.setText(null);
        String id = txtEmployeeId.getText();

        if (Regex.employee(id)) {
            lblID.setText("Please enter valid employee id");
        } else {
            btnRequestOtpOnAction(event);
        }
    }

    @FXML
    public void btnQrOnAction(ActionEvent event) {
        AtomicReference<String> empId = new AtomicReference<>();

        Thread workerThread = new Thread(() -> {
            String id = ReadQrCode.readQr();

            if (id != null) {
                empId.set(id);

            }
        });

        workerThread.start();

        try {
            workerThread.join();
        } catch (InterruptedException e) {}

        try {
            if (signUpBO.getEmployeeName (empId.get()) != null) {
                if (signUpBO.getUserName(empId.get()) == null) {
                    try {
                        SignUpVerifyOtpFormController.otp = otp;
                        empID = empId.get();
                        String name = signUpBO.getEmployeeName (empId.get());
                        Navigation.switchLoginPage("SignUpVerifyOtpForm.fxml");

                        signUpBO.sendEmail ( email, subject, "SignUpEmail.html", otp, name, empID );

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    lblID.setText("Employee already has a account");
                }
            } else {
                lblID.setText("Employee does not exist");
            }
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @FXML
    void btnQrOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip("QR");
        tooltip.setShowDelay(Duration.millis(150));
        btnQr.setTooltip(tooltip);

        imgQr.setImage(new Image("/assets/icon/qr_green.png"));
    }

    @FXML
    void btnQrOnMouseExited(MouseEvent event) {
        imgQr.setImage(new Image("/assets/icon/qr.png"));
    }
}
