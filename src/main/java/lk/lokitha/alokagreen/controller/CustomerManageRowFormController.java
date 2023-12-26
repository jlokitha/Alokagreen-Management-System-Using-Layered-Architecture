package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.CustomerBO;
import lk.lokitha.alokagreen.bo.custom.impl.CustomerBOImpl;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.model.CustomerModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerManageRowFormController {

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

    private final CustomerBO customerBO = (CustomerBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.CUSTOMER );

    @FXML
    void imgViewOnMouseClicked(MouseEvent event) {
        try {
            CustomerViewFormController.id = lblId.getText();
            Navigation.popupPane("CustomerViewForm.fxml");
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

    @FXML
    void imgDeleteOnMouseClicked(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Customer");
        alert.setContentText("Are you sure you want to delete this customer?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                customerBO.deleteCustomer( lblId.getText() );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            CustomerManageFormController.controller.getAllId();
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
            CustomerUpdateFormController.id = lblId.getText();
            Navigation.popupPane("CustomerUpdateForm.fxml");
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
            CustomerDto data = customerBO.getCustomerData( id );

            lblId.setText(data.getCustomer_Id());
            lblName.setText(data.getName());
            lblEmail.setText(data.getEmail());
            lblMobile.setText(data.getMobile());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
