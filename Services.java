package Kingk.travel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Services {
String number;
	String fromCity;
	String destinationCity;
	String seats;

	String amount;
	String date;

	public Services( String number,String fromCity, String destinationCity, String seats, String amount) {
		super();
		this.number=number;
		this.fromCity = fromCity;
		this.destinationCity = destinationCity;
		this.seats = seats;
		this.amount = amount;
	}

	public Services() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() 
	{
		return "Services [number=" + number + ", fromCity=" + fromCity + ", destinationCity=" + destinationCity
				+ ", seats=" + seats + ", amount=" + amount ;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static void main(String[] args) {

	}
	public static final String name = "D:\\\\Downloadss\\\\welcome.txt";
	public static final String paylogo = "C:\\Recent\\bubbles.txt";
	public static final int count = 5;

//public static   Map<String, Services> dataOftravelling ( ) 
//{
////	Map<String,Services> places=new HashMap<>();
////	Services firstcityaway=new Services("Vijayawada","Hyderabad","25","1800");
////	Services firstcitytowards=new Services("Hyderabad","Vijayawada","25","1800");
////	Services secondcityaway=new Services("Vijayawada","Chennai","20","3000");
////	Services secondcitytowards=new Services("Chennai","Vijayawada","25","3500");
////	Services thirdcityaway=new Services("Vijayawada","Tirupati","25","1800");
////	Services thirdcitytowards=new Services("Tirupati","Vijayawada","25","1800");
////	Services fourthcityaway=new Services("Vijayawada","Ooty","25","1800");
////	Services fourthcitytowards=new Services("Ooty","Vijayawada","25","1800");
////	Services fifthcityaway=new Services("Vijayawada","Bangalore","25","1800");
////	Services fifthcitytowards=new Services("Bangalore","Vijayawada","25","1800");
////	Services sixthcityaway=new Services("Vijayawada","Vishakapatnam","25","1800");
////	Services sixthcitytowards=new Services("Vishakapatnam","Vijayawada","25","1800");
////	places.put(firstcityaway.getFromCity(), firstcityaway);
////	places.put(firstcitytowards.getFromCity(), firstcitytowards);
////	places.put(secondcityaway.getFromCity(), secondcityaway);
////	places.put(secondcitytowards.getFromCity(), secondcitytowards);
////	places.put(thirdcityaway.getFromCity(), thirdcityaway);
////	places.put(thirdcitytowards.getFromCity(), thirdcitytowards);
////	places.put(fourthcityaway.getFromCity(), fourthcityaway);
////	places.put(fourthcitytowards.getFromCity(), fourthcitytowards);
////	places.put(fifthcityaway.getFromCity(), fifthcityaway);
////	places.put(fifthcitytowards.getFromCity(), fifthcitytowards);
////	places.put(sixthcityaway.getFromCity(), sixthcityaway);
////	places.put(sixthcitytowards.getFromCity(), sixthcitytowards);
////	
//	
//	
////	return places ;
//	
//}


}
