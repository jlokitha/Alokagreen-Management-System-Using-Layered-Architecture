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
import lk.lokitha.alokagreen.bo.custom.SupplierBO;
import lk.lokitha.alokagreen.bo.custom.impl.SupplierBOImpl;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierManageFormController implements Initializable {

    @FXML
    private VBox vBox;
    @FXML
    private JFXButton btnAdd;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblAdd;

    public static SupplierManageFormController controller;

    private final SupplierBO supplierBO = (SupplierBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.SUPPLIER );

    public SupplierManageFormController() {
        controller = this;
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        try {
            Navigation.popupPane("SupplierAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllId() {
        try {
            ArrayList<String> list = supplierBO.getAllSupplierIds();

            vBox.getChildren().clear();

            for (int i = 0; i < list.size(); i++) {
                loadDataTable(list.get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierManageFormController.class.getResource("/view/SupplierManageTableRowForm.fxml"));
            Parent root = loader.load();
            SupplierManageTableRowFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
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
