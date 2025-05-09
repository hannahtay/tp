package alerts;

import expenses.Ui;
import exceptions.BudgetTrackerException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Alerts when funds fall below a warning threshold.
 * This class observes financial data without being tightly coupled to Summary.
 */
public class FundsAlert implements FinancialObserver {
    private static final double MAX_THRESHOLD = 10000000.0;
    private static final Logger logger = Logger.getLogger(FundsAlert.class.getName());
    private double warningThreshold;
    private final Ui ui;
    
    /**
     * Creates a new FundsAlert with the default threshold of $5.
     * 
     * @param ui UI handler for displaying alerts
     */
    public FundsAlert(Ui ui) {
        assert ui != null : "Ui object cannot be null in FundsAlert constructor";
        this.warningThreshold = 5.0;
        this.ui = ui;
        assert warningThreshold > 0 : "Warning threshold must be positive";
        logger.log(Level.INFO, "FundsAlert initialized with threshold: " + warningThreshold);
    }
    
    /**
     * Changes the warning threshold.
     * 
     * @param newThreshold The new warning threshold
     * @return true if the threshold was changed successfully
     * @throws BudgetTrackerException if the threshold is negative
     */
    public boolean setWarningThreshold(double newThreshold) throws BudgetTrackerException {
        logger.log(Level.INFO, "Attempting to set warning threshold to: " + newThreshold);
        if (newThreshold < 0) {
            logger.log(Level.WARNING, "Attempted to set negative warning threshold: " + newThreshold);
            throw new BudgetTrackerException("Warning threshold cannot be negative");
        }
        if (newThreshold > MAX_THRESHOLD) {
            logger.log(Level.WARNING, "Attempted to set threshold exceeding maximum limit " +
                    "(" + MAX_THRESHOLD + "): " + newThreshold);
            throw new BudgetTrackerException("Warning threshold cannot be too large. " +
                    "The maximum allowed is " + MAX_THRESHOLD + ".");
        }
        
        assert newThreshold >= 0 : "Warning threshold must be non-negative";
        
        double oldThreshold = this.warningThreshold;
        this.warningThreshold = newThreshold;
        
        logger.log(Level.INFO, "Funds alert threshold changed from " + 
                               String.format("%.2f", oldThreshold) + " to " + 
                               String.format("%.2f", newThreshold));

        assert this.warningThreshold == newThreshold : "Threshold was not properly updated";
        assert this.warningThreshold != oldThreshold || newThreshold == oldThreshold : 
                "Threshold did not change when it should have";
        assert this.warningThreshold >= 0 : "Warning threshold should be non-negative after validation";
        
        return true;
    }
    
    /**
     * Gets the current warning threshold.
     * 
     * @return The current warning threshold
     */
    public double getWarningThreshold() {
        assert warningThreshold >= 0 : "Warning threshold should never be negative";
        return warningThreshold;
    }
    
    @Override
    public void update(double availableFunds, double totalIncome, double totalExpense, double totalSavings) {
        logger.log(Level.FINE, "FundsAlert update called. Available funds: "
                + availableFunds + ", Threshold: " + warningThreshold);
        assert availableFunds == totalIncome - totalExpense :
                "Available funds calculation is inconsistent";
        checkAndDisplayAlert(availableFunds);
    }
    
    /**
     * Checks funds against threshold and displays alert if needed.
     * 
     * @param availableFunds Current available funds
     */
    private void checkAndDisplayAlert(double availableFunds) {
        assert warningThreshold >= 0 : "Warning threshold should always be non-negative here";
        assert availableFunds >= 0 : "Available funds should always be non-negative";
        
        if (availableFunds < warningThreshold) {
            String message = "WARNING: Available funds ($" + String.format("%.2f", availableFunds) + 
                    ") are below warning threshold ($" + String.format("%.2f", warningThreshold) + ")";
            logger.log(Level.WARNING, "Alert triggered: " + message);
            ui.showAlert(message);
        } else {
            logger.log(Level.FINE, "Funds check for alerts passed: Available funds ($" + 
                                 String.format("%.2f", availableFunds) + ") >= Threshold ($" + 
                                 String.format("%.2f", warningThreshold) + ")");
        }
    }
    
    /**
     * Displays the initial notification about the funds alert feature.
     */
    public void displayInitialNotification() {
        assert ui != null : "UI handler cannot be null when displaying notifications";
        assert warningThreshold >= 0 : "Warning threshold should be non-negative";
        
        String message = "Funds Alert feature is active. You will be warned when available funds fall below $" 
                + String.format("%.2f", warningThreshold) + ".";
        ui.showMessage(message);
        logger.log(Level.INFO, "Displayed initial funds alert notification. " +
                "Threshold: $" + String.format("%.2f", warningThreshold));
        ui.showMessage("Use 'alert set <amount>' to change this threshold.");
    }
}
