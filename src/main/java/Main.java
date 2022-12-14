import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class Main {
    /**
     * Main logic that connects the various pieces of the device into a functioning Thermometer device / application
     */
    String state;
    int batteryLevel;

    public Main(int batteryLevel) {
        state = "OFF";
        this.batteryLevel = batteryLevel;
    }

    public void powerButton(int timeHeld) throws FileNotFoundException, InterruptedException {
        /** If device is powered OFF and has sufficient battery level: Power on device */
        if (this.state.equals("OFF") && this.batteryLevel > 0) {
            this.state = "ON";
            System.out.println(this.state);
            this.recordTemperature();
        }
        /** If device is powered on but user  holds power button for at least 5 seconds: Reset device */
        else if (this.state.equals("ON") && timeHeld >= 5) {
            Speaker.activate();
            this.recordTemperature();
        }
        /** If user clicks  power button when device is already powered on: Power off device. */
        else {
            this.state = "OFF";
            System.out.println(this.state);
        }
    }

    public void deviceIdle(int timeSeconds) {
        if (timeSeconds >= 600 ) {
            this.state = "OFF";
        }
    }

    public void recordTemperature() throws FileNotFoundException, InterruptedException {
        if (this.state.equals("ON")) {

            /** Automatically run Self Test upon being powered on to ensure the device hardware is functioning properly
             * Send message to display to alert user that self test is being conducted.
             * */
            Display.show("Performing Self Test...");

            if (SelfTest.selfTest(this.batteryLevel)) {
                /** Device will emit 2 audio beeps to indicate a successful self test */
                for (int i = 0; i < 2; i++) Speaker.activate();

                /** Message sent to display to indicate device is recording temperature. */
                Display.show("---");

                /** If Device passed Self Test: Automatically begin recording temperature. */
                for (int i = 0; i < 3; i++) Speaker.activate();
                /** Upon successful recording of temperature, message with temp is sent to display */
                Sensor.doubletListInRange();
                if (Sensor.getAverageTemperature() > 90.0 && Sensor.getAverageTemperature() < 110.0) {//normal range
                    DecimalFormat format = new DecimalFormat("###.#");
                    Display.show(format.format(Sensor.getAverageTemperature()) + " ??F");
                } //else {
                    //System.out.println("Reset?");//for temperature not in range reset
                //}
            } else {
                /** Device will emit 4 audio beeps to indicate an unsuccessful self test */
                for (int i = 0; i < 4; i++) Speaker.activate();
                this.state = "OFF";
            }
        }
    }



    public static void main (String[] args) throws FileNotFoundException, InterruptedException {
        /** Instantiate mock battery with power level at 75% */
        var Battery = new Battery(75);
        /** Instantiate mock Thermometer */
        var Thermometer = new Main(Battery.level);
        System.out.println(Thermometer.state);


        /** User activates device power button: Power On */
        Thermometer.powerButton(1);

        Thermometer.deviceIdle(60);

        /** User activates device power button for 5 seconds activating a reset */
        Thermometer.powerButton(5);

        /** Device is inactive for 10 minutes and therefore powers off */
        Thermometer.deviceIdle(600);
        System.out.println(Thermometer.state);

        /** User activates device power button: Power On */
        Thermometer.powerButton(1);
        /** User activates device power button: Power off  */
        Thermometer.powerButton(1);
    }
}
