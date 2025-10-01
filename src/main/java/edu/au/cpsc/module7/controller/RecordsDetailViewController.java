package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.data.Db;
import edu.au.cpsc.module7.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class RecordsDetailViewController {

    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextArea commentTextArea;

    public void initialize() {
        categoryComboBox.getItems().addAll("Rent", "Groceries", "Utilities", "Transportation"
                , "Entertainment", "Healthcare", "Dining Out", "Education", "Clothing", "Shopping", "Gifts");
    }

    public void updateTransaction(Transaction t) {
        t.setTransactionDate(dateDatePicker.getValue());
        t.setTransactionCategory(categoryComboBox.getValue());
        t.setTransactionAmount(Double.parseDouble(amountTextField.getText()));
        t.setTransactionComment(commentTextArea.getText());
    }

    public void showTransaction(Transaction t) {
        if (t == null) {
            dateDatePicker.setValue(null);
            categoryComboBox.setValue(null);
            amountTextField.clear();
            commentTextArea.clear();
            return;
        }
        dateDatePicker.setValue(t.getTransactionDate());
        categoryComboBox.setValue(t.getTransactionCategory());
        amountTextField.setText(Double.toString(t.getTransactionAmount()));
        commentTextArea.setText(t.getTransactionComment());
    }

    public boolean notNull() {
        boolean b = true;
        if (dateDatePicker.getValue() == null) {
            errorField(dateDatePicker);
            b = false;
        }
        if (categoryComboBox.getValue() == null) {
            errorField(categoryComboBox);
            b = false;
        }
        try {
            if (amountTextField.getText().isEmpty() || amountTextField.getText() == null
                    || Double.parseDouble(amountTextField.getText()) < 0.0) {
                errorField(amountTextField);
                b = false;
            }
        }
        catch (Exception e) {
            errorField(amountTextField);
            b = false;
        }
        return b;
    }

    public void errorField(Control t) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.RED);
        glow.setRadius(2);
        glow.setSpread(0.3);
        t.setEffect(glow);
    }

    public void clearEffects() {
        dateDatePicker.setEffect(null);
        categoryComboBox.setEffect(null);
        amountTextField.setEffect(null);
    }
}
