package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.util.Map;

public class MaterialStockShortcutFormController {

    @FXML
    private ImageView imgAddOrder;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    @FXML
    void imgAddOrderOnMouseClicked(MouseEvent event) {
        try {
            Navigation.popupPane("SupplierOrderAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgAddOrderOnMouseEntered(MouseEvent event) {
        imgAddOrder.setImage(new Image("/assets/icon/shortchut_add_btn_gree.png"));
    }

    @FXML
    void imgAddOrderOnMouseExited(MouseEvent event) {
        imgAddOrder.setImage(new Image("/assets/icon/shortcut_add_btn.png"));
    }

    public void setData(Map.Entry<String, Integer> entry) {
        lblDesc.setText(entry.getKey());
        lblQty.setText(String.valueOf(entry.getValue()));
    }

}
