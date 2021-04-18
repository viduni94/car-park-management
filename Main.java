public class Main {
  public static void main(String[] args) {
    BambaCarParkManager vehiclePark = new BambaCarParkManager();

    Runnable entranceGate = new EntranceGate(vehiclePark);
    Runnable exitGate = new ExitGate(vehiclePark);

    // 5 entry gate threads
    Thread northernEntryGateOneThread = new Thread(entranceGate, "NorthernEntryGateOne");
    Thread northernEntryGateTwoThread = new Thread(entranceGate, "NorthernEntryGateTwo");
    Thread southernEntryGateThread = new Thread(entranceGate, "SouthernEntryGate");
    Thread easternEntryGateThread = new Thread(entranceGate, "EasternEntryGate");
    Thread westernEntryGateThread = new Thread(entranceGate, "WesternEntryGate");

    // Set max priority to nothern gates
    northernEntryGateOneThread.setPriority(Thread.MAX_PRIORITY);
    northernEntryGateTwoThread.setPriority(Thread.MAX_PRIORITY);

    // 3 exit gate threads
    Thread southernExitGateThread = new Thread(exitGate, "SouthernExitGate");
    Thread westernExitGateThread = new Thread(exitGate, "WesternExitGate");
    Thread easternExitGateThread = new Thread(exitGate, "EasternExitGate");

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
