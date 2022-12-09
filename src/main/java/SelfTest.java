public class selfTest {

    private static boolean battery;
    private static boolean sensor;
    private static boolean display;

    public static boolean selfTest (boolean pass){

        //Checking battery levels
        float batteryLevel = 0;
        if (batteryLevel <= 0.0) {
            battery = false;
        }

        //Making sure the temperature collected falls within the appropriate range
        //*Verify temperature range
        float tempLevel = 0;
        if ((tempLevel > 85.0) && (tempLevel < 110.0)) {
            sensor = true;
        }

        //Checking display
        boolean displayPass;
        String ready = "READY";
        if (System.out.print(ready)) {
            displayPass = true;
        }
        if (displayPass = true) {
            display = true;
        }

        /** To pass the self test:
         * battery levels should be above 0
         * sensor should be collecting data (temperature)
         * display should be working (ready screen)
          */
        if ((display == true) && (sensor == true) && (battery == true)){
            pass = true;
        } else {
            pass = false;
        }
        return pass;
    }
    //After passing self test, return to display screen
}
