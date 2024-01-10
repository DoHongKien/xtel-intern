package N_DesignPattern.BehavioralPattern.Interator;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public StudentInterator<Student> interator() {
        return new MenuStudentInterator();
    }

    class MenuStudentInterator implements StudentInterator<Student> {

        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex < students.size();
        }

        @Override
        public Student next() {
            return students.get(currentIndex++);
        }
    }
}


