package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.MaterialBO;
import lk.ijse.alokagreen.bo.custom.impl.MaterialBOImpl;
import lk.ijse.alokagreen.dto.MaterialDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Regex;
import lk.ijse.alokagreen.util.Style;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setData(materialBO.getMaterialData( id ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
