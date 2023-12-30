package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.MaterialBO;
import lk.lokitha.alokagreen.bo.custom.impl.MaterialBOImpl;
import lk.lokitha.alokagreen.dto.MaterialDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MaterialListUpdateFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnUpdate;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private Label lblMaterialId;

    @FXML
    private Label lblDesc;

    public static String id;

    private final MaterialBO materialBO = (MaterialBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.MATERIAL );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if ( validateMaterial() ) {
            try {
                boolean isSaved = materialBO.updateMaterial( new MaterialDto(
                        lblMaterialId.getText(),
                        txtDescription.getText()
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    MaterialListManageFormController.controller.getAllId();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void txtDescOnMouseClicked(MouseEvent event) {
        lblDesc.setText(null);
    }

    public boolean validateMaterial() {
        String desc = txtDescription.getText();

        if (Regex.description(desc)) {
            lblDesc.setText("First letter should be capital and should only contain letters");
            return false;
        }

        return true;
    }

    private void setData(MaterialDto materialDto) {
        lblMaterialId.setText(materialDto.getMaterial_Code());
        txtDescription.setText(materialDto.getDescription());
    }

    public void txtDescOnAction(ActionEvent event) {
        lblDesc.setText(null);

        String desc = txtDescription.getText();

        if (Regex.description(desc)) {
            lblDesc.setText("First letter should be capital and should only contain letters");
        } else {
            btnUpdateOnAction(event);
        }
    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        btnUpdate.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        btnUpdate.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
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
        try {
            setData(materialBO.getMaterialData( id ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
