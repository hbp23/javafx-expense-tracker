package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.MainWindowApplication;
import edu.au.cpsc.module7.data.Db;
import edu.au.cpsc.module7.data.TransactionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Map;

public class MainWindowController {

    private double budget = 500.00;
    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    private ObservableList<PieChart.Data> monthlyPieChartData = getMonthlyExpenseData();
    public ObservableList<PieChart.Data> getMonthlyPieChartData() { return monthlyPieChartData; }
    public void setMonthlyPieChartData(ObservableList<PieChart.Data> m) { this.monthlyPieChartData = m; }
    public ObservableList<PieChart.Data> getMonthlyExpenseData() {
        Map<String, Double> expenseMap = Db.getDataBase().getMonthlyExpenseCategories();
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : expenseMap.entrySet()) {
            data.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        return data;
    }
    private ObservableList<PieChart.Data> totalPieChartData = getTotalExpenseData();
    public ObservableList<PieChart.Data> getTotalPieChartData() { return totalPieChartData; }
    public void setTotalPieChartData(ObservableList<PieChart.Data> t) { this.totalPieChartData = t; }
    public ObservableList<PieChart.Data> getTotalExpenseData() {
        Map<String, Double> expenseMap = Db.getDataBase().getTotalExpenseCategories();
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : expenseMap.entrySet()) {
            data.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        return data;
    }

    @FXML
    private Label currentMonthLabel, lastMonthLabel;

    @FXML
    private TextField monthlyBalanceField, lastMonthlyBalanceField;

    @FXML
    private ProgressIndicator budgetProgress;

    @FXML
    private MenuItem closeButton;

    public void initialize() {
        monthlyBalanceField.setEditable(false);
        lastMonthlyBalanceField.setEditable(false);
        currentMonthLabel.setText(LocalDate.now().getMonth().toString());
        lastMonthLabel.setText(LocalDate.now().minusMonths(1).getMonth().toString());
        updateBalances();
    }

    public void updateBalances() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        monthlyBalanceField.setText(formatter.format(Db.getDataBase().totalBalanceCurrentMonth()));
        lastMonthlyBalanceField.setText(formatter.format(Db.getDataBase().totalBalanceLastMonth()));
        budgetProgress.setProgress(Db.getDataBase().totalBalanceCurrentMonth() / getBudget());
        setMonthlyPieChartData(getMonthlyExpenseData());
        setTotalPieChartData(getTotalPieChartData());
    }

    @FXML
    protected void tableOfRecordsButtonAction() throws IOException {
        TableOfRecordsController.show(this);
    }

    @FXML
    protected void pieChartButtonAction() throws IOException {
        PieChartViewController.show(this);
    }

    @FXML
    protected void setBudgetAction() throws IOException {
        BudgetPopupViewController.show(this);
    }

    @FXML
    protected void shortcutsPopUpAction() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindowApplication.class.getResource("view/keyboard-shortcuts-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Keyboard Shortcuts");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void closeButtonAction() {
        Stage stage = (Stage) closeButton.getParentPopup().getOwnerWindow();
        stage.close();
    }
}