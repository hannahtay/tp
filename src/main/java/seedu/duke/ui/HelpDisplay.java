package seedu.duke.ui;

/**
 * Displays concise help information, categorized and with customizable headings.
 */
public class HelpDisplay {

    private static final String HELP_HEADER = "===== Budget Tracker Help =====";
    private static final String HELP_FOOTER = "===============================";

    // Category Headings
    private static final String INCOME_HEADING = "--- Income Management ---";
    private static final String EXPENSE_HEADING = "--- expenses.Expense Management ---";
    private static final String SAVINGS_HEADING = "--- Savings Management ---";
    private static final String GOALS_HEADING = "--- Savings Goals ---";
    private static final String GENERAL_HEADING = "--- General Commands ---";


    private final StringBuilder helpText = new StringBuilder();

    /**
     * Constructs a HelpDisplay object and builds the help text.
     */
    public HelpDisplay() {
        buildHelpText();
    }

    /**
     * Builds the complete help text string, organized by category.
     */
    private void buildHelpText() {
        helpText.append(HELP_HEADER).append("\n\n");

        addCategory(INCOME_HEADING);
        addCommandHelp("add income <AMOUNT> / <SOURCE>", "Adds an income record.");
        addCommandHelp("delete income <INDEX>", "Deletes an income record by index.");
        addCommandHelp("view income", "Lists all income records.");

        addCategory(EXPENSE_HEADING);
        addCommandHelp("add expense <AMOUNT> / <SOURCE>", "Adds an expense record.");
        addCommandHelp("delete expense <INDEX>", "Deletes an expense record by index.");
        addCommandHelp("view expense", "Lists all expense records.");

        addCategory(SAVINGS_HEADING);
        addCommandHelp("add savings <AMOUNT>", "Adds a savings record.");
        addCommandHelp("delete savings <INDEX>", "Deletes a savings record by index.");
        addCommandHelp("view savings", "Lists all savings records.");

        addCategory(GOALS_HEADING);
        addCommandHelp("savings goal set <AMOUNT> / <DESCRIPTION>", "Sets a new savings goal.");
        addCommandHelp("savings goal view", "Views all current savings goals.");
        addCommandHelp("savings goal update <INDEX> <AMOUNT> / <DESCRIPTION>"
                , "Updates an existing savings goal.");
        addCommandHelp("savings goal delete <INDEX>"
                , "Deletes a savings goal by index.");

        addCategory(GENERAL_HEADING);
        addCommandHelp("help", "Displays this help message.");

        helpText.append(HELP_FOOTER);
    }

    /**
     * Adds a category heading to the help text.
     *
     * @param heading The heading text.
     */
    private void addCategory(String heading) {
        helpText.append("\n").append(heading).append("\n");
    }

    /**
     * Adds help text for a single command.
     *
     * @param command     The command syntax.
     * @param description A brief description of the command.
     */
    private void addCommandHelp(String command, String description) {
        helpText.append(String.format("%-60s %s\n", command, description));
    }

    /**
     * Gets the complete help text.
     *
     * @return The help text as a String.
     */
    public String getHelpText() {
        return helpText.toString();
    }

    /**
     * Displays the help text to the console.
     */
    public void displayHelp() {
        System.out.println(helpText);
    }
}
