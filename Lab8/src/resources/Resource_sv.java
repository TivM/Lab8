package resources;

import java.util.ListResourceBundle;

public class Resource_sv extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"back_button", "Nazaj"},
                    {"nameTitle", "Ime"},
                    {"healthTitle", "Zdravje"},
                    {"heartTitle", "Srca"},
                    {"achieveTitle", "Dosežki"},
                    {"weaponTitle", "Orožje"},
                    {"chapterLegionTitle", "Legija"},
                    {"remove_button", "Odstrani"},
                    {"login_button", "Naprej"},
                    {"marineData_Title", "Podatki o Spacemarine"},
                    {"coordinatesData_Title", "Koordinate"},
                    {"chapterData_Title", "Del"},
                    {"registration", "Registracija"},

                    {"startMenu_closeButton", "Za zaprtje"},

                    {"mainMenu_signOutButton", "Pojdi ven"},
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
                    {"mainMenu_label1", "Dodaj/Uredi"},
                    {"mainMenu_label2", "Informacije o predmetih"},
                    {"mainMenu_label3", "Brisanje postavk"},
                    {"mainMenu_label4", "Storitveni ukazi"},

                    {"filter_label1", "Filtrirno polje:"},
                    {"filter_label2", "Argument:"},
                    {"filter_label3", "Vrsta filtra:"},
                    {"filter_filterButton", "Filter"},

                    {"login_name", "Vhod"},
                    {"login_loginName", "Prijava"},
                    {"login_passwordName", "Geslo"},

                    {"register_loginName", "Prijava"},
                    {"register_passwordName", "Geslo"},
                    {"register_confirmPassword", "Potrdi geslo"},

                    {"show_filterButton", "Filter"},
                    {"show_resetButton", "Pona"},
                    {"show_updateButton", "Posodobi"},
                    {"show_keyColumn", "ključ"},
                    {"show_nameColumn", "ime"},
                    {"show_dateColumn", "datum"},
                    {"show_healthColumn", "zdravje"},
                    {"show_heartColumn", "srca"},
                    {"show_achieveColumn", "dosežki"},
                    {"show_weaponColumn", "orožje"},
                    {"show_chapterNameColumn", "imeDel"},
                    {"show_chapterLegionColumn", "legijaDel"},
                    {"show_userColumn", "Lastnik"},

                    {"script_chooseFileButton", "Izberite datoteko"},

                    {"removeKey_label1", "Vnesite ključ"},

                    {"removeByWeapon_label1", "Izberite orožje"},

                    {"insert_keyName", "Ključ"},
                    {"insert_insertButton", "Dodaj"},

                    {"averCommandDescription", "izhodna povprečna vrednost polja 'heartCount' za vse postavke v zbirki"},
                    {"clearCommandDescription", "počisti zbirko"},
                    {"scriptCommandDescription", "izvedi skript iz navedene datoteke"},
                    {"exitCommandDescription", "končaj program"},
                    {"helpCommandDescription", "prikaži pomoč za razpoložljive ukaze"},
                    {"historyCommandDescription", "prikaži zgodovino uporabljenih ukazov"},
                    {"infoCommandDescription", "prikaži informacije o zbiranju"},
                    {"removeWeaponCommandDescription", "iz zbirke se odstranijo vse postavke, katerih vrednost polja weaponType je enaka določeni vrednosti"},
                    {"removeGreaterCommandDescription", "odstrani vse predmete iz zbirke, ki presegajo določeno omejitev"},
                    {"removeKeyCommandDescription", "zbriši predmet iz zbirke s ključem"},
                    {"removeLowerKeyCommandDescription", "odstrani vse predmete iz zbirke, katerih ključ je manjši od določenega"},
                    {"showCommandDescription", "natisni vse predmete v zbirki"},
                    {"signInCommandDescription", "prijavi se na svoj račun"},
                    {"logOutCommandDescription", "odjavi se iz računa"},
                    {"signUpCommandDescription", "registracija novega uporabnika"},
                    {"sumHealthCommandDescription", "natisni vsoto vrednosti polja health za vse predmete v zbirki"},
                    {"updateCommandDescription", "posodobi vrednost zbirke po id"},
                    {"loginCommandDescription", "prijavi se"},
                    {"insertCommandDescription", "dodaj postavko"},

                    {"nonUserError", "Prijaviti se moraš!"},
                    {"illegalError", "Prišlo je do nezakonite spremembe predmeta v bazi podatkov!\nZnova zaženi odjemalca, da bi se izognili napakam!"},
                    {"permissionError", "Predmeti v lasti drugih uporabnikov so samo za branje!"},
                    {"databaseError", "Prišlo je do napake pri dostopu do zbirke podatkov!"},
                    {"emptyError", "Zbirka je prazna!"},
                    {"notFoundError", "Vesoljsko plovilo ni najdeno!"},
                    {"multiUserError", "Ta uporabnik je že prijavljen!"},
                    {"userNotFoundError", "Nepravilno uporabniško ime ali geslo!"},
                    {"classCastError", "Predmet, ki ga je opravila stranka, je neveljaven!"},
                    {"userExistError", "Ta uporabnik že obstaja!"},

                    {"clearCommandSuccess", "Zbiranje uspešno očiščeno!"},

                    {"infoCommandText1", "na tej seji inicializacija še ni bila izvedena"},
                    {"infoCommandText2", "Informacije o zbiranju:"},
                    {"infoCommandText3", " Vrsta: "},
                    {"infoCommandText4", " Število elementov: "},
                    {"infoCommandText5", " Datum zadnje inicializacije: "},

                    {"insertCommandSuccess", "Uspešno dodan zbirki!"},

                    {"removeWeaponSuccessText", "Uspešno je odstranil vse vojake z vrsto orožja: "},

                    {"removeGreaterSuccess", "Vojaki uspešno odstranjeni!"},

                    {"removeKeySuccess", "Vojak uspešno odstranjen!"},

                    {"signInCommandSuccess", "Avtorizacija je bila uspešna!"},

                    {"signUpCommandSuccess", "Registracija je bila uspešna!"},

                    {"sumHealthCommandText", "Vsi vesoljski marinci so zdravi: "},

                    {"updateCommandSuccess", "Uspešno spremenjen!"},

                    {"averCommandText", "Povprečna vrednost polja heartCount vseh vesoljskih marincev: "},

                    {"historyError", "Ukazi še niso bili uporabljeni!"},
                    {"historyText", "Zadnji uporabljeni ukazi:"},

                    {"ioPaneError", "Prišlo je do napake pri pošiljanju zahteve strežniku!"},
                    {"classNotFoundError", "Prišlo je do napake med prejemanjem odgovora s strežnika!"},

                    {"filterOptionPaneError1", "Ni izbranega polja!"},
                    {"filterOptionPaneError2", "Ni argumenta!"},
                    {"filterOptionPaneError3", "Ni izbrane vrste filtrov!"},

                    {"valuesOptionPaneError13", "Ključ mora biti številka!\n"},
                    {"valuesOptionPaneError14", "Ključ mora biti večji od 0!\n"},

                    {"valuesOptionPaneError1", "Vrednost polja 'name' ne more biti prazna!\n"},
                    {"valuesOptionPaneError2", "Zdravje bi moralo biti število!\n"},
                    {"valuesOptionPaneError3", "Zdravje mora biti večje od 0!\n"},
                    {"valuesOptionPaneError4", "Število src mora biti število!\n"},
                    {"valuesOptionPaneError5", "Število src mora biti večje od 0!\n"},
                    {"valuesOptionPaneError6", "Vrednost polja 'achievements' ne more biti prazna!\n"},
                    {"valuesOptionPaneError7", "Koordinata 'x' mora biti številka!\n"},
                    {"valuesOptionPaneError8", "Koordinata 'x' mora biti večja od -666!\n"},
                    {"valuesOptionPaneError9", "Koordinata 'y' mora biti številka!\n"},
                    {"valuesOptionPaneError10", "Koordinate 'y' morajo biti večje od -666!\n"},
                    {"valuesOptionPaneError11", "Vrednost polja 'chapterName' ne more biti prazna!\n"},
                    {"valuesOptionPaneError12", "Vrednost polja 'chapterLegion' ne more biti prazna!\n"},

                    {"valuesOptionPaneError15", "Vrednost 'weapon' ne more biti prazna!"},
                    {"valuesOptionPaneError16", "Vrednost 'weapon' ne more biti takšna!"},

                    {"registerOptionPaneError", "Gesli sta različni!"},

                    {"scriptOptionPaneError1", "Ni uspelo izvesti ukaza! Preveri, ali so argumenti pravilno vneseni!\n"},
                    {"scriptOptionPaneError2", "Nisem našel datoteke!"}
            };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
