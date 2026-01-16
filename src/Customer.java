public class Customer {
    private int id;
    private int idGen;
    private String name;
    private int age;

    public Customer(int id, String name, int age) {
        this.id = idGen++;
        this.name = name;
        this.age = age;
    }

    public int getAge() {return age;}

    public int getId() {return id;}
}
