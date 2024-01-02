package L_Java8.StreamAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemoStream {

    public static void main(String[] args) {
        // Create a student list
        List<Student> students =
                List.of(new Student("Kien", 20, "VietNam", "Male", List.of("Math", "Science")),
                        new Student("Linh", 25, "USA", "Female", List.of("Math")),
                        new Student("Hoang", 18, "VietNam", "Male", List.of("English")),
                        new Student("Long", 30, "Korea", "Male", List.of("Literature", "English")),
                        new Student("Ha", 20, "Japan", "Female", List.of("Chemistry", "Science")));

//        listNameOfStudent(students);
//        sortStudentName(students);
//        listCountryOfStudent(students);
//        listAgeOfStudentGreaterThanOrEqual20(students);
//        listSubjectOfStudent(students);
//        groupTheStudentByCountry(students);
//        countStudentByCountry(students);
//        averageAgeOfStudents(students);
    }

    // Get the list name of students
    private static void listNameOfStudent(List<Student> students) {
        List<String> names = students
                .stream()
                .map(Student::getName)// Get the list name of students
                .toList();

        names.forEach(System.out::println);
    }

    // sort student's names
    private static void sortStudentName(List<Student> students) {
        List<Student> studentList = students
                .stream()
                .sorted(Comparator.comparing(Student::getName))// Sort students by name
                .toList();

        studentList.forEach(System.out::println);
    }

    // get the list countries of students distinct
    private static void listCountryOfStudent(List<Student> students) {
        List<String> countries = students
                .stream()
                .map(Student::getCountry)// Get the list countries
                .distinct()
                .toList();

        countries.forEach(System.out::println);
    }

    // Filter age of students greater than or equal to 20 years old
    private static void listAgeOfStudentGreaterThanOrEqual20(List<Student> students) {
        List<Student> ageOfStudents = students
                .stream()
                .filter(s -> s.getAge() >= 20)// Filter age of students
                .toList();

        ageOfStudents.forEach(System.out::println);
    }

    // get the list subject of students from list subject
    private static void listSubjectOfStudent(List<Student> students) {
        List<String> subjectOfStudents = students
                .stream()
                .flatMap(s -> s.getSubjects().stream())// Use flatMap to get subject in the list to string list
                .distinct()// No repeat subject
                .toList();

        subjectOfStudents.forEach(System.out::println);
    }

    // Group students by country
    private static void groupTheStudentByCountry(List<Student> students) {
        Map<String, List<Student>> countries = students
                .stream()
                .collect(Collectors.groupingBy(Student::getCountry));// Group by country

        countries.forEach((key, value) -> System.out.println(key + " - " + value));

    }

    // Count students by country
    private static void countStudentByCountry(List<Student> students) {
        Map<String, Long> countries = students
                .stream()
                .collect(Collectors.groupingBy(Student::getCountry, Collectors.counting()));// Group by country and count

        countries.forEach((key, value) -> System.out.println(key + " - " + value));

    }

    // Average age of students
    private static void averageAgeOfStudents(List<Student> students) {
        Map<String, Double> averageAge = students
                .stream()
                .collect(Collectors.groupingBy(Student::getGender,
                        Collectors.averagingInt(Student::getAge)));

        averageAge.forEach((key, value) -> System.out.printf(key + ": %.2f", value));
    }
}
