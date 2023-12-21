package G_MultiThread.BlockingQueue;

public class Student {

    private String code;

    private String name;

    private String subject;

    public Student() {
    }

    public Student(String code, String name, String subject) {
        this.code = code;
        this.name = name;
        this.subject = subject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Student{" +
               "code='" + code + '\'' +
               ", name='" + name + '\'' +
               ", subject='" + subject + '\'' +
               '}';
    }
}
