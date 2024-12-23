package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.ProductStockBO;
import lk.ijse.alokagreen.bo.custom.impl.ProductStockBOImpl;
import lk.ijse.alokagreen.dto.ProductDto;
import lk.ijse.alokagreen.dto.ProductStockDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductStockViewFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    private Label lblStockId;

    @FXML
    private Label lblPId;

    @FXML
    private Label lblPDesc;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblHQty;

    @FXML
    private Label lblRemaining;

    @FXML
    private Label lblHDate;

    @FXML
    private Label lbExpDate;

    public static String id;

    private final ProductStockBO productStockBO = (ProductStockBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PRODUCT_STOCK );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ProductStockDto data = productStockBO.getProductStockDetails( id );
            ProductDto PData = productStockBO.getProductDetails(data.getProduct_Code());

            lblStockId.setText(data.getStock_Id());
            lblPId.setText(PData.getProduct_Code());
            lblPDesc.setText(PData.getDescription());
            lblUnitPrice.setText(String.valueOf(PData.getUnit_Price()));
            lblHDate.setText(data.getDate());
            lbExpDate.setText(data.getExp_Date());
            lblHQty.setText(String.valueOf(data.getQty()));
            lblRemaining.setText(String.valueOf(data.getQty_On_Hand()));

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
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
