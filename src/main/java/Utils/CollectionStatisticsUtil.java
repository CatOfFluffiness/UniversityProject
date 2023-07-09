package Utils;

import classes.Statistics;
import classes.Student;
import classes.University;
import enumClasses.StudyProfile;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CollectionStatisticsUtil {
    private static final Logger logger = Logger.getLogger(CollectionStatisticsUtil.class.getName());

    public static List<Statistics> createStatistics(List<Student> students,
                                                    List<University> universities) {

        List<Statistics> statisticsList = new ArrayList<>();

        Set<StudyProfile> profiles = universities.stream()
                .map(University::getMainProfile)
                .collect(Collectors.toSet());

        profiles.forEach(profile -> {
            List<String> profileUniversityIds = universities.stream()
                    .filter(university -> university.getMainProfile().equals(profile))
                    .map(University::getId)
                    .toList();

            List<Student> profileStudents = students.stream()
                    .filter(student -> profileUniversityIds.contains(student.getUniversityId()))
                    .toList();

            OptionalDouble averageScore = profileStudents.stream()
                    .mapToDouble(Student::getAvgExamScore)
                    .average();

            BigDecimal bdAverageScore = BigDecimal.valueOf(averageScore.orElse(0))
                    .setScale(2, RoundingMode.HALF_UP);
            float avgScore = bdAverageScore.floatValue();

            String universityNames = universities.stream()
                    .filter(university -> profileUniversityIds.contains(university.getId()))
                    .map(University::getFullName)
                    .collect(Collectors.joining(";"));

            Statistics statistics = new Statistics()
                    .setProfile(profile)
                    .setNumberOfStudents(profileStudents.size())
                    .setNumberOfUniversities(profileUniversityIds.size())
                    .setUniversityNames(StringUtils.defaultString(universityNames))
                    .setAvgExamScore(avgScore);

            statisticsList.add(statistics);

            logger.info(String.format("Создана статистика [%s]: Количество студентов: %d, Количество университетов: %d, Средняя оценка: %.2f",
                    profile, profileStudents.size(), profileUniversityIds.size(), avgScore));
        });

        return statisticsList;
    }
}
