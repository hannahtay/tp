package seedu.duke;

import seedu.duke.expenses.Ui;
import seedu.duke.summary.Summary;
import seedu.duke.summary.ui.SummaryDisplay;
import seedu.duke.ui.HelpDisplay;
import seedu.duke.expenses.BudgetTracker;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.BudgetTrackerException;
import seedu.duke.expenses.ExpenseParser;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello!");

        Summary summary = new Summary();
        SummaryDisplay summaryDisplay = new SummaryDisplay(summary);
        HelpDisplay helpDisplay = new HelpDisplay();
        Ui ui = new Ui();
        BudgetTracker tracker = new BudgetTracker();

        while (true) {

            try {
                String fullCommand = in.nextLine();
                Command command = ExpenseParser.parse(fullCommand);
                if (command != null) {
                    tracker.executeCommand(command, ui);
                }
                if (command.isExit()) {
                    break;
                }
            } catch (BudgetTrackerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

