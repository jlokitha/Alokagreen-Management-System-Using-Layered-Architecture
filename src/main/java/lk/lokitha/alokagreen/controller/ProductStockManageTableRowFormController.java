package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.ProductStockBO;
import lk.lokitha.alokagreen.bo.custom.impl.ProductStockBOImpl;
import lk.lokitha.alokagreen.dto.ProductStockDto;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class ProductStockManageTableRowFormController {

    @FXML
    private Label lblId;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblExpDate;

    @FXML
    private ImageView imgView;

    @FXML
    private ImageView imgUpdate;

    private final ProductStockBO productStockBO = (ProductStockBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PRODUCT_STOCK );

    @FXML
    void imgUpdateOnMouseClicked(MouseEvent event) {
        try {
            ProductStockUpdateFormController.id = lblId.getText();
            Navigation.popupPane("ProductStockUpdateForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgUpdateOnMouseEntered(MouseEvent event) {
        imgUpdate.setImage(new Image("/assets/icon/update_green.png"));
    }

    @FXML
    void imgUpdateOnMouseExited(MouseEvent event) {
        imgUpdate.setImage(new Image("/assets/icon/update.png"));
    }

    @FXML
    void imgViewOnMouseClicked(MouseEvent event) {
        try {
            ProductStockViewFormController.id = lblId.getText();
            Navigation.popupPane("ProductStockViewForm.fxml");
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

    public void setStatus(String status) {
        lblExpDate.setAlignment(Pos.CENTER);

        if ( Objects.equals(status, "Not Expired") ) {
            lblExpDate.setStyle(
                    "-fx-background-color: #C7FFDE;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-text-fill: #139547");
        } else {
            lblExpDate.setStyle(
                    "-fx-background-color: #FFD3D3;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-text-fill: #FF0E0E");
        }
    }

    public void setData(String id) {
        try {
            ProductStockDto data = productStockBO.getProductStockDetails( id );
            String[] descPrice = productStockBO.getProductDescAndPrice(data.getProduct_Code());

            LocalDate currentDate = LocalDate.now();
            LocalDate expDate = LocalDate.parse(data.getExp_Date());

            if ( (currentDate.isEqual(expDate) || currentDate.isAfter(expDate)) && (data.getStatus().equals("Not Expired")) ) {
                ProductStockManageFormController.expList.add(id);
            }

            lblId.setText(data.getStock_Id());
            lblDesc.setText(descPrice[0]);
            lblQty.setText(String.valueOf(data.getQty_On_Hand()));
            lblUnitPrice.setText(descPrice[1]);
            lblExpDate.setText(data.getStatus());
            setStatus(data.getStatus());

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

}
