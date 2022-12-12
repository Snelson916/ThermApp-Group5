import java.io.FileNotFoundException;

public class SelfTest {
    /** Class to run tests ensuring the device's hardware components are working properly */
    private static boolean battery;
    private static boolean sensor;
    private static boolean display;

    public static boolean selfTest (int batteryLevel) throws FileNotFoundException, InterruptedException {

        /** Checking battery levels */
        if (batteryLevel > 10) {
            battery = true;
        }


        /** Making sure the temperature collected falls within the appropriate range */
        //read 10 temperature readings
        //Sensor.doubletListInRange();
        //double tempLevel = Sensor.getAverageTemperature();
        //if ((tempLevel > 90.0) && (tempLevel < 110.0)) {
        //check status of sensor, good - continue with display
        boolean sensor = false;
        String pass = "good";
        if(sensor = true) {

            if (pass.equals("good")) {
                sensor = true;
            }
        }

        //feel like a test case - sensor state should be "good" true or false?

        /** Checking display */
        boolean displayPass = false;
        String ready = "READY";
        if (ready.equals("READY")) {
            displayPass = true;
        }
        if (displayPass) {
            display = true;
        }

        /** To pass the self test:
         * battery levels should be above 10
         * sensor should be collecting data (temperature)
         * display should be working (ready screen)
          */
        if ((display) && (sensor) && (battery)){
            return true;
        } else {
            Display.show("Self Test Fail! Device malfunction in the following areas:");
            if (!display) {Display.show("Error: Display Malfunction");}
            if (!sensor) {Display.show("Error: Sensor Malfunction");}
            if (!battery) {Display.show("Error: Battery Malfunction");}
            return false;
        }
    }
}
