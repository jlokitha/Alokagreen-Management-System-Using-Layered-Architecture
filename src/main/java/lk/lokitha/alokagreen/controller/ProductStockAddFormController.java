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
import lk.lokitha.alokagreen.dto.SpoiledReportDto;
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.model.ProductStockModel;
import lk.lokitha.alokagreen.model.SpoiledReportModel;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductStockAddFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnAdd;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private DatePicker datePickerExp;

    @FXML
    private JFXComboBox<String> cmbDesc;

    @FXML
    private Label lblPDesc;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblDate;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if ( validateProductStock() ) {
            ProductStockDto pSD = new ProductStockDto();

            pSD.setStock_Id(NewId.newProductStockCode());
            pSD.setProduct_Code(txtId.getText());
            pSD.setQty_On_Hand(Integer.parseInt(txtQty.getText()));
            pSD.setQty(Integer.parseInt(txtQty.getText()));
            pSD.setDate(DateTime.dateNow());
            pSD.setExp_Date(String.valueOf(datePickerExp.getValue()));

            LocalDate expDate = datePickerExp.getValue();
            LocalDate now = LocalDate.now();

            if (expDate.isBefore(now) || expDate.isEqual(now)) {
                pSD.setStatus("Expired");
            } else {
                pSD.setStatus("Not Expired");
            }

            boolean isSaved = ProductStockModel.saveProductStock(pSD);

            if (isSaved && pSD.getStatus().equals("Expired")) {
                SpoiledReportDto sRD = new SpoiledReportDto();

                sRD.setReport_Id(NewId.newSpoiledReportId());
                sRD.setProduct_Code(txtId.getText());
                sRD.setSpoiled_Qty(Integer.parseInt(txtQty.getText()));
                sRD.setDate(DateTime.dateNow());
                sRD.setTime(DateTime.timeNow());

                try {
                    SpoiledReportModel.saveSpoiledReport( sRD );
                } catch (SQLException e) {
                    throw new RuntimeException( e );
                }
            }

            Navigation.closePane();
            ProductStockManageFormController.controller.getAllId();
        }

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void cmbProductDescOnAction(ActionEvent event) {
        lblPDesc.setText(null);

        if (cmbDesc.getSelectionModel().getSelectedItem() == null) {
            lblPDesc.setText("Please select a product");

        } else {
            String id = ProductModel.getIdOfDesc(getDesc());
            txtId.setText(id);

            txtQty.requestFocus();
        }
    }

    @FXML
    void txtQtyOnMouseClicked(MouseEvent event) {
        lblQty.setText(null);
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        lblQty.setText(null);

        String qty = txtQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
        } else {
            datePickerExp.requestFocus();
        }
    }

    @FXML
    void cmbDescOnMouseClicked(MouseEvent event) {
        lblPDesc.setText(null);
    }

    @FXML
    void DPExpOnMouseClicked(MouseEvent event) {
        lblDate.setText(null);
    }

    public boolean validateProductStock() {

        if (cmbDesc.getSelectionModel().getSelectedItem() == null) {
            lblPDesc.setText("Please select a product");
            return false;
        }

        String qty = txtQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
            return false;
        }

        if ( datePickerExp.getValue() == null ) {
            lblDate.setText("Please select an expiration date");
            return false;
        }

        return true;
    }

    public String getDesc() {
        return String.valueOf(cmbDesc.getSelectionModel().getSelectedItem());
    }

    public void setDataInComboBox() {
        ArrayList<String> products = ProductModel.getAllProductDesc();

        cmbDesc.getItems().addAll(products);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbDesc.setStyle("-fx-font-size: 16;");
        setDataInComboBox();
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

}
