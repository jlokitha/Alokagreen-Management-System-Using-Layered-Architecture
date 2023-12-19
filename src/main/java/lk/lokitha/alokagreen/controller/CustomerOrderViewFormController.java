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
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.model.CustomerModel;
import lk.lokitha.alokagreen.model.CustomerOrderDetailModel;
import lk.lokitha.alokagreen.model.CustomerOrderModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class CustomerOrderViewFormController implements Initializable {

    @FXML
    private VBox vBox;

    @FXML
    private Label labelTotal;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblCustId;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderTme;

    @FXML
    private JFXButton btnCancel;

    public static String id;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    public void getProduct(Map<String, String> list) {

        vBox.getChildren().clear();

        for (Map.Entry<String, String> entry : list.entrySet()) {
            String[] detail = new String[2];

            detail[0] = entry.getKey();
            detail[1] = String.valueOf(entry.getValue());

            loadDataTable(detail);
        }
    }

    private void loadDataTable(String[] detail) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderViewFormController.class.getResource("/view/CustomerOrderViewTableRowForm.fxml"));
            Parent root = loader.load();
            CustomerOrderViewTableRowFormController controller = loader.getController();
            controller.setData(detail);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData() {
        CustomerOrderDto dto = CustomerOrderModel.getData(id);
        String name = CustomerModel.getNameOfId(dto.getCustomer_Id());
        Map<String, String> data = CustomerOrderDetailModel.getData(dto.getCustomer_Order_Id());

        lblOrderId.setText(dto.getCustomer_Order_Id());
        lblCustId.setText(dto.getCustomer_Id());
        lblCustName.setText(name);
        lblOrderDate.setText(dto.getDate());
        lblOrderTme.setText(dto.getTime());
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
