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
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.DashboardBO;
import lk.lokitha.alokagreen.bo.custom.impl.DashboardBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.ReadQrCode;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private final DashboardBO dashboardBO = (DashboardBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.DASHBOARD );

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

            try {
                if (dashboardBO.getEmployeeName( id ) != null) {

                    dashboardBO.saveEmployeeAttendance( new EmployeeAttendanceDto(
                            null,
                            id,
                            null,
                            null
                    ) );
                }
            } catch ( SQLException e ) {
                e.printStackTrace();
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

        try {
            hBoxProducts.getChildren().clear();

            Map<String, Integer> pStock = dashboardBO.setProductDetails();

            for (Map.Entry<String, Integer> entry : pStock.entrySet()) {
                loadProductStockData(entry);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
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

        try {
            vBoxMaterial.getChildren().clear();

            Map<String, Integer> pStock = dashboardBO.setMaterialDetails();

            for (Map.Entry<String, Integer> entry : pStock.entrySet()) {
                loadMaterialStockData(entry);
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
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

        try {
            vBoxCustOrder.getChildren().clear();

            ArrayList<String> allId = dashboardBO.getAllCustomerOrderIds( );

            for (int i = 0; i < allId.size(); i++) {
                loadCustomerOrder(allId.get(i));
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
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
        try {
            double totalIncome = dashboardBO.getTotalIncome( );
            double totalExpense = dashboardBO.getTotalExpenses();

            lblIncome.setText(String.valueOf(totalIncome));
            lblExpense.setText(String.valueOf(totalExpense));
            lblSaving.setText(String.valueOf(totalIncome - totalExpense));

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public void setCmbEmpId() {
        try {
            cmbEmpId.getItems().addAll(dashboardBO.getAllEmployeeIds());
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
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
        setCmbEmpId();
        setProductDetail();
        setMaterialDetail();
        setCustomerOrderDetail();
        setWalletData();
    }
}
