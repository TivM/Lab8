package clientModule.utility;

import common.data.Chapter;
import common.data.Coordinates;
import common.data.Weapon;
import common.exceptions.NotDeclaredValueException;

import java.util.Scanner;

/**
 * Asks a user a marine's value.
 */
public class SpaceMarineBuilder {
    private Scanner scanner;
    private boolean fileMode;

    public SpaceMarineBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Sets a scanner to scan user input.
     * @param scanner Scanner to set.
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getScanner() {
        return scanner;
    }

    public void setFileMode() {
        this.fileMode = true;
    }

    public void setUserMode() {
        this.fileMode = false;
    }

    /**
     * Asks a user the marine's name.
     * @return Marine's name.
     */
    public String askName() {
        String name = scanner.nextLine().trim();;
        if (name.isEmpty()) name = null;
        return name;
    }

    /**
     * Asks a user the marine's X coordinate.
     * @return Marine's X coordinate.
     */
    public int askX() {
        String strX = scanner.nextLine().trim();
        int x;
        try {
            x = Integer.parseInt(strX);
            if (x <= -666) throw new NotDeclaredValueException();
        } catch (NumberFormatException | NotDeclaredValueException exception) {
            x = -666;
        }
        return x;
    }

    /**
     * Asks a user the marine's Y coordinate.
     * @return Marine's Y coordinate.
     */
    public Float askY() {
        String strY = scanner.nextLine().trim();
        float y;
        try {
            y = Float.parseFloat(strY);
            if (y <= -604) throw new NotDeclaredValueException();
        } catch (NumberFormatException | NotDeclaredValueException exception) {
            y = -604;
        }
        return y;
    }

    /**
     * Asks a user the marine's coordinates.
     * @return Marine's coordinates.
     */
    public Coordinates askCoordinates() {
        int x;
        float y;
        x = askX();
        y = askY();
        if (x == -666 || y == -604) return null;
        return new Coordinates(x, y);
    }

    /**
     * Asks a user the marine's health.
     * @return Marine's health.
     */
    public int askHealth() {
        String strHealth = scanner.nextLine().trim();
        int health;
        try {
            health = Integer.parseInt(strHealth);
            if (health <= 0) throw new NotDeclaredValueException();
        } catch (NumberFormatException | NotDeclaredValueException exception) {
            health = -1;
        }
        return health;
    }

    /**
     * Asks a user the marine's heartCount.
     * @return Marine's heartCount.
     */
    public Integer askHeartCount() {
        String strHeartCount = scanner.nextLine().trim();
        int heartCount;
        try {
            heartCount = Integer.parseInt(strHeartCount);
            if (heartCount < 0 || heartCount > 3) throw new NotDeclaredValueException();
        } catch (NumberFormatException | NotDeclaredValueException exception) {
            heartCount = -1;
        }
        return heartCount;
    }

    /**
     * Asks a user the marine's achievements.
     * @return Marine's achievements.
     */
    public String askAchievements() {
        String achieve = scanner.nextLine().trim();;
        if (achieve.isEmpty()) achieve = null;
        return achieve;
    }

    /**
     * Asks a user the marine's weapon type.
     * @return Marine's weapon type.
     */
    public Weapon askWeapon() {
        String strWeapon = scanner.nextLine().trim();
        Weapon weapon;
        try {
            weapon = Weapon.valueOf(strWeapon.toUpperCase());
        } catch (IllegalArgumentException exception) {
            weapon = null;
        }
        return weapon;
    }

    /**
     * Asks a user the marine's chapter.
     * @return Marine's chapter.
     */
    public Chapter askChapter() {
        String chapterName = askChapterName();
        String parentLegion = askChapterParentLegion();
        if (chapterName == null || parentLegion == null) return null;
        return new Chapter(chapterName, parentLegion);
    }

    /**
     * Asks a user the marine chapter's name.
     * @return Chapter's name.
     */
    public String askChapterName() {
        String chapterName = scanner.nextLine().trim();
        if (chapterName.isEmpty()) chapterName = null;
        return chapterName;
    }

    /**
     * Asks a user the marine chapter's parentLegion.
     * @return Chapter's parentLegion.
     */
    public String askChapterParentLegion() {
        String parentLegion = scanner.nextLine().trim();
        if (parentLegion.isEmpty()) parentLegion = null;
        return parentLegion;
    }

    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param ask A question.
     */
    public boolean askAboutChangingField(String ask) {
        String answer = scanner.nextLine().trim();
        return answer.equals("+");
    }
}