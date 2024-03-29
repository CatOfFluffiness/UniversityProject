package Utils;

import classes.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import static Utils.JsonUtil.*;

public class ObjectIdentityChecker {
    private static final Logger logger = Logger.getLogger(ObjectIdentityChecker.class.getName());

    public static boolean checkAllObjectsIdentical(List<?> objectsList) {
        AtomicBoolean allObjectsIdentical = new AtomicBoolean(true);

        objectsList.forEach(object -> {
            String objectJson = toJson(object);
            // Проверка json из отдельного элемента
            Object objectFromJson = fromJson(objectJson, object.getClass());
            // Проверка воссоздания
            boolean objectIsIdentical = object.equals(objectFromJson);
            if (!objectIsIdentical) {
                // Если найден хотя бы один неидентичный объект, устанавливаем флаг на false
                allObjectsIdentical.set(false);
            }
        });
        return allObjectsIdentical.get();
    }

    public static void checkAllObjectsIdentity(List<Student> studentsList, List<University> universitiesList) {

        boolean allStudentsIdentical = checkAllObjectsIdentical(studentsList);
        boolean allUniversitiesIdentical = checkAllObjectsIdentical(universitiesList);

        if (allStudentsIdentical && allUniversitiesIdentical) {
            logger.info("Все объекты студентов и университетов идентичны.");
        } else {
            logger.info("Найдены неидентичные объекты.");
        }
    }
}