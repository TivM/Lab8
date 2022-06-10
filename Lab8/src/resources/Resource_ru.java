package resources;

import java.util.ListResourceBundle;

public class Resource_ru extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"back_button", "Назад"},
                    {"nameTitle", "Имя"},
                    {"healthTitle", "Здоровье"},
                    {"heartTitle", "Сердца"},
                    {"achieveTitle", "Достижения"},
                    {"weaponTitle", "Оружие"},
                    {"chapterLegionTitle", "Легион"},
                    {"remove_button", "Удалить"},
                    {"login_button", "Войти"},
                    {"marineData_Title", "Данные о Spacemarine"},
                    {"coordinatesData_Title", "Координаты"},
                    {"chapterData_Title", "Часть"},
                    {"registration", "Регистрация"},

                    {"startMenu_closeButton", "Закрыть"},

                    {"mainMenu_signOutButton", "Выйти"},
                    {"mainMenu_insertButton", "insert"},
                    {"mainMenu_updateButton", "update"},
                    {"mainMenu_showButton", "show"},
                    {"mainMenu_sumHealthButton", "sumHealth"},
                    {"mainMenu_visualizeButton", "visualize"},
                    {"mainMenu_averHeartButton", "averHeart"},
                    {"mainMenu_byKeyButton", "byKey"},
                    {"mainMenu_greaterButton", "greater"},
                    {"mainMenu_infoButton", "info"},
                    {"mainMenu_historyButton", "history"},
                    {"mainMenu_scriptButton", "script"},
                    {"mainMenu_helpButton", "help"},
                    {"mainMenu_sumHealthButton", "sumHealth"},
                    {"mainMenu_label1", "Добавление/изменение"},
                    {"mainMenu_label2", "Информация об объектах"},
                    {"mainMenu_label3", "Удаление элементов"},
                    {"mainMenu_label4", "Служебные команды"},

                    {"filter_label1", "Поле фильтра:"},
                    {"filter_label2", "Аргумент:"},
                    {"filter_label3", "Тип фильтра:"},
                    {"filter_filterButton", "Фильтровать"},

                    {"login_name", "Вход"},
                    {"login_loginName", "Логин"},
                    {"login_passwordName", "Пароль"},

                    {"register_loginName", "Логин"},
                    {"register_passwordName", "Пароль"},
                    {"register_confirmPassword", "Подтверждение пароля"},

                    {"show_filterButton", "Фильтр"},
                    {"show_resetButton", "Сброс"},
                    {"show_updateButton", "Обновить"},
                    {"show_keyColumn", "ключ"},
                    {"show_nameColumn", "имя"},
                    {"show_dateColumn", "дата"},
                    {"show_healthColumn", "здоровье"},
                    {"show_heartColumn", "сердца"},
                    {"show_achieveColumn", "достижения"},
                    {"show_weaponColumn", "оружие"},
                    {"show_chapterNameColumn", "имяЧасти"},
                    {"show_chapterLegionColumn", "легионЧасти"},
                    {"show_userColumn", "Владелец"},

                    {"script_chooseFileButton", "Выбрать файл"},

                    {"removeKey_label1", "Введите ключ"},

                    {"removeByWeapon_label1", "Выберите оружие"},

                    {"insert_keyName", "Ключ"},
                    {"insert_insertButton", "Добавить"},

                    {"averCommandDescription", "вывести среднее значение поля heartCount для всех элементов коллекции"},
                    {"clearCommandDescription", "очистить коллекцию"},
                    {"scriptCommandDescription", "исполнить скрипт из указанного файла"},
                    {"exitCommandDescription", "завершить программу"},
                    {"helpCommandDescription", "вывести справку по доступным командам"},
                    {"historyCommandDescription", "вывести историю использованных команд"},
                    {"infoCommandDescription", "вывести информацию о коллекции"},
                    {"removeWeaponCommandDescription", "удалить из коллекции все элементы, значение поля weaponType которого эквивалентно заданному"},
                    {"removeGreaterCommandDescription", "удалить из коллекции все элементы, превышающие заданный"},
                    {"removeKeyCommandDescription", "удалить элемент из коллекции по его ключу"},
                    {"removeLowerKeyCommandDescription", "удалить из коллекции все элементы, ключ которых меньше, чем заданный"},
                    {"showCommandDescription", "вывести все элементы коллекции"},
                    {"signInCommandDescription", "войти в аккаунт"},
                    {"logOutCommandDescription", "выйти из аккаунта"},
                    {"signUpCommandDescription", "регистрация нового пользователя"},
                    {"sumHealthCommandDescription", "вывести сумму значений поля health для всех элементов коллекции"},
                    {"updateCommandDescription", "обновить значение элемента коллекции по id"},
                    {"loginCommandDescription", "авторизоваться"},
                    {"insertCommandDescription", "добавить элемент"},

                    {"nonUserError", "Необходимо авторизоваться!"},
                    {"illegalError", "Произошло нелегальное изменение объекта в базе данных!\nПерезапустите клиент для избежания ошибок!"},
                    {"permissionError", "Принадлежащие другим пользователям объекты доступны только для чтения!"},
                    {"databaseError", "Произошла ошибка при обращении к базе данных!"},
                    {"emptyError", "Коллекция пуста!"},
                    {"notFoundError", "Космический десант не найден!"},
                    {"multiUserError", "Этот пользователь уже авторизован!"},
                    {"userNotFoundError", "Неправильные имя пользователя или пароль!"},
                    {"classCastError", "Переданный клиентом объект неверен!"},
                    {"userExistError", "Этот пользователь уже существует!"},

                    {"clearCommandSuccess", "Коллекция успешно очищена!"},

                    {"infoCommandText1", "в данной сессии инициализации еще не происходило"},
                    {"infoCommandText2", "Информация о коллекции:"},
                    {"infoCommandText3", " Тип: "},
                    {"infoCommandText4", " Количество элементов: "},
                    {"infoCommandText5", " Дата последней инициализации: "},

                    {"insertCommandSuccess", "Успешно добавлено в коллекцию!"},

                    {"removeWeaponSuccessText", "Успешно удалены все солдаты с типом оружия: "},

                    {"removeGreaterSuccess", "Солдаты успешно удалены!"},

                    {"removeKeySuccess", "Солдат успешно удален!"},

                    {"signInCommandSuccess", "Авторизация прошла успешно!"},

                    {"signUpCommandSuccess", "Регистрация прошла успешно!"},

                    {"sumHealthCommandText", "Сумма здоровья всех космических десантов: "},

                    {"updateCommandSuccess", "Успешно изменено!"},

                    {"averCommandText", "Среднее значение поля heartCount всех космических десантов: "},

                    {"historyError", "Ни одной команды еще не было использовано!"},
                    {"historyText", "Последние использованные команды:"},

                    {"ioPaneError", "Произошла ошибка при отправке запроса на сервер!"},
                    {"classNotFoundError", "Произошла ошибка при получении ответа с сервера!"},

                    {"filterOptionPaneError1", "Не выбрано поле"},
                    {"filterOptionPaneError2", "Не введен аргумент!"},
                    {"filterOptionPaneError3", "Не выбран тип фильтра!"},

                    {"valuesOptionPaneError13", "Ключ должен быть числом!\n"},
                    {"valuesOptionPaneError14", "Ключ должен быть больше 0!\n"},

                    {"valuesOptionPaneError1", "Значение поля 'name' не может быть пустым!\n"},
                    {"valuesOptionPaneError2", "Здоровье должно быть числом!\n"},
                    {"valuesOptionPaneError3", "Здоровье должно быть больше 0!\n"},
                    {"valuesOptionPaneError4", "Кол-во сердец должно быть числом!\n"},
                    {"valuesOptionPaneError5", "Кол-во сердец должно быть в пределе от 1 до 3!\n"},
                    {"valuesOptionPaneError6", "Значение поля 'achievements' не может быть пустым!\n"},
                    {"valuesOptionPaneError7", "Координата 'x' должна быть числом!\n"},
                    {"valuesOptionPaneError8", "Координата 'x' должна быть больше -666!\n"},
                    {"valuesOptionPaneError9", "Координата 'y' должна быть числом!\n"},
                    {"valuesOptionPaneError10", "Координата 'y' должна быть больше -666!\n"},
                    {"valuesOptionPaneError11", "Значение поля 'chapterName' не может быть пустым!\n"},
                    {"valuesOptionPaneError12", "Значение поля 'chapterLegion' не может быть пустым!\n"},

                    {"valuesOptionPaneError15", "Значение 'weapon' не может быть пустым!"},
                    {"valuesOptionPaneError16", "Значение 'weapon' не может быть таким!"},

                    {"registerOptionPaneError", "Пароли различаются!"},

                    {"scriptOptionPaneError1", "Не удалось выполнить команду! Проверьте правильность ввода аргументов!\n"},
                    {"scriptOptionPaneError2", "Не удалось найти файл!"}
            };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
