package classes;

import enumClasses.StudyProfile;

import java.util.Objects;

public class University {

    private String id;
    private String fullName;
    private String shortName;
    private int yearOfFoundation;
    private StudyProfile mainProfile;

    public University(String id, String fullName, String shortName, int yearOfFoundation, StudyProfile mainProfile) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;

        this.yearOfFoundation = yearOfFoundation;
        this.mainProfile = mainProfile;
    }
    public String toString() {
        return "University:\n"
                + "fullName = " + fullName + '\n'
                + "shortName = " + shortName+ '\n'
                + "yearOfFoundation = " + yearOfFoundation+ '\n'
                + "mainProfile = " + mainProfile.getProfileName() + '\n';
    }
    //Переопределение equals() и hashCode() для сравнения объектов университетов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof University university)) return false;
        return getYearOfFoundation() == university.getYearOfFoundation() &&
                Objects.equals(getId(), university.getId()) &&
                Objects.equals(getFullName(), university.getFullName()) &&
                Objects.equals(getShortName(), university.getShortName()) &&
                Objects.equals(getMainProfile(),university.getMainProfile());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getShortName(), getYearOfFoundation(), getMainProfile());
    }

    //Дальше геттеры и сеттеры университетов
    public String getId() {
        return id;
    }

    public University setId(String id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public University setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public University setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public University setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
        return this;
    }

    public StudyProfile getMainProfile() {
        return mainProfile;
    }

    public University setMainProfile(StudyProfile mainProfile) {
        this.mainProfile = mainProfile;
        return this;
    }
}
