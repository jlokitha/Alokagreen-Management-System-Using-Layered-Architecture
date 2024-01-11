package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.ProfileBO;
import lk.lokitha.alokagreen.bo.custom.impl.ProfileBOImpl;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.Style;

import java.sql.SQLException;

public class ChangeCredentialFormController {

    @FXML
    public JFXButton btnCancel;
    
    @FXML
    public JFXButton btnUpdate;
    
    @FXML
    private JFXTextField txtNEWPass;

    @FXML
    private JFXTextField txtCurrentPass;

    @FXML
    private JFXPasswordField txtConNewPass;

    @FXML
    private Label lblCurentPass;

    @FXML
    private Label lblNewPass;

    @FXML
    private Label lblConPass;

    private final ProfileBO profileBO = (ProfileBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PROFILE );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if ( validatePassword() ) {
            try {
                boolean isCorrect = profileBO.checkPassword( GlobalFormController.user, txtCurrentPass.getText( ) );

                if (isCorrect) {
                    boolean equals = txtNEWPass.getText().equals(txtConNewPass.getText());

                    if (equals) {
                        boolean isUpdated = profileBO.updatePassword(GlobalFormController.user, txtConNewPass.getText());

                        if (isUpdated) {
                            String employeeId = profileBO.getEmployeeId(GlobalFormController.user);
                            String email = profileBO.getEmployeeEmail(employeeId);

                            profileBO.sendPasswordChangeEmail( email );

                            Navigation.closePane();
                        }
                    } else {
                        lblConPass.setText("PassWord does not match");
                    }
                } else {
                    lblCurentPass.setText("Wrong PassWord");
                }

            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void txtConPassOnMouseClicked(MouseEvent event) {
        lblConPass.setText(null);
    }

    @FXML
    void txtConPassOnAction(ActionEvent event) {
        lblConPass.setText(null);
        String password = txtConNewPass.getText();

        if ( Regex.password(password) ) {
            lblConPass.setText("Password should contain at least 6 characters");
        } else if (!txtNEWPass.getText().equals(txtConNewPass.getText())) {
            lblConPass.setText("PassWord does not match");
        }else {
            btnUpdateOnAction(event);
        }
    }

    @FXML
    void txtCurentPassOnMouseClicked(MouseEvent event) {
        lblCurentPass.setText(null);
    }

    @FXML
    void txtCurrentPassOnAction(ActionEvent event) {
        lblCurentPass.setText(null);
        String curPassword = txtCurrentPass.getText();

        if (Regex.password(curPassword)) {
            lblCurentPass.setText("Please enter your current password");
        } else {
            txtNEWPass.requestFocus();
        }
    }

    @FXML
    void txtNewPassOnMouseClicked(MouseEvent event) {
        lblNewPass.setText(null);
    }

    @FXML
    void txtNewPassOnAction(ActionEvent event) {
        lblNewPass.setText(null);
        String newPassword = txtNEWPass.getText();

        if ( Regex.password(newPassword) ) {
            lblNewPass.setText("Password should contain at least 6 characters");
        } else {
            txtConNewPass.requestFocus();
        }
    }

    public boolean validatePassword() {
        String curPassword = txtCurrentPass.getText();

        if (Regex.password(curPassword)) {
            lblCurentPass.setText("Please enter your current password");
            return false;
        }

        String newPassword = txtNEWPass.getText();

        if ( Regex.password(newPassword) ) {
            lblNewPass.setText("Password should contain at least 6 characters");
            return false;
        }

        String password = txtConNewPass.getText();

        if ( Regex.password(password) ) {
            lblConPass.setText("Password should contain at least 6 characters");
            return false;
        }

        return true;
    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnUpdate );
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnUpdate );
    }

    @FXML
    public void btnCancelOnMoseEntered(MouseEvent mouseEvent) {
        Style.btnOnMoseEnteredWithBorder ( btnCancel );
    }

    @FXML
    public void btnCancelOnMoseExited(MouseEvent mouseEvent) {
        Style.btnOnMoseExitedWithBorder ( btnCancel );
    }
}
