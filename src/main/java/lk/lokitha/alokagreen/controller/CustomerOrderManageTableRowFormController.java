package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.model.CustomerModel;
import lk.lokitha.alokagreen.model.CustomerOrderModel;
import lk.lokitha.alokagreen.util.Navigation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
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
    void imgPrintOnMouseClicked(MouseEvent event) throws JRException, SQLException {
        Map<String, Object> map = setData();

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/CustomerOrderReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT" +
                "    cod.stock_Id," +
                "    cod.qty," +
                "    p.description," +
                "    p.unit_Price," +
                "    cod.qty * p.unit_Price AS total " +
                "FROM" +
                "    customer_Order co " +
                "JOIN " +
                "    customer_Order_Detail cod ON co.customer_Order_Id = cod.customer_Order_Id " +
                "JOIN " +
                "    product_Stock ps ON cod.stock_Id = ps.stock_Id " +
                "JOIN " +
                "    product_List p ON p.product_Code = ps.product_Code " +
                "WHERE " +
                "    co.customer_Order_Id = '" + lblCusOrderId.getText() + "'");

        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                map,
                DbConnection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/lokitha/Documents/Jasper/Customer Order PDF/" + lblCusOrderId.getText() + ".pdf");
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
        CustomerOrderDto dto = CustomerOrderModel.getData(lblCusOrderId.getText());
        CustomerDto data = CustomerModel.getData(dto.getCustomer_Id());

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
    }

    public void setData(String id) {

        CustomerOrderDto data = CustomerOrderModel.getData(id);

        lblCusOrderId.setText(data.getCustomer_Order_Id());
        lblCustId.setText(data.getCustomer_Id());
        lblAmount.setText(String.valueOf(data.getTotal_Amount()));
        lblDate.setText(data.getDate());
        lblTime.setText(data.getTime());

    }
}
