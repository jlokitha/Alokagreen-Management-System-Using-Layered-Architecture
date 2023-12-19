package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.model.UserModel;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteUserFormController implements Initializable {

    @FXML
    private Label lblUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private Label lblOtp;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if ( validatePassword() ) {
            String password = txtPassword.getText();

            if ( UserModel.checkPassword(GlobalFormController.user, password) ) {
                String employeeId = UserModel.getEmployeeId(lblUserName.getText());
                String email = EmployeeModel.getEmailOfId(employeeId);
                boolean deleted = UserModel.deleteUser(lblUserName.getText());

                if ( deleted ) {
                    SendEmail sendEmail = new SendEmail();
                    String subject = "Account Deletion Confirmation";
                    String htmlPath = "DeleteUserEmail.html";

                    new Thread(() -> {
                        try {
                            sendEmail.sendEmail(email, subject, htmlPath);

                        } catch (MessagingException e) {}
                    }).start();

                    SignInFormController.stage.close();
                    try {
                        Navigation.switchNavigation("GlobalLoginForm.fxml", event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                lblOtp.setText("Please enter correct password");
            }
        }
    }

    @FXML
    void txtPasswordOnMouseClicked(MouseEvent event) {
        lblOtp.setText(null);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        lblOtp.setText(null);

        String password = txtPassword.getText();

        if (Regex.password(password)) {
            lblOtp.setText("Password should contain at least 6 characters");
        } else {
            btnDeleteOnAction(event);
        }
    }

    public boolean validatePassword() {
        String password = txtPassword.getText();

        if (Regex.password(password)) {
            lblOtp.setText("Password should contain at least 6 characters");
            return false;
        }

        return true;
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #EEEEEE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUserName.setText(GlobalFormController.user);
    }

    public void btnDeleteOnMouseEntered(MouseEvent mouseEvent) {
        btnDelete.setStyle(
                "-fx-background-color: #ff6a6a;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    public void btnDeleteOnMouseExited(MouseEvent mouseEvent) {
                btnDelete.setStyle(
                "-fx-background-color:  #f44930;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }
}
