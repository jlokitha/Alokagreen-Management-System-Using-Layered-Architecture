package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.ProductBO;
import lk.ijse.alokagreen.bo.custom.impl.ProductBOImpl;
import lk.ijse.alokagreen.dto.ProductDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductListUpdateFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnUpdate;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private Label lblProductId;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblUnitPrice;

    public static String id;

    private final ProductBO productBO = (ProductBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PRODUCT );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if ( validateProduct() ) {
            try {
                boolean isSaved = productBO.updateProduct( new ProductDto(
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
        String desc = txtDescription.getText();

        if (Regex.description(desc)) {
            lblDesc.setText("First letter should be capital and should only contain letters");
        }
    }

    @FXML
    void txtUnitPriceOnMouseClicked(MouseEvent event) {
        lblUnitPrice.setText(null);
    }

    @FXML
    void txtUnitPriceOnAction(ActionEvent event) {
        String unitPrice = txtUnitPrice.getText();

        if (Regex.money(unitPrice)) {
            lblUnitPrice.setText("Unit price should be decimal or integer price");
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

    private void setData(ProductDto productDto) {

        lblProductId.setText(productDto.getProduct_Code());
        txtDescription.setText(productDto.getDescription());
        txtUnitPrice.setText(String.valueOf(productDto.getUnit_Price()));

    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnUpdate );
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnUpdate );
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
            setData(productBO.getProductData( id ));
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }
}
