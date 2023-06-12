package comparators;

import classes.Student;

public class StAvgExamNumberComparator implements StudentComparator{
    @Override
    public int compare(Student student1, Student student2) {
        return Double.compare(student1.getAvgExamScore(), student2.getAvgExamScore());
    }
}