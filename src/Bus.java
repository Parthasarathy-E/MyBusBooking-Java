/*
Application Title:Bus Ticket Reservation System
Author name:Parthasarathy E
create on:12/10/2022
last Modified Date and time:13/10/2022
reviewed by:Anushya
reviewed Date:12.10.2022

*/

public class Bus {
private int busNumber;
private boolean ac;
private int capacity;
  Bus(int number,boolean ac,int capacity){
	 this.busNumber=number;
	 this.ac=ac;
	 this.capacity=capacity;
	 
  }
  public int getbusNo() {                    //accessor method
	  return busNumber;
  }
  public void setbusNo(int number) {      //mutator  ---- for changing the value
	  busNumber=number;
  }
  public boolean isAc() {
	  return ac;
  }
  public void setac(boolean value) {
	  ac= value;
  }
  public int getCapacity() {
	  return capacity;
  }
  public void setCapacity(int capacity) {
	  this.capacity=capacity;
  }
  
  public void displayBusInfo() {
	  System.out.println("Bus No:" + busNumber +"  AC:" + ac +"  Total Capacity:"+capacity);
  }
}
