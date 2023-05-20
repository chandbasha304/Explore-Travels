package Kingk.travel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.validation.Validator;

public class ExploreTravels extends Services {
	public ExploreTravels(String firstname, String lastname, String mobile, String gender, String email,
			String password, String userWalletAmount) {
	}

	public ExploreTravels() {
		// TODO Auto-generated constructor stub
	}

	static Map<String, String> bookingHistory = new HashMap<>();
	 Map<String, String> bookingHistoryfor = new HashMap<>();
	// For Continuation
	static boolean view = true;
	// Login Attempts
	static int failedCountLimit = ExploreTravels.count;
	static int userfailedcount = 0;
	// taking Runtime Data by using Scanner
	static Scanner enter = new Scanner(System.in);
	// adding places details by using Map
	static Map<String, Services> places = new HashMap<>();
//adding new user details by using Map 
	static Map<String, Passenger> newUserDetails = new HashMap<>();

	// program starts from here
	public static void main(String[] args) {
		ArrayList<Passenger> passengerData = StoredUserData();

		// here adding stored data to newuserDetails which is a map collection
		for (Passenger passenger : passengerData) {
			newUserDetails.put(passenger.getEmail(), passenger);
		}
		System.out.println(LocalDate.now() + "   " + LocalDateTime.now().getMonth());
		LocalDate date = LocalDate.now();
		System.out.println(date.getDayOfWeek());
		AddingStoredDatatoMap();
		// Travel Image or Logo printing and receiving logopath from other JavaClass
		File travelsLogo = LogoReading();

//			checking if logo is in local disk or not
		if (travelsLogo.exists()) {

			try {
//						Here Comes the Working process 
				TravelsPassengerData();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void AddingStoredDatatoMap() {
		ArrayList<Passenger> passengerData = StoredUserData();

		// here adding stored data to newuserDetails which is a map collection
		for (Passenger passenger : passengerData) {
			newUserDetails.put(passenger.getEmail(), passenger);
		}
	}

	// program starts from here
	public static ArrayList<ExploreTravels> TravelsPassengerData() throws IOException {

		while (view == true) {

//Displaying The Menu 
			DisplayMainMenu();
//		
//		Giving options to user for selecting any option from menu

			int option = enter.nextInt();

			switch (option) {
			case 1:

				System.out.println(newUserDetails.size() + "before length");

//			receiving runtime Data from Captureuserdetails 
				try {
					Passenger userDetails = captureUserDetails();
//			here we adding runtime data to newUserDetails and which shows both runtime data and Stored Data
					newUserDetails.put(userDetails.getEmail(), userDetails);
					System.out.println(newUserDetails);
					System.out.println(newUserDetails.size() + "after length");
				} catch (InputMismatchException e) {
					System.out.println("Provide Correct Inputs");
					TravelsPassengerData();
				} catch (NullPointerException e) {
					System.out.println("NullPointer Exception");
					TravelsPassengerData();
				} catch (Exception e) {
					System.out.println("Wrong Exceptions");
					TravelsPassengerData();
				}

//			Again We are Going To Menu for selecting different options 
				break;

			case 2:
				System.out.println("Welcome To Explore Travels\nPlease Login Your Account\n");
//Login The Account using Emailid and Password
				try {

					LoginProcess();
				} catch (InputMismatchException e) {
					System.out.println("Provide Correct Inputs");
				} catch (NullPointerException e) {
					System.out.println("NullPointer Exception");
				} catch (Exception e) {

					System.out.println("Wrong Exceptions");
				}
				TravelsPassengerData();

				break;
			case 3:
				BookingTravels();
//			LocalDate date=LocalDate.now();
//			System.out.println(date.getMonthValue());

				ArrayList<Services> receive = dataStoring();
				for (Services services : receive) {
					places.put(services.getNumber(), services);
				}
				System.out.println(places.size() + "size");
				System.out.println(places);

				RouteSelection();
				try {

					String busOption = enter.next();

					String bookingdate = BookTicketsonDate();

					LocalDate date = LocalDate.now();
					LocalDate checkingDate = LocalDate.parse(bookingdate);
					DayOfWeek ac = checkingDate.getDayOfWeek();
					System.out.println(checkingDate.getMonth());
					System.out.println(checkingDate.getDayOfMonth());
					System.out.println(checkingDate.getDayOfYear());
					System.out.println(checkingDate.getMonthValue());
					System.out.println(checkingDate.getDayOfWeek());
					System.out.println(checkingDate.getYear());
					if (checkingDate.getDayOfMonth() >= date.getDayOfMonth()
							& checkingDate.getMonthValue() >= date.getMonthValue()) 
					{
						System.out.println(checkingDate + "bookingdata");
						int g = date.getDayOfMonth();
						int ao = checkingDate.getDayOfMonth();
						System.out.println(ac + " " + g + " " + ao);
						System.out.println(ao > g);

						String checkdate = ac.toString();
						System.out.println(checkdate);
						System.out.println(places.get(busOption) + "details");

						if (checkdate.equalsIgnoreCase("Saturday") | checkdate.equalsIgnoreCase("Sunday")) {
							System.out.println("True+hi");

							weekendsBooking(busOption, checkingDate);

						}

						else {
//						Services sourceData = places.get(busOption);
							System.out.println("True");

							normalDaysBooking(busOption, checkingDate);

						}

					} 
					else 
					{
						try {

							throw new InputMismatchException(
									"Please Enter Current Date Or Future Dates with current year and current month or Upcoming Months ");
						} catch (Exception e) {
							System.out.println(e.getMessage());

						}

						break;
					}

				}

				catch (InputMismatchException e) {
					System.out.println("Provide Correct Inputs");
				} catch (NullPointerException e) {
					System.out.println("NullPointer Exception");
				} catch (Exception e) {

					System.out.println("You Provide Wrong Input ,Kindly Use correct Data3");
				}
				TravelsPassengerData();
				break;
			case 4:
				try
				{
					
				
				System.out.println("1.View Your Bookings\t 2.Reschedule Your Booking ");
				int viewopt = enter.nextInt();
				switch (viewopt) {
				case 1:
					System.out.println("Please Enter Your Email ID ");
					String mailId = enter.next();
					for (Entry<String, String> entry : bookingHistory.entrySet()) {
						String key = entry.getKey();
						String val = entry.getValue();
						System.out.println(mailId.equalsIgnoreCase(key));

					}

					System.out.println(bookingHistory + "result");

					break;
				case 2:
					System.out.println("Reschedule or Cancel Ticket");
					System.out.println(bookingHistory);
					System.out.println("Please Enter Your Email ID ");
					String newEmailID = enter.next();
					String x = bookingHistory.get(newEmailID);
					System.out.println(x + "ju");
					String[] m = x.split("_");
					System.out.println(m.toString());
					System.out.println(m.length + "length");
					ArrayList<String> fetchData = new ArrayList<>();
					for (String string : m) {
						fetchData.add(string);
					}
					System.out.println(fetchData);
					Object[] arr = fetchData.toArray();
					for (int i = 0; i < arr.length; i++) {
						System.out.println(arr[i]);
					}
					Object ticks = arr[1];
					Object tick1 = arr[4];
					Object ticks2 = arr[5];
					System.out.println("Please Enter Reschedule Date\n");
					String rescheduldate = enter.next();
					LocalDate reschediles = LocalDate.now();
					Passenger userRescheduling = newUserDetails.get(newEmailID);
					System.out.println(userRescheduling.getUserWalletAmount() + "direct from amount");
					bookingHistory.put(newEmailID,
							"Current Balance In Wallet=" + userRescheduling.getUserWalletAmount() + "_" + ticks + "_"
									+ "Ticket Booked on " + reschediles + "_" + "Ticket Booked for Travel Date="
									+ rescheduldate + "_" + tick1 + "_" + "Bus Details" + ticks2);

					break;
				case 3:
					view = false;

					break;
				}
				}

				catch (InputMismatchException e) {
					System.out.println("Provide Correct Inputs");
				} catch (NullPointerException e) {
					System.out.println("NullPointer Exception You Don't Have Any Bookings,Book Your Tickets");
				} catch (Exception e) {

					System.out.println("Wrong Exceptions");
				}
				TravelsPassengerData();
                   break;
			case 5:
				view=false;
				break;
			}
		}
		return null;
	}
	

	@Override
	public String toString() {
		return "ExploreTravels [number=" + number + ", fromCity=" + fromCity + ", destinationCity=" + destinationCity
				+ ", seats=" + seats + ", amount=" + amount + ", date=" + date + ", toString()=" + super.toString()
				+ ", getNumber()=" + getNumber() + ", getFromCity()=" + getFromCity() + ", getDestinationCity()="
				+ getDestinationCity() + ", getSeats()=" + getSeats() + ", getAmount()=" + getAmount() + ", getDate()="
				+ getDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	private static String BookTicketsonDate() {
		System.out.println("Enter Your Data in this format\n" + "Year-Month-Date like 2023-05-10");
		String bookingdate = enter.next();
		return bookingdate;
	}

	private static void normalDaysBooking(String busOption, LocalDate checkingDate)
			throws FileNotFoundException, IOException {
		Services sourceData = places.get(busOption);
		System.out.println(sourceData + "before seats booking");
		System.out.println("True");

		System.out.println("Book Tickets");
		System.out.println("Enter Number Of Tickets");
		int seatnumber = enter.nextInt();
		if (sourceData.getSeats().equalsIgnoreCase("0")) {
			System.out.println("No Seats Available Choose Another Bus");
			BookingTravels();

		} else {

			int listseats = Integer.parseInt(sourceData.getSeats());
			int updatedseat = listseats - seatnumber;
			String conversion = Integer.toString(updatedseat);
			sourceData.setSeats(conversion);
			System.out.println(sourceData + "after booking");

			String amountfromwallet = PaymentProcessShowing();
			String paylog = ExploreTravels.paylogo;
			File readingFile = new File(paylog);
			FileInputStream logopay = new FileInputStream(readingFile);
			int logs;
			while ((logs = logopay.read()) != -1) {
				char logos = (char) logs;
				System.out.print(logos);
			}
			System.out.println("Payment Successfull\n");
			String[] bothEmailandWallet = amountfromwallet.split(",");
			Double ticketRatefromwallet = Double.parseDouble(bothEmailandWallet[1]);
			System.out.println(ticketRatefromwallet + "from account");
			Passenger userBioData = newUserDetails.get(bothEmailandWallet[0]);
			Double ticketdata = Double.parseDouble(sourceData.getAmount());
			double totalRate = ticketdata * seatnumber;

			double rateofticket = ticketRatefromwallet - totalRate;

			Object rateconvert = totalRate;

			Object convertDatatoObject = rateofticket;
			String updatedAmount = convertDatatoObject.toString();

			userBioData.setUserWalletAmount(updatedAmount);
			System.out.println(userBioData + "After");
			LocalDate datetimes = LocalDate.now();
			String bookedDate = datetimes.toString();
			String particularate = checkingDate.toString();
			System.out.println(particularate + "Ondate Booking");
			System.out.println(bookingHistory + "new");
			System.out.println(userBioData.getEmail() + "emailId");

			bookingHistory.put(userBioData.getEmail(),
					"Current Balance In Wallet=" + updatedAmount + "_" + "Cost Of The Ticket =" + rateconvert.toString()
							+ "_" + "Ticket Booked on " + bookedDate + "_" + "Ticket Booked for Travel Date="
							+ particularate + "_" + "Number Of Seats=" + seatnumber + "_" + "Bus Details" + sourceData);
		}
	}

	private static void weekendsBooking(String busOption, LocalDate checkingDate)
			throws FileNotFoundException, IOException {
		Services sourceData = places.get(busOption);
		System.out.println(sourceData + "before seats booking");

		System.out.println("Book Tickets");
		System.out.println("Enter Number Of Tickets");
		int seatnumber = enter.nextInt();
		
		if (sourceData.getSeats().equalsIgnoreCase("0")) {
			System.out.println("No Seats Available Choose Another Bus");
			BookingTravels();

		} else {

			int listseats = Integer.parseInt(sourceData.getSeats());
			int updatedseat = listseats - seatnumber;
			String conversion = Integer.toString(updatedseat);
			sourceData.setSeats(conversion);
			System.out.println(sourceData + "after booking");

			String amountfromwallet = PaymentProcessShowing();
			String paylog = ExploreTravels.paylogo;
			File readingFile = new File(paylog);
			FileInputStream logopay = new FileInputStream(readingFile);
			int logs;
			while ((logs = logopay.read()) != -1) {
				char logos = (char) logs;
				System.out.print(logos);

			}
			System.out.println("Payment Successfull\n");
			String[] bothEmailandWallet = amountfromwallet.split(",");
			Double ticketRatefromwallet = Double.parseDouble(bothEmailandWallet[1]);
			System.out.println(ticketRatefromwallet + "from account");
			Passenger userBioData = newUserDetails.get(bothEmailandWallet[0]);
			Double ticketdata = Double.parseDouble(sourceData.getAmount());
			System.out.println(ticketdata + "TicketRate for Travelling");
			double totalRate = (ticketdata + (ticketdata * 0.05)) * seatnumber;
			Object rateconvert = totalRate;

			double rateofticket = ticketRatefromwallet - totalRate;
			System.out.println(rateofticket + "extracharges");

			Object convertDatatoObject = rateofticket;
			String updatedAmount = convertDatatoObject.toString();

			userBioData.setUserWalletAmount(updatedAmount);
			System.out.println(userBioData + "After");
			LocalDate datetimes = LocalDate.now();
			String bookedDate = datetimes.toString();
			String particularate = checkingDate.toString();

			bookingHistory.put(userBioData.getEmail(),
					"Current Balance In Wallet=" + updatedAmount + "_" + "Cost Of The Ticket =" + rateconvert.toString()
							+ "_" + "Ticket Booked on " + bookedDate + "_" + "Ticket Booked for Travel Date="
							+ particularate + "_" + "Number Of Seats=" + seatnumber + "_" + "Bus Details" + sourceData);
		
		}
	}

	public static Map<String, String> getBookingHistory() {
		return bookingHistory;
	}

	public static void setBookingHistory(Map<String, String> bookingHistory) {
		ExploreTravels.bookingHistory = bookingHistory;
	}

	private static void BookingTravels() {
		System.out.println("Plan Journey");
		System.out.println("Explore Travles Top Tourist Bus Routes");
		System.out.println("1.Vijayawada ->Hyderabad\t   2. Hyderabad-> Vijayawada\n"
				+ "3.Vijayawada -> Chennai\t  4.Chennai-> Vijayawada\n"
				+ "5.Vijayawada -> Tirupati\t  6.Tirupathi-> Vijayawada\n"
				+ "7.Vijayawada -> Ooty\t  8.Ooty -> Vijayawada\n"
				+ "9.Vijayawada -> Bangalore\t   10.Bangalore-> Vijayawada\n "
				+ "11.Hyderabad -> Chennai\t  12.Chennai-> Hyderabad\n"
				+ "13.Hyderabad -> Bangalore\t  14.Bangalore-> Hyderabad\n"
				+ "15.Hyderabad -> Tirupati\t  16.Tirupati-> Hyderabad\n"
				+ "17..Hyderabad -> Ooty\t  18.Ooty-> Hyderabad\n" + "19.Bangalore -> Ooty\t  20.Ooty-> Bangalore\n"
				+ "21.Bangalore -> Chennai\t  22.Chennai-> Bangalore\n"
				+ "23.Bangalore -> Tirupati\t  24.Tirupati-> Bangalore\n" + "25.Chennai -> Ooty\t  26.Ooty-> Chennai\n"
				+ "27.Chennai -> Tirupati\t  28.Tirupati-> Chennai\n" + "29.Ooty -> Chennai\t  30.Chennai-> Ooty\n");
	}

	private static void RouteSelection() {
		System.out.println("Select Your Travel Route\n" + "Kindly Use 1 - 30 Numbers ");
	}

	private static String PaymentProcessShowing() throws FileNotFoundException, IOException {
		System.out.println(
				"Choose Your  Amount Transaction Option\n " + "1.ExploreTravlesWallet 2.Debit Card 3.UPI Payments");
		System.out.println(
				"4.If You are a New User Then Create Your Account In Explore Travels OR PayThrough Debit Card OR UPI");
		ArrayList<Services> receive = dataStoring();
		for (Services services : receive) {
			places.put(services.getFromCity(), services);
		}

		int options = enter.nextInt();
		switch (options) {
		case 1:
			System.out.println("Enter Your Email Id While Creating Explore Travels Account");
			String correctemail = enter.next();

			Map<String, Passenger> forPayments = newUserDetails;

			for (Map.Entry<String, Passenger> entry : forPayments.entrySet()) {
				String key = entry.getKey();
				Passenger val = entry.getValue();

				if (correctemail.equalsIgnoreCase(key)) {
					System.out.println(key);
					String amountInWallet = val.getUserWalletAmount();
					System.out.println(amountInWallet);
					System.out.println(correctemail);

					return correctemail + "," + amountInWallet;
				}

			}
			break;
		case 2:
			System.out.println("Currently We are Providing Services Through Wallet \n"
					+ "We Will Provide Debit Card Services Very Soon" + "Sorry For Inconvenience");
			PaymentProcessShowing();

			break;
		case 3:
			System.out.println("Currently We are Providing Services Through Wallet \n"
					+ "We Will Provide UPI Services Very Soon" + "Sorry For Inconvenience");
			PaymentProcessShowing();
			break;
		case 4:
			System.exit(0);
			break;

		}
		return null;
	}

	private static ArrayList<Services> dataStoring() {
		ArrayList<Services> touringPlaces = new ArrayList<>();
		Services firstcityaway = new Services("1", "Vijayawada", "Hyderabad", "25", "2500");
		touringPlaces.add(firstcityaway);
		Services firstcitytowards = new Services("2", "Hyderabad", "Vijayawada", "25", "3000");
		touringPlaces.add(firstcitytowards);
		Services secondcityaway = new Services("3", "Vijayawada", "Chennai", "20", "6500");
		touringPlaces.add(secondcityaway);
		Services secondcitytowards = new Services("4", "Chennai", "Vijayawada", "05", "3000");
		touringPlaces.add(secondcitytowards);
		Services thirdcityaway = new Services("5", "Vijayawada", "Tirupati", "15", "4555");
		touringPlaces.add(thirdcityaway);
		Services thirdcitytowards = new Services("6", "Tirupati", "Vijayawada", "0", "6500");
		touringPlaces.add(thirdcitytowards);
		Services fourthcityaway = new Services("7", "Vijayawada", "Ooty", "12", "7546");
		touringPlaces.add(fourthcityaway);
		Services fourthcitytowards = new Services("8", "Ooty", "Vijayawada", "25", "7545");
		touringPlaces.add(fourthcitytowards);
		Services fifthcityaway = new Services("9", "Vijayawada", "Bangalore", "0", "5239");
		touringPlaces.add(fifthcityaway);
		Services fifthcitytowards = new Services("10", "Bangalore", "Vijayawada", "10", "5285");
		touringPlaces.add(fifthcitytowards);
		Services sixthcityaway = new Services("11", "Hyderabad", "Chennai", "25", "2500");
		touringPlaces.add(sixthcityaway);
		Services sixthcitytowards = new Services("12", "Chennai", "Hyderabad", "25", "3000");
		touringPlaces.add(sixthcitytowards);
		Services seventhcityaway = new Services("13", "Hyderabad", "Bangalore", "20", "6500");
		touringPlaces.add(seventhcityaway);
		Services seventhcitytowards = new Services("14", "Bangalore", "Hyderabad", "05", "3000");
		touringPlaces.add(seventhcitytowards);
		Services eighthcityaway = new Services("15", "Hyderabad", "Tirupati", "15", "4555");
		touringPlaces.add(eighthcityaway);
		Services eighthcitytowards = new Services("16", "Tirupati", "Hyderabad", "0", "6500");
		touringPlaces.add(eighthcitytowards);
		Services ninethcityaway = new Services("17", "Hyderabad", "Ooty", "12", "7546");
		touringPlaces.add(ninethcityaway);
		Services ninethcitytowards = new Services("18", "Ooty", "Hyderabad", "25", "7545");
		touringPlaces.add(ninethcitytowards);
		Services tenthcityaway = new Services("19", "Bangalore", "Ooty", "0", "5239");
		touringPlaces.add(tenthcityaway);
		Services tenthcitytowards = new Services("20", "Ooty", "Bangalore", "10", "5285");
		touringPlaces.add(tenthcitytowards);
		Services eleventhcityaway = new Services("21", "Bangalore", "Chennai", "0", "5239");
		touringPlaces.add(eleventhcityaway);
		Services eleventhcitytowards = new Services("22", "Chennai", "Bangalore", "10", "5285");
		touringPlaces.add(eleventhcitytowards);
		Services twelvethcityaway = new Services("23", "Bangalore", "Tirupathi", "0", "5239");
		touringPlaces.add(twelvethcityaway);
		Services twelvethcitytowards = new Services("24", "Tirupathi", "Bangalore", "10", "5285");
		touringPlaces.add(twelvethcitytowards);
		Services thirteenthcityaway = new Services("25", "Chennai", "Ooty", "0", "5239");
		touringPlaces.add(thirteenthcityaway);
		Services thirteenthcitytowards = new Services("26", "Ooty", "Chennai", "10", "5285");
		touringPlaces.add(thirteenthcitytowards);
		Services fourteenthcityaway = new Services("27", "Chennai", "Tirupathi", "2", "5239");
		touringPlaces.add(fourteenthcityaway);
		Services fourteenthcitytowards = new Services("28", "Tirupathi", "Chennai", "10", "5285");
		touringPlaces.add(fourteenthcitytowards);
		Services fifteenthcityaway = new Services("29", "Ooty", "Tirupathi", "0", "5239");
		touringPlaces.add(fifteenthcityaway);
		Services sixteenthcitytowards = new Services("30", "Tirupathi", "Ooty", "10", "5285");
		touringPlaces.add(sixteenthcitytowards);

		System.out.println(touringPlaces.size() + "length");
		return touringPlaces;

	}

	private static void DisplayMainMenu() {
		System.out.print(
				"1.New Admin User Registration  2.Login Account 3.Plan Journey 4.Reschedule Ticket Operations 5.Stop The Working Process\nPlease Enter Your Option\n");
	}

	private static void LoginProcess()

	{
		System.out.println("Enter Your EmailId");
		String emailId = enter.next();
		System.out.println("Password");
		String password = enter.next();

		if (userfailedcount < failedCountLimit) {
			System.out.println(userfailedcount + "count of login");

			if (newUserDetails.containsKey(emailId)) {

				System.out.println("Email Id Found In Map");
				Passenger logindetails = newUserDetails.get(emailId);
				String usermail = logindetails.getEmail();
				String userpassword = logindetails.getPassword();
				System.out.println(userfailedcount + "count of login");
				boolean result = password.equals(userpassword);
				System.out.println(result + "result");
				enteringIntoAccount(emailId, password, usermail, userpassword);
			} else {
				System.out.println(emailId + "not found");
				System.out.println("Invalid UserName");
				userfailedcount++;
				LoginProcess();
			}
		} else {

			System.out.println(userfailedcount + "times");
			System.out.println(
					"Your Account Is Blocked ,Please Visit Our Main Branch OF Explore Travels Or Try Again After 24 hours");
			System.exit(0);
		}
	}

	private static void enteringIntoAccount(String emailId, String password, String usermail, String userpassword) {
		if (password.equals(userpassword)) {

			System.out.println("Welocome To Explore Travels PLease Book Your Tickets\n");
			System.out.println("Explore Travles Top Tourist Bus Routes");
			System.out.println("1.Vijayawada ->Hyderabad\t   2. Hyderabad-> Vijayawada\n"
					+ "3.Vijayawada -> Chennai\t  4.Chennai-> Vijayawada\n"
					+ "5.Vijayawada -> Tirupati\t  6.Tirupathi-> Vijayawada\n"
					+ "7.Vijayawada -> Ooty\t  8.Ooty -> Vijayawada\n"
					+ "9.Vijayawada -> Bangalore\t   10.Bangalore-> Vijayawada\n "
					+ "11.Hyderabad -> Chennai\t  12.Chennai-> Hyderabad\n"
					+ "13.Hyderabad -> Bangalore\t  14.Bangalore-> Hyderabad\n"
					+ "15.Hyderabad -> Tirupati\t  16.Tirupati-> Hyderabad\n"
					+ "17..Hyderabad -> Ooty\t  18.Ooty-> Hyderabad\n" + "19.Bangalore -> Ooty\t  20.Ooty-> Bangalore\n"
					+ "21.Bangalore -> Chennai\t  22.Chennai-> Bangalore\n"
					+ "23.Bangalore -> Tirupati\t  24.Tirupati-> Bangalore\n"
					+ "25.Chennai -> Ooty\t  26.Ooty-> Chennai\n" + "27.Chennai -> Tirupati\t  28.Tirupati-> Chennai\n"
					+ "29.Ooty -> Chennai\t  30.Chennai-> Ooty\n");
			System.out.println("1.My Bookings 2.My Profile 3. Logout ");

			int loginoption = enter.nextInt();
			switch (loginoption) {
			case 1:
				String x = bookingHistory.get(emailId);
				System.out.println(x.equals(null));
				String[] output = x.split(",");
				System.out.println(output);
				if (x.equals(null)) {
					try {
						throw new InputMismatchException(
								"No Bookings Available \nPlease Book Your Tickets On Main Menu ");
					} catch (Exception e) {
						System.out.println(e.getMessage());
						DisplayMainMenu();
					}
				}
				ArrayList<String> fetchData = new ArrayList<>();
				for (String string : output) {
					fetchData.add(string);
				}

				System.out.println(fetchData);

				break;
			case 3:
				System.exit(0);
				break;
			}

		} else {
			userfailedcount++;
			System.out.println(" Invalid Password");
			System.out.println("Valid Attempts are" + failedCountLimit + "times");
			System.out.println("You Have" + (failedCountLimit - userfailedcount) + "Attempts Left");
			LoginProcess();
		}
	}

	private static File LogoReading() {
		String logoPath = ExploreTravels.name;
		File x = new File(logoPath);
		FileInputStream take = null;
		try {
			take = new FileInputStream(x);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int u;
		try {
			while ((u = take.read()) != -1) {
				System.out.print((char) u);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n");
//		 boolean result=x.exists(); 
		return x;
	}

//	Adding runtime Data to Passenger By using Constructor or Setters
	public static Passenger captureUserDetails() throws IOException {
		System.out.println("New User Registration");
		System.out.println(
				"Please Enter\n 1.First Name\n2.Last Name \n3.Mobile Number\n4.Gender  \n5.Email id \n6. Password like in this format");

		System.out.println("Please Enter First Name\n");
		String firstname = enter.next();
		System.out.println("Please Enter second Name\n");
		String secondname = enter.next();

		DisplayMobile();

		String mobile = enter.next();

		System.out.println(" Please Enter Gender\n");
		String gender = enter.next();
		System.out.println("Please Enter Email id\n "
				+ "Kindly use all small letters  \nPlease Use This Pattern:xyz145@gmail.com");
		String email = enter.next();
		String loweremail = email.toLowerCase();

		System.out.println("Please Enter password\n");
		String password = enter.next();

		System.out.println("Please Enter UserWallet Amount");
		String userwallet = enter.next();
		if (mobile.length() < 10) {

			try {
				throw new FileNotFoundException("Please Enter Mobile Number Upto 10 Digits\n");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				captureUserDetails();
			}

		}
		Passenger user = new Passenger(firstname, secondname, mobile, gender, loweremail, password, userwallet);
//			user.setFirstname(firstname);
//			user.setLastname(secondname);
//			user.setMobile(mobile);
//			user.setGender(gender);
//			user.setEmail(loweremail);
//			user.setPassword(password);

		return user;

	}

	private static void DisplayMobile() {
		System.out.print("Please Enter Mobile Number\n");
	}

	public static ArrayList<Passenger> StoredUserData() {
		ArrayList<Passenger> detailsofpassenger = new ArrayList<>();
		Passenger first = new Passenger("Seenu", "Dubai", "9635412786", "Male", "dubaiseenu007@gmail.com", "Seenu@123",
				"75000");
		detailsofpassenger.add(first);
		Passenger second = new Passenger("Priya", "Hema", "9639412786", "Female", "hema147@gmail.com", "Priya@346",
				"25000");
		detailsofpassenger.add(second);
		Passenger third = new Passenger("Diwakar", "GachiBowli", "7984536542", "Male", "diwakar369@gmail.com",
				"Diwakar@789", "85000");
		detailsofpassenger.add(third);
		Passenger fourth = new Passenger("Kakarla", "Harish", "9456312784", "Male", "harishbro909@gmail.com",
				"Harish@468", "67500");
		detailsofpassenger.add(fourth);
		Passenger fifth = new Passenger("Vadlamani", "Priya", "7365412849", "Female", "vadlamani159@gmail.com",
				"Vadla@364", "35000");
		detailsofpassenger.add(fifth);
		Passenger sixth = new Passenger("Simha", "Padmanabha", "6325698417", "Male", "manonfire420@gmail.com",
				"Manonfire@024", "65000");
		detailsofpassenger.add(sixth);
		Passenger seventh = new Passenger("Nagalingam", "Bommakanti", "7896541266", "Male", "lingam654@gmail.com",
				"Nagalingam@654", "65078");
		detailsofpassenger.add(seventh);
		Passenger eighth = new Passenger("Thrilok", "Naga", "9635412786", "Male", "paruchuru147@gmail.com",
				"Thrilok@345", "69541");
		detailsofpassenger.add(eighth);
		Passenger nineth = new Passenger("Shahul", "Shaik", "9854536217", "Male", "shaikshahul462@gmail.com",
				"Shahul@367", "65472");
		detailsofpassenger.add(nineth);
		Passenger tenth = new Passenger("Nagamani", "Lakshmi", "6304784564", "Female", "nagamani487@gmail.com",
				"Nagalakshmi@194", "75269");
		detailsofpassenger.add(tenth);
		int length = detailsofpassenger.size();
//		
		return detailsofpassenger;
	}

//	String paylog=ExploreTravels.paylogo;
//	File ab=new File(paylog);
//	FileInputStream logopay=new FileInputStream(ab);
//	int logs;
//	while((logs=logopay.read())!=-1)
//	{
//		char logos=(char) logs;
//		System.out.print(logos);
//	}

}
