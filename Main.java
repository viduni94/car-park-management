public class Main {
  public static void main(String[] args) {
    BambaCarParkManager vehiclePark = new BambaCarParkManager();
    int noOfSlotsInGroundFloor = 80;

    // Ground Floor
    // Create 5 entrances
    Runnable entranceGate1 = new EntranceGate(vehiclePark, noOfSlotsInGroundFloor);
    Runnable entranceGate2 = new EntranceGate(vehiclePark, noOfSlotsInGroundFloor);
    Runnable entranceGate3 = new EntranceGate(vehiclePark, noOfSlotsInGroundFloor);
    Runnable entranceGate4 = new EntranceGate(vehiclePark, noOfSlotsInGroundFloor);
    Runnable entranceGate5 = new EntranceGate(vehiclePark, noOfSlotsInGroundFloor);

    // Create 3 exits
    Runnable exitGate1 = new ExitGate(vehiclePark, noOfSlotsInGroundFloor);
    Runnable exitGate2 = new ExitGate(vehiclePark, noOfSlotsInGroundFloor);
    Runnable exitGate3 = new ExitGate(vehiclePark, noOfSlotsInGroundFloor);

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

    // Make exit gate threads deamons so that it runs in the background and dies when there is nothing to exit
    southernExitGateThread.setDaemon(true);
    westernExitGateThread.setDaemon(true);
    easternExitGateThread.setDaemon(true);

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
