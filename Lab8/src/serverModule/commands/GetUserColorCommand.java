package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.User;
import serverModule.utility.DatabaseUserManager;
import serverModule.utility.ResponseOutputer;

public class GetUserColorCommand extends AbstractCommand{
    private DatabaseUserManager databaseUserManager;

    public GetUserColorCommand(DatabaseUserManager databaseUserManager) {
        super("get_user_color", "служебная команда");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfParametersException();
            String color = databaseUserManager.getColorByUsername(user);
            ResponseOutputer.append(color);
            return true;
        } catch (WrongAmountOfParametersException e) {
            ResponseOutputer.append("У этой команды должен быть только один параметр: 'user'\n");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("Произошла ошибка при обращении к базе данных!\n");
        } catch (ClassCastException e) {
            ResponseOutputer.append("Переданный клиентом объект неверен!\n");
        }
        return false;
    }
}
