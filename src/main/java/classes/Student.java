package classes;

import java.util.Objects;

public class Student {

    private String fullName;
    private String universityId;
    private int currentCourseNumber;
    private float avgExamScore;

    public Student(String fullName, String universityId, int currentCourseNumber, float avgExamScore) {
        this.fullName = fullName;
        this.universityId = universityId;
        this.currentCourseNumber = currentCourseNumber;
        this.avgExamScore = avgExamScore;
    }

    public Student() {}

    public String toString() {
        return "Student:\n"
                + "fullName = " + fullName + '\n'
                + "universityId = " + universityId + '\n'
                + "currentCourseNumber = " + currentCourseNumber + '\n'
                + "avgExamScore = " + avgExamScore + '\n';
    }
    //Переопределение equals() и hashCode() для сравнения объектов студентов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(getFullName(), student.getFullName()) &&
                Objects.equals(getUniversityId(), student.getUniversityId()) &&
                Objects.equals(getCurrentCourseNumber(), student.getCurrentCourseNumber()) &&
                Objects.equals(getAvgExamScore(), student.getAvgExamScore());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getUniversityId(), getCurrentCourseNumber(), getAvgExamScore());
    }

    //Дальше геттеры и сеттеры студентов
    public String getFullName() {
        return fullName;
    }

    public Student setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUniversityId() {
        return universityId;
    }

    public Student setUniversityId(String universityId) {
        this.universityId = universityId;
        return this;
    }

    public int getCurrentCourseNumber() {
        return currentCourseNumber;
    }

    public Student setCurrentCourseNumber(int currentCourseNumber) {
        this.currentCourseNumber = currentCourseNumber;
        return this;
    }

    public float getAvgExamScore() {
        return avgExamScore;
    }

    public Student setAvgExamScore(float avgExamScore) {
        this.avgExamScore = avgExamScore;
        return this;
    }
}