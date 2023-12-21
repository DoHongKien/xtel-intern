package B_OOP;

public class DongVat {

    // Thuộc tính
    private Integer id;
    private String name;

    public DongVat() {
    }

    public DongVat(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Phương thức - Hành vi
    public void eat() {
        System.out.println("Ăn");
    }

    public void speak() {
        System.out.println("Nói");
    }

    public void sleep() {
        System.out.println("Ngủ");
    }

    public void choi(String hanhDong) {
        System.out.println("Hành động: " + hanhDong);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
