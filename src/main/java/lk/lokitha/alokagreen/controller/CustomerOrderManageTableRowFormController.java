package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.CustomerOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.CustomerOrderBOImpl;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.util.Navigation;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerOrderManageTableRowFormController {

    @FXML
    private Label lblCusOrderId;

    @FXML
    private Label lblCustId;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private ImageView imgView;

    @FXML
    private ImageView imgPrint;

    private final CustomerOrderBO customerOrderBO = (CustomerOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.CUSTOMER_ORDER );

    @FXML
    void imgViewOnMouseClicked(MouseEvent event) {
        try {
            CustomerOrderViewFormController.id = lblCusOrderId.getText();
            Navigation.popupPane("CustomerOrderViewForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgViewOnMouseEntered(MouseEvent event) {
        imgView.setImage(new Image("/assets/icon/view_blue.png"));
    }

    @FXML
    void imgViewOnMouseExited(MouseEvent event) {
        imgView.setImage(new Image("/assets/icon/view_default.png"));
    }

    @FXML
    void imgPrintOnMouseClicked(MouseEvent event) {
        try {
            customerOrderBO.saveCustomerOrderReportAsPDF ( lblCusOrderId.getText (), setData () );
        } catch ( JRException | SQLException e ) {
            e.printStackTrace ();
        }
    }

    @FXML
    void imgPrintOnMouseEntered(MouseEvent event) {
        imgPrint.setImage(new Image("/assets/icon/print_green.png"));
    }

    @FXML
    void imgPrintOnMouseExited(MouseEvent event) {
        imgPrint.setImage(new Image("/assets/icon/print.png"));
    }

    public Map<String, Object> setData() {
        try {
            CustomerOrderDto dto = customerOrderBO.getCustomerOrderDetails ( lblCusOrderId.getText ( ) );
            CustomerDto data = customerOrderBO.getCustomerDetails (dto.getCustomer_Id());

            Map<String, Object> temp = new HashMap<>();

            temp.put("OrderId", dto.getCustomer_Order_Id());
            temp.put("OrderedDate", dto.getDate());
            temp.put("OrderedTime", dto.getTime());
            temp.put("CustomerID", data.getCustomer_Id());
            temp.put("Name", data.getName());
            temp.put("Mobile", data.getMobile());
            temp.put("Email", data.getEmail());
            temp.put("total", String.valueOf(dto.getTotal_Amount()));

            return temp;
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }

        return null;
    }

    public void setData(String id) {
        try {
            CustomerOrderDto data = customerOrderBO.getCustomerOrderDetails ( id );

            lblCusOrderId.setText(data.getCustomer_Order_Id());
            lblCustId.setText(data.getCustomer_Id());
            lblAmount.setText(String.valueOf(data.getTotal_Amount()));
            lblDate.setText(data.getDate());
            lblTime.setText(data.getTime());

        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }
}
