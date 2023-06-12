package classes;

import comparators.*;
import enumClasses.StudentEnum;
import enumClasses.UniversityEnum;

public class ComparatorFactory {
    public static StudentComparator getStudentComparator(StudentEnum studentEnum) {
        return switch (studentEnum) {
            case FULL_NAME -> new StFullNameComparator();
            case ID -> new StIDComparator();
            case COURSE_NUMBER -> new StCourseNumberComparator();
            case AVG_EXAM_SCORE -> new StAvgExamNumberComparator();
        };
    }

    public static UniversityComparator getUniversityComparator(UniversityEnum universityEnum) {
        return switch (universityEnum) {
            case ID -> new UniIDComparator();
            case FULL_NAME -> new UniFullNameComparator();
            case SHORT_NAME -> new UniShortNameComparator();
            case YEAR_OF_FOUNDATION -> new UniYearOfFoundationComparator();
            case STUDY_PROFILE -> new UniStudyProfileComparator();
        };
    }
}
