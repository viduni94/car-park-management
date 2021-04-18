import java.awt.*;
import java.time.LocalDate;
import java.util.Random;

public class EntranceGateGroundFloor implements Runnable {

  private BambaCarParkManager carPark;
  private final int NO_OF_SLOTS;

  public EntranceGateGroundFloor(BambaCarParkManager carPark, int noOfSlots) {
    super();
    this.carPark = carPark;
    NO_OF_SLOTS = noOfSlots;
  }

  private String generateNumberPlate() {
    int low = 1;
    int high = 250;

    Random r = new Random();
    int randomNumber = r.nextInt(high - low) + low;
    return "ABC-"+randomNumber;
  }

  private static int createRandomIntBetween(int start, int end) {
    return start + (int) Math.round(Math.random() * (end - start));
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i < NO_OF_SLOTS; i++) {
        Vehicle vehicle;
        DateTime date = new DateTime(2021, createRandomIntBetween(1, 12), createRandomIntBetween(1, 28),
                createRandomIntBetween(00, 23), createRandomIntBetween(00, 60), createRandomIntBetween(00, 60));

        if (Math.random() < 0.33) {
          vehicle = new Car(generateNumberPlate(), "Toyota", date, 4, Color.BLACK, 1);
        } else if (Math.random() < 0.67) {
          vehicle = new Van(generateNumberPlate(), "Nissan", date, 20, 2);
        } else {
          vehicle = new MotorBike(generateNumberPlate(), "Honda", date, "660cc", 3);
        }
        carPark.addVehicle(vehicle);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
