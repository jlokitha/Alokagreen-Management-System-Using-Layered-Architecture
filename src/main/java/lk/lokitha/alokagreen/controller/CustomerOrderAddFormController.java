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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.CustomerOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.CustomerOrderBOImpl;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.Style;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class CustomerOrderAddFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbMobile;

    @FXML
    private JFXComboBox<String> cmbStockId;

    @FXML
    private JFXComboBox<String> cmbProductDesc;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtExpDate;

    @FXML
    private JFXTextField txtOrderedQty;

    @FXML
    private VBox vBox;

    @FXML
    public Label labelTotal;

    @FXML
    private ImageView imgAddCustomer;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblStockId;

    @FXML
    private Label lblQty;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAddItem;

    public CustomerOrderDto dto;

    public static CustomerOrderAddFormController controller;

    private final CustomerOrderBO customerOrderBO = (CustomerOrderBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.CUSTOMER_ORDER );

    public CustomerOrderAddFormController() {
        controller = this;
        dto = new CustomerOrderDto();
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

        if (validateItem()) {
            int quantity = Integer.parseInt(txtQty.getText());
            int orderedQty = Integer.parseInt(txtOrderedQty.getText());

            if (orderedQty <= quantity) {
                String stockId = getCmbValue(cmbStockId);
                String current = dto.getItems().get(stockId);
                int currentQty = current == null ? 0 : Integer.parseInt(current);
                int qty = (Integer.parseInt(txtOrderedQty.getText()) + currentQty);

                dto.getItems().put(stockId, String.valueOf(qty));
                getProduct();

                double itemTotal = Double.parseDouble(String.valueOf(qty)) * Double.parseDouble(txtUnitPrice.getText());
                double netTotal = itemTotal + Double.parseDouble(labelTotal.getText());

                labelTotal.setText(String.valueOf(netTotal));

                clear();
            }
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnOrderOnAction (ActionEvent event) {
        if ( validateOrder ( ) ) {
            if ( !dto.getItems ( ).isEmpty ( ) ) {

                try {
                    String id = customerOrderBO.getCustomerIdUsingMobile ( getCmbValue ( cmbMobile ) );
                    dto.setCustomer_Id ( id );
                    dto.setTotal_Amount ( Double.parseDouble ( labelTotal.getText ( ) ) );

                    boolean isSaved = customerOrderBO.saveCustomerOrder ( dto );

                    if ( isSaved ) {
                        Navigation.closePane ( );
                        CustomerOrderManageFormController.controller.getAllId ( );
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
    void cmbMobileOnAction(ActionEvent event) {
        try {
            txtCustName.setText(customerOrderBO.getCustomerNameUsingMobile (getCmbValue(cmbMobile)));
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @FXML
    void cmbProductDescOnAction(ActionEvent event) {
        try {
            cmbStockId.getItems().clear();
            txtQty.clear();
            txtExpDate.clear();
            txtUnitPrice.clear();
            ArrayList<String> stockIds = customerOrderBO.getAllProductStockIdOfDesc ( getCmbValue ( cmbProductDesc ) );
            cmbStockId.getItems().addAll(stockIds);
            cmbStockId.requestFocus();
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @FXML
    void cmbStockIdOnAction(ActionEvent event) {
        try {
            String[] stockDetails = customerOrderBO.getProductStockDetails (getCmbValue(cmbStockId));
            txtExpDate.setText(stockDetails[0]);
            txtUnitPrice.setText(stockDetails[1]);
            txtOrderedQty.requestFocus();

            if (dto.getItems().containsKey(getCmbValue(cmbStockId))) {

                int currentQty = Integer.parseInt(dto.getItems().get(getCmbValue(cmbStockId)));
                int qty = Integer.parseInt(stockDetails[2]);
                txtQty.setText(String.valueOf(qty - currentQty));

            } else {
                txtQty.setText(stockDetails[2]);
            }
        } catch ( NullPointerException | SQLException e) {
            e.printStackTrace ();
        }
    }

    public String getCmbValue(JFXComboBox<String> cmb) {
        return String.valueOf(cmb.getSelectionModel().getSelectedItem());
    }

    public void setDataInCmbMobile() {
        try {
            cmbMobile.getItems().addAll(customerOrderBO.getAllCustomerMobile ());
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }
    public void setDataInCmbDesc() {
        try {
            cmbProductDesc.getItems().addAll(customerOrderBO.getAllProductDesc ());
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        try {
            Navigation.closePane();
            Navigation.popupPane("CustomerAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getProduct() {

        vBox.getChildren().clear();

        for (Map.Entry<String, String> entry : dto.getItems().entrySet()) {
            String[] detail = new String[2];

            detail[0] = entry.getKey();
            detail[1] = String.valueOf(entry.getValue());

            loadDataTable(detail);
        }
    }

    private void loadDataTable(String[] detail) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderAddFormController.class.getResource("/view/CustomerOrderAddTableRowForm.fxml"));
            Parent root = loader.load();
            CustomerOrderAddTableRowFormController controller = loader.getController();
            controller.setData(detail);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        cmbProductDesc.getItems().clear();
        cmbStockId.getItems().clear();
        txtExpDate.clear();
        txtQty.clear();
        txtUnitPrice.clear();
        txtOrderedQty.clear();
        setDataInCmbDesc();
        setDataInCmbMobile();
    }

    public static CustomerOrderAddFormController getInstance() {
        return controller;
    }

    @FXML
    void cmbStockIdOnMouseClicked(MouseEvent event) {
        lblStockId.setText(null);
    }

    @FXML
    void txtOrderQtyOnMouseClicked(MouseEvent event) {
        lblQty.setText(null);
    }

    @FXML
    void txtOrderQtyOnAction(ActionEvent event) {
        lblQty.setText(null);

        String qty = txtOrderedQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
        } else {
            btnAddItemOnAction(event);
        }
    }

    @FXML
    void cmbMobileOnMouseClicked(MouseEvent event) {
        lblMobile.setText(null);
    }

    @FXML
    void cmbDescOnMouseClicked(MouseEvent event) {
        lblDesc.setText(null);
    }

    public boolean validateItem() {

        if (cmbProductDesc.getSelectionModel().getSelectedItem() == null) {
            lblDesc.setText("Please select a product");
            return false;
        }


        if (cmbStockId.getSelectionModel().getSelectedItem() == null) {
            lblStockId.setText("Please select a stock");
            return false;
        }

        String qty = txtOrderedQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
            return false;
        }

        return true;
    }

    public boolean validateOrder() {

        if ( cmbMobile.getSelectionModel().getSelectedItem().isEmpty()) {
            lblMobile.setText("Please select a customer");
            return false;
        }

        return true;
    }

    @FXML
    void btnAddCustomerOnMouseEntered(MouseEvent event) {
        imgAddCustomer.setImage(new Image("/assets/icon/in_order_add_gree.png"));
    }

    @FXML
    void btnAddCustomerOnMouseExited(MouseEvent event) {
        imgAddCustomer.setImage(new Image("/assets/icon/in_order_add.png"));
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
        Style.btnOnMoseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMoseExitedWithBorder ( btnCancel );
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
        cmbMobile.setStyle("-fx-font-size: 16;");
        cmbProductDesc.setStyle("-fx-font-size: 16;");
        cmbStockId.setStyle("-fx-font-size: 16;");
        setDataInCmbDesc();
        setDataInCmbMobile();
    }
}
