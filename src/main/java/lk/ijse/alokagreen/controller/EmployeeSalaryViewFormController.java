package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.SalaryBO;
import lk.ijse.alokagreen.bo.custom.impl.SalaryBOImpl;
import lk.ijse.alokagreen.dto.EmployeeSalaryDto;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeSalaryViewFormController implements Initializable {

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblEmpName;

    @FXML
    private Label lblWorkedDays;

    @FXML
    private Label lblBonus;

    @FXML
    private Label lblBase;

    @FXML
    private Label lblTotal;

    @FXML
    private JFXButton btnCancel;

    public static String id;

    private final SalaryBO salaryBO = (SalaryBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.SALARY );

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
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            EmployeeSalaryDto data = salaryBO.getSalaryData( id );
            String name = salaryBO.getEmployeeName(data.getEmployee_Id());

            double base = data.getTotal_Salary() - data.getBonus();

            lblEmpId.setText(data.getEmployee_Id());
            lblEmpName.setText(name);
            lblWorkedDays.setText(String.valueOf(data.getWorked_Days()));
            lblBonus.setText(String.valueOf(data.getBonus()));
            lblBase.setText(String.valueOf(base));
            lblTotal.setText(String.valueOf(data.getTotal_Salary()));

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }
}
