package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;

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

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure you want to delete this product?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            ProductModel.deleteProduct(lblProductId.getText());
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
            ProductUpdateFormController.id = lblProductId.getText();
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

        ProductDto data = ProductModel.getData(id);

        lblProductId.setText(data.getProduct_Code());
        lblDescription.setText(data.getDescription());
        lblUnitPrice.setText(String.valueOf(data.getUnit_Price()));

    }

}
