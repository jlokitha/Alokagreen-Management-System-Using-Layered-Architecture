package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.model.CustomerOrderModel;

public class CustomerOrderShortcutFormController {

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblContent;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    public void setData(String id) {

        CustomerOrderDto data = CustomerOrderModel.getData(id);
        String content = " has placed an order totaling Rs. ";

        lblOrderId.setText(data.getCustomer_Order_Id());
        lblContent.setText(data.getCustomer_Id() + content + data.getTotal_Amount());
        lblDate.setText(data.getDate());
        lblTime.setText(data.getTime());

    }

}
