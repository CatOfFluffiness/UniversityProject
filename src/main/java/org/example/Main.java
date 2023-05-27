package org.example;

import static org.example.StudyProfile.MEDICINE;

public class Main {
    public static void main(String[] args) {

        University university = new University("1", "SGUPS", "SGUPS", 1932, MEDICINE);
        Student student1 = new Student("Gennadiy", "1", 1, 2);

        System.out.println(university);
        System.out.println(student1);
    }
}