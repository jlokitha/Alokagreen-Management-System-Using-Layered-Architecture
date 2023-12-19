package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductAddFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnAdd;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private Label lblProductId;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblUnitPrice;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if ( validateProduct() ) {
            ProductDto productDto = new ProductDto();

            productDto.setProduct_Code(lblProductId.getText());
            productDto.setDescription(txtDescription.getText());
            productDto.setUnit_Price(Double.parseDouble(txtUnitPrice.getText()));

            boolean isSaved = ProductModel.saveProduct(productDto);

            if (isSaved) {
                Navigation.closePane();
                ProductListManageFormController.controller.getAllId();
            }
        }
    }

    @FXML
    void txtDescOnMouseClicked(MouseEvent event) {
        lblDesc.setText(null);
    }

    @FXML
    void txtDescOnAction(ActionEvent event) {
        lblDesc.setText(null);
        String desc = txtDescription.getText();

        if (Regex.description(desc)) {
            lblDesc.setText("First letter should be capital and should only contain letters");
        } else {
            txtUnitPrice.requestFocus();
        }
    }

    @FXML
    void txtUnitPriceOnMouseClicked(MouseEvent event) {
        lblUnitPrice.setText(null);
    }

    @FXML
    void txtUnitPriceOnAction(ActionEvent event) {
        lblUnitPrice.setText(null);
        String unitPrice = txtUnitPrice.getText();

        if (Regex.money(unitPrice)) {
            lblUnitPrice.setText("Unit price should be decimal or integer price");
        } else {
            btnAddOnAction(event);
        }
    }

    public boolean validateProduct() {
        String desc = txtDescription.getText();

        if (Regex.description(desc)) {
            lblDesc.setText("First letter should be capital and should only contain letters");
            return false;
        }

        String unitPrice = txtUnitPrice.getText();

        if (Regex.money(unitPrice)) {
            lblUnitPrice.setText("Unit price should be decimal or integer price");
            return false;
        }

        return true;
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
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
        lblProductId.setText(NewId.newProductCode());
    }
}
