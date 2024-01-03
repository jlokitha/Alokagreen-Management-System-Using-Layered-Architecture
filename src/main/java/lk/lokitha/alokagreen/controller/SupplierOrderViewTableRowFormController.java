package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SupplierOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.SupplierOrderBOImpl;
import lk.lokitha.alokagreen.dto.MaterialDto;
import lk.lokitha.alokagreen.dto.MaterialStockDto;

import java.sql.SQLException;

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

    private final SupplierOrderBO supplierOrderBO = (SupplierOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SUPPLIER_ORDER );

    public void setData(String id) {
        try {
            MaterialStockDto dto = supplierOrderBO.getMaterialStockDetails ( id );
            MaterialDto data = supplierOrderBO.getMaterialDetails (dto.getMaterial_Code());

            double tot = dto.getUnit_Price() * dto.getQty();

            lblStockId.setText(id);
            lblDesc.setText(data.getDescription());
            lblUnitPrice.setText(String.valueOf(dto.getUnit_Price()));
            lblQty.setText(String.valueOf(dto.getQty()));
            lblTotal.setText(String.valueOf(tot));

        } catch ( SQLException e ) {
            e.printStackTrace ();

        }
    }
}
