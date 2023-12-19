package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.lokitha.alokagreen.dto.tm.SupplierOrderTm;
import lk.lokitha.alokagreen.model.SupplierModel;
import lk.lokitha.alokagreen.model.SupplierOrderDetailModel;
import lk.lokitha.alokagreen.model.SupplierOrderModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierOrderViewFormController implements Initializable {

    @FXML
    private VBox vBox;

    @FXML
    private Label labelTotal;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lblSupName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderTime;

    @FXML
    private JFXButton btnCancel;

    public static String id;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    public void getProduct(ArrayList<String> list) {

        vBox.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierOrderViewFormController.class.getResource("/view/SupplierOrderViewTableRowForm.fxml"));
            Parent root = loader.load();
            SupplierOrderViewTableRowFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData() {
        SupplierOrderTm dto = SupplierOrderModel.getData(id);
        String name = SupplierModel.getNameOfId(dto.getSupplier_Id());
        ArrayList<String> data = SupplierOrderDetailModel.getData(dto.getSupplier_Order_Id());

        lblOrderId.setText(dto.getSupplier_Order_Id());
        lblSupId.setText(dto.getSupplier_Id());
        lblSupName.setText(name);
        lblOrderDate.setText(dto.getDate());
        lblOrderTime.setText(dto.getTime());
        labelTotal.setText(String.valueOf(dto.getTotal_Amount()));

        getProduct(data);
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
