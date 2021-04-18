import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class BambaCarParkManager implements CarParkManager {
	
	private PriorityQueue<Vehicle> listOfVehicle = new PriorityQueue<Vehicle>(500, new VehicleTypeComparator());
	private static BambaCarParkManager instance = null;

	// Maximum number of slots available in the car park
	private double availableSlotsInGroundFloor = 80;
	private double availableSlotsInFirstFloor = 60;
	private double getAvailableSlotsInSecondFloor = 70;

	private final int SPACE_FOR_CAR = 1;
	private final int SPACE_FOR_VAN = 2;
	private final double SPACE_FOR_MOTORBIKE = 0.3333333333;

	private double chargePerHour = 300;
	private double addCharge = 100;
	private double maxCharge = 3000;
	private int addFromthisHour = 3;
	
	// Method which returns an object of same type
	public static BambaCarParkManager getInstance() {
		if(instance == null) {
			synchronized(BambaCarParkManager.class){
				if(instance==null) {
					instance = new BambaCarParkManager();
				}
			}
		}
		return instance;
	}

	@Override
	public synchronized void addVehicle(Vehicle obj) {
		// Check whether the vehicle is already parked or not
		for (Vehicle item : listOfVehicle) {
			if (item.equals(obj)) {
				System.out.println("This vehicle is already parked.");
				return;
			}
		}

		if (obj instanceof Van ) {
			while (availableSlotsInGroundFloor < SPACE_FOR_VAN) {
				try {
					System.out.println("Van " + obj.getIdPlate() + " is waiting to be parked."+"\n");
					wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			listOfVehicle.offer(obj);
			availableSlotsInGroundFloor -= SPACE_FOR_VAN;
			System.out.println("Van " + obj.getIdPlate() + " is parked. "  + "Available slots : " + availableSlotsInGroundFloor);
			System.out.println("\n");
			notifyAll();
		}

		if (obj instanceof Car) {
			while(availableSlotsInGroundFloor < SPACE_FOR_CAR) {
				try {
					System.out.println("Car " + obj.getIdPlate() + " is waiting to be parked."+"\n");
					wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			listOfVehicle.offer(obj);
			availableSlotsInGroundFloor --;
			System.out.println("Car " + obj.getIdPlate() + " is parked. "  + "Available slots : " + availableSlotsInGroundFloor);
			notifyAll();
		}

		if (obj instanceof MotorBike) {
			while(availableSlotsInGroundFloor < SPACE_FOR_MOTORBIKE) {
				try {
					System.out.println("Bike " + obj.getIdPlate() + " is waiting to be parked."+"\n");
					wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			listOfVehicle.offer(obj);
			availableSlotsInGroundFloor = availableSlotsInGroundFloor - SPACE_FOR_MOTORBIKE;
			if (availableSlotsInGroundFloor < SPACE_FOR_MOTORBIKE) availableSlotsInGroundFloor = 0;
			System.out.println("Bike " + obj.getIdPlate() + " is parked. " + "Available slots : " + availableSlotsInGroundFloor);
			notifyAll();
		}
		System.out.println((listOfVehicle));
	}
  
	@Override
	public synchronized void deleteVehicle(String IdPlate) {
		while (listOfVehicle.size() == 0) {
			try {
				wait(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (Vehicle item: listOfVehicle) {
			// Checking for a particular vehicle with its' plate ID
			if (item.getIdPlate().equals(IdPlate)) {
				if (item instanceof Van) {
					availableSlotsInGroundFloor += SPACE_FOR_VAN;
					System.out.println("Space cleared after a Van left the park.\nAvailable Slots : " + availableSlotsInGroundFloor);
				} else if (item instanceof Car) {
					availableSlotsInGroundFloor++;
					System.out.println("Space cleared after a Car left the park.\nAvailable Slots : " + availableSlotsInGroundFloor);
				} else if (item instanceof MotorBike) {
					availableSlotsInGroundFloor = availableSlotsInGroundFloor + SPACE_FOR_MOTORBIKE;
					System.out.println("Space cleared after a Bike left the park.\nAvailable Slots : " + availableSlotsInGroundFloor);
				}
				// Vehicle has exited, notify all waiting entrance threads to admit more vehicles to the park
				notifyAll();
			}
		}
	}
			
	
	@Override
	public void printCurrentVehicles() {
		ArrayList vehicleList = new ArrayList(listOfVehicle);
		Collections.sort(vehicleList, Collections.reverseOrder());
		for (Vehicle item:listOfVehicle) {
			if (item instanceof Van) {
				System.out.println("Vehicle Type is a Van");
			} else if(item instanceof MotorBike) {
				System.out.println("Vehicle Type is a MotorBike");
			} else {
				System.out.println("Vehicle Type is a Car.");
			}
			System.out.println("******************");
			System.out.println("ID Plate : "+item.getIdPlate());
			System.out.println("Entry Time : "
			+item.getEntryDate().getHours()+":"+item.getEntryDate().getMinutes()
			+":"+item.getEntryDate().getSeconds()+"-"+item.getEntryDate().getDate()
			+"/"+item.getEntryDate().getMonth()+"/"+item.getEntryDate().getYear());
			System.out.println("\n");
		}	
	}

	@Override
	public void printLongestPark() {
		// Sort to the ascending order
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>(listOfVehicle);
		Collections.sort(vehicleList);
		System.out.println("The longest parked vehicle is : ");
		System.out.println("................................................");
		System.out.println("ID Plate : "+vehicleList.get(0).getIdPlate());
		if(vehicleList.get(0) instanceof Car) {
			System.out.println("Vehicle Type is a Car.");
		} else if(vehicleList.get(0) instanceof Van){
			System.out.println("Vehicle Type is a Van.");
		} else {
			System.out.println("Vehicle Type is a MotorBike.");
		}
		System.out.println("Parked Time : "+vehicleList.get(0).getEntryDate().getHours()
				+":"+vehicleList.get(0).getEntryDate().getMinutes()
				+":"+vehicleList.get(0).getEntryDate().getSeconds());
		System.out.println("Parked Date  : "+vehicleList.get(0).getEntryDate().getDate()
				+"/"+vehicleList.get(0).getEntryDate().getMonth()
				+"/"+vehicleList.get(0).getEntryDate().getYear());
	}

	@Override
	public void printLatestPark() {
		// Sort to the descending order
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>(listOfVehicle);
		Collections.sort(vehicleList, Collections.reverseOrder());
		System.out.println("The latest parked vehicle is : ");
		System.out.println("..............................................");
		System.out.println("ID Plate : "+vehicleList.get(0).getIdPlate());
		if (vehicleList.get(0) instanceof Car) {
			System.out.println("Vehicle Type is a Car.");
		} else if(vehicleList.get(0) instanceof Van) {
			System.out.println("Vehicle Type is a Van.");
		} else {
			System.out.println("Vehicle Type is a MotorBike.");
		}
		System.out.println("Parked Time : "+vehicleList.get(0).getEntryDate().getHours()
				+":"+vehicleList.get(0).getEntryDate().getMinutes()
				+":"+vehicleList.get(0).getEntryDate().getSeconds());
		System.out.println("Parked Date  : "+vehicleList.get(0).getEntryDate().getDate()
				+"/"+vehicleList.get(0).getEntryDate().getMonth()
				+"/"+vehicleList.get(0).getEntryDate().getYear());
	}

	
	@Override
	public void printVehicleByDay(DateTime givenDate) {
		for (Vehicle item:listOfVehicle) {
		if (givenDate.getYear()==item.getEntryDate().getYear() &&
				givenDate.getMonth()==item.getEntryDate().getMonth() && 
						givenDate.getDate() == item.getEntryDate().getDate()) {
			
				System.out.println("ID Plate : "+item.getIdPlate());
				
				System.out.println("Parked Date and Time : "+item.getEntryDate().getDate()+"/"+
				item.getEntryDate().getMonth()+"/"+item.getEntryDate().getHours()+"-"
				+item.getEntryDate().getHours()+":"+item.getEntryDate().getMinutes()
				+":"+item.getEntryDate().getYear());
				
				if(item instanceof Van) {
					System.out.println("Vehicle Type is a Van");
				} else if(item instanceof MotorBike) {
					System.out.println("Vehicle Type is a Motor Bike.");
				} else {
					System.out.println("Vehicle Type is a Car.");
				}	
				System.out.println("--------------------------");
				System.out.println("\n");
			}
		}
	}
		
	@Override
	public void printVehiclePercentage() {
		int numCars=0;
		int numBikes=0;
		int numVans=0;
		for (Vehicle item:listOfVehicle) {
			if (item instanceof Car) {
				numCars++;
			} else if(item instanceof MotorBike) {
				numBikes++;
			} else {
				numVans++;
			}
		}
		double carPercentage = (numCars/listOfVehicle.size())*100;
		double bikePercentage = (numBikes/listOfVehicle.size())*100;
		double vanPercentage = (numVans/listOfVehicle.size())*100;
		
		System.out.printf("Car Percentage is : %.f ",carPercentage);
		System.out.printf("\nBike Percentage is : %.f ",bikePercentage);
		System.out.printf("\nVan Percentage is : %.f ",vanPercentage);
		System.out.println("\n");
	}

	@Override
	public BigDecimal calculateChargers(String plateID, DateTime currentTime) {
		boolean found = false;
		BigDecimal charges = null;
		for (Vehicle item:listOfVehicle) {
			if (item.getIdPlate().equals(plateID)) {
				System.out.println("Vehicle found.");
				// Vehicle parked time
				System.out.println("Parked Time : "+item.getEntryDate().getDate()+"/"
						+item.getEntryDate().getMonth()+"/"+item.getEntryDate().getDate()
								+"-"+item.getEntryDate().getHours()+":"+item.getEntryDate().getMinutes()
								+":"+item.getEntryDate().getSeconds());
				// Making the charges
				found = true;
				DateTime entryDateTime = item.getEntryDate();
				int differenceInSeconds = currentTime.compareTo(entryDateTime);
				double differenceInHours = differenceInSeconds/(60.0*60.0);
				
				double dayCharge=0;
				double hourCharge = 0;
				double totalCost=0;
				double days = differenceInHours/24;
				
				if (days > 1) {
					dayCharge =maxCharge;	
				}
				if (differenceInHours >= 3) {
					double additional = (differenceInHours-addFromthisHour) ;
					hourCharge=(additional*addCharge)+(addFromthisHour *chargePerHour);
					System.out.printf("hour Charge : %.2f",hourCharge);
				} else if (differenceInHours < 1) {
					hourCharge = chargePerHour;
				} else {
					hourCharge=(differenceInHours * chargePerHour);
				}
				
				totalCost=dayCharge + hourCharge;
				BigDecimal vehicleCharge = new BigDecimal(totalCost);
				System.out.printf("Total charge for the vehicle is LKR %.2f", vehicleCharge);
				System.out.println("\n");
			}
		}
		if (!found) {
			System.out.println("Vehicle not found\n");
		}
		return charges;
	}
}
