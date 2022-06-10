package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.UserAlreadyExistException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.User;
import resources.LocaleBundle;
import serverModule.utility.DatabaseUserManager;
import serverModule.utility.ResponseOutputer;

public class SignUpCommand extends AbstractCommand{
    private DatabaseUserManager databaseUserManager;

    public SignUpCommand(DatabaseUserManager databaseUserManager) {
        super("sign_up", "signUpCommandDescription");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (!argument.isEmpty() || objectArgument != null || user == null) throw new WrongAmountOfParametersException();
            if (databaseUserManager.insertUser(user))
                ResponseOutputer.append("signUpCommandSuccess");
            else throw new UserAlreadyExistException();
            return true;
        } catch (WrongAmountOfParametersException e) {
            ResponseOutputer.append("У этой команды должен быть только один параметр: 'user'\n");
        } catch (DatabaseManagerException exception) {
            ResponseOutputer.append("databaseError");
        } catch (UserAlreadyExistException e) {
            ResponseOutputer.append("userExistError");
        } catch (ClassCastException e) {
            ResponseOutputer.append("classCastError");
        }
        return false;
    }
}
