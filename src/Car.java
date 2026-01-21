public class Car {
    private int id;
    private String model;
    private int year;
    private static int idGen;
    private double basePrice;
    private String rank;
    private String type;
    private boolean available;
    public Car(String model, int year, double basePrice) {
        id = idGen++;
        setModel(model);
        setYear(year);
        setBasePrice(basePrice);
        setType(type);
        setRank(rank);
        this.available = true;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getModel() {return model;}
    public void setModel(String model) {
        if ((model == null)||(model.trim().isEmpty()))
        {
            throw new IllegalArgumentException("Model name cannot be empty");
        }
        else {
            this.model = model;
        }
    }
    public int getYear() {return year;}
    public void setYear(int year) {
        if ((1885>year)||(year<2025))
        {
            throw new IllegalArgumentException("Year out of range");
        }
        else {
            this.year = year;
        }
    }
    public double getBasePrice() {return basePrice;}
    public void setBasePrice(double basePrice) {
        if (basePrice < 0){
            throw new IllegalArgumentException("Base price cannot be less than 0");
        }
        else {
            this.basePrice = basePrice;
        }
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        if ((rank == null)||(rank.trim().isEmpty()))
        {
            throw new IllegalArgumentException("Rank cannot be empty");
        }
        else {
            this.rank = rank;
        }
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        if ((type == null)||(type.trim().isEmpty()))
        {
            throw new IllegalArgumentException("Car type cannot be empty");
        }
        else {
            this.type = type;
        }
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double calculateRent(){return 1;}
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", basePrice=" + basePrice +
                ", rank='" + rank + '\'' +
                ", type='" + type + '\'' +
                ", available=" + available +
                '}';
    }
}
