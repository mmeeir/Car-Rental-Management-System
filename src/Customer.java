public class Customer {
    private int id;
    private int idGen;
    private String name;
    private int age;

    public Customer(int id, String name, int age) {
        this.id = idGen++;
        setAge(age);
        setName(name);
    }
    public int getAge() {return age;}
    public int getId() {return id;}
    public void setName(String name) {
        if (name==null||name.trim().isEmpty())
        {throw new IllegalArgumentException("Name can not be empty");}
        else
        {this.name = name;}
    }
    public void setAge(int age) {
        if (age<18)
        {throw new IllegalArgumentException("Illegal age");}
        else
        {this.age = age;}
    }
}
