package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SupplierBO;
import lk.ijse.alokagreen.bo.custom.impl.SupplierBOImpl;
import lk.ijse.alokagreen.dto.SupplierDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierUpdateFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnUpdate;

    @FXML
    private JFXTextField txtSupMobile;

    @FXML
    private JFXTextField txtSupEmail;

    @FXML
    private JFXTextField txtSupLocation;

    @FXML
    private JFXTextField txtSupName;

    @FXML
    private Label lblname;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    public static String id;

    private final SupplierBO supplierBO = (SupplierBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.SUPPLIER );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if ( validateSupplier() ) {
            try {
                boolean isSaved = supplierBO.updateSupplier( new SupplierDto(
                        id,
                        txtSupName.getText(),
                        txtSupMobile.getText(),
                        txtSupEmail.getText(),
                        txtSupLocation.getText(),
                        null,
                        null
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    SupplierManageFormController.controller.getAllId();
                }

            } catch (SQLException e) {
                throw new RuntimeException( e );
            }
        }
    }

    private void setDate(SupplierDto supplierDto) {
        txtSupName.setText(supplierDto.getCompany_Name());
        txtSupMobile.setText(supplierDto.getCompany_Mobile());
        txtSupEmail.setText(supplierDto.getCompany_Email());
        txtSupLocation.setText(supplierDto.getCompany_Location());
    }

    @FXML
    void txtAddressOnMouseClicked(MouseEvent event) {
        lblAddress.setText(null);
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        lblAddress.setText(null);

        String address = txtSupLocation.getText();

        if (Regex.address(address)) {
            lblAddress.setText("Please enter valid address");
        } else {
            btnUpdateOnAction(event);
        }
    }

    @FXML
    void txtEmailOnMouseClicked(MouseEvent event) {
        lblEmail.setText(null);
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        lblEmail.setText(null);

        String email = txtSupEmail.getText();

        if (Regex.email(email)) {
            lblEmail.setText("Please enter valid email");
        } else {
            txtSupLocation.requestFocus();
        }
    }

    @FXML
    void txtMobileOnMouseClicked(MouseEvent event) {
        lblMobile.setText(null);
    }

    @FXML
    void txtMobileOnAction(ActionEvent event) {
        lblMobile.setText(null);

        String mobile = txtSupMobile.getText();

        if (Regex.mobile(mobile)) {
            lblMobile.setText("Please enter valid mobile number");
        } else {
            txtSupEmail.requestFocus();
        }
    }

    @FXML
    void txtNameOnMouseClicked(MouseEvent event) {
        lblname.setText(null);
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        lblname.setText(null);

        String name = txtSupName.getText();

        if (Regex.fullName(name)) {
            lblname.setText("Name should contain at least 3 letters and only letters");
        } else {
            txtSupMobile.requestFocus();
        }
    }

    public boolean validateSupplier() {
        String name = txtSupName.getText();

        if (Regex.fullName(name)) {
            lblname.setText("Name should contain at least 3 letters and only letters");
            return false;
        }

        String mobile = txtSupMobile.getText();

        if (Regex.mobile(mobile)) {
            lblMobile.setText("Please enter valid mobile number");
            return false;
        }

        String email = txtSupEmail.getText();

        if (Regex.email(email)) {
            lblEmail.setText("Please enter valid email");
            return false;
        }

        String address = txtSupLocation.getText();

        if (Regex.address(address)) {
            lblAddress.setText("Please enter valid address");
            return false;
        }

        return true;
    }

    @FXML
    public void btnCancelOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    public void btnCancelOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    @FXML
    public void btnUpdateOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEntered ( btnUpdate );
    }

    @FXML
    public void btnUpdateOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExited ( btnUpdate );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SupplierDto sDto = supplierBO.getSupplierData( id );

            setDate(sDto);
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }
}
