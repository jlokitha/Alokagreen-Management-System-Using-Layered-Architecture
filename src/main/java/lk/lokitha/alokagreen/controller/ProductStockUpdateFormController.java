package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.ProductStockDto;
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.model.ProductStockModel;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProductStockUpdateFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnUpdate;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtRemainedQty;

    @FXML
    private JFXTextField txtNetQty;

    @FXML
    private JFXComboBox<String> cmbDesc;

    @FXML
    private DatePicker datePickerExp;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblNetQty;

    @FXML
    private Label lblRemaining;

    @FXML
    private Label lblExpDate;

    public static String id;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if ( validateStock() ) {
            double net = Double.parseDouble(txtNetQty.getText());
            double remain = Double.parseDouble(txtRemainedQty.getText());

            if ( remain <= net ) {
                ProductStockDto pSDto = new ProductStockDto();

                pSDto.setStock_Id(id);
                pSDto.setProduct_Code(txtId.getText());
                pSDto.setQty_On_Hand(Integer.parseInt(txtRemainedQty.getText()));
                pSDto.setQty(Integer.parseInt(txtNetQty.getText()));
                pSDto.setExp_Date(String.valueOf(datePickerExp.getValue()));
                pSDto.setStatus(txtStatus.getText());

                boolean isSaved = ProductStockModel.updateProductStock(pSDto);

                if (isSaved) {
                    Navigation.closePane();
                    ProductStockManageFormController.controller.getAllId();
                }
            } else {
                lblRemaining.setText("Remaining quantity can not exceed net quantity");
            }
        }

    }

    @FXML
    void cmbProductDescOnAction(ActionEvent event) {
        lblDesc.setText(null);

        if (cmbDesc.getSelectionModel().getSelectedItem().isEmpty()) {
            lblDesc.setText("Please select a product");

        } else {
            String id = ProductModel.getIdOfDesc(cmbDesc.getSelectionModel().getSelectedItem());
            txtId.setText(id);
            txtNetQty.requestFocus();
        }
    }

    private void setData(ProductStockDto productStockDto) {

        String desc = ProductModel.getDescOfId(productStockDto.getProduct_Code());

        cmbDesc.setValue(desc);
        txtId.setText(productStockDto.getProduct_Code());
        txtRemainedQty.setText(String.valueOf(productStockDto.getQty_On_Hand()));
        txtNetQty.setText(String.valueOf(productStockDto.getQty()));
        datePickerExp.setValue(LocalDate.parse(productStockDto.getExp_Date()));
        txtStatus.setText(productStockDto.getStatus());
    }

    @FXML
    void cmbDescOnMouseCliked(MouseEvent event) {
        lblDesc.setText(null);
    }


    @FXML
    void txtNetOnMouseClicked(MouseEvent event) {
        lblNetQty.setText(null);
    }

    @FXML
    void txtNetOnAction(ActionEvent event) {
        lblNetQty.setText(null);
        String netQty = txtNetQty.getText();

        if (Regex.qty(netQty)) {
            lblNetQty.setText("Please enter valid quantity");
        } else {
            txtRemainedQty.requestFocus();
        }
    }

    @FXML
    void txtRemianingOnMouseClicked(MouseEvent event) {
        lblRemaining.setText(null);
    }

    @FXML
    void txtRemainingOnAction(ActionEvent event) {
        lblRemaining.setText(null);
        int remaining = Integer.parseInt(txtRemainedQty.getText());
        int net = Integer.parseInt(txtNetQty.getText());

        String remainedQty = txtRemainedQty.getText();

        if (Regex.qty(remainedQty)) {
            lblRemaining.setText("Please enter valid quantity");
        } else if (!(remaining <= net)) {
            lblRemaining.setText("Remaining quantity can not exceed net quantity");
        } else {
            datePickerExp.requestFocus();
        }
    }

    @FXML
    void DPOnMouseClicked(MouseEvent event) {
        lblExpDate.setText(null);
    }

    @FXML
    void dpExpDateOnAction(ActionEvent event) {
        lblExpDate.setText(null);

        LocalDate currentDate = LocalDate.now();
        LocalDate expDate = datePickerExp.getValue();
        if ( !currentDate.isEqual(expDate) || currentDate.isAfter(expDate) ) {
            txtStatus.setText("Not Expired");
        }

        if ( currentDate.isEqual(expDate) || currentDate.isAfter(expDate) ) {
            txtStatus.setText("Expired");
        }
    }

    public boolean validateStock() {

        if (cmbDesc.getSelectionModel().getSelectedItem() == null) {
            lblDesc.setText("Please select a product");
            return false;
        }

        String netQty = txtNetQty.getText();

        if (Regex.qty(netQty)) {
            lblNetQty.setText("Please enter valid quantity");
            return false;
        }

        String remainedQty = txtRemainedQty.getText();

        if (Regex.qty(remainedQty)) {
            lblNetQty.setText("Please enter valid quantity");
            return false;
        }

        if ( datePickerExp.getValue() == null ) {
            lblExpDate.setText("Please select an expiration date");
            return false;
        }

        return true;
    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        btnUpdate.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        btnUpdate.setStyle(
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
        cmbDesc.setStyle("-fx-font-size: 16;");
        ProductStockDto pSDto = ProductStockModel.getData(id);

        cmbDesc.getItems().addAll(ProductModel.getAllProductDesc());

        setData(pSDto);
    }
}
