package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.CustomerOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.CustomerOrderBOImpl;
import lk.lokitha.alokagreen.dto.ProductDto;

import java.sql.SQLException;

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

    private final CustomerOrderBO customerOrderBO = (CustomerOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.CUSTOMER_ORDER );

    public void setData(String[] detail) {
        try {
            String pId = customerOrderBO.getProductId ( detail[0] );
            ProductDto data = customerOrderBO.getProductDetails (pId);

            double tot = data.getUnit_Price() * Double.parseDouble(detail[1]);

            lblStockId.setText(detail[0]);
            lblDesc.setText(data.getDescription());
            lblUnitPrice.setText(String.valueOf(data.getUnit_Price()));
            lblQty.setText(detail[1]);
            lblTotal.setText(String.valueOf(tot));

        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }
}
