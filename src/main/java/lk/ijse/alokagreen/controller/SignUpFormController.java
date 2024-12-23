package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SignUpBO;
import lk.ijse.alokagreen.bo.custom.impl.SignUpBOImpl;
import lk.ijse.alokagreen.dto.UserDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpFormController {

    @FXML
    public JFXButton btnSignUp;

    @FXML
    public JFXButton btnCancel;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblPassword;

    private final SignUpBO signUpBO = (SignUpBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SIGN_UP );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        try {
            Navigation.switchLoginPage("SignInForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        if ( validateSignUp() ) {
            try {
                if ( signUpBO.getEmployeeId(txtUsername.getText()) == null ) {

                    boolean saved = signUpBO.saveUser(new UserDto (
                            EmployeeVerificationFormController.empID,
                            txtUsername.getText (),
                            txtPassword.getText ()
                    ));

                    if (saved) {
                        try {
                            GlobalFormController.user = txtUsername.getText();
                            Navigation.switchNavigation("GlobalForm.fxml", event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong !").show();
                    }
                }
            } catch ( SQLException e ) {
                e.printStackTrace ();
            }
            {
                lblUserName.setText("Username is already exists");
            }
        }
    }

    @FXML
    void txtPasswordOnMouseClicked(MouseEvent event) {
        lblPassword.setText(null);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        lblPassword.setText(null);

        String password = txtPassword.getText();

        if ( Regex.password(password) ) {
            lblPassword.setText("Password should contain at least 6 characters");
        } else {
            btnSignUpOnAction(event);
        }
    }

    @FXML
    void txtUserNameOnMouseClicked(MouseEvent event) {
        lblUserName.setText(null);
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        lblUserName.setText(null);

        String userName = txtUsername.getText();

        if (Regex.userName(userName)) {
            lblUserName.setText("Please enter a username");
        } else {
            txtPassword.requestFocus();
        }
    }

    public boolean validateSignUp() {
        String userName = txtUsername.getText();

        if (Regex.userName(userName)) {
            lblUserName.setText("Please enter a username");
            return false;
        }

        String password = txtPassword.getText();

        if ( Regex.password(password) ) {
            lblPassword.setText("Password should contain at least 6 characters");
            return false;
        }

        return true;
    }

    @FXML
    public void btnSignUpOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEntered ( btnSignUp );
    }

    @FXML
    public void btnSignUpOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExited ( btnSignUp );
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
