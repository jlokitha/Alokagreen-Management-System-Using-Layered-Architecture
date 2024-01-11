package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.MaterialStockBO;
import lk.lokitha.alokagreen.bo.custom.impl.MaterialStockBOImpl;
import lk.lokitha.alokagreen.dto.MaterialStockDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
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

    private final MaterialStockBO materialStockBO = (MaterialStockBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.MATERIAL_STOCK );

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

                try {
                    boolean isSaved = materialStockBO.updateMaterialStockQty( id, txtUsedQty.getText( ) );

                    if (isSaved) {
                        Navigation.closePane();
                        MaterialStockManageFormController.controller.getAllId();
                    }

                } catch ( SQLException e ) {
                    e.printStackTrace();
                }

            } else {
                lblUsedQty.setText("Used quantity exceed the quantity on hand");
            }
        }
    }

    private void setData(MaterialStockDto materialStockDto) {
        try {
            txtMId.setText(materialStockDto.getMaterial_Code());
            txtMDesc.setText(materialStockBO.getMaterialDescription(txtMId.getText()));
            txtQtyOnHand.setText(String.valueOf(materialStockDto.getQty_On_Hand()));
            txtExpDate.setText(materialStockDto.getExp_Date());

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
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

        try {
            MaterialStockDto mSDto = materialStockBO.getMaterialStockDetail( id );
            setData(mSDto);
            txtUsedQty.requestFocus();

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
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
