package models;

public class Student {
    private Long ID;
    private String name;
    private String surname;
    private int age;

    public Student(Long ID, String name, String surname, int age) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
