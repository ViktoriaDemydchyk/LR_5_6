package Command;

import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private final Map<Integer, Command> commandMap = new HashMap<>();

    public void setCommand(int option, Command command) {
        commandMap.put(option, command);
    }

    public int getCommandSize(){
        return commandMap.size();
    }

    public void executeCommand(int option) {
        Command command = commandMap.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid selection");
        }
    }
}
