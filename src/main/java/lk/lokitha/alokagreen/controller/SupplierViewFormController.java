package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SupplierBO;
import lk.lokitha.alokagreen.bo.custom.impl.SupplierBOImpl;
import lk.lokitha.alokagreen.dto.SupplierDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
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

    private final SupplierBO supplierBO = (SupplierBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.SUPPLIER );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SupplierDto  data = supplierBO.getSupplierData( id );

            lblSupId.setText(data.getSupplier_Id());
            lblName.setText(data.getCompany_Name());
            lblMobile.setText(data.getCompany_Mobile());
            lblEmail.setText(data.getCompany_Email());
            lblAddress.setText(data.getCompany_Location());
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }
}
