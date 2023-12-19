package lk.lokitha.alokagreen.controller;

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
import lk.lokitha.alokagreen.dto.SupplierOrderDto;
import lk.lokitha.alokagreen.model.MaterialModel;
import lk.lokitha.alokagreen.model.PlaceOrdersModel;
import lk.lokitha.alokagreen.model.SupplierModel;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.Regex;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
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
    void btnOrderOnAction(ActionEvent event) {
        if ( validateOrder() ) {
            if ( !supplierOrderDto.getItems().isEmpty() ) {
                String id = SupplierModel.getIdOfName(getCmbValue(cmbSupName));

                supplierOrderDto.setSupplier_Order_Id(NewId.newSupplierOrderId());
                supplierOrderDto.setSupplier_Id(id);
                supplierOrderDto.setTotal_Amount(Double.parseDouble(labelTotal.getText()));
                supplierOrderDto.setDate(DateTime.dateNow());
                supplierOrderDto.setTime(DateTime.timeNow());

                try {
                    boolean isSaved = PlaceOrdersModel.saveSupplierOrder(supplierOrderDto);

                    if (isSaved) {
                        Navigation.closePane();
                        SupplierOrderManageFormController.controller.getAllId();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Does not have any products !").show();
            }
        }
    }

    @FXML
    void cmbMaterialDescOnAction(ActionEvent event) {
        txtMId.setText(MaterialModel.getIdOfDesc(getCmbValue(cmbMDesc)));
        txtMQty.requestFocus();
    }

    @FXML
    void cmbSupNameOnAction(ActionEvent event) {
        txtSupId.setText(SupplierModel.getIdOfName(getCmbValue(cmbSupName)));
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

    @FXML
    void btnAddItemOnMouseEntered(MouseEvent event) {
        btnAddItem.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnAddItemOnMouseExited(MouseEvent event) {
        btnAddItem.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }

    @FXML
    void btnOrderOnMouseEntered(MouseEvent event) {
        btnOrder.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnOrderOnMouseExited(MouseEvent event) {
        btnOrder.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
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
        ArrayList<String> supId = SupplierModel.getAllId();
        ArrayList<String> supName = new ArrayList<>();

        for (String id : supId) {
            supName.add(SupplierModel.getNameOfId(id));
        }
        cmbSupName.getItems().addAll(supName);

        ArrayList<String> materialDesc = MaterialModel.getAllMaterialDesc();

        cmbMDesc.getItems().addAll(materialDesc);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbSupName.setStyle("-fx-font-size: 16;");
        cmbMDesc.setStyle("-fx-font-size: 16;");
        setDataInComboBox();
    }
}
