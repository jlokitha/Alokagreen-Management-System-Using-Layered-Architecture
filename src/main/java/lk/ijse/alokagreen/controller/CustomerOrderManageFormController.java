package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.CustomerOrderBO;
import lk.ijse.alokagreen.bo.custom.impl.CustomerOrderBOImpl;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Style;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerOrderManageFormController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private ImageView imgAdd;
    @FXML
    private Label lblAdd;

    public static CustomerOrderManageFormController controller;

    private final CustomerOrderBO customerOrderBO = (CustomerOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.CUSTOMER_ORDER );

    public CustomerOrderManageFormController() {
        controller = this;
    }

    @FXML
    void btnAddOrderOnAction(ActionEvent event) {
        try {
            Navigation.popupPane("CustomerOrderAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierOrderOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("SupplierOrderManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllId() {
        try {
            ArrayList<String> list = customerOrderBO.getAllCustomerOrderIds ( );

            vbox.getChildren().clear();

            for (int i = 0; i < list.size(); i++) {
                loadDataTable(list.get(i));
            }
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderManageFormController.class.getResource("/view/CustomerOrderManageTableRowForm.fxml"));
            Parent root = loader.load();
            CustomerOrderManageTableRowFormController controller = loader.getController();
            controller.setData(id);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnAdd, lblAdd, imgAdd );
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnAdd, lblAdd, imgAdd );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
    }
}
