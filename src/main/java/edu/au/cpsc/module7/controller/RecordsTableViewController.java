package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RecordsTableViewController {

    @FXML
    private TableView<Transaction> transactionTableView;

    @FXML
    private TableColumn<Transaction, String> dateColumn, categoryColumn, amountColumn;

    @FXML
    private TableColumn<Transaction, Void> commentColumn;

    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionDate"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionCategory"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionAmount"));
        transactionTableView.getSelectionModel().selectedItemProperty().addListener(c -> tableSelectionChanged());
    }

    private void tableSelectionChanged() {
        Transaction selectedTransaction = transactionTableView.getSelectionModel().getSelectedItem();
        TransactionTableEvent event = new TransactionTableEvent(TransactionTableEvent.TRANSACTION_SELECTED, selectedTransaction);
        transactionTableView.fireEvent(event);
    }

    public void showTransactions(List<Transaction> transactions) {
        SortedList<Transaction> sortedList = new SortedList<>(FXCollections.observableList(transactions));
        transactionTableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(transactionTableView.comparatorProperty());
        transactionTableView.refresh();
    }

    public void onTransactionSelectionChanged(EventHandler<TransactionTableEvent> handler) {
        transactionTableView.addEventHandler(TransactionTableEvent.TRANSACTION_SELECTED, handler);
    }

    public void select(Transaction t) {
        transactionTableView.getSelectionModel().select(t);
    }

    public static class TransactionTableEvent extends Event {
        public static final EventType<TransactionTableEvent> ANY = new EventType<>(Event.ANY, "ANY");
        public static final EventType<TransactionTableEvent> TRANSACTION_SELECTED = new EventType<>(ANY, "TRANSACTION_SELECTED");
        private Transaction t;
        public TransactionTableEvent(EventType<? extends Event> eventType, Transaction t) {
            super(eventType);
            this.t = t;
        }
        public Transaction getSelectedTransaction() { return t;}
    }
}
