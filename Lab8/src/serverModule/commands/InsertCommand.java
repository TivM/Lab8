package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.SpaceMarineLite;
import common.utility.User;
import resources.LocaleBundle;
import serverModule.utility.CollectionManager;
import serverModule.utility.DatabaseCollectionManager;
import serverModule.utility.ResponseOutputer;

/**
 * Command 'insert'. Adds a new element to collection.
 */
public class InsertCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public InsertCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("insert null {element}", "insertCommandDescription");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (argument.isEmpty() || objectArgument == null) throw new WrongAmountOfParametersException();
            int key = Integer.parseInt(argument);
            SpaceMarineLite marineLite = (SpaceMarineLite) objectArgument;
            collectionManager.addToCollection(key, databaseCollectionManager.insertSpaceMarine(marineLite, user, key));
            ResponseOutputer.append("insertCommandSuccess");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ResponseOutputer.append("Вместе с этой командой должен быть передан параметр! Наберит 'help' для справки\n");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("databaseError");
        }
        return false;
    }
}
