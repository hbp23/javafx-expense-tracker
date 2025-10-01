package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.MainWindowApplication;
import edu.au.cpsc.module7.data.Db;
import edu.au.cpsc.module7.model.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class TableOfRecordsController {

    private MainWindowController mainWindowController;

    public static void show(MainWindowController mainWindowController) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindowApplication.class.getResource("view/table-of-records-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        TableOfRecordsController controller = fxmlLoader.getController();
        controller.mainWindowController = mainWindowController;
        stage.setTitle("Table Of Records");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private RecordsTableViewController recordsTableViewController;

    @FXML
    private RecordsDetailViewController recordsDetailViewController;

    @FXML
    private Button addButton;

    private Transaction transactionBeingEdited;

    private boolean transactionBeingEditedIsNew;

    public void initialize() {
        recordsTableViewController.showTransactions(Db.getDataBase().getTransactions());
        recordsTableViewController.onTransactionSelectionChanged(event -> showTransaction(event.getSelectedTransaction()));
        showTransaction(null);
    }

    private void showTransaction(Transaction t) {
        recordsDetailViewController.clearEffects();
        recordsDetailViewController.showTransaction(t);
        transactionBeingEdited = t == null ? new Transaction(LocalDate.now(), "", 0.0, "") : t;
        transactionBeingEditedIsNew = t == null;
        String buttonLabel = transactionBeingEditedIsNew ? "Add" : "Update";
        addButton.setText(buttonLabel);
    };

    @FXML
    protected void addButtonAction() {
        if (!isValid()) {
            return;
        }
        recordsDetailViewController.updateTransaction(transactionBeingEdited);
        if (transactionBeingEditedIsNew) {
            Db.getDataBase().addTransaction(transactionBeingEdited);
        } else {
            Db.getDataBase().updateTransaction(transactionBeingEdited);
        }
        Db.saveDatabase();
        recordsTableViewController.showTransactions(Db.getDataBase().getTransactions());
        recordsTableViewController.select(transactionBeingEdited);
        recordsDetailViewController.clearEffects();
        mainWindowController.updateBalances();
    }

    @FXML
    protected void deleteButtonAction() {
        if (transactionBeingEditedIsNew) {
            recordsTableViewController.select(null);
        }
        Db.getDataBase().removeTransaction(transactionBeingEdited);
        Db.saveDatabase();
        recordsTableViewController.showTransactions(Db.getDataBase().getTransactions());
        mainWindowController.updateBalances();
    }

    @FXML
    protected void clearButtonAction() {
        recordsDetailViewController.clearEffects();
        recordsTableViewController.select(null);
    }

    private boolean isValid() {
        return (recordsDetailViewController.notNull());
    }
}
