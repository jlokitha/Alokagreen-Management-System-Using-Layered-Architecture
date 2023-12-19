package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.tm.MaterialStockTm;
import lk.lokitha.alokagreen.model.MaterialStockModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class MaterialStockManageTableRowFormController {

    @FXML
    private Label lblId;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblExpDate;

    @FXML
    private ImageView imgView;

    @FXML
    private ImageView imgUpdate;

    @FXML
    void imgUpdateOnMouseClicked(MouseEvent event) {
        try {
            MaterialStockUpdateFormController.id = lblId.getText();
            Navigation.popupPane("MaterialStockUpdateForm.fxml");
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
            MaterialStockViewFormController.id = lblId.getText();
            Navigation.popupPane("MaterialStockViewForm.fxml");
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

    public void setStatus(String status) {
        lblExpDate.setAlignment(Pos.CENTER);

        if ( Objects.equals(status, "Not Expired") ) {
            lblExpDate.setStyle(
                    "-fx-background-color: #C7FFDE;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-text-fill: #139547");
        } else {
            lblExpDate.setStyle(
                    "-fx-background-color: #FFD3D3;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-text-fill: #FF0E0E");
        }
    }

    public void setData(String id) {

        MaterialStockTm data = MaterialStockModel.getData(id);

        LocalDate currentDate = LocalDate.now();
        LocalDate expDate = LocalDate.parse(data.getExp_Date());

        if ( currentDate.isEqual(expDate) || currentDate.isAfter(expDate) ) {
            MaterialStockManageFormController.expList.add(id);
        }

        lblId.setText(data.getStock_Id());
        lblDesc.setText(data.getDesc());
        lblQty.setText(data.getQty());
        lblExpDate.setText(data.getStatus());
        setStatus(data.getStatus());

    }

}
