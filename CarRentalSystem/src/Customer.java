public class Customer {
    private String customerid;
    private String name;

    public Customer(String name, String customerid) {
        
        this.name = name;
        this.customerid = customerid;
    }

    public String getcustomerid() {
        return customerid;
    }

    public String getname() {
        return name;
    }

}
