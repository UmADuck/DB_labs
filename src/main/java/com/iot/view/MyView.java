package com.iot.view;

import com.iot.controller.*;
import com.iot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {

    @Autowired
    private BookingController bookingController;
    @Autowired
    private CityController cityController;
    @Autowired
    private CompanyCardController companyCardController;
    @Autowired
    private CustomerCardController customerCardController;
    @Autowired
    private ParkingController parkingController;
    @Autowired
    private ParkingSpotsInfoController parkingSpotsInfoController;
    @Autowired
    private ParkingTicketController parkingTicketController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Booking nullBooking = new Booking(null, null, null, null);
    private final City nullCity = new City(null, null);
    private final CompanyCard nullCompanyCard = new CompanyCard(null, null);
    private final CustomerCard nullCustomerCard = new CustomerCard(null, null, null, null, null, null);
    private final Parking nullParking = new Parking(null, null, null, null);
    private final ParkingSpotsInfo nullParkingSpotsInfo = new ParkingSpotsInfo(null, null, null, null, null, null, null);
    private final ParkingTicket nullParkingTicket = new ParkingTicket(null, null, null, null);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: Booking");
        menu.put("11", "  11 - Create Booking");
        menu.put("12", "  12 - Update Booking");
        menu.put("13", "  13 - Delete from Booking");
        menu.put("14", "  14 - Find all Booking");
        menu.put("15", "  15 - Find Booking by ID");
        menu.put("16", "  16 - Find Booking by customer id");

        menu.put("2", "   2 - Table: City");
        menu.put("21", "  21 - Create City");
        menu.put("22", "  22 - Update City");
        menu.put("23", "  23 - Delete from City");
        menu.put("24", "  24 - Find all Cities");
        menu.put("25", "  25 - Find City by name");
        menu.put("26", "  26 - Find City by region name");

        menu.put("3", "   3 - Table: Company card");
        menu.put("31", "  31 - Create Company card");
        menu.put("32", "  32 - Update Company card");
        menu.put("33", "  33 - Delete from Company card");
        menu.put("34", "  34 - Find all Company card");
        menu.put("35", "  35 - Find Company card by ID");
        menu.put("36", "  36 - Find all Company card by name");

        menu.put("4", "   4 - Table: Customer card");
        menu.put("41", "  41 - Create Customer card");
        menu.put("42", "  42 - Update Customer card");
        menu.put("43", "  43 - Delete from Customer card");
        menu.put("44", "  44 - Find all Customer card");
        menu.put("45", "  45 - Find Customer card by ID");

        menu.put("5", "   5 - Table: Parking ");
        menu.put("51", "  51 - Create Parking");
        menu.put("52", "  52 - Update Parking");
        menu.put("53", "  53 - Delete from Parking");
        menu.put("54", "  54 - Find all Parking");
        menu.put("55", "  55 - Find Parking by ID");

        menu.put("6", "   6 - Table: Parking spots info");
        menu.put("61", "  61 - Create Parking spots info");
        menu.put("62", "  62 - Update Parking spots info");
        menu.put("63", "  63 - Delete from Parking spots info");
        menu.put("64", "  64 - Find all Parking spots info");
        menu.put("65", "  65 - Find Parking spots info by ID");

        menu.put("7", "   7 - Table: Parking Ticket");
        menu.put("71", "  71 - Create Parking Ticket");
        menu.put("72", "  72 - Update Parking Ticket");
        menu.put("73", "  73 - Delete from Parking Ticket");
        menu.put("74", "  74 - Find all Parking Ticket");
        menu.put("75", "  75 - Find Parking Ticket by ID");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createBooking);
        methodsMenu.put("12", this::updateBooking);
        methodsMenu.put("13", this::deleteFromBooking);
        methodsMenu.put("14", this::findAllBookings);
        methodsMenu.put("15", this::findBookingById);
        methodsMenu.put("16", this::findBookingByCustomerId);


        methodsMenu.put("21", this::createCity);
        methodsMenu.put("22", this::updateCity);
        methodsMenu.put("23", this::deleteFromCity);
        methodsMenu.put("24", this::findAllCities);
        methodsMenu.put("25", this::findCityByName);
        methodsMenu.put("26", this::findCityByRegionName);

        methodsMenu.put("31", this::createCompanyCard);
        methodsMenu.put("32", this::updateCompanyCard);
        methodsMenu.put("33", this::deleteFromCompanyCard);
        methodsMenu.put("34", this::findAllCompanyCards);
        methodsMenu.put("35", this::findCompanyCardById);
        methodsMenu.put("36", this::findCompanyCardByName);

        methodsMenu.put("41", this::createCustomerCard);
        methodsMenu.put("42", this::updateCustomerCard);
        methodsMenu.put("43", this::deleteFromCustomerCard);
        methodsMenu.put("44", this::findAllCustomerCards);
        methodsMenu.put("45", this::findCustomerCardById);


        methodsMenu.put("51", this::createParking);
        methodsMenu.put("52", this::updateParking);
        methodsMenu.put("53", this::deleteFromParking);
        methodsMenu.put("54", this::findAllParkings);
        methodsMenu.put("55", this::findParkingById);


        methodsMenu.put("61", this::createParkingSpotsInfo);
        methodsMenu.put("62", this::updateParkingSpotsInfo);
        methodsMenu.put("63", this::deleteFromParkingSpotsInfo);
        methodsMenu.put("64", this::findAllParkingSpotsInfos);
        methodsMenu.put("65", this::findParkingSpotsInfoById);

        methodsMenu.put("71", this::createParkingTicket);
        methodsMenu.put("72", this::updateParkingTicket);
        methodsMenu.put("73", this::deleteFromParkingTicket);
        methodsMenu.put("74", this::findAllParkingTickets);
        methodsMenu.put("75", this::findParkingTicketById);

    }

    private void selectAllTable() {
        findAllBookings();
        findAllCities();
        findAllCompanyCards();
        findAllCustomerCards();
        findAllParkings();
        findAllParkingSpotsInfos();
        findAllParkingTickets();
    }

    // region BOOKING ---------------------------------------------------
    private void createBooking() {
        System.out.println("Input 'customer_id': ");
        Integer customerId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'parked_car_number_plate': ");
        String parkedCarNumberPlate = input.nextLine();
        Booking booking = new Booking(null,null, customerId, parkedCarNumberPlate );

        int count = bookingController.create(booking);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateBooking() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'customer_id': ");
        Integer customerId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'parked_car_number_plate': ");
        String parkedCarNumberPlate = input.nextLine();
        Booking booking = new Booking(null,null, customerId, parkedCarNumberPlate);

        int count = bookingController.update(id, booking);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromBooking() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = bookingController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllBookings() {
        System.out.println("\nTable: BOOKING");
        List<Booking> bookings = bookingController.findAll();
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private void findBookingById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Booking> book = bookingController.findById(id);
        System.out.println(book.orElse(nullBooking));
    }

    private void findBookingByCustomerId() {
        System.out.println("Input 'customer_id': ");
        Integer customerId = Integer.valueOf(input.nextLine());

        Optional<Booking> book = bookingController.findByCustomerIdCard(customerId);
        System.out.println(book.orElse(nullBooking));
    }

    //endregion
    // region CITY ---------------------------------------------------
    private void createCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();

        City city = new City(cityName, regionName);

        int count = cityController.create(city);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input new 'city_name': ");
        String newCityName = input.nextLine();
        System.out.println("Input new 'region_name': ");
        String newRegionName = input.nextLine();

        City city = new City(newCityName, newRegionName);

        int count = cityController.update(cityName, city);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        int count = cityController.delete(cityName);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCities() {
        System.out.println("\nTable: CITY");
        List<City> cities = cityController.findAll();
        for (City city : cities) {
            System.out.println(city);
        }
    }

    private void findCityByName() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        Optional<City> city = cityController.findByCityName(cityName);
        System.out.println(city.orElse(nullCity));
    }

    private void findCityByRegionName() {
        System.out.println("Input 'region_name': ");
        String regionName = input.nextLine();

        Optional<City> city = cityController.findByRegion(regionName);
        System.out.println(city.orElse(nullCity));
    }

    // endregion
    // region COMPANY_CARD -------------------------------------------------
    private void createCompanyCard() {
        System.out.println("Input 'company_name': ");
        String companyName = input.nextLine();

        CompanyCard companyCard = new CompanyCard(null, companyName);

        int count = companyCardController.create(companyCard);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCompanyCard() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'company_name': ");
        String companyName = input.nextLine();

        CompanyCard companyCard = new CompanyCard(null, companyName);

        int count = companyCardController.update(id, companyCard);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCompanyCard() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = companyCardController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCompanyCards() {
        System.out.println("\nTable: COMPANY_CARD");
        List<CompanyCard> cards = companyCardController.findAll();
        for (CompanyCard companyCard : cards) {
            System.out.println(companyCard);
        }
    }

    private void findCompanyCardById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<CompanyCard> companyCard = companyCardController.findById(id);
        System.out.println(companyCard.orElse(nullCompanyCard));
    }

    private void findCompanyCardByName() {
        System.out.println("Input 'company_name': ");
        String companyName = input.nextLine();

        Optional<CompanyCard> companyCard = companyCardController.findByCompanyName(companyName);
        System.out.println(companyCard.orElse(nullCompanyCard));
    }
    //endregion
    // region CUSTOMER_CARD ---------------------------------------------------
    private void createCustomerCard() {
        System.out.println("Input 'customer_name': ");
        String customerName = input.nextLine();
        System.out.println("Input 'customer_surname': ");
        String customerSurname = input.nextLine();
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        CustomerCard customerCard = new CustomerCard(null, customerName, customerSurname, cityName, null, null);

        int count = customerCardController.create(customerCard);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCustomerCard() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'customer_name': ");
        String newCustomerName = input.nextLine();
        System.out.println("Input new 'customer_surname': ");
        String newCustomerSurname = input.nextLine();
        System.out.println("Input new 'city_name': ");
        String newCityName = input.nextLine();
        CustomerCard customerCard = new CustomerCard(null, newCustomerName, newCustomerSurname, newCityName, null, null);

        int count = customerCardController.update(id, customerCard);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCustomerCard() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = customerCardController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCustomerCards() {
        System.out.println("\nTable: CUSTOMER_CARD");
        List<CustomerCard> customerCards = customerCardController.findAll();
        for (CustomerCard customerCard : customerCards) {
            System.out.println(customerCard);
        }
    }

    private void findCustomerCardById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<CustomerCard> customerCard = customerCardController.findById(id);
        System.out.println(customerCard.orElse(nullCustomerCard));
    }

    //endregion
    // region PARKING ---------------------------------------------------
    private void createParking() {
        System.out.println("Input 'number_of_parking_spots': ");
        String numberOfParkingSpots = input.nextLine();

        Parking parking = new Parking(null, numberOfParkingSpots, null, null);

        int count = parkingController.create(parking);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateParking() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'number_of_parking_spots': ");
        String numberOfParkingSpots = input.nextLine();

        Parking parking = new Parking(null, numberOfParkingSpots, null, null);

        int count = parkingController.update(id, parking);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromParking() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = parkingController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllParkings() {
        System.out.println("\nTable: BOOK");
        List<Parking> parkings = parkingController.findAll();
        for (Parking parking : parkings) {
            System.out.println(parking);
        }
    }

    private void findParkingById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Parking> parking = parkingController.findById(id);
        System.out.println(parking.orElse(nullParking));
    }
    //endregion
    // region PARKING_SPOTS_INFO ---------------------------------------------------
    private void createParkingSpotsInfo() {
        System.out.println("Input 'parking_id': ");
        Integer parkingId = Integer.valueOf((input.nextLine()));

        ParkingSpotsInfo parkingSpotsInfo = new ParkingSpotsInfo(null, parkingId, null, null, null, null, null);

        int count = parkingSpotsInfoController.create(parkingSpotsInfo);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateParkingSpotsInfo() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'parking_id': ");
        Integer parkingId = Integer.valueOf((input.nextLine()));

        ParkingSpotsInfo parkingSpotsInfo = new ParkingSpotsInfo(null, parkingId, null, null, null, null, null);

        int count = parkingSpotsInfoController.update(id, parkingSpotsInfo);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromParkingSpotsInfo() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = parkingSpotsInfoController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllParkingSpotsInfos() {
        System.out.println("\nTable: BOOK");
        List<ParkingSpotsInfo> spots = parkingSpotsInfoController.findAll();
        for (ParkingSpotsInfo spot : spots) {
            System.out.println(spot);
        }
    }

    private void findParkingSpotsInfoById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<ParkingSpotsInfo> parkingSpotsInfo = parkingSpotsInfoController.findById(id);
        System.out.println(parkingSpotsInfo.orElse(nullParkingSpotsInfo));
    }
    //endregion
    // region PARKING_TICKET ---------------------------------------------------
    private void createParkingTicket() {
        System.out.println("Input 'parked_car_number_plates': ");
        String parkedCarNumberPlates = input.nextLine();

        ParkingTicket parkingTicket = new ParkingTicket(null, parkedCarNumberPlates, null, null);

        int count = parkingTicketController.create(parkingTicket);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateParkingTicket() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'parked_car_number_plates': ");
        String parkedCarNumberPlates = input.nextLine();

        ParkingTicket parkingTicket = new ParkingTicket(null, parkedCarNumberPlates, null, null);

        int count = parkingTicketController.update(id, parkingTicket);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromParkingTicket() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = parkingTicketController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllParkingTickets() {
        System.out.println("\nTable: BOOK");
        List<ParkingTicket> tickets = parkingTicketController.findAll();
        for (ParkingTicket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    private void findParkingTicketById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<ParkingTicket> parkingTicket = parkingTicketController.findById(id);
        System.out.println(parkingTicket.orElse(nullParkingTicket));
    }
    //endregion
    //-------------------------------------------------------------------------
    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }
    //endregion
}