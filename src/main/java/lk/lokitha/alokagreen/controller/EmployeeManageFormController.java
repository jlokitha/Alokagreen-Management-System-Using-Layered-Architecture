package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.EmployeeBO;
import lk.lokitha.alokagreen.bo.custom.impl.EmployeeBOImpl;
import lk.lokitha.alokagreen.model.EmployeeModel;
import lk.lokitha.alokagreen.util.Navigation;

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
        btnDelete.setStyle(
                "-fx-background-color: #FFE0E0;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #FD3535;" +
                        "-fx-border-radius: 20px;");
        lblDelete.setStyle(
                "-fx-text-fill:  #FD3535;");
        imgDelete.setImage(new Image("/assets/icon/delete_red.png"));
    }

    @FXML
    void btnDeleteOnMouseExited(MouseEvent event) {
        btnDelete.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-radius: 20px;");
        lblDelete.setStyle(
                "-fx-text-fill:  #727374;");
        imgDelete.setImage(new Image("/assets/icon/delete.png"));
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-radius: 20px;");
        lblAdd.setStyle(
                "-fx-text-fill:  #139547;");
        imgAdd.setImage(new Image("/assets/icon/add_green.png"));
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-radius: 20px;");
        lblAdd.setStyle(
                "-fx-text-fill:  #727374;");
        imgAdd.setImage(new Image("/assets/icon/add.png"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
    }
}
