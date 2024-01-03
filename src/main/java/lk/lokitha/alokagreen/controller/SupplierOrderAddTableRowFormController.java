package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SupplierOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.SupplierOrderBOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SupplierOrderAddTableRowFormController {

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

    private String expDate;

    private final SupplierOrderBO supplierOrderBO = (SupplierOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SUPPLIER_ORDER );

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {
        String[] remove = {null, lblItemId.getText(), lblQty.getText(), lblUnitPrice.getText(), expDate};
        ArrayList<String[]> items = SupplierOrderAddFormController.getInstance().supplierOrderDto.getItems();

        boolean removed = removeElement(items, remove);

        if (removed) {
            SupplierOrderAddFormController.getInstance().getAllMaterial();
            double total = Double.parseDouble(SupplierOrderAddFormController.getInstance().labelTotal.getText());
            double itemTot = Double.parseDouble(lblQty.getText()) * Double.parseDouble(lblUnitPrice.getText());

            SupplierOrderAddFormController.getInstance().labelTotal.setText(String.valueOf(total - itemTot));
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

    private static boolean removeElement(List<String[]> list, String[] target) {
        return list.removeIf(element -> Arrays.equals(element, target));
    }

    public void setData(String[] detail) {
        try {
            String desc = supplierOrderBO.getMaterialDeskOfId ( detail[1] );

            lblItemId.setText(detail[1]);
            lblDesc.setText(desc);
            lblQty.setText(detail[2]);
            lblUnitPrice.setText(detail[3]);

            int qty = Integer.parseInt(detail[2]);
            double unitPrice = Double.parseDouble(detail[3]);

            lblTotal.setText(String.valueOf(qty * unitPrice));

            expDate = detail[4];

        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }
}
