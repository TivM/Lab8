package serverModule.commands;

import common.data.SpaceMarine;
import common.exceptions.*;
import common.utility.SpaceMarineLite;
import common.utility.User;
import resources.LocaleBundle;
import serverModule.utility.CollectionManager;
import serverModule.utility.DatabaseCollectionManager;
import serverModule.utility.ResponseOutputer;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("remove_greater {element}", "removeGreaterCommandDescription");
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
            if (!argument.isEmpty() || objectArgument == null) throw new WrongAmountOfParametersException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            SpaceMarineLite marineLite = (SpaceMarineLite) objectArgument;
            SpaceMarine marineToCompare = new SpaceMarine(
                    collectionManager.generateId(),
                    marineLite.getName(),
                    marineLite.getCoordinates(),
                    LocalDateTime.now(),
                    marineLite.getHealth(),
                    marineLite.getHeartCount(),
                    marineLite.getAchievements(),
                    marineLite.getWeaponType(),
                    marineLite.getChapter(),
                    user
            );
            List<SpaceMarine> marines = collectionManager.getGreater(marineToCompare);
            for (SpaceMarine marine : marines) {
                if (!marine.getOwner().equals(user)) continue;
                if (!databaseCollectionManager.checkSpaceMarineByIdAndUserId(marine.getId(), user)) throw new IllegalDatabaseEditException();
                databaseCollectionManager.deleteSpaceMarineById(marine.getId());
                collectionManager.removeByValue(marine);
            }
            ResponseOutputer.append("removeGreaterSuccess");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ResponseOutputer.append("У этой команды нет параметров!\n");
        } catch (EmptyCollectionException exception) {
            ResponseOutputer.append("emptyError");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("databaseError");
        } catch (IllegalDatabaseEditException exception) {
            ResponseOutputer.append("illegalError");
        }
        return false;
    }
}
