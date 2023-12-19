package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.SpoiledReportDto;
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.model.SpoiledReportModel;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SpoiledReportAddFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnAdd;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXComboBox<String> cmbDesc;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if ( validateReport() ) {
            SpoiledReportDto sPD = new SpoiledReportDto();

            sPD.setReport_Id(NewId.newSpoiledReportId());
            sPD.setProduct_Code(txtId.getText());
            sPD.setSpoiled_Qty(Integer.parseInt(txtQty.getText()));
            sPD.setDate(DateTime.dateNow());
            sPD.setTime(DateTime.timeNow());

            boolean isSaved = false;
            try {
                isSaved = SpoiledReportModel.saveSpoiledReport(sPD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (isSaved) {
                Navigation.closePane();
                SpoiledReportManageFormController.controller.getAllId();
            }
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void cmbProductDescOnAction(ActionEvent event) {
        lblDesc.setText(null);
        String id = ProductModel.getIdOfDesc(getDesc());
        txtId.setText(id);

        txtQty.requestFocus();
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
            btnAddOnAction(event);
        }
    }

    public boolean validateReport() {

        if (cmbDesc.getSelectionModel().getSelectedItem() == null) {
            lblDesc.setText("Please select a product");
            return false;
        }

        String qty = txtQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
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

    public void cmbDescOnMouseClicked(MouseEvent mouseEvent) {
        lblDesc.setText(null);
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
