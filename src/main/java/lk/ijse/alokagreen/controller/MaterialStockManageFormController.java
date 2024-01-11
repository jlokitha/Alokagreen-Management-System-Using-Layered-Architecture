package lk.ijse.alokagreen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.MaterialStockBO;
import lk.ijse.alokagreen.bo.custom.impl.MaterialStockBOImpl;
import lk.ijse.alokagreen.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MaterialStockManageFormController implements Initializable {

    @FXML
    private VBox vbox;

    public static MaterialStockManageFormController controller;

    public static ArrayList<String> expList;

    private final MaterialStockBO materialStockBO = (MaterialStockBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.MATERIAL_STOCK );

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
        try {
            ArrayList<String> list = materialStockBO.getAllMaterialStockIds( );

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
            FXMLLoader loader = new FXMLLoader(MaterialListManageFormController.class.getResource("/view/MaterialStockManageTableRowForm.fxml"));
            Parent root = loader.load();
            MaterialStockManageTableRowFormController controller = loader.getController();
            controller.setData(id);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMaterialStockStatus() {
        try {
            materialStockBO.updateMaterialStockStatus(expList);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
        updateMaterialStockStatus();
    }
}
