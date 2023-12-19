package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.SupplierDto;
import lk.lokitha.alokagreen.dto.tm.SupplierOrderTm;
import lk.lokitha.alokagreen.model.SupplierModel;
import lk.lokitha.alokagreen.model.SupplierOrderModel;
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

public class SupplierOrderManageTableRowFormController {

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblSupId;

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
            SupplierOrderViewFormController.id = lblOrderId.getText();
            Navigation.popupPane("SupplierOrderViewForm.fxml");
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

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/SupplierOrderReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT" +
                "    sod.stock_Id," +
                "    ms.qty," +
                "    m.description," +
                "    ms.unit_Price," +
                "    ms.qty * ms.unit_Price AS total " +
                "FROM " +
                "    supplier_Order so " +
                "JOIN " +
                "    supplier_Order_Detail sod ON so.supplier_Order_Id = sod.supplier_Order_Id " +
                "JOIN " +
                "    material_Stock ms ON sod.stock_Id = ms.stock_Id " +
                "JOIN " +
                "    material_List m ON m.material_Code = ms.material_Code " +
                "WHERE " +
                "    so.supplier_Order_Id ='" + lblOrderId.getText() + "'");

        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                map,
                DbConnection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/lokitha/Documents/Jasper/Supplier Order PDF/" + lblOrderId.getText() + ".pdf");
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
        SupplierOrderTm dto = SupplierOrderModel.getData(lblOrderId.getText());
        SupplierDto data = SupplierModel.getData(dto.getSupplier_Id());

        Map<String, Object> temp = new HashMap<>();

        temp.put("OrderId", dto.getSupplier_Order_Id());
        temp.put("OrderedDate", dto.getDate());
        temp.put("OrderedTime", dto.getTime());
        temp.put("SupplierId", data.getSupplier_Id());
        temp.put("Name", data.getCompany_Name());
        temp.put("Mobile", data.getCompany_Mobile());
        temp.put("Email", data.getCompany_Email());
        temp.put("total", String.valueOf(dto.getTotal_Amount()));

        return temp;
    }

    public void setData(String id) {

        SupplierOrderTm data = SupplierOrderModel.getData(id);

        lblOrderId.setText(data.getSupplier_Order_Id());
        lblSupId.setText(data.getSupplier_Id());
        lblAmount.setText(String.valueOf(data.getTotal_Amount()));
        lblDate.setText(data.getDate());
        lblTime.setText(data.getTime());

    }

}
