package lk.lokitha.alokagreen.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.stage.StageStyle;
import lk.lokitha.alokagreen.controller.GlobalFormController;
import lk.lokitha.alokagreen.controller.GlobalLoginFormController;
import lk.lokitha.alokagreen.controller.SignInFormController;


import java.io.IOException;

public class Navigation {

    private static Parent parent;
    private static Stage stage;
    private static Scene scene;

    public static void switchNavigation(String path, ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Navigation.class.getResource("/view/" + path));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SignInFormController.stage = stage;
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void switchPaging(String path) throws IOException {
        GlobalFormController.getInstance().pagingPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/view/"+path));
        Parent root = loader.load();
        GlobalFormController.getInstance().pagingPane.getChildren().add(root);
    }

    public static void popupPane(String path) throws IOException {

        GlobalFormController.getInstance().popUpPane.setVisible(true);
        GlobalFormController.getInstance().imgGreyBack.setVisible(true);

        AnchorPane root = FXMLLoader.load(Navigation.class.getResource("/view/" + path));

        Stage popupStage = new Stage();
        GlobalFormController.getInstance().setPopupStage(popupStage);
        popupStage.setScene(new Scene(root));
        popupStage.centerOnScreen();
        popupStage.initOwner(GlobalFormController.getInstance().popUpPane.getScene().getWindow());
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.show();
    }

    public static void switchLoginPage(String path) throws IOException {
        GlobalLoginFormController.getInstance().loginPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/view/"+path));
        Parent root = loader.load();
        GlobalLoginFormController.getInstance().loginPane.getChildren().add(root);
    }

    public static void closePane(){
        GlobalFormController.getInstance().popupStage.close();
        GlobalFormController.getInstance().popUpPane.getChildren().clear();
        GlobalFormController.getInstance().popUpPane.setVisible(false);
        GlobalFormController.getInstance().imgGreyBack.setVisible(false);
    }

}
