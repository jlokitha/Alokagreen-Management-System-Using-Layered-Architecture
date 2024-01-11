package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.CustomerBO;
import lk.ijse.alokagreen.bo.custom.impl.CustomerBOImpl;
import lk.ijse.alokagreen.dto.CustomerDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Style;

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
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder2 ( btnCancel );
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
