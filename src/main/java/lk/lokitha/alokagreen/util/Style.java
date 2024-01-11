package lk.lokitha.alokagreen.util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Style {
    public static void btnOnMouseExited( JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;"
        );
    }

    public static void btnOnMouseEntered(JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;"
        );
    }

    public static void btnOnMouseEnteredWithBorder ( JFXButton btn ) {
        btn.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;"
        );
    }

    public static void btnOnMouseEnteredWithBorder2 ( JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #EEEEEE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }

    public static void btnOnMouseExitedWithBorder ( JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;"
        );
    }

    public static void btnOnMouseExitedWithBorder2(JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;"
        );
    }

    public static void btnOnMouseEntered( JFXButton btn, Label lbl, ImageView img ) {
        btn.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-radius: 20px;"
        );
        lbl.setStyle( "-fx-text-fill:  #139547;");
        img.setImage(new Image ("/assets/icon/add_green.png"));
    }

    public static void btnOnMouseExited(JFXButton btn, Label lbl, ImageView img) {
        btn.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-radius: 20px;"
        );
        lbl.setStyle( "-fx-text-fill:  #727374;" );
        img.setImage(new Image("/assets/icon/add.png") );
    }

    public static void btnAddAttendanceMouseEntered(JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 10px;"
        );
    }

    public static void btnAddAttendanceMouseExited(JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 10px;"
        );
    }

    public static void btnDeleteOnMouseEntered(JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #ff6a6a;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;"
        );
    }

    public static void btnDeleteOnMouseExited(JFXButton btn) {
        btn.setStyle(
                "-fx-background-color:  #f44930;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;"
        );
    }

    public static void btnDeleteOnMouseEntered( JFXButton btn, Label lbl, ImageView img) {
        btn.setStyle(
                "-fx-background-color: #FFE0E0;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: #FD3535;" +
                        "-fx-border-radius: 20px;");
        lbl.setStyle(
                "-fx-text-fill:  #FD3535;");
        img.setImage(new Image("/assets/icon/delete_red.png"));
    }

    public static void btnDeleteOnMouseEnteredWithBorder( JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #FFC4C4;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #f44949;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill: #f44949;"
        );
    }

    public static void btnDeleteOnMouseExitedWithBorder(JFXButton btn) {
        btn.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #f44949;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill: #f44949;"
        );
    }
}
