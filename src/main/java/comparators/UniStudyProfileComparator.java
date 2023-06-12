package comparators;

import classes.University;
import org.apache.commons.lang3.StringUtils;

public class UniStudyProfileComparator implements UniversityComparator{
    @Override
    public int compare(University university1, University university2) {
        return StringUtils.compare(university1.getMainProfile().name(), university2.getMainProfile().name());
    }
}