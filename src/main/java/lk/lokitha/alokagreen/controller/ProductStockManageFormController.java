package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.ProductStockBO;
import lk.lokitha.alokagreen.bo.custom.impl.ProductStockBOImpl;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Style;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductStockManageFormController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnAdd;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblAdd;

    public static ProductStockManageFormController controller;

    public static ArrayList<String> expList;

    private final ProductStockBO productStockBO = (ProductStockBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PRODUCT_STOCK );

    public ProductStockManageFormController() {
        controller = this;
        expList = new ArrayList<>();
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        try {
            Navigation.popupPane("ProductStockAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnMaterialStockOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("MaterialStockManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSpoiledReportOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("SpoiledReportManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllId() {
        try {
            ArrayList<String> list = productStockBO.getAllProductStockIds( );

            vbox.getChildren().clear();

            for (int i = 0; i < list.size(); i++) {
                loadDataTable(list.get(i));
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(ProductStockManageFormController.class.getResource("/view/ProductStockManageTableRowForm.fxml"));
            Parent root = loader.load();
            ProductStockManageTableRowFormController controller = loader.getController();
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

    public void updateProductStockExp() {
        try {
            productStockBO.updateProductStockExp(expList);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
        updateProductStockExp();
    }
}
