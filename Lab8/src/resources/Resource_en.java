package resources;

import java.util.ListResourceBundle;

public class Resource_en extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"back_button", "Back"},
                    {"nameTitle", "Name"},
                    {"healthTitle", "Health"},
                    {"heartTitle", "Heart"},
                    {"achieveTitle", "Achieve"},
                    {"weaponTitle", "Weapon"},
                    {"chapterLegionTitle", "Legion"},
                    {"remove_button", "Remove"},
                    {"login_button", "Sign in"},
                    {"marineData_Title", "Spacemarine data"},
                    {"coordinatesData_Title", "Coordinates"},
                    {"chapterData_Title", "Chapter"},
                    {"registration", "Sign up"},

                    {"startMenu_closeButton", "Close"},

                    {"mainMenu_signOutButton", "Sign out"},
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
                    {"mainMenu_label1", "Add/Update"},
                    {"mainMenu_label2", "Objects info"},
                    {"mainMenu_label3", "Remove objects"},
                    {"mainMenu_label4", "Service commands"},

                    {"filter_label1", "Filter field:"},
                    {"filter_label2", "Argument:"},
                    {"filter_label3", "Filter type:"},
                    {"filter_filterButton", "Filter"},

                    {"login_name", "Sign In"},
                    {"login_loginName", "Username"},
                    {"login_passwordName", "Password"},

                    {"register_loginName", "Username"},
                    {"register_passwordName", "Password"},
                    {"register_confirmPassword", "Confirm password"},

                    {"show_filterButton", "Filter"},
                    {"show_resetButton", "Reset"},
                    {"show_updateButton", "Reload"},
                    {"show_keyColumn", "key"},
                    {"show_nameColumn", "name"},
                    {"show_dateColumn", "date"},
                    {"show_healthColumn", "health"},
                    {"show_heartColumn", "heart"},
                    {"show_achieveColumn", "achieve"},
                    {"show_weaponColumn", "weapon"},
                    {"show_chapterNameColumn", "chapterName"},
                    {"show_chapterLegionColumn", "chapterLegion"},
                    {"show_userColumn", "user"},

                    {"script_chooseFileButton", "Choose file"},

                    {"removeKey_label1", "Enter key"},

                    {"removeByWeapon_label1", "Choose weapon"},

                    {"insert_keyName", "key"},
                    {"insert_insertButton", "Insert"},

                    {"averCommandDescription", "output the average value of the heartCount field for all items in the collection"},
                    {"clearCommandDescription", "clear the collection"},
                    {"scriptCommandDescription", "execute the script from the specified file"},
                    {"exitCommandDescription", "end the program"},
                    {"helpCommandDescription", "display help for available commands"},
                    {"historyCommandDescription", "display the history of the commands used"},
                    {"infoCommandDescription", "display information about the collection"},
                    {"removeWeaponCommandDescription", "remove from the collection all items whose value of the weaponType field is equivalent to the specified one"},
                    {"removeGreaterCommandDescription", "remove all items from the collection that exceed the specified limit"},
                    {"removeKeyCommandDescription", "remove an item from the collection by its key"},
                    {"removeLowerKeyCommandDescription", "remove all items from the collection whose key is less than the specified one"},
                    {"showCommandDescription", "print all items in the collection"},
                    {"signInCommandDescription", "log in to your account"},
                    {"logOutCommandDescription", "log out of your account"},
                    {"signUpCommandDescription", "registering a new user"},
                    {"sumHealthCommandDescription", "print the sum of the values of the health field for all the items in the collection"},
                    {"updateCommandDescription", "update the value of a collection item by id"},
                    {"loginCommandDescription", "log in"},
                    {"insertCommandDescription", "insert object"},

                    {"nonUserError", "You need to log in!"},
                    {"illegalError", "There was an illegal change of the object in the database!\nRestart the client to avoid errors!"},
                    {"permissionError", "Objects owned by other users are read-only!"},
                    {"databaseError", "An error occurred while accessing the database!"},
                    {"emptyError", "The collection is empty!"},
                    {"notFoundError", "Space marine not found!"},
                    {"multiUserError", "This user is already logged in!"},
                    {"userNotFoundError", "Incorrect username or password!"},
                    {"classCastError", "The object passed by the client is invalid!"},
                    {"userExistError", "This user already exists!"},

                    {"clearCommandSuccess", "Collection cleared successfully!"},

                    {"infoCommandText1", "in this session initialization has not yet occurred"},
                    {"infoCommandText2", "Information about the collection:"},
                    {"infoCommandText3", " Type: "},
                    {"infoCommandText4", " Number of elements: "},
                    {"infoCommandText5", " Date of last initialization: "},

                    {"insertCommandSuccess", "Successfully added to the collection!"},

                    {"removeWeaponSuccessText", "Successfully removed all soldiers with the weapon type: "},

                    {"removeGreaterSuccess", "Soldiers successfully removed!"},

                    {"removeKeySuccess", "Soldier successfully removed!"},

                    {"signInCommandSuccess", "Authorization was successful!"},

                    {"signUpCommandSuccess", "Registration was successful!"},

                    {"sumHealthCommandText", "The sum of the health of all space marines: "},

                    {"updateCommandSuccess", "Updated successfully!"},

                    {"averCommandText", "The average value of the heartCount field of all space marines: "},

                    {"historyError", "No commands have been used yet!"},
                    {"historyText", "Last used commands:"},

                    {"ioPaneError", "An error occurred while sending the request to the server!"},
                    {"classNotFoundError", "An error occurred while receiving a response from the server!"},

                    {"filterOptionPaneError1", "No field selected!"},
                    {"filterOptionPaneError2", "No argument entered!"},
                    {"filterOptionPaneError3", "The filter type is not selected!"},

                    {"valuesOptionPaneError13", "The key must be a number!\n"},
                    {"valuesOptionPaneError14", "The key must be greater than 0!\n"},

                    {"valuesOptionPaneError1", "The value of the 'name' field cannot be empty!\n"},
                    {"valuesOptionPaneError2", "Health should be a number!\n"},
                    {"valuesOptionPaneError3", "Health must be greater than 0!\n"},
                    {"valuesOptionPaneError4", "The number of hearts must be a number!\n"},
                    {"valuesOptionPaneError5", "The number of hearts must be greater than 0!\n"},
                    {"valuesOptionPaneError6", "The value of the 'achievements' field cannot be empty!\n"},
                    {"valuesOptionPaneError7", "The 'x' coordinate must be a number!\n"},
                    {"valuesOptionPaneError8", "The 'x' coordinate must be greater than -666!\n"},
                    {"valuesOptionPaneError9", "The 'y' coordinate must be a number!\n"},
                    {"valuesOptionPaneError10", "The 'y' coordinate must be greater than -666!\n"},
                    {"valuesOptionPaneError11", "The value of the 'chapterName' field cannot be empty!\n"},
                    {"valuesOptionPaneError12", "The value of the 'chapterLegion' field cannot be empty!\n"},

                    {"valuesOptionPaneError15", "The 'weapon' value cannot be empty!"},
                    {"valuesOptionPaneError16", "The value of 'weapon' can't be like this!"},

                    {"registerOptionPaneError", "Passwords are different!"},

                    {"scriptOptionPaneError1", "Failed to execute the command! Check that the arguments are entered correctly!\n"},
                    {"scriptOptionPaneError2", "Couldn't find the file!"}
            };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
