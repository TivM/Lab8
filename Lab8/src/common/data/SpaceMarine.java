package common.data;

import common.utility.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Main character. Is stored in the collection.
 */
public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {
    private int id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private int health;
    private Integer heartCount;
    private String achievements;
    private Weapon weaponType;
    private Chapter chapter;
    private User owner;

    public SpaceMarine(int id, String name, Coordinates coordinates, LocalDateTime creationDate, int health, Integer heartCount, String achievements, Weapon weaponType, Chapter chapter, User owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.achievements = achievements;
        this.weaponType = weaponType;
        this.chapter = chapter;
        this.owner = owner;
    }

    /**
     * @return ID of the marine.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Name of the marine.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coordinates of the marine.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Creation date of the marine.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return Health of the marine.
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return HeartCount of the marine.
     */
    public Integer getHeartCount() {
        return heartCount;
    }

    /**
     * @return Achievements of the marine.
     */
    public String getAchievements() {
        return achievements;
    }

    /**
     * @return Weapon type of the marine.
     */
    public Weapon getWeaponType() {
        return weaponType;
    }

    /**
     * @return Chapter of the marine.
     */
    public Chapter getChapter() {
        return chapter;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        String info = "";
        info += "?????????????????????? ???????????? ???" + id;
        info += " (???????????????? " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n ??????: " + name;
        info += "\n ????????????????????????????: " + coordinates;
        info += "\n ????????????????: " + health;
        info += "\n ?????????? ????????????: " + heartCount;
        info += "\n ?????????????? ????????????: " + weaponType;
        info += "\n ??????????????????: " + achievements;
        info += "\n ??????????: " + chapter;
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, heartCount, achievements, weaponType, chapter, owner);
    }
}
