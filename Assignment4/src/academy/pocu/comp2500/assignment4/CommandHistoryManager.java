package academy.pocu.comp2500.assignment4;

public class CommandHistoryManager {
    private final Canvas canvas;

    public CommandHistoryManager(Canvas canvas) {
        this.canvas = canvas;
    }

    public boolean execute(ICommand command) {
        return command.execute(this.canvas);
    }
}
