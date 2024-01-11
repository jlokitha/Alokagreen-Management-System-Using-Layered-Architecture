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
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.CustomerOrderBO;
import lk.lokitha.alokagreen.bo.custom.impl.CustomerOrderBOImpl;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Style;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    private final CustomerOrderBO customerOrderBO = (CustomerOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.CUSTOMER_ORDER );

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
        try {
            CustomerOrderDto dto = customerOrderBO.getCustomerOrderDetails ( id );
            String name = customerOrderBO.getCustomerNameOfId (dto.getCustomer_Id());
            Map<String, String> data = customerOrderBO.getCustomerOrderDetailsData (dto.getCustomer_Order_Id());

            lblOrderId.setText(dto.getCustomer_Order_Id());
            lblCustId.setText(dto.getCustomer_Id());
            lblCustName.setText(name);
            lblOrderDate.setText(dto.getDate());
            lblOrderTme.setText(dto.getTime());
            labelTotal.setText(String.valueOf(dto.getTotal_Amount()));

            getProduct(data);
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMoseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder2 ( btnCancel );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
