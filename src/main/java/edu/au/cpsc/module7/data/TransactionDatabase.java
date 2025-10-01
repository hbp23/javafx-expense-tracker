package edu.au.cpsc.module7.data;

import edu.au.cpsc.module7.model.Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionDatabase implements Serializable {

    // fields
    private List<Transaction> transactions;

    // constructor
    public TransactionDatabase() { transactions = new ArrayList<>(); }

    // methods
    public List<Transaction> getTransactions() { return transactions; }
    public void addTransaction(Transaction t) { transactions.add(t); }
    public void removeTransaction(Transaction t) { transactions.remove(t); }
    public void updateTransaction(Transaction t) {}

    public double totalBalanceCurrentMonth() {
        double sum = 0.0;
        int currMonth = LocalDate.now().getMonthValue();
        for (Transaction t: transactions) {
            if (t.getTransactionDate().getMonthValue() == currMonth) {
                sum += t.getTransactionAmount();
            }
        }
        return sum;
    }

    public double totalBalanceLastMonth() {
        double sum = 0.0;
        int lastMonth = LocalDate.now().minusMonths(1).getMonthValue();
        for (Transaction t: transactions) {
            if (t.getTransactionDate().getMonthValue() == lastMonth) {
                sum += t.getTransactionAmount();
            }
        }
        return sum;
    }

    public Map<String, Double> getTotalExpenseCategories() {
        List<Transaction> transactions = getTransactions();
        Map<String, Double> expenseCategories = new HashMap<>();
        for (Transaction t : transactions) {
            String category = t.getTransactionCategory();
            Double amount = t.getTransactionAmount();
            expenseCategories.put(category, expenseCategories.getOrDefault(category, 0.0) + amount);
        }
        return expenseCategories;
    }

    public Map<String, Double> getMonthlyExpenseCategories() {
        List<Transaction> transactions = getTransactions();
        Map<String, Double> expenseCategories = new HashMap<>();
        int currMonth = LocalDate.now().getMonthValue();
        for (Transaction t : transactions) {
            if (t.getTransactionDate().getMonthValue() == currMonth) {
                String category = t.getTransactionCategory();
                Double amount = t.getTransactionAmount();
                expenseCategories.put(category, expenseCategories.getOrDefault(category, 0.0) + amount);
            }
        }
        return expenseCategories;
    }
}
