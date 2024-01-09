package N_DesignPattern.BehavioralPattern.Interator;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addStudent(new Student("Kien1", "Hung Yen1"));
        menu.addStudent(new Student("Kien2", "Hung Yen2"));
        menu.addStudent(new Student("Kien3", "Hung Yen3"));

        StudentInterator<Student> studentInterator = menu.interator();
        while(studentInterator.hasNext()) {
            Student student = studentInterator.next();
            System.out.println(student);
        }
    }
}
