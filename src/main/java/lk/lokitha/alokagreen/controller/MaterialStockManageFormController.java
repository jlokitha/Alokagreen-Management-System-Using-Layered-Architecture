package lk.lokitha.alokagreen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import lk.lokitha.alokagreen.model.MaterialStockModel;
import lk.lokitha.alokagreen.util.Navigation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MaterialStockManageFormController implements Initializable {

    @FXML
    private VBox vbox;

    public static MaterialStockManageFormController controller;

    public static ArrayList<String> expList;

    public MaterialStockManageFormController() {
        controller = this;
        expList = new ArrayList<>();
    }

    @FXML
    void btnProductStockOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("ProductStockManageForm.fxml");
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

        ArrayList<String> list = MaterialStockModel.getAllId();

        vbox.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(MaterialListManageFormController.class.getResource("/view/MaterialStockManageTableRowForm.fxml"));
            Parent root = loader.load();
            MaterialStockManageTableRowFormController controller = loader.getController();
            controller.setData(id);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
        MaterialStockModel.UpdateMaterialStockExp(expList);
    }
}
