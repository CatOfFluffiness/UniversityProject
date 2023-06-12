package comparators;

import classes.Student;

public class StAvgExamNumberComparator implements StudentComparator{

    public int compare(Student student1, Student student2) {
        return Double.compare(student1.getAvgExamScore(), student2.getAvgExamScore());
    }
}
