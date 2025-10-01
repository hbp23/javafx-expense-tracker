package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.MainWindowApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class BudgetPopupViewController {

    private MainWindowController mainWindowController;

    private static Stage stage;

    public static void show(MainWindowController mainWindowController) throws IOException {
        stage = new Stage(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindowApplication.class.getResource("view/budget-popup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        BudgetPopupViewController controller = fxmlLoader.getController();
        controller.mainWindowController = mainWindowController;
        stage.setTitle("Table Of Records");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private TextField budgetTextField;

    @FXML
    protected void applyButtonAction() {
        try {
            if (budgetTextField.getText().isEmpty() || budgetTextField.getText() == null
                    || Double.parseDouble(budgetTextField.getText()) <= 0.0) {
                errorField(budgetTextField);
                return;
            }
            mainWindowController.setBudget(Double.parseDouble(budgetTextField.getText()));
            closeButtonAction();
            mainWindowController.updateBalances();
        }
        catch (Exception e) {
            errorField(budgetTextField);
        }
    }

    @FXML
    protected void closeButtonAction() {
        stage.close();
    }

    public void errorField(Control t) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.RED);
        glow.setRadius(2);
        glow.setSpread(0.3);
        t.setEffect(glow);
    }
}
