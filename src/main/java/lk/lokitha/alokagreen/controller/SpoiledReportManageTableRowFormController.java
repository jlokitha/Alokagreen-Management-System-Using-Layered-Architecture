package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.SpoiledReportBO;
import lk.lokitha.alokagreen.bo.custom.impl.SpoiledReportBOImpl;
import lk.lokitha.alokagreen.dto.SpoiledReportDto;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class SpoiledReportManageTableRowFormController {

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private ImageView imgUpdate;

    private String id;

    private final SpoiledReportBO spoiledReportBO = (SpoiledReportBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.SPOILED );

    @FXML
    void imgUpdateOnMouseClicked(MouseEvent event) {
        try {
            SpoiledReportUpdateFormController.id = id;
            Navigation.popupPane("SpoiledReportUpdateForm.fxml");
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
            SpoiledReportDto data = spoiledReportBO.getSpoiledReportDetails ( id );
            String desc = spoiledReportBO.getProductDesc (data.getProduct_Code());

            this.id = data.getReport_Id();
            lblItemId.setText(data.getProduct_Code());
            lblDesc.setText(desc);
            lblQty.setText(String.valueOf(data.getSpoiled_Qty()));
            lblDate.setText(data.getDate());
            lblTime.setText(data.getTime());
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }

    }

}
