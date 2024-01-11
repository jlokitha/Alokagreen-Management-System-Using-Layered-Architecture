package lk.ijse.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.ProductBO;
import lk.ijse.alokagreen.bo.custom.impl.ProductBOImpl;
import lk.ijse.alokagreen.dto.ProductDto;
import lk.ijse.alokagreen.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class ProductListManageTableRowFormController {

    @FXML
    private Label lblProductId;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private ImageView imgUpdate;

    @FXML
    private ImageView imgDelete;

    private final ProductBO productBO = (ProductBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PRODUCT );

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure you want to delete this product?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                productBO.deleteProduct( lblProductId.getText() );
            } catch (SQLException e) {
                throw new RuntimeException( e );
            }
            ProductListManageFormController.controller.getAllId();
        }
    }

    @FXML
    void imgDeleteOnMouseEntered(MouseEvent event) {
        imgDelete.setImage(new Image("/assets/icon/delete_red_row.png"));
    }

    @FXML
    void imgDeleteOnMouseExited(MouseEvent event) {
        imgDelete.setImage(new Image("/assets/icon/delete_default.png"));
    }

    @FXML
    void imgUpdateOnMouseClicked(MouseEvent event) {
        try {
            ProductListUpdateFormController.id = lblProductId.getText();
            Navigation.popupPane("ProductUpdateForm.fxml");
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

    public void setData(String id) {
        try {
            ProductDto data = productBO.getProductData( id );

            lblProductId.setText(data.getProduct_Code());
            lblDescription.setText(data.getDescription());
            lblUnitPrice.setText(String.valueOf(data.getUnit_Price()));

        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

}
