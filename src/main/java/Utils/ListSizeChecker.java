package Utils;

import java.util.List;

public class ListSizeChecker {
    public static void isListsSizeEqual(List<?> list1, List<?> list2, String objectType) {
        int size1 = list1.size();
        int size2 = list2.size();
        if (size1 == size2) {
            System.out.println("Размеры списков " + objectType + " равны: " + size1);
        } else {
            System.out.println("Размеры списков " + objectType + " не равны. Первый список имеет размер " + size1 + ", а второй список имеет размер " + size2);
        }
    }
}
