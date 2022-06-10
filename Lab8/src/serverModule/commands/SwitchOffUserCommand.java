package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.MultiUserException;
import common.exceptions.UserNotFoundException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.User;
import serverModule.utility.DatabaseUserManager;
import serverModule.utility.ResponseOutputer;

public class SwitchOffUserCommand extends AbstractCommand{
    private DatabaseUserManager databaseUserManager;

    public SwitchOffUserCommand(DatabaseUserManager databaseUserManager) {
        super("switch_off_user", "служебная команда");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfParametersException();
            databaseUserManager.updateOnline(user, false);
            return true;
        } catch (WrongAmountOfParametersException e) {
            ResponseOutputer.append("У этой команды должен быть только один параметр: 'user'\n");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("databaseError");
        } catch (ClassCastException e) {
            ResponseOutputer.append("classCastError");
        }
        return false;
    }
}
