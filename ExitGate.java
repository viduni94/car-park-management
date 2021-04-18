import java.awt.*;
import java.util.Random;

public class ExitGate implements Runnable {

  private BambaCarParkManager carPark;
  private final int NO_OF_SLOTS;

  public ExitGate(BambaCarParkManager carPark, int noOfSlots) {
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

  @Override
  public void run() {
    try {
      for (int i = 0; i < NO_OF_SLOTS; i++) {
        carPark.deleteVehicle(generateNumberPlate());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
