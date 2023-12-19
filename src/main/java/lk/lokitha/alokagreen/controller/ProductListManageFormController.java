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
import lk.lokitha.alokagreen.model.ProductModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductListManageFormController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnAdd;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblAdd;

    public static ProductListManageFormController controller;

    public ProductListManageFormController() {
        controller =this;
    }

    @FXML
    void btnAddProductOnAction(ActionEvent event) {
        try {
            Navigation.popupPane("ProductAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnMaterialListOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("MaterialListManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllId() {

        ArrayList<String> list = ProductModel.getAllId();

        vbox.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(ProductListManageFormController.class.getResource("/view/ProductListManageTableRowForm.fxml"));
            Parent root = loader.load();
            ProductListManageTableRowFormController controller = loader.getController();
            controller.setData(id);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-radius: 20px;");
        lblAdd.setStyle(
                "-fx-text-fill:  #139547;");
        imgAdd.setImage(new Image("/assets/icon/add_green.png"));
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-radius: 20px;");
        lblAdd.setStyle(
                "-fx-text-fill:  #727374;");
        imgAdd.setImage(new Image("/assets/icon/add.png"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
    }
}
