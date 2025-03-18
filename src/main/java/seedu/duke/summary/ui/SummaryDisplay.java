package seedu.duke.summary.ui;

import seedu.duke.summary.Summary;

/**
 * Class responsible for formatting and displaying the summary information.
 */
public class SummaryDisplay {
    private static final String SUMMARY_HEADER = "===== BUDGET SUMMARY =====";
    private static final String SUMMARY_FOOTER = "===========================";
    private static final String INCOME_LABEL = "Total Income:";
    private static final String EXPENSES_LABEL = "Total Expenses:";
    private static final String BALANCE_LABEL = "Available Balance:";
    private static final String SAVINGS_LABEL = "Total Savings:";
    private Summary summary;

    /**
     * Constructs a new SummaryDisplay object with summary to be printed.
     */
    public SummaryDisplay(Summary summary) {
        this.summary = summary;
    }
    /**
     * Displays the summary data (income, expenses, balance, savings) to the console.
     */
    public String displaySummary() {
        StringBuilder sb = new StringBuilder(); // Use StringBuilder for efficiency
        sb.append(SUMMARY_HEADER).append("\n");
        sb.append(String.format("%-20s $%.2f%n", INCOME_LABEL, summary.getTotalIncome()));
        sb.append(String.format("%-20s $%.2f%n", EXPENSES_LABEL, summary.getTotalExpense()));
        sb.append(String.format("%-20s $%.2f%n", BALANCE_LABEL, summary.getTotalBalance()));
        sb.append(String.format("%-20s $%.2f%n", SAVINGS_LABEL, summary.getTotalSavings()));
        sb.append(SUMMARY_FOOTER);
        return sb.toString(); // Return the formatted string
    }
}
