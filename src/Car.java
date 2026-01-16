public class Car {
    private String model;
    private int id;
    private int idGen;
    private int numbers ;
    private String rank;
    private String type;
    private boolean available;
    private double dailyPrice;


    public Car(int id, String model, int numbers, String rank, String type, boolean available, double dailyPrice) {
        this.model = model;
        this.id = idGen++;
        this.numbers = numbers;
        this.rank = rank;
        this.type = type;
        this.available = available;
        this.dailyPrice = dailyPrice;
    }
    public int getId(){return id;}
    public boolean isAvailable(){return available;}
    public double getDailyPrice(){return dailyPrice;}


}
