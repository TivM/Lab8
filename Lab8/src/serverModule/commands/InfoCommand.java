package serverModule.commands;

import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.User;
import resources.LocaleBundle;
import serverModule.utility.CollectionManager;
import serverModule.utility.ResponseOutputer;

import java.time.LocalDateTime;

public class InfoCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "infoCommandDescription");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try{
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfParametersException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "infoCommandText1" :
                                        lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();
            ResponseOutputer.append("infoCommandText2");
            ResponseOutputer.append("infoCommandText3:" + collectionManager.collectionType());
            ResponseOutputer.append("infoCommandText4:" + collectionManager.collectionSize());
            ResponseOutputer.append("infoCommandText5:" + lastInitTimeString);
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ResponseOutputer.append("У этой команды нет параметров!\n");
        }
        return false;
    }
}
