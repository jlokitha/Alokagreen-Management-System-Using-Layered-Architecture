package lk.ijse.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

public class ProductStockShortcutFormController {

    @FXML
    private Label lblPDesc;

    @FXML
    private Label lblQty;

    public void setData(Map.Entry<String, Integer> entry) {
        lblPDesc.setText(entry.getKey());
        lblQty.setText(String.valueOf(entry.getValue()));
    }

}
