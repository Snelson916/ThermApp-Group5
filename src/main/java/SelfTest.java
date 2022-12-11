import java.io.FileNotFoundException;

public class SelfTest {

    private static boolean battery;
    private static boolean sensor;
    private static boolean display;

    public static boolean selfTest (int batteryLevel) throws FileNotFoundException, InterruptedException {

        //Checking battery levels
        if (batteryLevel > 0) {
            battery = true;
        }

        //Making sure the temperature collected falls within the appropriate range
        //*Verify temperature range
        double tempLevel = Sensor.getAverageTemperature();
        if ((tempLevel > 90.0) && (tempLevel < 110.0)) {
            sensor = true;
        }

        //Checking display
        boolean displayPass = false;
        String ready = "READY";
        if (ready.equals("READY")) {
            displayPass = true;
        }
        if (displayPass) {
            display = true;
        }

        /** To pass the self test:
         * battery levels should be above 0
         * sensor should be collecting data (temperature)
         * display should be working (ready screen)
          */
        if ((display) && (sensor) && (battery)){
            return true;
        } else {
            return false;
        }
    }
    //After passing self test, return to display screen
}
