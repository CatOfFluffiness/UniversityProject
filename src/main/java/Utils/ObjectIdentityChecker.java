package Utils;

import classes.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ObjectIdentityChecker {

    public static boolean checkAllObjectsIdentical(List<?> objectsList, String objectName) {
        AtomicBoolean allObjectsIdentical = new AtomicBoolean(true);
        String objectFieldName = null;

        // Получаем имя поля из первого объекта в списке
        if (!objectsList.isEmpty()) {
            Object firstObject = objectsList.get(0);
            Field[] fields = firstObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == String.class && field.getName().endsWith("Name")) {
                    objectFieldName = field.getName();
                    break;
                }
            }
        }

        String finalObjectFieldName = objectFieldName;
        objectsList.forEach(object -> {
            String objectJson = null;
            // Проверяем тип объекта и используем соответствующие методы сериализации и десериализации
            if (object instanceof Student) {
                objectJson = JsonUtil.serializeStudent((Student) object);
            } else if (object instanceof University) {
                objectJson = JsonUtil.serializeUniversity((University) object);
            }
            // Проверка json из отдельного элемента
            Object objectFromJson = null;
            if (object instanceof Student) {
                objectFromJson = JsonUtil.deserializeStudent(objectJson);
            } else if (object instanceof University) {
                objectFromJson = JsonUtil.deserializeUniversity(objectJson);
            }
            // Проверка воссоздания
            boolean objectIsIdentical = object.equals(objectFromJson);
            System.out.printf("Воссозданный и оригинальный объект %s %s идентичны: %s%n", objectName, getObjectFieldName(object, finalObjectFieldName), objectIsIdentical);
            if (!objectIsIdentical) {
                // Если найден хотя бы один неидентичный объект, устанавливаем флаг на false
                allObjectsIdentical.set(false);
            }
        });
        return allObjectsIdentical.get();
    }

    public static void checkAllStudentsIdentity(List<Student> studentsList) {
        boolean allStudentsIdentical = checkAllObjectsIdentical(studentsList, "студенты");
        if (allStudentsIdentical) {
            System.out.println("Все объекты студентов идентичны.");
        } else {
            System.out.println("Найдены неидентичные объекты студентов.");
        }
    }

    public static void checkAllUniversitiesIdentity(List<University> universitiesList) {
        boolean allUniversitiesIdentical = checkAllObjectsIdentical(universitiesList, "университеты");
        if (allUniversitiesIdentical) {
            System.out.println("Все объекты университетов идентичны.");
        } else {
            System.out.println("Найдены неидентичные объекты университетов.");
        }
    }

    private static String getObjectFieldName(Object object, String objectFieldName) {
        try {
            return (String) object.getClass().getMethod("get" +
                    StringUtils.capitalize(objectFieldName)).invoke(object);
        } catch (Exception e) {
            return "";
        }
    }
}