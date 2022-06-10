package resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleBundle {
    private static ResourceBundle bundle_en = ResourceBundle.getBundle("resources.Resource_en", new Locale("en", "US"));
    private static ResourceBundle bundle_ru = ResourceBundle.getBundle("resources.Resource_ru", new Locale("ru", "RU"));
    private static ResourceBundle bundle_sv = ResourceBundle.getBundle("resources.Resource_sv", new Locale("sl", "Sl"));
    private static ResourceBundle bundle_uk = ResourceBundle.getBundle("resources.Resource_uk", new Locale("uk", "UA"));

    private static ResourceBundle currentBundle = bundle_ru;

    public static void setBundle(String lang) {
        switch (lang) {
            case "Russian":
                currentBundle = bundle_ru;
                break;
            case "English(USA)":
                currentBundle = bundle_en;
                break;
            case "Slovenian":
                currentBundle = bundle_sv;
                break;
            case "Ukranian":
                currentBundle = bundle_uk;
                break;
        }
    }

    public static ResourceBundle getCurrentBundle() {
        return currentBundle;
    }
}
