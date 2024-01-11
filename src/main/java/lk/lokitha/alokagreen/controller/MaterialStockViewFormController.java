package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.lokitha.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MaterialStockViewFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblStockId;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lblSupName;

    @FXML
    private Label lblMId;

    @FXML
    private Label lblMDesc;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblBoughtQty;

    @FXML
    private Label lblRemaining;

    @FXML
    private Label lblExpDate;

    @FXML
    private Label lblBoughtDate;

    public static String id;

    private final MaterialStockBO materialStockBO = (MaterialStockBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.MATERIAL_STOCK );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MaterialStockDto data = materialStockBO.getMaterialStockDetail( id );
            String desc = materialStockBO.getMaterialDescription(data.getMaterial_Code());
            String orderId = materialStockBO.getSupplierOrderId(data.getStock_Id());
            String supplierId = materialStockBO.getSupplierId(orderId);
            String name = materialStockBO.getSupplierName(supplierId);

            if (data.getStatus().equals("Expired")) {
                lblStatus.setText(data.getStatus());
                lblStatus.setStyle("-fx-text-fill: #FF0E0E;");
            } else {
                lblStatus.setText(data.getStatus());
                lblStatus.setStyle("-fx-text-fill: #139547;");
            }

            lblStockId.setText(data.getStock_Id());
            lblSupId.setText(supplierId);
            lblSupName.setText(name);
            lblMId.setText(data.getMaterial_Code());
            lblMDesc.setText(desc);
            lblUnitPrice.setText(String.valueOf(data.getUnit_Price()));
            lblBoughtQty.setText(String.valueOf(data.getQty()));
            lblRemaining.setText(String.valueOf(data.getQty_On_Hand()));
            lblBoughtDate.setText(data.getDate());
            lblExpDate.setText(data.getExp_Date());
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
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
