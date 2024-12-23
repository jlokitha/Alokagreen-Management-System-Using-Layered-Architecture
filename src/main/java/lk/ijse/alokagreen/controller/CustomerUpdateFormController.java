package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.CustomerBO;
import lk.ijse.alokagreen.bo.custom.impl.CustomerBOImpl;
import lk.ijse.alokagreen.dto.CustomerDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerUpdateFormController implements Initializable {

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtCustMobile;

    @FXML
    private JFXTextField txtCustEmail;

    @FXML
    private JFXTextField txtCustAddress;

    @FXML
    private Label lblName;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnUpdate;

    public static String id;

    private final CustomerBO customerBO = (CustomerBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.CUSTOMER );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if ( validateCustomer() ) {
            try {
                boolean isSaved = customerBO.updateCustomer( new CustomerDto(
                        id,
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
        lblAddress.setText(null);
    }

    @FXML
    void txtCustAddressOnAction(ActionEvent event) {
        lblAddress.setText(null);

        String address = txtCustAddress.getText();

        if (Regex.address(address)) {
            lblAddress.setText("Please enter valid address");
        } else {
            btnUpdateOnAction(event);
        }
    }

    @FXML
    void txtcustEmailOnMouseClicked(MouseEvent event) {
        lblEmail.setText(null);
    }

    @FXML
    void txtCustEmailOnAction(ActionEvent event) {
        lblEmail.setText(null);

        String email = txtCustEmail.getText();

        if (Regex.email(email)) {
            lblEmail.setText("Please enter valid email");
        } else {
            txtCustAddress.requestFocus();
        }
    }

    @FXML
    void txtcustMobileOnMouseClicked(MouseEvent event) {
        lblMobile.setText(null);
    }

    @FXML
    void txtCustMobileOnAction(ActionEvent event) {
        lblMobile.setText(null);

        String mobile = txtCustMobile.getText();

        if (Regex.mobile(mobile)) {
            lblMobile.setText("Please enter valid mobile number");
        } else {
            txtCustEmail.requestFocus();
        }
    }

    @FXML
    void txtcustNameMouseClicked(MouseEvent event) {
        lblName.setText(null);
    }

    @FXML
    void txtCustNameOnAction(ActionEvent event) {
        lblName.setText(null);

        String name = txtCustName.getText();

        if (Regex.fullName(name)) {
            lblName.setText("Name should contain at least 3 letters and only letters");
        } else {
            txtCustMobile.requestFocus();
        }
    }

    public boolean validateCustomer() {
        String name = txtCustName.getText();

        if (Regex.fullName(name)) {
            lblName.setText("Name should contain at least 3 letters and only letters");
            return false;
        }

        String mobile = txtCustMobile.getText();

        if (Regex.mobile(mobile)) {
            lblMobile.setText("Please enter valid mobile number");
            return false;
        }

        String email = txtCustEmail.getText();

        if (Regex.email(email)) {
            lblEmail.setText("Please enter valid email");
            return false;
        }

        String address = txtCustAddress.getText();

        if (Regex.address(address)) {
            lblAddress.setText("Please enter valid address");
            return false;
        }

        return true;
    }

    private void setData(CustomerDto customerDto) {
        txtCustName.setText(customerDto.getName());
        txtCustMobile.setText(customerDto.getMobile());
        txtCustEmail.setText(customerDto.getEmail());
        txtCustAddress.setText(customerDto.getAddress());
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
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CustomerDto cDto = null;
        try {
            cDto = customerBO.getCustomerData( id );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setData(cDto);
    }
}
