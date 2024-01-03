package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SupplierOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.SupplierOrderBOImpl;
import lk.lokitha.alokagreen.dto.SupplierDto;
import lk.lokitha.alokagreen.dto.SupplierOrderDto;
import lk.lokitha.alokagreen.util.Navigation;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
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

    private final SupplierOrderBO supplierOrderBO = (SupplierOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SUPPLIER_ORDER );

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
    void imgPrintOnMouseClicked(MouseEvent event) {
        try {
            supplierOrderBO.saveSupplierOrderAsPDF ( lblOrderId.getText (), setData () );
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
            SupplierOrderDto dto = supplierOrderBO.getSupplierOrderDetails ( lblOrderId.getText ( ) );
            SupplierDto data = supplierOrderBO.getSupplierDetails (dto.getSupplier_Id());

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

        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
        return null;
    }

    public void setData(String id) {
        try {
            SupplierOrderDto data = supplierOrderBO.getSupplierOrderDetails ( id );

            lblOrderId.setText(data.getSupplier_Order_Id());
            lblSupId.setText(data.getSupplier_Id());
            lblAmount.setText(String.valueOf(data.getTotal_Amount()));
            lblDate.setText(data.getDate());
            lblTime.setText(data.getTime());

        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }
}
