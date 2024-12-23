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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SpoiledReportAddFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnAdd;

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

    private final SpoiledReportBO spoiledReportBO = (SpoiledReportBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SPOILED );

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if ( validateReport() ) {
            try {
                boolean isSaved = spoiledReportBO.saveSpoiledReport ( new SpoiledReportDto (
                        null,
                        txtId.getText ( ),
                        Integer.parseInt ( txtQty.getText ( ) ),
                        null,
                        null
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    SpoiledReportManageFormController.controller.getAllId();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void cmbProductDescOnAction(ActionEvent event) {
        try {
            lblDesc.setText(null);
            String id = spoiledReportBO.getProductId ( getDesc ( ) );
            txtId.setText(id);
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
            btnAddOnAction(event);
        }
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

    public String getDesc() {
        return String.valueOf(cmbDesc.getSelectionModel().getSelectedItem());
    }

    public void setDataInComboBox() {
        try {
            ArrayList<String> products = spoiledReportBO.getAllProductDesc ( );

            cmbDesc.getItems().addAll(products);
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbDesc.setStyle("-fx-font-size: 16;");
        setDataInComboBox();
    }

    public void cmbDescOnMouseClicked(MouseEvent mouseEvent) {
        lblDesc.setText(null);
    }

    @FXML
    public void btnCancelOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    public void btnCancelOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    @FXML
    public void btnAddOnMouseEntered(MouseEvent mouseEvent) {
        Style.btnOnMouseEntered ( btnAdd );
    }

    @FXML
    public void btnAddOnMouseExited(MouseEvent mouseEvent) {
        Style.btnOnMouseExited ( btnAdd );
    }
}
