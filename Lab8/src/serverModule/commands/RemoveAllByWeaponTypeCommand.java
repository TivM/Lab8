package serverModule.commands;

import common.data.SpaceMarine;
import common.data.Weapon;
import common.exceptions.*;
import common.utility.User;
import resources.LocaleBundle;
import serverModule.utility.CollectionManager;
import serverModule.utility.DatabaseCollectionManager;
import serverModule.utility.ResponseOutputer;

import java.util.List;

/**
 * Command 'remove_all_by_weapon_type'. Removes from the collection all items whose value of the weaponType field is equivalent to the specified one.
 */
public class RemoveAllByWeaponTypeCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public RemoveAllByWeaponTypeCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("remove_all_by_weapon_type <weaponType>", "removeWeaponCommandDescription");
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
            if (argument.isEmpty() || objectArgument != null) throw new WrongAmountOfParametersException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            Weapon weapon = Weapon.valueOf(argument.toUpperCase());
            List<SpaceMarine> marines = collectionManager.getAllByWeaponType(weapon);
            for (SpaceMarine marine : marines) {
                if (!marine.getOwner().equals(user)) continue;
                if (!databaseCollectionManager.checkSpaceMarineByIdAndUserId(marine.getId(), user)) throw new IllegalDatabaseEditException();
                databaseCollectionManager.deleteSpaceMarineById(marine.getId());
                collectionManager.removeByValue(marine);
            }
            ResponseOutputer.append("removeWeaponSuccessText:" + weapon.toString());
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ResponseOutputer.append("Вместе с этой командой должен быть передан параметр! Наберит 'help' для справки\n");
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
