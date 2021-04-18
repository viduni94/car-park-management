import java.util.Comparator;

// Implement priority lane to give more priority to Car
public class VehicleTypeComparator implements Comparator<Vehicle> {
  @Override
  public int compare(Vehicle o1, Vehicle o2) {
    return (o1.getPriority() < o2.getPriority()) ? 1 : -1;
  }
}
