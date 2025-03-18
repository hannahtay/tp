package seedu.duke.commands;

import seedu.duke.expenses.ExpenseList;
import seedu.duke.expenses.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        ui.showMessage("Exiting the application. Goodbye!");
    }

    @Override
    public boolean isExit() {
        return true; // Indicates the program should exit
    }
}
