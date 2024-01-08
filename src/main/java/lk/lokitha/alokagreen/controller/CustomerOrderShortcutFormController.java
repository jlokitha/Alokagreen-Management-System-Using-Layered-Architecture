package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.CustomerOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.CustomerOrderBOImpl;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;

import java.sql.SQLException;

public class CustomerOrderShortcutFormController {

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblContent;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    private final CustomerOrderBO customerOrderBO = (CustomerOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.CUSTOMER_ORDER );

    public void setData(String id) {

        try {
            CustomerOrderDto data = customerOrderBO.getCustomerOrderDetails ( id );
            String content = " has placed an order totaling Rs. ";

            lblOrderId.setText(data.getCustomer_Order_Id());
            lblContent.setText(data.getCustomer_Id() + content + data.getTotal_Amount());
            lblDate.setText(data.getDate());
            lblTime.setText(data.getTime());
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

}
