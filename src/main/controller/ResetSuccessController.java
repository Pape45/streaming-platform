package main.controller;

import javafx.scene.input.MouseEvent;
import main.Main;

import java.io.IOException;

public class ResetSuccessController {

    public ResetSuccessController() {
    }

    public void userLogin(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("login");
    }
}
