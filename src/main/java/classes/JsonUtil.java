package classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public final class JsonUtil {

    static Gson gson = new Gson();

    public static String serializeStudent(Student student) {
        return gson.toJson(student);
    }

    public static Student deserializeStudent(String jsonString) {
        return gson.fromJson(jsonString, Student.class);
    }

    public static String serializeUniversity(University university) {
        return gson.toJson(university);
    }

    public static University deserializeUniversity(String jsonString) {
        return gson.fromJson(jsonString, University.class);
    }

    public static String serializeStudentList(List<Student> student) {
        return gson.toJson(student);
    }

    public static List<Student> deserializeStudentList(String jsonString) {
        Type type = new TypeToken<List<Student>>() {}.getType();
        return gson.fromJson(jsonString, type);
    }

    public static String serializeUniversityList(List<University> university) {
        return gson.toJson(university);
    }

    public static List<University> deserializeUniversityList(String jsonString) {
        Type type = new TypeToken<List<University>>() {}.getType();
        return gson.fromJson(jsonString, type);
    }
}
