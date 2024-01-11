package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SpoiledReportBO;
import lk.ijse.alokagreen.bo.custom.impl.SpoiledReportBOImpl;
import lk.ijse.alokagreen.dto.SpoiledReportDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SpoiledReportUpdateFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnUpdate;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXComboBox<String> cmbDesc;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    public static String id;

    private final SpoiledReportBO spoiledReportBO = (SpoiledReportBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SPOILED );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if ( validateReport() ) {
            try {
                boolean isSaved = spoiledReportBO.updateSpoiledReport ( id, txtId.getText ( ), txtQty.getText ( ) );

                if (isSaved) {
                    Navigation.closePane();
                    SpoiledReportManageFormController.controller.getAllId();
                }
            } catch ( SQLException e ) {
                e.printStackTrace ();
            }
        }

    }

    @FXML
    void cmbProductDescOnAction(ActionEvent event) {
        try {
            lblDesc.setText(null);
            String idOfDesc = spoiledReportBO.getProductId ( cmbDesc.getSelectionModel ( ).getSelectedItem ( ) );
            txtId.setText(idOfDesc);
            txtQty.requestFocus();
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @FXML
    void txtQtyOnMouseClicked(MouseEvent event) {
        lblQty.setText(null);
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        lblQty.setText(null);

        String qty = txtQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
        } else {
            btnUpdateOnAction(event);
        }
    }

    @FXML
    void cmbDescOnMouseClicked(MouseEvent event) {
        lblDesc.setText(null);
    }


    public boolean validateReport() {

        if (cmbDesc.getSelectionModel().getSelectedItem() == null) {
            lblDesc.setText("Please select a product");
            return false;
        }

        String qty = txtQty.getText();

        if (Regex.qty(qty)) {
            lblQty.setText("Please enter valid quantity");
            return false;
        }

        return true;
    }

    private void setData() {
        try {
            SpoiledReportDto data = spoiledReportBO.getSpoiledReportDetails (id);
            String desc = spoiledReportBO.getProductDesc ( data.getProduct_Code ( ) );

            cmbDesc.setValue(desc);
            txtId.setText(data.getProduct_Code());
            txtQty.setText(String.valueOf(data.getSpoiled_Qty()));
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    public void setCmb() {
        try {
            cmbDesc.getItems().addAll(spoiledReportBO.getAllProductDesc());
            cmbDesc.setStyle("-fx-font-size: 16;");
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCmb();
        setData();
    }

    @FXML
    public void btnUpdateOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEntered ( btnUpdate );
    }

    @FXML
    public void btnUpdateOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExited ( btnUpdate );
    }

    @FXML
    public void btnCancelOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    public void btnCancelOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }
}
