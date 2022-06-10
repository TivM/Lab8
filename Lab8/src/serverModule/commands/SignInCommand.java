package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.MultiUserException;
import common.exceptions.UserNotFoundException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.User;
import resources.LocaleBundle;
import serverModule.utility.DatabaseUserManager;
import serverModule.utility.ResponseOutputer;

public class SignInCommand extends AbstractCommand{
    private DatabaseUserManager databaseUserManager;

    public SignInCommand(DatabaseUserManager databaseUserManager) {
        super("sign_in", "signInCommandDescription");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfParametersException();
            if (databaseUserManager.checkUserByUsernameAndPassword(user)) {
                ResponseOutputer.append("signInCommandSuccess");
                databaseUserManager.updateOnline(user, true);
            }
            else throw new UserNotFoundException();
            return true;
        } catch (WrongAmountOfParametersException e) {
            ResponseOutputer.append("У этой команды должен быть только один параметр: 'user'\n");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("databaseError");
        } catch (UserNotFoundException e) {
            ResponseOutputer.append("userNotFoundError");
        } catch (ClassCastException e) {
            ResponseOutputer.append("classCastError");
        } catch (MultiUserException e) {
            ResponseOutputer.append("multiUserError");
        }
        return false;
    }
}
