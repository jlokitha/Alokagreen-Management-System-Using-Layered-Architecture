package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SupplierOrderBO;
import lk.ijse.alokagreen.bo.custom.impl.SupplierOrderBOImpl;
import lk.ijse.alokagreen.dto.SupplierOrderDto;
import lk.ijse.alokagreen.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierOrderAddFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbSupName;

    @FXML
    private JFXComboBox<String> cmbMDesc;

    @FXML
    private JFXTextField txtSupId;

    @FXML
    private JFXTextField txtMId;

    @FXML
    private JFXTextField txtMQty;

    @FXML
    private JFXTextField txtMUnitPrice;

    @FXML
    private DatePicker datepickerExp;

    @FXML
    private VBox vBox;

    @FXML
    public Label labelTotal;

    @FXML
    private ImageView imgAddSupplier;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblExpDate;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAddItem;

    public SupplierOrderDto supplierOrderDto;

    public static SupplierOrderAddFormController controller;

    private final SupplierOrderBO supplierOrderBO = (SupplierOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SUPPLIER_ORDER );

    public SupplierOrderAddFormController() {
        controller = this;
        supplierOrderDto = new SupplierOrderDto();
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

        if ( validateItem() ) {
            String[] detail = new String[6];

            detail[1] = txtMId.getText();
            detail[2] = txtMQty.getText();
            detail[3] = txtMUnitPrice.getText();
            detail[4] = String.valueOf(datepickerExp.getValue());

            LocalDate now = LocalDate.now();
            LocalDate exp = datepickerExp.getValue();

            if (exp.isBefore( now ) || exp.isEqual( now )) {
                detail[5] = "Expired";
            } else {
                detail[5] = "Not Expired";
            }

            supplierOrderDto.getItems().add(detail);
            getAllMaterial();

            double materialTot = Double.parseDouble(txtMQty.getText()) * Double.parseDouble(txtMUnitPrice.getText());
            double netTotal = materialTot + Double.parseDouble(labelTotal.getText());

            labelTotal.setText(String.valueOf(netTotal));

            clear();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnOrderOnAction ( ActionEvent event ) {
        if ( validateOrder ( ) ) {
            if ( !supplierOrderDto.getItems ( ).isEmpty ( ) ) {
                try {
                    String id = supplierOrderBO.getSupplierIdOfName ( getCmbValue ( cmbSupName ) );

                    supplierOrderDto.setSupplier_Order_Id ( NewId.newSupplierOrderId ( ) );
                    supplierOrderDto.setSupplier_Id ( id );
                    supplierOrderDto.setTotal_Amount ( Double.parseDouble ( labelTotal.getText ( ) ) );
                    supplierOrderDto.setDate ( DateTime.dateNow ( ) );
                    supplierOrderDto.setTime ( DateTime.timeNow ( ) );

                    boolean isSaved = supplierOrderBO.saveSupplierOrder ( supplierOrderDto );

                    if ( isSaved ) {
                        Navigation.closePane ( );
                        SupplierOrderManageFormController.controller.getAllId ( );
                    }

                } catch ( SQLException e ) {
                    e.printStackTrace ( );
                }
            } else {
                new Alert ( Alert.AlertType.ERROR, "Order Does not have any products !" ).show ( );
            }
        }
    }

    @FXML
    void cmbMaterialDescOnAction(ActionEvent event) {
        try {
            txtMId.setText(supplierOrderBO.getMaterialIdOfDesc (getCmbValue(cmbMDesc)));
            txtMQty.requestFocus();
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @FXML
    void cmbSupNameOnAction(ActionEvent event) {
        try {
            txtSupId.setText(supplierOrderBO.getIdOfName(getCmbValue(cmbSupName)));
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    public String getCmbValue(JFXComboBox<String> cmb) {
        return String.valueOf(cmb.getSelectionModel().getSelectedItem());
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        try {
            Navigation.closePane();
            Navigation.popupPane("SupplierAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void dpOnMouseClicked(MouseEvent event) {
        lblExpDate.setText(null);
    }

    @FXML
    void txtQtyOnMouseClicked(MouseEvent event) {
        lblQty.setText(null);
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        lblQty.setText(null);

        String qty = txtMQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
        } else {
            txtMUnitPrice.requestFocus();
        }
    }

    @FXML
    void txtUnitPriceOnMouseClicked(MouseEvent event) {
        lblUnitPrice.setText(null);
    }

    @FXML
    void txtUnitPriceOnAction(ActionEvent event) {
        lblUnitPrice.setText(null);

        String unitPrice = txtMUnitPrice.getText();

        if ( Regex.money(unitPrice) ) {
            lblUnitPrice.setText("Please enter valid unit price");
        } else {
            datepickerExp.requestFocus();
        }
    }

    @FXML
    void cmbSupNameONMouseClicked(MouseEvent event) {
        lblSupId.setText(null);
    }

    @FXML
    void cmbDescOnMouseClicked(MouseEvent event) {
        lblDesc.setText(null);
    }


    @FXML
    void btnAddSupplierOnMouseEntered(MouseEvent event) {
        imgAddSupplier.setImage(new Image("/assets/icon/in_order_add_gree.png"));
    }

    @FXML
    void btnAddSupplierOnMouseExited(MouseEvent event) {
        imgAddSupplier.setImage(new Image("/assets/icon/in_order_add.png"));
    }

    public boolean validateItem() {

        if (cmbMDesc.getSelectionModel().getSelectedItem() == null) {
            lblDesc.setText("Please select a product");
            return false;
        }

        String qty = txtMQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
            return false;
        }

        String unitPrice = txtMUnitPrice.getText();

        if ( Regex.money(unitPrice) ) {
            lblUnitPrice.setText("Please enter valid unit price");
            return false;
        }

        if ( datepickerExp.getValue() == null ) {
            lblExpDate.setText("Please select Date");
            return false;
        }

        return true;
    }

    public boolean validateOrder() {

        if ( cmbSupName.getSelectionModel().getSelectedItem() == null) {
            lblSupId.setText("Please select a supplier");
            return false;
        }

        return true;
    }

    public void setDataInComboBox() {
        try {
            ArrayList<String> supId = supplierOrderBO.getAllSupplierIds ( );
            ArrayList<String> supName = new ArrayList<>();

            for (String id : supId) {
                supName.add(supplierOrderBO.getSupplierNameOfId (id));
            }
            cmbSupName.getItems().addAll(supName);

            ArrayList<String> materialDesc = supplierOrderBO.getAllMaterialDesc();

            cmbMDesc.getItems().addAll(materialDesc);

        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    public void getAllMaterial() {

        vBox.getChildren().clear();

        for (int i = 0; i < supplierOrderDto.getItems().size(); i++) {
            loadDataTable(supplierOrderDto.getItems().get(i));
        }
    }

    private void loadDataTable(String[] detail) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierOrderAddFormController.class.getResource("/view/SupplierOrderAddTableRowForm.fxml"));
            Parent root = loader.load();
            SupplierOrderAddTableRowFormController controller = loader.getController();
            controller.setData(detail);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        cmbMDesc.getItems().clear();
        txtMId.clear();
        txtMUnitPrice.clear();
        txtMQty.clear();
        datepickerExp.setValue(null);
        setDataInComboBox();
    }

    public static SupplierOrderAddFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddItemOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnAddItem );
    }

    @FXML
    void btnAddItemOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnAddItem );
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    @FXML
    void btnOrderOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnOrder );
    }

    @FXML
    void btnOrderOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnOrder );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbSupName.setStyle("-fx-font-size: 16;");
        cmbMDesc.setStyle("-fx-font-size: 16;");
        setDataInComboBox();
    }
}
