package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SignInBO;
import lk.ijse.alokagreen.bo.custom.impl.SignInBOImpl;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

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
        Style.btnOnMouseExited ( btnConfirm );
    }

    @FXML
    public void btnConfirmOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExited ( btnConfirm );
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
