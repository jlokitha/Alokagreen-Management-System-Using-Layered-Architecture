package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.EmployeeBO;
import lk.ijse.alokagreen.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Style;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeManageFormController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private ImageView imgDelete;

    @FXML
    private Label lblDelete;

    public static ArrayList<String> ids;


    public static EmployeeManageFormController controller;

    private final EmployeeBO employeeBO = (EmployeeBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.EMPLOYEE );

    public EmployeeManageFormController() {
        controller = this;
        ids = new ArrayList<>();
    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {
        try {
            Navigation.popupPane("EmployeeAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if ( ! ids.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete Employee");
            alert.setContentText("Are you sure you want to delete this employees ?");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if ( result == ButtonType.OK ) {
                for (String id : ids) {
                    try {
                        employeeBO.deleteEmployee( id );
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                EmployeeManageFormController.controller.getAllId();
                ids = new ArrayList<>();
            }
        }
    }

    @FXML
    void btnEmployeeAttendanceOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("EmployeeAttendanceManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSalaryManageOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("EmployeeSalaryManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllId() {

        ArrayList<String> list = null;
        try {
            list = employeeBO.getAllId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        vbox.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeManageFormController.class.getResource("/view/EmployeeManageRowForm.fxml"));
            Parent root = loader.load();
            EmployeeManageRowFormController controller = loader.getController();
            controller.setData(id);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnMouseEntered(MouseEvent event) {
        Style.btnDeleteOnMouseEntered ( btnDelete, lblDelete, imgDelete );
    }

    @FXML
    void btnDeleteOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnDelete, lblDelete, imgDelete );
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnAdd, lblAdd, imgAdd );
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnAdd, lblAdd, imgAdd );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
    }
}
