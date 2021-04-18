public class Main {
  public static void main(String[] args) {
    BambaCarParkManager vehiclePark = new BambaCarParkManager();
    int noOfSlotsInGroundFloor = 80;
    int noOfSlotsInFirstFloor = 60;
    int noOfSlotsInSecondFloor = 70;

    // Ground Floor
    // Create 5 entrances
    Runnable entranceGate1 = new EntranceGateGroundFloor(vehiclePark, noOfSlotsInGroundFloor);
    Runnable entranceGate2 = new EntranceGateGroundFloor(vehiclePark, noOfSlotsInGroundFloor);
    Runnable entranceGate3 = new EntranceGateGroundFloor(vehiclePark, noOfSlotsInGroundFloor);
    Runnable entranceGate4 = new EntranceGateGroundFloor(vehiclePark, noOfSlotsInGroundFloor);
    Runnable entranceGate5 = new EntranceGateGroundFloor(vehiclePark, noOfSlotsInGroundFloor);

    // Create 3 exits
    Runnable exitGate1 = new ExitGateGroundFloor(vehiclePark, noOfSlotsInGroundFloor);
    Runnable exitGate2 = new ExitGateGroundFloor(vehiclePark, noOfSlotsInGroundFloor);
    Runnable exitGate3 = new ExitGateGroundFloor(vehiclePark, noOfSlotsInGroundFloor);

    // 5 entry gate threads
    Thread northernEntryGateOneThread = new Thread(entranceGate1, "NorthernEntryGateOne");
    northernEntryGateOneThread.setPriority(Thread.MAX_PRIORITY); // Set max priority to northern gates

    Thread northernEntryGateTwoThread = new Thread(entranceGate2, "NorthernEntryGateTwo");
    northernEntryGateTwoThread.setPriority(Thread.MAX_PRIORITY); // Set max priority to northern gates

    Thread southernEntryGateThread = new Thread(entranceGate3, "SouthernEntryGate");
    Thread easternEntryGateThread = new Thread(entranceGate4, "EasternEntryGate");
    Thread westernEntryGateThread = new Thread(entranceGate5, "WesternEntryGate");

    // 3 exit gate threads
    Thread southernExitGateThread = new Thread(exitGate1, "SouthernExitGate");
    Thread westernExitGateThread = new Thread(exitGate2, "WesternExitGate");
    Thread easternExitGateThread = new Thread(exitGate3, "EasternExitGate");

    // Start the threads
    northernEntryGateOneThread.start();
    northernEntryGateTwoThread.start();
    southernEntryGateThread.start();
    easternEntryGateThread.start();
    westernEntryGateThread.start();
    southernExitGateThread.start();
    westernExitGateThread.start();
    easternExitGateThread.start();
  }
}
