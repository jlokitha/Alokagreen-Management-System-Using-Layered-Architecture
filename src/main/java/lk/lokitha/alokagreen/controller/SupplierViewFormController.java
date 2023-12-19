package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.SupplierDto;
import lk.lokitha.alokagreen.model.SupplierModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierViewFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    public static String id;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SupplierDto data = SupplierModel.getData(id);

        lblSupId.setText(data.getSupplier_Id());
        lblName.setText(data.getCompany_Name());
        lblMobile.setText(data.getCompany_Mobile());
        lblEmail.setText(data.getCompany_Email());
        lblAddress.setText(data.getCompany_Location());
    }
}
