package resources;

import java.util.ListResourceBundle;

public class Resource_uk extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"back_button", "Назад"},
                    {"nameTitle", "Ім'я"},
                    {"healthTitle", "Здоров'я"},
                    {"heartTitle", "Серце"},
                    {"achieveTitle", "Досягнення"},
                    {"weaponTitle", "Зброя"},
                    {"chapterLegionTitle", "Легіон"},
                    {"remove_button", "Видалити"},
                    {"login_button", "Увійти"},
                    {"marineData_Title", "Дані про Spacemarine"},
                    {"coordinatesData_Title", "Координата"},
                    {"chapterData_Title", "Частина"},
                    {"registration", "Реєстрація"},

                    {"startMenu_closeButton", "Заплющити"},

                    {"mainMenu_signOutButton", "Вийти"},
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
                    {"mainMenu_label1", "Додавання/зміна"},
                    {"mainMenu_label2", "Інформація про об'єкти"},
                    {"mainMenu_label3", "Видалення елементів"},
                    {"mainMenu_label4", "Службові команди"},

                    {"filter_label1", "Поле фільтра:"},
                    {"filter_label2", "Аргумент:"},
                    {"filter_label3", "Тип фільтра:"},
                    {"filter_filterButton", "Фільтрувати"},

                    {"login_name", "Вход"},
                    {"login_loginName", "Логін"},
                    {"login_passwordName", "Пароль"},

                    {"register_loginName", "Логін"},
                    {"register_passwordName", "Пароль"},
                    {"register_confirmPassword", "Підтвердження пароля"},

                    {"show_filterButton", "Фільтр"},
                    {"show_resetButton", "Скидання"},
                    {"show_updateButton", "Оновити"},
                    {"show_keyColumn", "ключ"},
                    {"show_nameColumn", "iм'я"},
                    {"show_dateColumn", "дата"},
                    {"show_healthColumn", "здоров'я"},
                    {"show_heartColumn", "серце"},
                    {"show_achieveColumn", "досягнення"},
                    {"show_weaponColumn", "зброя"},
                    {"show_chapterNameColumn", "iм'яЧастина"},
                    {"show_chapterLegionColumn", "легiонЧастина"},
                    {"show_userColumn", "Власник"},

                    {"script_chooseFileButton", "Вибрати файл"},

                    {"removeKey_label1", "Введіть ключ"},

                    {"removeByWeapon_label1", "Виберіть зброю"},

                    {"insert_keyName", "Ключ"},
                    {"insert_insertButton", "Додавши"},

                    {"averCommandDescription", "вивести середнє значення поля heartCount для всіх елементів колекції"},
                    {"clearCommandDescription", "очистити колекцію"},
                    {"scriptCommandDescription", "виконати скрипт із зазначеного файлу"},
                    {"exitCommandDescription", "завершити програму"},
                    {"helpCommandDescription", "вивести довідку за доступними командам"},
                    {"historyCommandDescription", "вивести історію використаних команд"},
                    {"infoCommandDescription", "вивести інформацію про колекцію"},
                    {"removeWeaponCommandDescription", "видалити з колекції всі елементи, значення поля weaponType якого еквівалентно заданому"},
                    {"removeGreaterCommandDescription", "видалити з колекції всі елементи, що перевищують заданий"},
                    {"removeKeyCommandDescription", "видалити елемент з колекції по його ключу"},
                    {"removeLowerKeyCommandDescription", "видалити з колекції всі елементи, ключ яких менше, ніж заданий"},
                    {"showCommandDescription", "вивести всі елементи колекції"},
                    {"signInCommandDescription", "увійти в акаунт"},
                    {"logOutCommandDescription", "вийти з облікового запису"},
                    {"signUpCommandDescription", "реєстрація нового користувача"},
                    {"sumHealthCommandDescription", "вивести суму значень поля health для всіх елементів колекції"},
                    {"updateCommandDescription", "оновити значення елемента колекції по id"},
                    {"loginCommandDescription", "авторизувати"},
                    {"insertCommandDescription", "додати елемент"},

                    {"nonUserError", "Необхідно авторизуватися!"},
                    {"illegalError", "Відбулася нелегальна зміна об'єкта в базі даних!\nПерезапустіть клієнт для уникнення помилок!"},
                    {"permissionError", "Об'єкти, що належать іншим користувачам, доступні лише для читання!"},
                    {"databaseError", "Сталася помилка при зверненні до бази даних!"},
                    {"emptyError", "Колекція порожня!"},
                    {"notFoundError", "Космічний десант не знайдено!"},
                    {"multiUserError", "Цей користувач вже авторизований!"},
                    {"userNotFoundError", "Неправильні ім'я користувача або пароль!"},
                    {"classCastError", "Переданий клієнтом об'єкт невірний!"},
                    {"userExistError", "Цей користувач вже існує!"},

                    {"clearCommandSuccess", "Колекція успішно очищена!"},

                    {"infoCommandText1", "у даній сесії ініціалізації ще не відбувалося"},
                    {"infoCommandText2", "Інформація про колекцію:"},
                    {"infoCommandText3", " Тип: "},
                    {"infoCommandText4", " Кількість елементів: "},
                    {"infoCommandText5", " Дата останньої ініціалізації: "},

                    {"insertCommandSuccess", "Успішно додано в колекцію!"},

                    {"removeWeaponSuccessText", "Успішно видалені всі солдати з типом зброї: "},

                    {"removeGreaterSuccess", "Солдати успішно видалені!"},

                    {"removeKeySuccess", "Солдат успішно видалений!"},

                    {"signInCommandSuccess", "Авторизація пройшла успішно!"},

                    {"signUpCommandSuccess", "Реєстрація пройшла успішно!"},

                    {"sumHealthCommandText", "Сума здоров'я всіх космічних десантів: "},

                    {"updateCommandSuccess", "Успішно змінено!"},

                    {"averCommandText", "Середнє значення поля heartCount всіх космічних десантів: "},

                    {"historyError", "Жодної команди ще не було використано!"},
                    {"historyText", "Останні використані команди:"},

                    {"ioPaneError", "Сталася помилка при відправці запиту на сервер!"},
                    {"classNotFoundError", "Сталася помилка при отриманні відповіді з сервера!"},

                    {"filterOptionPaneError1", "Не вибрано поле!"},
                    {"filterOptionPaneError2", "Не введений аргумент!"},
                    {"filterOptionPaneError3", "Не вибрано тип фільтра!"},

                    {"valuesOptionPaneError13", "Ключ повинен бути числом!\n"},
                    {"valuesOptionPaneError14", "Ключ повинен бути більше 0!\n"},

                    {"valuesOptionPaneError1", "Значення поля 'name' не може бути порожнім!\n"},
                    {"valuesOptionPaneError2", "Здоров'я має бути числом!\n"},
                    {"valuesOptionPaneError3", "Здоров'я має бути більше 0!\n"},
                    {"valuesOptionPaneError4", "Кількість сердець має бути числом!\n"},
                    {"valuesOptionPaneError5", "Кількість сердець має бути більше 0!\n"},
                    {"valuesOptionPaneError6", "Значення поля 'achievements' не може бути порожнім!\n"},
                    {"valuesOptionPaneError7", "Координата 'x' повинна бути числом!\n"},
                    {"valuesOptionPaneError8", "Координата 'x' повинна бути більше -666!\n"},
                    {"valuesOptionPaneError9", "Координата 'y' повинна бути числом!\n"},
                    {"valuesOptionPaneError10", "Координата 'y' повинна бути більше -666!\n"},
                    {"valuesOptionPaneError11", "Значення поля 'chapterName' не може бути порожнім!\n"},
                    {"valuesOptionPaneError12", "Значення поля 'chapterLegion' не може бути порожнім!\n"},

                    {"valuesOptionPaneError15", "Значення 'weapon' не може бути порожнім!"},
                    {"valuesOptionPaneError16", "Значення 'weapon' не може бути таким!"},

                    {"registerOptionPaneError", "Паролі різняться!"},

                    {"scriptOptionPaneError1", "Не вдалося виконати команду! Перевірте правильність введення аргументів!\n"},
                    {"scriptOptionPaneError2", "Не вдалося знайти файл!"}
            };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
