package L_Java8.StreamAPI;

import java.util.List;

public class Student {

    private String name;

    private Integer age;

    private String country;

    private List<String> subjects;

    private String gender;

    public Student() {
    }

    public Student(String name, Integer age, String country, String gender, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.subjects = subjects;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", country='" + country + '\'' +
               ", subjects=" + subjects +
               ", gender='" + gender + '\'' +
               '}';
    }
}
