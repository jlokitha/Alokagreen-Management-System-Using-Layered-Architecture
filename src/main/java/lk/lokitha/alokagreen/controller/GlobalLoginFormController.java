package lk.lokitha.alokagreen.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lk.lokitha.alokagreen.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GlobalLoginFormController implements Initializable {

    @FXML
    public AnchorPane loginPane;

    public static GlobalLoginFormController controller;

    public GlobalLoginFormController() {
        controller = this;
    }

    public static GlobalLoginFormController getInstance() {
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Navigation.switchLoginPage("SignInForm.fxml");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
