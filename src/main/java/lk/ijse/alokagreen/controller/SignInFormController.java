package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SignInBO;
import lk.ijse.alokagreen.bo.custom.impl.SignInBOImpl;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

public class SignInFormController {
    @FXML
    public JFXButton btnLogIn;

    @FXML
    public Pane paneShutDown;

    @FXML
    public JFXButton btnSignUp;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblPassword;

    @FXML
    private ImageView imgShutDown;

    public static Stage stage;

    private final SignInBO signInBO = (SignInBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SIGN_IN );

    @FXML
    void btnLogInOnAction(ActionEvent event) {
        if ( validateSignIn() ) {
            try {
                if (signInBO.checkPassword(txtUsername.getText(), txtPassword.getText())) {
                    GlobalFormController.user = txtUsername.getText();
                    Navigation.switchNavigation("GlobalForm.fxml", event);
                } else {
                    lblPassword.setText("Invalid username or password");
                }
            } catch ( IOException | SQLException e) {
                e.printStackTrace ();
            }
        }
    }

    @FXML
    void imgShutDownOnMouseClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void txtPasswordOnMouseClicked(MouseEvent event) {
        lblPassword.setText(null);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        String password = txtPassword.getText();

        if ( Regex.password(password) ) {
            lblPassword.setText("Please enter a password");
        } else {
            btnLogInOnAction(event);
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
            lblUserName.setText("Invalid Username");
        } else {
            txtPassword.requestFocus();
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        try {
            Navigation.switchLoginPage("EmployeeVerificationForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateSignIn() {
        String userName = txtUsername.getText();

        if (Regex.userName(userName)) {
            lblUserName.setText("Invalid Username");
            return false;
        }

        String password = txtPassword.getText();

        if ( Regex.password(password) ) {
            lblPassword.setText("Please enter a password");
            return false;
        }

        return true;
    }

    @FXML
    void linkForgotPasswordOnAction(ActionEvent event) {
        try {
            Navigation.switchLoginPage("ForgotPasswordForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnLogInOnMouseEnter(MouseEvent mouseEvent) {
        Style.btnOnMouseEntered ( btnLogIn );
    }
    @FXML
    public void btnLogInOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExited ( btnLogIn );
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
    public void btnSignUpOnMouseEntered(MouseEvent mouseEvent) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.WHITE);
        btnSignUp.setEffect(dropShadow);
    }

    @FXML
    public void btnSignUpOnMouseExited(MouseEvent mouseEvent) {
        btnSignUp.setEffect(null);
    }
}
