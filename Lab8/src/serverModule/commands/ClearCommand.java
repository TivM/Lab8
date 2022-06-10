package serverModule.commands;

import common.data.SpaceMarine;
import common.exceptions.*;
import common.utility.User;
import resources.LocaleBundle;
import serverModule.utility.CollectionManager;
import serverModule.utility.DatabaseCollectionManager;
import serverModule.utility.ResponseOutputer;

/**
 * Command 'clear'. Clears the collection.
 */
public class ClearCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public ClearCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("clear", "clearCommandDescription");
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
            if(!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfParametersException();
            for (SpaceMarine marine : collectionManager.getCollection().values()) {
                if (!marine.getOwner().equals(user)) throw new PermissionDeniedException();
                if (!databaseCollectionManager.checkSpaceMarineByIdAndUserId(marine.getId(), user)) throw new IllegalDatabaseEditException();
            }
            databaseCollectionManager.clearCollection();
            collectionManager.clearCollection();
            ResponseOutputer.append("clearCommandSuccess");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ResponseOutputer.append("У этой команды нет параметров!\n");
        } catch (PermissionDeniedException exception) {
            ResponseOutputer.append("permissionError");
        } catch (DatabaseManagerException exception) {
            ResponseOutputer.append("databaseError");
        } catch (IllegalDatabaseEditException exception) {
            ResponseOutputer.append("illegalError");
        }
        return false;
    }
}
