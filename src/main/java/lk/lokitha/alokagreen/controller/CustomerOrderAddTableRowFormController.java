package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.model.ProductStockModel;

import java.util.Map;

public class CustomerOrderAddTableRowFormController {

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblTotal;

    @FXML
    private ImageView imgDelete;

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Map<String, String> items = CustomerOrderAddFormController.getInstance().dto.getItems();

        String itemIdToUpdate = lblItemId.getText();
        String qtyToRemove = lblQty.getText();

        if (items.containsKey(itemIdToUpdate)) {

            int currentQty = Integer.parseInt(items.get(itemIdToUpdate));

            int qtyToRemoveValue = Integer.parseInt(qtyToRemove);

            int updatedQty = currentQty - qtyToRemoveValue;

            items.put(itemIdToUpdate, String.valueOf(updatedQty));

            if (updatedQty <= 0) {
                items.remove(itemIdToUpdate);
            }

            CustomerOrderAddFormController.getInstance().getProduct();

            double total = Double.parseDouble(CustomerOrderAddFormController.getInstance().labelTotal.getText());
            double itemTot = Double.parseDouble(qtyToRemove) * Double.parseDouble(lblUnitPrice.getText());

            CustomerOrderAddFormController.getInstance().labelTotal.setText(String.valueOf(total - itemTot));
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

    public void setData(String[] detail) {

        String id = ProductStockModel.getProductId(detail[0]);
        String[] data = ProductModel.getDescUnitPriceOfId(id);

        lblItemId.setText(detail[0]);
        lblDesc.setText(data[1]);
        lblQty.setText(detail[1]);
        lblUnitPrice.setText(data[1]);
        
        int qty = Integer.parseInt(detail[1]);
        double unitPrice = Double.parseDouble(data[1]);

        lblTotal.setText(String.valueOf(qty * unitPrice));

    }

}
