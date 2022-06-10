package clientModule.utility;

import common.data.Chapter;
import common.data.Coordinates;
import common.data.Weapon;
import common.exceptions.IncorrectInputInScriptException;
import common.exceptions.ScriptRecursionException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.Request;
import common.utility.ResponseCode;
import common.utility.SpaceMarineLite;
import common.utility.User;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Operates command input.
 */
public class Console {
    private Stack<String> scriptFileNames = new Stack<>();
    private Stack<Scanner> scannerStack = new Stack<>();
    private JTextPane writePane;

    public Console(File script, JTextPane writePane) {
        this.scriptFileNames.add(script.getName());
        this.writePane = writePane;
    }

    /**
     * Mode for catching commands from user input.
     */
    public Request interactiveMode(ResponseCode serverResponseCode, User user, Scanner scanner) {
        String userInput = "";
        String[] userCommand = {"", ""};
        ProcessCode processCode = null;
        try {
            do {
                try {
                    if (serverResponseCode == ResponseCode.SERVER_EXIT || serverResponseCode == ResponseCode.ERROR) {
                        throw new IncorrectInputInScriptException();
                    }
                    while (!scanner.hasNextLine()) {
                        scanner.close();
                        scanner = scannerStack.pop();
                        writePane.setText(writePane.getText() + "Возвращаюсь из скрипта '" + scriptFileNames.pop() + "'!\n");
                    }
                    userInput = scanner.nextLine();
                    if (!userInput.isEmpty()) {
                        writePane.setText(writePane.getText() + userInput + "\n");
                    }
                    userCommand = (userInput.trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                } catch (NoSuchElementException | IllegalStateException exception) {
                    writePane.setText(writePane.getText() + "Произошла ошибка при вводе команды!\n");
                    userCommand = new String[]{"", ""};
                }
                processCode = checkCommand(userCommand[0], userCommand[1]);
            } while (processCode == ProcessCode.ERROR && !fileMode() || userCommand[0].isEmpty());
            try {
                switch (processCode) {
                    case OBJECT:
                        SpaceMarineLite marineToInsert = generateMarineToInsert(scanner);
                        if (marineToInsert != null) {
                            return new Request(userCommand[0], userCommand[1], marineToInsert, user);
                        }
                        return null;
                    case UPDATE_OBJECT:
                        SpaceMarineLite marineToUpdate = generateMarineToUpdate(scanner);
                        if (marineToUpdate != null) {
                            return new Request(userCommand[0], userCommand[1], marineToUpdate, user);
                        }
                        return null;
                    case SCRIPT:
                        File scriptFile = new File("src/" + userCommand[1]);
                        if (!scriptFileNames.isEmpty() && scriptFileNames.search(scriptFile.getName()) != -1) {
                            throw new ScriptRecursionException();
                        }
                        scannerStack.push(scanner);
                        scriptFileNames.push(scriptFile.getName());
                        scanner = new Scanner(scriptFile);
                        writePane.setText(writePane.getText() + "Выполняю скрипт '" + scriptFile.getName() + "'!\n");
                        interactiveMode(null, user, scanner);
                        break;
                }
            } catch (FileNotFoundException exception) {
                writePane.setText(writePane.getText() + "Файл со скриптом не найден!\n");
            } catch (ScriptRecursionException exception) {
                writePane.setText(writePane.getText() + "Скрипты не могут вызываться рекурсивно!\n");
            }
        } catch (IncorrectInputInScriptException exception) {
            writePane.setText(writePane.getText() + "Выполнение скрипта прервано!\n");
            while (!scannerStack.isEmpty()) {
                scanner.close();
                scanner = scannerStack.pop();
            }
        }
        return new Request(userCommand[0], userCommand[1], null, user);
    }



    /**
     * Launches the command.
     * @param command Command to launch.
     * @return Exit code.
     */
    private ProcessCode checkCommand(String command, String argument) {
        try {
            switch (command) {
                case "":
                    return ProcessCode.ERROR;
                case "help":
                case "show":
                case "info":
                case "clear":
                case "history":
                case "sum_of_health":
                case "average_of_heart_count":
                case "exit":
                    if (!argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.OK;
                case "insert":
                    if (argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.OBJECT;
                case "update":
                    if (argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.UPDATE_OBJECT;
                case "remove_key":
                case "remove_lower_key":
                case "remove_all_by_weapon_type":
                    if (argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.OK;
                case "execute_script":
                    if (argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.SCRIPT;
                case "remove_greater":
                    if (!argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.OBJECT;
                default:
                    writePane.setText(writePane.getText() + "Команда '" + command + "' не найдена. Наберите 'help' для справки.\n");
                    return ProcessCode.ERROR;
            }
        } catch (WrongAmountOfParametersException e) {
            writePane.setText(writePane.getText() + "Проверьте правильность ввода аргументов!\n");
        }
        return ProcessCode.OK;
    }

    private SpaceMarineLite generateMarineToInsert(Scanner scanner) {
        SpaceMarineBuilder builder = new SpaceMarineBuilder(scanner);
        if (fileMode()) {
            builder.setFileMode();
        } else {
            builder.setUserMode();
        }
        String name = builder.askName();
        Coordinates coordinates = builder.askCoordinates();
        int health = builder.askHealth();
        int heartCount = builder.askHeartCount();
        String achieve = builder.askAchievements();
        Weapon weapon = builder.askWeapon();
        Chapter chapter = builder.askChapter();
        if (name != null && coordinates.getX() != -666 && coordinates.getY() != -604 && health != -1 && heartCount != -1 && achieve != null && weapon != null && chapter.getName() != null && chapter.getParentLegion() != null) {
            return new SpaceMarineLite(name, coordinates, health, heartCount, achieve, weapon, chapter);
        }
        return null;
    }

    private SpaceMarineLite generateMarineToUpdate(Scanner scanner) {
        SpaceMarineBuilder builder = new SpaceMarineBuilder(scanner);
        if (fileMode()) {
            builder.setFileMode();
        } else {
            builder.setUserMode();
        }
        boolean askName = builder.askAboutChangingField("Хотите изменить имя космического солдата?");
        String name = askName ? builder.askName() : null;
        if (askName && name == null) return null;

        boolean askCoordinates = builder.askAboutChangingField("Хотите изменить координаты космического солдата?");
        Coordinates coordinates = askCoordinates ? builder.askCoordinates() : null;
        if (askCoordinates && coordinates == null) return null;

        boolean askHealth = builder.askAboutChangingField("Хотите изменить здоровье космического солдата?");
        int health = askHealth ? builder.askHealth() : -1;
        if (askHealth && health == -1) return null;

        boolean askHeartCount = builder.askAboutChangingField("Хотите изменить количество сердец космического солдата?");
        int heartCount = askHeartCount ? builder.askHeartCount() : -1;
        if (askHeartCount && heartCount == -1) return null;

        boolean askAchievements = builder.askAboutChangingField("Хотите изменить достижения космического солдата?");
        String achievements = askAchievements ? builder.askAchievements() : null;
        if (askAchievements && achievements == null) return null;

        boolean askWeapon = builder.askAboutChangingField("Хотите изменить оружие космического солдата?");
        Weapon weapon = askWeapon ? builder.askWeapon() : null;
        if (askWeapon && weapon == null) return null;

        boolean askChapter = builder.askAboutChangingField("Хотите изменить часть, к которой принадлежит космический солдат?");
        Chapter chapter = askChapter ? builder.askChapter() : null;
        if (askChapter && chapter == null) return null;

        return new SpaceMarineLite(
                name,
                coordinates,
                health,
                heartCount,
                achievements,
                weapon,
                chapter
        );
    }

    private boolean fileMode() {
        return !scannerStack.isEmpty();
    }
}
