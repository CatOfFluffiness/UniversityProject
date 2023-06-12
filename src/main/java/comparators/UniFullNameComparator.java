package comparators;

import classes.University;
import org.apache.commons.lang3.StringUtils;

public class UniFullNameComparator implements UniversityComparator{
    @Override
    public int compare(University university1, University university2) {
        return StringUtils.compare(university1.getFullName(), university2.getFullName());
    }
}