package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.model.ProductStockModel;

public class CustomerOrderViewTableRowFormController {

    @FXML
    private Label lblStockId;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblTotal;

    public void setData(String[] detail) {

        String pId = ProductStockModel.getProductId(detail[0]);
        ProductDto data = ProductModel.getData(pId);

        double tot = data.getUnit_Price() * Double.parseDouble(detail[1]);

        lblStockId.setText(detail[0]);
        lblDesc.setText(data.getDescription());
        lblUnitPrice.setText(String.valueOf(data.getUnit_Price()));
        lblQty.setText(detail[1]);
        lblTotal.setText(String.valueOf(tot));

    }

}
