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
import lk.ijse.alokagreen.bo.custom.SupplierOrderBO;
import lk.ijse.alokagreen.bo.custom.impl.SupplierOrderBOImpl;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Style;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierOrderManageFormController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private ImageView imgAdd;
    @FXML
    private Label lblAdd;

    public static SupplierOrderManageFormController controller;

    private final SupplierOrderBO supplierOrderBO = (SupplierOrderBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SUPPLIER_ORDER );

    public SupplierOrderManageFormController() {
        controller = this;
    }

    @FXML
    void btnAddOrderOnAction(ActionEvent event) {
        try {
            Navigation.popupPane("SupplierOrderAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomerOrderOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("CustomerOrderManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllId() {
        try {
            ArrayList<String> list = supplierOrderBO.getAllSupplierOrderIds ( );

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
            FXMLLoader loader = new FXMLLoader(SupplierOrderManageFormController.class.getResource("/view/SupplierOrderManageTableRowForm.fxml"));
            Parent root = loader.load();
            SupplierOrderManageTableRowFormController controller = loader.getController();
            controller.setData(id);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SupplierOrderManageFormController getInstance() {
        return controller;
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
