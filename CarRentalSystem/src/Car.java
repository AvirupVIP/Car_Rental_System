public class Car {
    private String carid;
    private String brand;
    private String model;
    private double price;
    private boolean isAvailable;

    public Car(String carid, String brand, String model, double price) {
        this.carid = carid;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.isAvailable = true;
    }

    public String getcarid() {
        return carid;
    }

    public String getbrand() {
        return brand;
    }

    public String getmodel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }

    public double calculatePrice(int rentalDays) {
        return price * rentalDays;//wow niiiicee
    }
}
