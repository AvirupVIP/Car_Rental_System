import java.util.*;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("The Car Is Not Available Right Now");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToremove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToremove = rental;
                break;
            }
            if (rentalToremove != null) {
                rentals.remove(rentalToremove);
                System.out.println("Car Returned Successfully");
            } else {
                System.out.println("Car was not Rented");
            }

        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("======CAR RENTAL SYSTEM======");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("===Rent a Car===");
                System.out.println("Enter your name:");
                String customerName = scanner.nextLine();

                System.out.println("Available Cars:");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getcarid() + " - " + car.getbrand() + " - " + car.getmodel());
                    }
                }

                System.out.println("Enter CarID you want to rent:");
                String carid = scanner.nextLine();

                System.out.println("Enter the number of days for rental:");
                int rentalDays = scanner.nextInt();
                scanner.nextLine();

                Customer newCustomer = new Customer(customerName, "CUS" + (customers.size() + 1));
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getcarid().equals(carid) && car.isAvailable()) {
                        selectedCar = car;
                        break;

                    }

                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("=== Rental Information ===");
                    System.out.println("Customer ID: " + newCustomer.getcustomerid());
                    System.out.println("Customer Name: " + newCustomer.getname());
                    System.out.println("Car:" + selectedCar.getbrand() + " " + selectedCar.getmodel());
                    System.out.println("Rental days: " + rentalDays);
                    System.out.println("Total price: RS " + totalPrice);

                    System.out.println("\n Confirm Rental (Y/N)?");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar Rented Successfully");
                    } else {
                        System.out.println("\nRental Cancel");
                    }
                } else {
                    System.out.println("\nInvalid car Selection or Car is nor available for rent");
                }
            }

            else if (choice == 2) {
                System.out.println("===Return a Car===");
                System.out.println("Enter CarID you want to return: ");
                String carid = scanner.nextLine();

                Car carToreturn = null;
                for (Car car : cars) {
                    if (car.getcarid().equals(carid) && !car.isAvailable()) {
                        carToreturn = car;
                        break;

                    }

                }

                if (carToreturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar().equals(carToreturn)) {
                            customer = rental.getCustomer();
                            break;

                        }

                    }

                    if (customer != null) {
                        returnCar(carToreturn);
                        System.out.println("Car Returned Successfully by " + customer.getname());
                    } else {
                        System.out.println("Car was not rented or Rental information is missing");
                    }

                } else {
                    System.out.println("Invalid CarID or Car is not Rented");
                }

            }

            else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid Input.Please enter valid option");
            }

        }
        System.out.println("\nThank you for using Car Rental System");
    }
}
