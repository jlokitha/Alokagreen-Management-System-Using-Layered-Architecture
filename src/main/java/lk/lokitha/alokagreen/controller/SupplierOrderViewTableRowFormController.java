package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.lokitha.alokagreen.dto.MaterialDto;
import lk.lokitha.alokagreen.dto.MaterialStockDto;
import lk.lokitha.alokagreen.model.MaterialModel;
import lk.lokitha.alokagreen.model.MaterialStockModel;

public class SupplierOrderViewTableRowFormController {

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

    public void setData(String id) {

        MaterialStockDto dto = MaterialStockModel.getDetail(id);
        MaterialDto data = MaterialModel.getData(dto.getMaterial_Code());

        double tot = dto.getUnit_Price() * dto.getQty();

        lblStockId.setText(id);
        lblDesc.setText(data.getDescription());
        lblUnitPrice.setText(String.valueOf(dto.getUnit_Price()));
        lblQty.setText(String.valueOf(dto.getQty()));
        lblTotal.setText(String.valueOf(tot));

    }

}
