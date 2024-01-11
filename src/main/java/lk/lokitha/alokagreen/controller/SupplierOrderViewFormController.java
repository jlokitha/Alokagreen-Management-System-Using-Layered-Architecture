package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SupplierOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.SupplierOrderBOImpl;
import lk.lokitha.alokagreen.dto.SupplierOrderDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Style;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierOrderViewFormController implements Initializable {

    @FXML
    private VBox vBox;

    @FXML
    private Label labelTotal;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lblSupName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderTime;

    @FXML
    private JFXButton btnCancel;

    public static String id;

    private final SupplierOrderBO supplierOrderBO = (SupplierOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SUPPLIER_ORDER );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierOrderViewFormController.class.getResource("/view/SupplierOrderViewTableRowForm.fxml"));
            Parent root = loader.load();
            SupplierOrderViewTableRowFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData() {
        try {
            SupplierOrderDto dto = supplierOrderBO.getSupplierOrderDetails ( id );
            String name = supplierOrderBO.getSupplierNameOfId (dto.getSupplier_Id());
            ArrayList<String> data = supplierOrderBO.getSupplierOrderDetailsData (dto.getSupplier_Order_Id());

            lblOrderId.setText(dto.getSupplier_Order_Id());
            lblSupId.setText(dto.getSupplier_Id());
            lblSupName.setText(name);
            lblOrderDate.setText(dto.getDate());
            lblOrderTime.setText(dto.getTime());
            labelTotal.setText(String.valueOf(dto.getTotal_Amount()));

            getProduct(data);
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    public void getProduct(ArrayList<String> list) {

        vBox.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMoseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder2 ( btnCancel );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
