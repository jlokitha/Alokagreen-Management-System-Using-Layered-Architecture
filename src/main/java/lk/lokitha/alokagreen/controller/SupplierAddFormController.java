package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.SupplierDto;
import lk.lokitha.alokagreen.model.SupplierModel;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.Regex;

public class SupplierAddFormController {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnAdd;

    @FXML
    private JFXTextField txtSupName;

    @FXML
    private JFXTextField txtSupMobile;

    @FXML
    private JFXTextField txtSupEmail;

    @FXML
    private JFXTextField txtSupLocation;

    @FXML
    private Label lblname;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if (validateSupplier()) {
            SupplierDto supplierDto = new SupplierDto();

            supplierDto.setSupplier_Id(NewId.newSupplierId());
            supplierDto.setCompany_Name(txtSupName.getText());
            supplierDto.setCompany_Email(txtSupEmail.getText());
            supplierDto.setCompany_Mobile(txtSupMobile.getText());
            supplierDto.setCompany_Location(txtSupLocation.getText());
            supplierDto.setTime(DateTime.timeNow());
            supplierDto.setDate(DateTime.dateNow());

            boolean isSaved = SupplierModel.saveSupplier(supplierDto);

            if (isSaved) {
                Navigation.closePane();
                SupplierManageFormController.controller.getAllId();
            }
        }
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
            btnAddOnAction(event);
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
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
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

    @FXML
    public void btnAddOnMouseEntered(MouseEvent mouseEvent) {
        btnAdd.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnAddOnMouseExited(MouseEvent mouseEvent) {
        btnAdd.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }
}
