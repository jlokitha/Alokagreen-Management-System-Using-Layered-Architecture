package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.UserDto;
import lk.lokitha.alokagreen.model.UserModel;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;

import java.io.IOException;

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
            if ( UserModel.getEmployeeId(txtUsername.getText()) == null ) {
                UserDto userDto = new UserDto();

                userDto.setUsername(txtUsername.getText());
                userDto.setPassword(txtPassword.getText());
                userDto.setEmpId(EmployeeVerificationFormController.empID);

                boolean saved = UserModel.saveUser(userDto);

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
            } {
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
        btnSignUp.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnSignUpOnMouseExited(MouseEvent mouseEvent) {
        btnSignUp.setStyle(
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
