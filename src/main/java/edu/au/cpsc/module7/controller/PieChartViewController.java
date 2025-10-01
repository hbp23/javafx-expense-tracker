package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.MainWindowApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class PieChartViewController {

    private MainWindowController mainWindowController;

    public static void show(MainWindowController mainWindowController) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindowApplication.class.getResource("view/pie-chart-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PieChartViewController controller = fxmlLoader.getController();
        controller.mainWindowController = mainWindowController;
        controller.setMainWindowController(mainWindowController);
        stage.setTitle("Pie Chart of Expenses");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private PieChart monthlyPieChart;

    @FXML
    private PieChart totalPieChart;

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
        setCharts();
    }

    public void setCharts() {
        monthlyPieChart.setData(mainWindowController.getMonthlyPieChartData());
        totalPieChart.setData(mainWindowController.getTotalPieChartData());
    }
}
