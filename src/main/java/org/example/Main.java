package org.example;

import static org.example.StudyProfile.MEDICINE;

public class Main {
    public static void main(String[] args) {

        University university = new University("1", "Sibirsky gosudarstvenniy universitet putei soobsheniya", "SGUPS", 1932, MEDICINE);
        Student student1 = new Student("Gennadiy", "1", 1, 2);

        System.out.println(university + "\n");
        System.out.println(student1);
    }
}