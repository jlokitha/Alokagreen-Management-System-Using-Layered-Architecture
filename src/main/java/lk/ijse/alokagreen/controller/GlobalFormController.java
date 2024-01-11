package lk.ijse.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.alokagreen.bo.BOFactory;
import lk.ijse.alokagreen.bo.custom.DashboardBO;
import lk.ijse.alokagreen.bo.custom.impl.DashboardBOImpl;
import lk.ijse.alokagreen.util.Navigation;
import lk.ijse.alokagreen.util.Style;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GlobalFormController implements Initializable {

    @FXML
    private JFXButton btnDashBoard;

    @FXML
    private ImageView imgDashboard;

    @FXML
    private Label lblDashboard;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private ImageView imgOrder;

    @FXML
    private Label lblOrder;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private ImageView imgEmployee;

    @FXML
    private Label lblEmployee;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private ImageView imgCustomer;

    @FXML
    private Label lblCustomer;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private ImageView imgSupplier;

    @FXML
    private Label lblSupplier;

    @FXML
    private JFXButton btnStock;

    @FXML
    private ImageView imgStock;

    @FXML
    private Label lblStock;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private ImageView imgLogOut;

    @FXML
    private Label lblLogout;

    @FXML
    private JFXButton btnProfile;

    @FXML
    private ImageView imgProfile;

    @FXML
    private JFXButton btnItem;

    @FXML
    private ImageView imgItem;

    @FXML
    private Label lblItem;

    @FXML
    private Label labelUser;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    public AnchorPane pagingPane;

    @FXML
    public ImageView imgGreyBack;

    @FXML
    public AnchorPane popUpPane;

    private boolean dashboard;
    private boolean order;
    private boolean employee;
    private boolean customer;
    private boolean supplier;
    private boolean item;
    private boolean stock;

    public Stage popupStage;

    public static String user;

    private static GlobalFormController globalFormController;

    public GlobalFormController() {
        globalFormController = this;
    }

    private final DashboardBO dashboardBO = (DashboardBOImpl) BOFactory.getBoFactory ().getBO ( BOFactory.BOType.DASHBOARD );

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        unSelected();

        try {
            Style.btnSelected(btnDashBoard,imgDashboard,"dashboard_green.png",lblDashboard);
            Navigation.switchPaging("DashboardForm.fxml");
            dashboard = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProfileOnAction(ActionEvent event) {
        try {
            Navigation.popupPane("ProfileForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        unSelected();

        try {
            Style.btnSelected(btnCustomer,imgCustomer,"customer_green.png",lblCustomer);
            Navigation.switchPaging("CustomerManageForm.fxml");
            customer = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        unSelected();

        try {
            Style.btnSelected(btnEmployee,imgEmployee,"employee_green.png", lblEmployee);
            Navigation.switchPaging("EmployeeManageForm.fxml");
            employee = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        unSelected();

        try {
            Style.btnSelected(btnItem, imgItem, "item_green.png", lblItem);
            Navigation.switchPaging("ProductListManageForm.fxml");
            item = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

        try {
            SignInFormController.stage.close();
            Navigation.switchNavigation("GlobalLoginForm.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        unSelected();

        try {
            Style.btnSelected(btnOrder,imgOrder,"order_green.png", lblOrder);
            Navigation.switchPaging("CustomerOrderManageForm.fxml");
            order = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnStockOnAction(ActionEvent event) {
        unSelected();

        try {
            Style.btnSelected(btnStock,imgStock,"stock_green.png", lblStock);
            Navigation.switchPaging("ProductStockManageForm.fxml");
            stock = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        unSelected();

        try {
            Style.btnSelected(btnSupplier,imgSupplier,"supplier_green.png", lblSupplier);
            Navigation.switchPaging("SupplierManageForm.fxml");
            supplier = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDashboardOnMouseEntered(MouseEvent event) {
        if ( !dashboard ) {
            Style.btnHover(btnDashBoard,imgDashboard,"dashboard.png",lblDashboard);
        }
    }

    @FXML
    void btnDashboardOnMouseExited(MouseEvent event) {
        if ( !dashboard ) {
            Style.btnUnselected(btnDashBoard, imgDashboard, "dashboard.png", lblDashboard);
        }
    }

    @FXML
    void btnEmployeeOnMouseEntered(MouseEvent event) {
        if ( !employee ) {
            Style.btnHover(btnEmployee,imgEmployee,"employee.png", lblEmployee);
        }
    }

    @FXML
    void btnEmployeeOnMouseExited(MouseEvent event) {
        if ( !employee ) {
            Style.btnUnselected(btnEmployee, imgEmployee, "employee.png", lblEmployee);
        }
    }

    @FXML
    void btnItemOnMouseEntered(MouseEvent event) {
        if ( !item ) {
            Style.btnHover(btnItem, imgItem, "item.png", lblItem);
        }
    }

    @FXML
    void btnItemOnMouseExited(MouseEvent event) {
        if ( !item ) {
            Style.btnUnselected(btnItem, imgItem, "item.png", lblItem);
        }
    }

    @FXML
    void btnLogOutOnMouseEntered(MouseEvent event) {
        Style.btnSelectedLogOut(btnLogOut, lblLogout, imgLogOut);
    }

    @FXML
    void btnLogOutOnMouseExited(MouseEvent event) {
        Style.btnUnselected(btnLogOut, imgLogOut, "log_out.png", lblLogout);
    }

    @FXML
    void btnSupplierOnMouseEntered(MouseEvent event) {
        if ( !supplier ) {
            Style.btnHover(btnSupplier,imgSupplier,"supplier.png", lblSupplier);
        }
    }

    @FXML
    void btnSupplierOnMouseExited(MouseEvent event) {
        if ( !supplier ) {
            Style.btnUnselected(btnSupplier, imgSupplier, "supplier.png", lblSupplier);
        }
    }

    @FXML
    void btnOrderedOnMouseEntered(MouseEvent event) {
        if ( !order ) {
            Style.btnHover(btnOrder,imgOrder,"order.png", lblOrder);
        }
    }

    @FXML
    void btnOrderedOnMouseExited(MouseEvent event) {
        if ( !order ) {
            Style.btnUnselected(btnOrder, imgOrder, "order.png", lblOrder);
        }
    }

    @FXML
    void btnStockOnMouseEntered(MouseEvent event) {
        if ( !stock ) {
            Style.btnHover(btnStock,imgStock,"stock.png", lblStock);
        }
    }

    @FXML
    void btnStockOnMouseExited(MouseEvent event) {
        if ( !stock ) {
            Style.btnUnselected(btnStock, imgStock, "stock.png", lblStock);
        }
    }

    @FXML
    void btnProfileOnMouseEntered(MouseEvent event) {
        Tooltip tooltip = new Tooltip("Profile");
        tooltip.setShowDelay(Duration.millis(150));
        btnProfile.setTooltip(tooltip);

        Style.btnSelectedProfile(imgProfile);
    }

    @FXML
    void btnProfileOnMouseExited(MouseEvent event) {
        Style.btnUnselectedProfile(imgProfile);
    }

    @FXML
    void btnCustomerOnMouseEntered(MouseEvent mouseEvent) {
        if ( !customer ) {
            Style.btnHover(btnCustomer,imgCustomer,"customer.png",lblCustomer);
        }
    }
    @FXML
    void btnCustomerOnMouseExited(MouseEvent mouseEvent) {
        if ( !customer ) {
            Style.btnUnselected(btnCustomer, imgCustomer, "customer.png", lblCustomer);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        if (!txtSearch.getText().isEmpty()) {
            String substring = txtSearch.getText().substring(0, 2);

            try {
                String id = txtSearch.getText();
                switch (substring) {
                    case "E-":
                        if (dashboardBO.getEmployeeName (id) != null) {
                            EmployeeViewFormController.id = id;
                            Navigation.popupPane("EmployeeViewForm.fxml");
                        }
                        txtSearch.clear();
                        break;
                    case "C-":
                        if (dashboardBO.getCustomerName (id) != null) {
                            CustomerViewFormController.id = txtSearch.getText();
                            Navigation.popupPane("CustomerViewForm.fxml");
                        }
                        txtSearch.clear();
                        break;
                    case "S-":
                        if (dashboardBO.getSupplierName (id) != null) {
                            SupplierViewFormController.id = txtSearch.getText();
                            Navigation.popupPane("SupplierViewForm.fxml");
                        }
                        txtSearch.clear();
                        break;
                    case "PS":
                        if (dashboardBO.getProductIdOfStock (id) != null) {
                            ProductStockViewFormController.id = txtSearch.getText();
                            Navigation.popupPane("ProductStockViewForm.fxml");
                        }
                        txtSearch.clear();
                        break;
                    case "MS":
                        if (dashboardBO.getMaterialStockDetails (id) != null) {
                            MaterialStockViewFormController.id = txtSearch.getText();
                            Navigation.popupPane("MaterialStockViewForm.fxml");
                        }
                        txtSearch.clear();
                        break;
                    case "CO":
                        if (dashboardBO.getCustomerOrderDetails (id) != null) {
                            CustomerOrderViewFormController.id = txtSearch.getText();
                            Navigation.popupPane("CustomerOrderViewForm.fxml");
                        }
                        txtSearch.clear();
                        break;
                    case "SO":
                        if (dashboardBO.getSupplierIdOfSupilerOrder (id) != null) {
                            SupplierOrderViewFormController.id = txtSearch.getText();
                            Navigation.popupPane("SupplierOrderViewForm.fxml");
                        }
                        txtSearch.clear();
                        break;
                    case "07":
                        String idOfMobile = dashboardBO.getCustomerIdOfMobile (id);
                        if (idOfMobile != null) {
                            CustomerViewFormController.id = idOfMobile;
                            Navigation.popupPane("CustomerViewForm.fxml");
                        }
                        txtSearch.clear();
                        break;
                    default:
                        txtSearch.setStyle("-fx-text-fill: red;");
                }
            } catch ( IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void txtSearchOnMouseClicked(MouseEvent event) {
        txtSearch.setStyle("-fx-text-fill: black;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String employeeId = dashboardBO.getUserEmpId (user);
            String name = dashboardBO.getEmployeeName ( employeeId );

            labelUser.setText(name);
            btnDashboardOnAction(null);
        } catch ( SQLException e ) {
            e.printStackTrace ();
        }
    }

    public void unSelected() {
        Style.btnUnselected(btnDashBoard, imgDashboard, "dashboard.png", lblDashboard);
        Style.btnUnselected(btnOrder, imgOrder, "order.png", lblOrder);
        Style.btnUnselected(btnEmployee, imgEmployee, "employee.png", lblEmployee);
        Style.btnUnselected(btnCustomer, imgCustomer, "customer.png", lblCustomer);
        Style.btnUnselected(btnSupplier, imgSupplier, "supplier.png", lblSupplier);
        Style.btnUnselected(btnStock, imgStock, "stock.png", lblStock);
        Style.btnUnselected(btnItem, imgItem, "item.png", lblItem);
        Style.btnUnselected(btnLogOut, imgLogOut, "log_out.png", lblLogout);

        dashboard = false;
        order = false;
        employee = false;
        customer = false;
        supplier = false;
        stock = false;
        item = false;
    }

    public static GlobalFormController getInstance() {
        return globalFormController ;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }
}
