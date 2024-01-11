package lk.ijse.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SupplierBO;
import lk.ijse.alokagreen.bo.custom.impl.SupplierBOImpl;
import lk.ijse.alokagreen.dto.SupplierDto;
import lk.ijse.alokagreen.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class SupplierManageTableRowFormController {

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblEmail;

    @FXML
    private ImageView imgView;

    @FXML
    private ImageView imgUpdate;

    @FXML
    private ImageView imgDelete;

    private final SupplierBO supplierBO = (SupplierBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.SUPPLIER );

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Supplier");
        alert.setContentText("Are you sure you want to delete this supplier?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                supplierBO.deleteSupplier( lblId.getText() );
            } catch (SQLException e) {
                throw new RuntimeException( e );
            }
            SupplierManageFormController.controller.getAllId();
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
            SupplierUpdateFormController.id = lblId.getText();
            Navigation.popupPane("SupplierUpdateForm.fxml");
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

    @FXML
    void imgViewOnMouseClicked(MouseEvent event) {
        try {
            SupplierViewFormController.id = lblId.getText();
            Navigation.popupPane("SupplierViewForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgViewOnMouseEntered(MouseEvent event) {
        imgView.setImage(new Image("/assets/icon/view_blue.png"));
    }

    @FXML
    void imgViewOnMouseExited(MouseEvent event) {
        imgView.setImage(new Image("/assets/icon/view_default.png"));
    }

    public void setData(String id) {
        try {
            SupplierDto data = supplierBO.getSupplierData( id );

            lblId.setText(data.getSupplier_Id());
            lblName.setText(data.getCompany_Name());
            lblEmail.setText(data.getCompany_Email());
            lblMobile.setText(data.getCompany_Mobile());

        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

}
