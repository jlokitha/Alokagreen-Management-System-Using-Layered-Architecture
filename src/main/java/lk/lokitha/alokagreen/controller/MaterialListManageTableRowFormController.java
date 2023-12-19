package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.MaterialDto;
import lk.lokitha.alokagreen.model.MaterialModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;

public class MaterialListManageTableRowFormController {

    @FXML
    private Label lblMaterialId;

    @FXML
    private Label lblDescription;

    @FXML
    private ImageView imgUpdate;

    @FXML
    private ImageView imgDelete;

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Material");
        alert.setContentText("Are you sure you want to delete this material?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            MaterialModel.deleteMaterial(lblMaterialId.getText());
            MaterialListManageFormController.controller.getAllId();
        }
    }

    @FXML
    void imgDeleteOnMouseEntered(MouseEvent event) {
        imgDelete.setImage(new Image("/assets/icon/delete_red_row.png"));
    }

    @FXML
    void imgDeleteOnMouseExited(MouseEvent event) {
        imgDelete.setImage(new Image("/assets/icon/delete_default.png"));
    }

    @FXML
    void imgUpdateOnMouseClicked(MouseEvent event) {
        try {
            MaterialUpdateFormController.id = lblMaterialId.getText();
            Navigation.popupPane("MaterialUpdateForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgUpdateOnMouseEntered(MouseEvent event) {
        imgUpdate.setImage(new Image("/assets/icon/update_green.png"));
    }

    @FXML
    void imgUpdateOnMouseExited(MouseEvent event) {
        imgUpdate.setImage(new Image("/assets/icon/update.png"));
    }

    public void setData(String id) {

        MaterialDto data = MaterialModel.getData(id);

        lblMaterialId.setText(data.getMaterial_Code());
        lblDescription.setText(data.getDescription());

    }

}
