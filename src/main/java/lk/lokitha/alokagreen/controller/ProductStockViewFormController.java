package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.dto.ProductStockDto;
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.model.ProductStockModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductStockViewFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    private Label lblStockId;

    @FXML
    private Label lblPId;

    @FXML
    private Label lblPDesc;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblHQty;

    @FXML
    private Label lblRemaining;

    @FXML
    private Label lblHDate;

    @FXML
    private Label lbExpDate;

    public static String id;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductStockDto data = ProductStockModel.getData(id);
        ProductDto PData = ProductModel.getData(data.getProduct_Code());

        lblStockId.setText(data.getStock_Id());
        lblPId.setText(PData.getProduct_Code());
        lblPDesc.setText(PData.getDescription());
        lblUnitPrice.setText(String.valueOf(PData.getUnit_Price()));
        lblHDate.setText(data.getDate());
        lbExpDate.setText(data.getExp_Date());
        lblHQty.setText(String.valueOf(data.getQty()));
        lblRemaining.setText(String.valueOf(data.getQty_On_Hand()));
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
