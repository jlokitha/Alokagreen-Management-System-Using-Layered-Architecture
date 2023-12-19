package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.model.*;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.ReadQrCode;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private HBox hBoxProducts;

    @FXML
    private VBox vBoxMaterial;

    @FXML
    private VBox vBoxCustOrder;

    @FXML
    private Label lblIncome;

    @FXML
    private Label lblExpense;

    @FXML
    private Label lblSaving;

    @FXML
    private JFXComboBox<String > cmbEmpId;

    @FXML
    private JFXButton btnAddAttendance;

    @FXML
    private Label lblEmpId;

    @FXML
    private ImageView imgQr;

    @FXML
    private JFXButton btnQr;

    ArrayList<String[]> pStock;

    public DashboardFormController() {
        pStock = new ArrayList<>();
    }

    @FXML
    void btnMarkAttendanceOnAction(ActionEvent event) {
        if ( validateEmpId() ) {
            try {
                EmployeeAttendanceAddFormController.id = cmbEmpId.getSelectionModel().getSelectedItem();
                clearCmbEmpId();
                Navigation.popupPane("EmployeeAttendanceAddForm.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean validateEmpId() {
        if (cmbEmpId.getItems().isEmpty()) {
            lblEmpId.setText("Please select an employee id");
            return false;
        }
        return true;
    }

    public void clearCmbEmpId() {
        EventHandler<ActionEvent> onAction = cmbEmpId.getOnAction();
        cmbEmpId.setOnAction(null);
        cmbEmpId.setValue(null);
        cmbEmpId.setOnAction(onAction);
    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {
        lblEmpId.setText(null);
    }

    @FXML
    void btnQrOnAction(ActionEvent event) {
        new Thread(() -> {
            String id = ReadQrCode.readQr();

            if (EmployeeModel.getNameOfId(id) != null) {
                EmployeeAttendanceDto dto = new EmployeeAttendanceDto();

                dto.setAttendance_Id(NewId.newAttendanceId());
                dto.setEmployee_Id(id);
                dto.setDate(DateTime.dateNow());
                dto.setTime(DateTime.timeNow());

                EmployeeAttendanceModel.saveEmployeeAttendance(dto);
            }
        }).start();
    }

    @FXML
    void btnQrOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip("QR");
        tooltip.setShowDelay(Duration.millis(150));
        btnQr.setTooltip(tooltip);

        imgQr.setImage(new Image("/assets/icon/qr_green.png"));
    }

    @FXML
    void btnQrOnMouseExited(MouseEvent event) {
        imgQr.setImage(new Image("/assets/icon/qr.png"));
    }

    public void setProductDetail() {
        hBoxProducts.getChildren().clear();

        ArrayList<String> allId = ProductModel.getAllId();

        Map<String, Integer> pStock = new HashMap<>();

        for (int i = 0; i < allId.size(); i++) {
            String desc = ProductModel.getDescOfId(allId.get(i));
            int qty = ProductStockModel.getProductQty(allId.get(i));

            if (qty != 0) {
                pStock.put(desc, pStock.getOrDefault(desc, 0) + qty);
            }
        }

        for (Map.Entry<String, Integer> entry : pStock.entrySet()) {
            loadProductStockData(entry);
        }
    }

    private void loadProductStockData(Map.Entry<String, Integer> entry) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardFormController.class.getResource("/view/ProductStockShortcutForm.fxml"));
            Parent root = loader.load();
            ProductStockShortcutFormController controller = loader.getController();
            controller.setData(entry);
            hBoxProducts.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMaterialDetail() {
        vBoxMaterial.getChildren().clear();

        ArrayList<String> allMaterialId = MaterialModel.getAllMaterialId();

        Map<String, Integer> pStock = new HashMap<>();

        for (int i = 0; i < allMaterialId.size(); i++) {
            String desc = MaterialModel.getDescOfId(allMaterialId.get(i));
            int qty = MaterialStockModel.getProductQty(allMaterialId.get(i));

            if ( qty != 0 ) {
                pStock.put(desc, pStock.getOrDefault(desc, 0) + qty);
            }
        }

        for (Map.Entry<String, Integer> entry : pStock.entrySet()) {
            loadMaterialStockData(entry);
        }
    }

    private void loadMaterialStockData(Map.Entry<String, Integer> entry) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardFormController.class.getResource("/view/MaterialStockShortcutForm.fxml"));
            Parent root = loader.load();
            MaterialStockShortcutFormController controller = loader.getController();
            controller.setData(entry);
            vBoxMaterial.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCustomerOrderDetail() {
        vBoxCustOrder.getChildren().clear();

        ArrayList<String> allId = CustomerOrderModel.getAllId();

        for (int i = 0; i < allId.size(); i++) {
            loadCustomerOrder(allId.get(i));
        }
    }

    private void loadCustomerOrder(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardFormController.class.getResource("/view/CustomerOrderShortcutForm.fxml"));
            Parent root = loader.load();
            CustomerOrderShortcutFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustOrder.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWalletData() {
        double totalIncome = CustomerOrderModel.getTotalIncome();
        double totalExpense = SupplierOrderModel.getTotalExpense() + EmployeeSalaryModel.getTotalSalary();
        double saving = totalIncome - totalExpense;

        lblIncome.setText(String.valueOf(totalIncome));
        lblExpense.setText(String.valueOf(totalExpense));
        lblSaving.setText(String.valueOf(saving));
    }

    @FXML
    void btnAddAttendanceMouseEntered(MouseEvent event) {
        btnAddAttendance.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 10px;");
    }

    @FXML
    void btnAddAttendanceMouseExited(MouseEvent event) {
        btnAddAttendance.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 10px;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbEmpId.getItems().addAll(EmployeeModel.getAllId());
        setProductDetail();
        setMaterialDetail();
        setCustomerOrderDetail();
        setWalletData();
    }
}
