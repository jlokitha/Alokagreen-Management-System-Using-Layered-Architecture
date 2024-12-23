package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.ProductStockBO;
import lk.ijse.alokagreen.bo.custom.impl.ProductStockBOImpl;
import lk.ijse.alokagreen.dto.ProductStockDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductStockAddFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnAdd;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private DatePicker datePickerExp;

    @FXML
    private JFXComboBox<String> cmbDesc;

    @FXML
    private Label lblPDesc;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblDate;

    private final ProductStockBO productStockBO = (ProductStockBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PRODUCT_STOCK );

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if ( validateProductStock() ) {
            try {
                String isSaved = productStockBO.saveProductStock( new ProductStockDto(
                        null,
                        txtId.getText( ),
                        Integer.parseInt( txtQty.getText( ) ),
                        Integer.parseInt( txtQty.getText( ) ),
                        null,
                        String.valueOf( datePickerExp.getValue( ) ),
                        null
                ) );
                if (isSaved != null && isSaved.equals("Expired")) {
                    productStockBO.saveSpoiledProductReport( txtId.getText( ), Integer.parseInt( txtQty.getText( ) ) );
                }

                Navigation.closePane();
                ProductStockManageFormController.controller.getAllId();

            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void cmbProductDescOnAction(ActionEvent event) {
        lblPDesc.setText(null);

        if (cmbDesc.getSelectionModel().getSelectedItem() == null) {
            lblPDesc.setText("Please select a product");

        } else {
            try {
                String id = productStockBO.getProductId( getDesc( ) );
                txtId.setText(id);
                txtQty.requestFocus();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void txtQtyOnMouseClicked(MouseEvent event) {
        lblQty.setText(null);
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        lblQty.setText(null);

        String qty = txtQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
        } else {
            datePickerExp.requestFocus();
        }
    }

    @FXML
    void cmbDescOnMouseClicked(MouseEvent event) {
        lblPDesc.setText(null);
    }

    @FXML
    void DPExpOnMouseClicked(MouseEvent event) {
        lblDate.setText(null);
    }

    public boolean validateProductStock() {

        if (cmbDesc.getSelectionModel().getSelectedItem() == null) {
            lblPDesc.setText("Please select a product");
            return false;
        }

        String qty = txtQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
            return false;
        }

        if ( datePickerExp.getValue() == null ) {
            lblDate.setText("Please select an expiration date");
            return false;
        }

        return true;
    }

    public String getDesc() {
        return cmbDesc.getSelectionModel().getSelectedItem();
    }

    public void setDataInComboBox() {
        try {
            ArrayList<String> products = productStockBO.getAllProductDescription( );
            cmbDesc.getItems().addAll(products);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbDesc.setStyle("-fx-font-size: 16;");
        setDataInComboBox();
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnAdd );
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnAdd );
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

}
