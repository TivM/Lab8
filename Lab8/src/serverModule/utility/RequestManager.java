package serverModule.utility;

import common.utility.Request;
import common.utility.Response;
import common.utility.ResponseCode;
import common.utility.User;

public class RequestManager {
    private CommandManager commandManager;
    private CollectionManager collectionManager;

    public RequestManager(CommandManager commandManager, CollectionManager collectionManager) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }

    public Response manage(Request request) {
        User hashUser = new User(
                    request.getUser().getLogin(), DataHasher.hash(request.getUser().getPassword() + "!Hq78p@T"), request.getUser().getColor());
        commandManager.addToHistory(request.getCommandName(), request.getUser());
        ResponseCode responseCode = executeCommand(request.getCommandName(), request.getArgument(), request.getObjectArgument(), hashUser);
        if (request.getCommandName().equals("show")) return new Response(responseCode, ResponseOutputer.getAndClear(), collectionManager.getCollection());
        return new Response(responseCode, ResponseOutputer.getAndClear(), null);
    }

    private synchronized ResponseCode executeCommand(String command, String argument, Object objectArgument, User user) {
        switch (command) {
            case "":
                break;
            case "help":
                if (!commandManager.help(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "info":
                if (!commandManager.info(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "show":
                if (!commandManager.show(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "insert":
                if (!commandManager.insert(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "update":
                if (!commandManager.update(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "remove_key":
                if (!commandManager.removeKey(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "clear":
                if (!commandManager.clear(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "execute_script":
                if (!commandManager.executeScript(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "exit":
                if (!commandManager.exit(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "remove_greater":
                if (!commandManager.removeGreater(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "history":
                if (!commandManager.history(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "remove_lower_key":
                if (!commandManager.removeLowerKey(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "remove_all_by_weapon_type":
                if (!commandManager.removeAllByWeaponType(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "sum_of_health":
                if (!commandManager.sumOfHealth(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "average_of_heart_count":
                if (!commandManager.averageOfHeartCount(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "sign_up":
                if (!commandManager.sign_up(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "sign_in":
                if (!commandManager.sign_in(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "log_out":
                if (!commandManager.log_out(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "get_user_color":
                if (!commandManager.get_user_color(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "switch_off_user":
                if (!commandManager.switch_off_user(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            default:
                ResponseOutputer.append("Команда '" + command + "' не найдена. Наберите 'help' для справки.\n");
                return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }
}
