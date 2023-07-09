package Utils;

import java.util.List;
import java.util.logging.Logger;

public class ListSizeChecker {
    private static final Logger logger = Logger.getLogger(ListSizeChecker.class.getName());

    public static void isListsSizeEqual(List<?> list1, List<?> list2, String objectType) {
        int size1 = list1.size();
        int size2 = list2.size();
        if (size1 == size2) {
            logger.info("Размеры списков " + objectType + " равны: " + size1);
        } else {
            logger.warning("Размеры списков " + objectType + " не равны. Первый список имеет размер " + size1 + ", а второй список имеет размер " + size2);
        }
    }
}
