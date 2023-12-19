package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.MaterialDto;
import lk.lokitha.alokagreen.model.MaterialModel;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.Regex;

import java.net.URL;
import java.util.ResourceBundle;

public class MaterialAddFormController implements Initializable {

    @FXML
    public JFXButton btnCancel;

    @FXML
    public JFXButton btnAdd;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private Label lblMaterialId;

    @FXML
    private Label lblDesc;


    @FXML
    void btnAddOnAction(ActionEvent event) {

        if (validateMaterial()) {
            MaterialDto materialDto = new MaterialDto();

            materialDto.setMaterial_Code(lblMaterialId.getText());
            materialDto.setDescription(txtDescription.getText());

            boolean isSaved = MaterialModel.saveMaterial(materialDto);

            if (isSaved) {
                Navigation.closePane();
                MaterialListManageFormController.controller.getAllId();
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

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblMaterialId.setText(NewId.newMaterialCode());
    }

    @FXML
    public void txtDescOnAction(ActionEvent event) {
        lblDesc.setText(null);

        String desc = txtDescription.getText();

        if (Regex.description(desc)) {
            lblDesc.setText("First letter should be capital and should only contain letters");
        } else {
            btnAddOnAction(event);
        }
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        btnAdd.setStyle(
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
}
