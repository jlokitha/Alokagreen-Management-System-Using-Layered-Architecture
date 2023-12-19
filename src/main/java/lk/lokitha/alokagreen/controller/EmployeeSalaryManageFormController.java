package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lk.lokitha.alokagreen.model.EmployeeSalaryModel;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeSalaryManageFormController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblAdd;

    @FXML
    private JFXComboBox<String> cmbYear;

    @FXML
    private JFXComboBox<String> cmbMonth;

    @FXML
    private Label lblDate;

    @FXML
    private ImageView imgReset;

    @FXML
    private ImageView imgSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnReset;

    public static EmployeeSalaryManageFormController controller;

    public EmployeeSalaryManageFormController() {
        controller = this;
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
    void btnEmployeeManageOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("EmployeeManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSalaryAddOnAction(ActionEvent event) {
        try {
            Navigation.popupPane("EmployeeSalaryAddForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if ( validateSearch() ) {
            String year = cmbYear.getSelectionModel().getSelectedItem();
            String month = cmbMonth.getSelectionModel().getSelectedItem();
            String date = year + "-" + mapMonthToNumber(month) + "%";

            ArrayList<String> ids = EmployeeSalaryModel.getSalaryForMonth(date);

            if ( !ids.isEmpty() ) {
                vbox.getChildren().clear();

                for (String id : ids) {
                    loadDataTable(id);
                }
            }
            cmbYear.setValue(null);
            cmbMonth.setValue(null);
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        cmbYear.setValue(null);
        cmbMonth.setValue(null);
        lblDate.setText(null);
        getAllId();
    }

    @FXML
    void cmbMonthOnMouseClicked(MouseEvent event) {
        lblDate.setText(null);
    }

    @FXML
    void cmbYearOnMouseClicked(MouseEvent event) {
        lblDate.setText(null);
    }

    public void getAllId() {

        vbox.getChildren().clear();

        ArrayList<String> allId = EmployeeSalaryModel.getAllId();

        for (int i = 0; i < allId.size(); i++) {
            loadDataTable(allId.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeSalaryManageFormController.class.getResource("/view/EmployeeSalaryManageTableRowForm.fxml"));
            Parent root = loader.load();
            EmployeeSalaryManageTableRowFormController controller = loader.getController();
            controller.setData(id);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMonth() {
        ObservableList<String> months = FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );

        cmbMonth.setItems(months);
    }


    public void setYear() {
        int currentYear = LocalDate.now().getYear();
        int pastYear = 2010;

        List<String> years = new ArrayList<>();
        for (int year = currentYear; year >= pastYear; year--) {
            years.add(String.valueOf(year));
        }

        cmbYear.setItems(FXCollections.observableArrayList(years));
    }

    private String mapMonthToNumber(String month) {
        if ( month != null ) {
            switch (month) {
                case "January":
                    return "01";
                case "February":
                    return "02";
                case "March":
                    return "03";
                case "April":
                    return "04";
                case "May":
                    return "05";
                case "June":
                    return "06";
                case "July":
                    return "07";
                case "August":
                    return "08";
                case "September":
                    return "09";
                case "October":
                    return "10";
                case "November":
                    return "11";
                case "December":
                    return "12";
            }
        }
        return "20";
    }

    public boolean validateSearch() {
        if ( cmbYear.getSelectionModel().getSelectedItem() == null ) {
            lblDate.setText("Please select a year");
            return false;
        }

        if ( cmbMonth.getSelectionModel().getSelectedItem() == null ) {
            lblDate.setText("Please select a month");
            return false;
        }
        return true;
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
        imgAdd.setImage(new Image("/assets/icon/salary_green.png"));
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
        imgAdd.setImage(new Image("/assets/icon/add_salary.png"));
    }

    @FXML
    void btnResetOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip("Reset");
        tooltip.setShowDelay(Duration.millis(150));
        btnReset.setTooltip(tooltip);

        imgReset.setImage(new Image("/assets/icon/redo_green.png"));
    }

    @FXML
    void btnResetOnMouseExited(MouseEvent event) {
        imgReset.setImage(new Image("/assets/icon/redo.png"));
    }

    @FXML
    void btnSearchOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip("Search");
        tooltip.setShowDelay(Duration.millis(150));
        btnSearch.setTooltip(tooltip);

        imgSearch.setImage(new Image("/assets/icon/search_green.png"));
    }

    @FXML
    void btnSearchOnMouseExited(MouseEvent event) {
        imgSearch.setImage(new Image("/assets/icon/btn_search.png"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
        setMonth();
        setYear();
    }
}
