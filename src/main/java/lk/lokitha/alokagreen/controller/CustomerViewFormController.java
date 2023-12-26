package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.CustomerBO;
import lk.lokitha.alokagreen.bo.custom.impl.CustomerBOImpl;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.model.CustomerModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerViewFormController implements Initializable {

    @FXML
    private Label lblCustId;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblCustMobile;

    @FXML
    private Label lblCustEmail;

    @FXML
    private Label lblCustAddress;

    @FXML
    private JFXButton btnCancel;

    public static String id;

    private final CustomerBO customerBO = (CustomerBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.CUSTOMER );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
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
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerDto data = null;
        try {
            data = customerBO.getCustomerData( id );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (data != null) {
            lblCustId.setText(data.getCustomer_Id());
            lblCustName.setText(data.getName());
            lblCustMobile.setText(data.getMobile());
            lblCustEmail.setText(data.getEmail());
            lblCustAddress.setText(data.getAddress());
        }
    }
}
