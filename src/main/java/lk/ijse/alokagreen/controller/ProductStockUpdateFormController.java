package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.ProductStockBO;
import lk.ijse.alokagreen.bo.custom.impl.ProductStockBOImpl;
import lk.ijse.alokagreen.dto.ProductStockDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProductStockUpdateFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnUpdate;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtRemainedQty;

    @FXML
    private JFXTextField txtNetQty;

    @FXML
    private JFXComboBox<String> cmbDesc;

    @FXML
    private DatePicker datePickerExp;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblNetQty;

    @FXML
    private Label lblRemaining;

    @FXML
    private Label lblExpDate;

    public static String id;

    private final ProductStockBO productStockBO = (ProductStockBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.PRODUCT_STOCK );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if ( validateStock() ) {
            double net = Double.parseDouble(txtNetQty.getText());
            double remain = Double.parseDouble(txtRemainedQty.getText());

            if ( remain <= net ) {
                try {
                    boolean isSaved = productStockBO.updateProductStock(new ProductStockDto(
                            id,
                            txtId.getText(),
                            Integer.parseInt( txtRemainedQty.getText() ),
                            Integer.parseInt( txtNetQty.getText() ),
                            null,
                            String.valueOf( datePickerExp.getValue() ),
                            txtStatus.getText()
                    ));

                    if (isSaved) {
                        Navigation.closePane();
                        ProductStockManageFormController.controller.getAllId();
                    }

                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            } else {
                lblRemaining.setText("Remaining quantity can not exceed net quantity");
            }
        }

    }

    @FXML
    void cmbProductDescOnAction(ActionEvent event) {
        lblDesc.setText(null);

        if (cmbDesc.getSelectionModel().getSelectedItem().isEmpty()) {
            lblDesc.setText("Please select a product");

        } else {
            try {
                String id = productStockBO.getProductId(cmbDesc.getSelectionModel().getSelectedItem());
                txtId.setText(id);
                txtNetQty.requestFocus();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    private void setData(ProductStockDto productStockDto) {
        try {
            String desc = productStockBO.getProductDescription( productStockDto.getProduct_Code( ) );

            cmbDesc.setValue(desc);
            txtId.setText(productStockDto.getProduct_Code());
            txtRemainedQty.setText(String.valueOf(productStockDto.getQty_On_Hand()));
            txtNetQty.setText(String.valueOf(productStockDto.getQty()));
            datePickerExp.setValue(LocalDate.parse(productStockDto.getExp_Date()));
            txtStatus.setText(productStockDto.getStatus());

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbDescOnMouseCliked(MouseEvent event) {
        lblDesc.setText(null);
    }


    @FXML
    void txtNetOnMouseClicked(MouseEvent event) {
        lblNetQty.setText(null);
    }

    @FXML
    void txtNetOnAction(ActionEvent event) {
        lblNetQty.setText(null);
        String netQty = txtNetQty.getText();

        if (Regex.qty(netQty)) {
            lblNetQty.setText("Please enter valid quantity");
        } else {
            txtRemainedQty.requestFocus();
        }
    }

    @FXML
    void txtRemianingOnMouseClicked(MouseEvent event) {
        lblRemaining.setText(null);
    }

    @FXML
    void txtRemainingOnAction(ActionEvent event) {
        lblRemaining.setText(null);
        int remaining = Integer.parseInt(txtRemainedQty.getText());
        int net = Integer.parseInt(txtNetQty.getText());

        String remainedQty = txtRemainedQty.getText();

        if (Regex.qty(remainedQty)) {
            lblRemaining.setText("Please enter valid quantity");
        } else if (!(remaining <= net)) {
            lblRemaining.setText("Remaining quantity can not exceed net quantity");
        } else {
            datePickerExp.requestFocus();
        }
    }

    @FXML
    void DPOnMouseClicked(MouseEvent event) {
        lblExpDate.setText(null);
    }

    @FXML
    void dpExpDateOnAction(ActionEvent event) {
        lblExpDate.setText(null);

        LocalDate currentDate = LocalDate.now();
        LocalDate expDate = datePickerExp.getValue();
        if ( !currentDate.isEqual(expDate) || currentDate.isAfter(expDate) ) {
            txtStatus.setText("Not Expired");
        }

        if ( currentDate.isEqual(expDate) || currentDate.isAfter(expDate) ) {
            txtStatus.setText("Expired");
        }
    }

    public boolean validateStock() {

        if (cmbDesc.getSelectionModel().getSelectedItem() == null) {
            lblDesc.setText("Please select a product");
            return false;
        }

        String netQty = txtNetQty.getText();

        if (Regex.qty(netQty)) {
            lblNetQty.setText("Please enter valid quantity");
            return false;
        }

        String remainedQty = txtRemainedQty.getText();

        if (Regex.qty(remainedQty)) {
            lblNetQty.setText("Please enter valid quantity");
            return false;
        }

        if ( datePickerExp.getValue() == null ) {
            lblExpDate.setText("Please select an expiration date");
            return false;
        }

        return true;
    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnUpdate );
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnUpdate );
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    public void setProductDesc() {
        try {
            cmbDesc.getItems().addAll(productStockBO.getAllProductDescription());
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbDesc.setStyle("-fx-font-size: 16;");
        setData(productStockBO.getProductStockDetails( id ));
    }
}
