package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.ProductBO;
import lk.lokitha.alokagreen.bo.custom.impl.ProductBOImpl;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductAddFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnAdd;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private Label lblProductId;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblUnitPrice;

    private final ProductBO productBO = (ProductBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PRODUCT );

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if ( validateProduct() ) {
            try {
                boolean isSaved = productBO.saveProduct( new ProductDto(
                        lblProductId.getText( ),
                        txtDescription.getText( ),
                        Double.parseDouble( txtUnitPrice.getText( ) )
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    ProductListManageFormController.controller.getAllId();
                }

            } catch (SQLException e) {
                throw new RuntimeException( e );
            }
        }
    }

    @FXML
    void txtDescOnMouseClicked(MouseEvent event) {
        lblDesc.setText(null);
    }

    @FXML
    void txtDescOnAction(ActionEvent event) {
        lblDesc.setText(null);
        String desc = txtDescription.getText();

        if (Regex.description(desc)) {
            lblDesc.setText("First letter should be capital and should only contain letters");
        } else {
            txtUnitPrice.requestFocus();
        }
    }

    @FXML
    void txtUnitPriceOnMouseClicked(MouseEvent event) {
        lblUnitPrice.setText(null);
    }

    @FXML
    void txtUnitPriceOnAction(ActionEvent event) {
        lblUnitPrice.setText(null);
        String unitPrice = txtUnitPrice.getText();

        if (Regex.money(unitPrice)) {
            lblUnitPrice.setText("Unit price should be decimal or integer price");
        } else {
            btnAddOnAction(event);
        }
    }

    public boolean validateProduct() {
        String desc = txtDescription.getText();

        if (Regex.description(desc)) {
            lblDesc.setText("First letter should be capital and should only contain letters");
            return false;
        }

        String unitPrice = txtUnitPrice.getText();

        if (Regex.money(unitPrice)) {
            lblUnitPrice.setText("Unit price should be decimal or integer price");
            return false;
        }

        return true;
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblProductId.setText(productBO.generateNewProductId());
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }
}
