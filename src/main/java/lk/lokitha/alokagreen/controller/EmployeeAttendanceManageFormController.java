package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.AttendanceBO;
import lk.lokitha.alokagreen.bo.custom.impl.AttendanceBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.ReadQrCode;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class EmployeeAttendanceManageFormController implements Initializable {

    @FXML
    public JFXButton btnQr;

    @FXML
    private VBox vbox;

    @FXML
    private ImageView imgReset;

    @FXML
    private DatePicker DPDate;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblAdd;

    @FXML
    private JFXButton btnReset;

    @FXML
    private ImageView imgQr;

    public static EmployeeAttendanceManageFormController controller;

    private final AttendanceBO attendanceBO = (AttendanceBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.ATTENDANCE );

    public EmployeeAttendanceManageFormController() {
        controller = this;
    }

    @FXML
    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            Navigation.popupPane("EmployeeAttendanceAddForm.fxml");
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
    void btnSalaryManageOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging("EmployeeSalaryManageForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void DPDateOnAction(ActionEvent event) {
        try {
            ArrayList<String> ids = attendanceBO.getAttendanceForDate( String.valueOf( DPDate.getValue( ) ) );

            if ( !ids.isEmpty() ) {
                vbox.getChildren().clear();

                for (int i = 0; i < ids.size(); i++) {
                    loadDataTable(ids.get(i));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    @FXML
    void btnQrOnAction(ActionEvent event) {
        AtomicReference<String> result = new AtomicReference<>();

        Thread workerThread = new Thread(() -> {

            String id = ReadQrCode.readQr();
            if (id != null) {
                result.set(id);
            }
        });

        workerThread.start();

        try {
            // Wait for the worker thread to complete
            workerThread.join();
        } catch (InterruptedException ignored) {
        }

        if ( result.get( ) != null ) {
            try {
                String employeeName = attendanceBO.getEmployeeName( result.get( ) );

                if ( employeeName != null ) {

                    try {
                        attendanceBO.saveAttendance( new EmployeeAttendanceDto(
                                null,
                                result.get( ),
                                null,
                                null
                        ) );
                    } catch ( SQLException e ) {
                        throw new RuntimeException( e );
                    }

                    if ( EmployeeAttendanceManageFormController.controller != null ) {
                        EmployeeAttendanceManageFormController.controller.getAllId( );
                    }
                } else {
                    new Alert( Alert.AlertType.ERROR, "Employee does not exist" ).show( );
                }
            } catch ( SQLException e ) {
                throw new RuntimeException( e );
            }
        }
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

    public void clearDate() {
        EventHandler<ActionEvent> originalHandler = DPDate.getOnAction();
        DPDate.setOnAction(null);
        DPDate.setValue(null);
        DPDate.setOnAction(originalHandler);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        clearDate();
        getAllId();
    }

    public void getAllId() {

        vbox.getChildren().clear();

        try {
            ArrayList<String> allId = attendanceBO.getAllAttendanceIds( );

            for (int i = 0; i < allId.size(); i++) {
                loadDataTable(allId.get(i));
            }

        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeAttendanceManageFormController.class.getResource("/view/EmployeeAttendanceManageTableRowForm.fxml"));
            Parent root = loader.load();
            EmployeeAttendanceManageTableRowFormController controller = loader.getController();
            controller.setData(id);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    void btnAddOnMouseEntered(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-radius: 15px;");
        lblAdd.setStyle(
                "-fx-text-fill:  #139547;");
        imgAdd.setImage(new Image("/assets/icon/mark_attendance_btn_green.png"));
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-radius: 15px;");
        lblAdd.setStyle(
                "-fx-text-fill:  #727374;");
        imgAdd.setImage(new Image("/assets/icon/mark_attendance_btn.png"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllId();
    }
}
