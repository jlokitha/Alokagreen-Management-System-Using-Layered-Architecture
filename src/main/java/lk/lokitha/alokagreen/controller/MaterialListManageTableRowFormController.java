package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.MaterialBO;
import lk.lokitha.alokagreen.bo.custom.impl.MaterialBOImpl;
import lk.lokitha.alokagreen.dto.MaterialDto;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class MaterialListManageTableRowFormController {

    @FXML
    private Label lblMaterialId;

    @FXML
    private Label lblDescription;

    @FXML
    private ImageView imgUpdate;

    @FXML
    private ImageView imgDelete;

    private final MaterialBO materialBO = (MaterialBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.MATERIAL );

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Material");
        alert.setContentText("Are you sure you want to delete this material?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                materialBO.deleteMaterial( lblMaterialId.getText() );
                MaterialListManageFormController.controller.getAllId();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            MaterialListUpdateFormController.id = lblMaterialId.getText();
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
        try {
            MaterialDto data = materialBO.getMaterialData( id );

            lblMaterialId.setText(data.getMaterial_Code());
            lblDescription.setText(data.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
