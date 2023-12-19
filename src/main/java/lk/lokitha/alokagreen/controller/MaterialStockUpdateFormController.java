package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.MaterialStockDto;
import lk.lokitha.alokagreen.model.MaterialModel;
import lk.lokitha.alokagreen.model.MaterialStockModel;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.util.ResourceBundle;

public class MaterialStockUpdateFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnUpdate;

    @FXML
    private JFXTextField txtMDesc;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUsedQty;

    @FXML
    private JFXTextField txtMId;

    @FXML
    private JFXTextField txtExpDate;

    @FXML
    private Label lblUsedQty;

    public static String id;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if ( validateQty() ) {
            double current = Double.parseDouble(txtQtyOnHand.getText());
            int used = Integer.parseInt(txtUsedQty.getText());

            if ( used <= current ) {
                if (txtUsedQty.getText().isEmpty()) {
                    txtUsedQty.setText("0");
                }

                boolean isSaved = MaterialStockModel.updateMaterialStock(id, txtUsedQty.getText());

                if (isSaved) {
                    Navigation.closePane();
                    MaterialStockManageFormController.controller.getAllId();
                }
            } else {
                lblUsedQty.setText("Used quantity exceed the quantity on hand");
            }
        }
    }

    private void setData(MaterialStockDto materialStockDto) {
        txtMId.setText(materialStockDto.getMaterial_Code());
        txtMDesc.setText(MaterialModel.getDescOfId(txtMId.getText()));
        txtQtyOnHand.setText(String.valueOf(materialStockDto.getQty_On_Hand()));
        txtExpDate.setText(materialStockDto.getExp_Date());
    }

    @FXML
    void txtUsedQtyOnMouseClicked(MouseEvent event) {
        lblUsedQty.setText(null);
    }

    public boolean validateQty() {
        String qty = txtUsedQty.getText();

        if (Regex.qty(qty)) {
            lblUsedQty.setText("Please enter valid quantity");
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MaterialStockDto mSDto = MaterialStockModel.getDetail(id);
        setData(mSDto);
        txtUsedQty.requestFocus();
    }

    @FXML
    public void btnUpdateOnMouseEntered(MouseEvent mouseEvent) {
        btnUpdate.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnUpdateOnMouseExited(MouseEvent mouseEvent) {
        btnUpdate.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    public void btnCancelOnMouseEntered(MouseEvent mouseEvent) {
        btnCancel.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    public void btnCancelOnMouseExited(MouseEvent mouseEvent) {
        btnCancel.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }

    @FXML
    public void txtUsedQtyOnAction(ActionEvent event) {
        lblUsedQty.setText(null);
        String qty = txtUsedQty.getText();
        int currentQty = Integer.parseInt(txtQtyOnHand.getText());
        int used = Integer.parseInt(txtUsedQty.getText());

        if (Regex.qty(qty)) {
            lblUsedQty.setText("Please enter valid quantity");
        } else if (!(used <= currentQty)) {
            lblUsedQty.setText("Used quantity cannot exceed net quantity");
        } else {
            btnUpdateOnAction(event);
        }
    }
}
