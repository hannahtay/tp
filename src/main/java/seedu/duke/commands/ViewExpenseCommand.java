package seedu.duke.commands;

import seedu.duke.expenses.ExpenseList;
import seedu.duke.expenses.Ui;

public class ViewExpenseCommand extends Command {

    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        expenseList.showExpenses();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
