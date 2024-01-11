package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.CustomerBO;
import lk.lokitha.alokagreen.bo.custom.impl.CustomerBOImpl;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.Style;

import java.sql.SQLException;

public class CustomerAddFormController {

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtCustMobile;

    @FXML
    private JFXTextField txtCustEmail;

    @FXML
    private JFXTextField txtCustAddress;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblCustMobile;

    @FXML
    private Label lblCustEmail;

    @FXML
    private Label lblCustAddress;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAdd;

    private final CustomerBO customerBO = (CustomerBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.CUSTOMER );

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (validateCustomer()) {
            try {
                boolean isSaved = customerBO.saveCustomer( new CustomerDto(
                        null,
                        txtCustName.getText(),
                        txtCustMobile.getText(),
                        txtCustEmail.getText(),
                        txtCustAddress.getText(),
                        null,
                        null
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    CustomerManageFormController.controller.getAllId();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void txtcustAddressOnMouseClicked(MouseEvent event) {
        lblCustAddress.setText(null);
    }

    @FXML
    void txtCustAddressOnAction(ActionEvent event) {
        lblCustAddress.setText(null);

        String address = txtCustAddress.getText();

        if (Regex.address(address)) {
            lblCustAddress.setText("Please enter valid address");
        } else {
            btnAddOnAction(event);
        }
    }

    @FXML
    void txtcustEmailOnMouseClicked(MouseEvent event) {
        lblCustEmail.setText(null);
    }

    @FXML
    void txtCustEmailOnAction(ActionEvent event) {
        lblCustEmail.setText(null);

        String email = txtCustEmail.getText();

        if (Regex.email(email)) {
            lblCustEmail.setText("Please enter valid email");
        } else {
            txtCustAddress.requestFocus();
        }
    }

    @FXML
    void txtcustMobileOnMouseClicked(MouseEvent event) {
        lblCustMobile.setText(null);
    }

    @FXML
    void txtCustMobileOnAction(ActionEvent event) {
        lblCustMobile.setText(null);

        String mobile = txtCustMobile.getText();

        if (Regex.mobile(mobile)) {
            lblCustMobile.setText("Please enter valid mobile number");
        } else {
            txtCustEmail.requestFocus();
        }
    }

    @FXML
    void txtcustNameMouseClicked(MouseEvent event) {
        lblCustName.setText(null);
    }

    @FXML
    void txtCustNameOnAction(ActionEvent event) {
        lblCustName.setText(null);

        String name = txtCustName.getText();

        if (Regex.fullName(name)) {
            lblCustName.setText("Name should contain at least 3 letters and only letters");
        } else {
            txtCustMobile.requestFocus();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    public boolean validateCustomer() {
        String name = txtCustName.getText();

        if (Regex.fullName(name)) {
            lblCustName.setText("Name should contain at least 3 letters and only letters");
            return false;
        }

        String mobile = txtCustMobile.getText();

        if (Regex.mobile(mobile)) {
            lblCustMobile.setText("Please enter valid mobile number");
            return false;
        }

        String email = txtCustEmail.getText();

        if (Regex.email(email)) {
            lblCustEmail.setText("Please enter valid email");
            return false;
        }

        String address = txtCustAddress.getText();

        if (Regex.address(address)) {
            lblCustAddress.setText("Please enter valid address");
            return false;
        }

        return true;
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnAdd );
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnAdd );
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

}
