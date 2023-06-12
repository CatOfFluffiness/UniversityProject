package comparators;

import classes.University;
import org.apache.commons.lang3.StringUtils;

public class UniYearOfFoundationComparator implements UniversityComparator{
    @Override
    public int compare(University university1, University university2) {
        return Integer.compare(university1.getYearOfFoundation(), university2.getYearOfFoundation());
    }
}