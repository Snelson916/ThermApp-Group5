import java.io.FileNotFoundException;

public class Main {
    /** Main logic that connects the various pieces of the device into a functioning Thermometer device / application */
    String state;
    int batteryLevel;

    public Main (int batteryLevel) {
        state = "OFF";
        this.batteryLevel = batteryLevel;
    }

    public void powerButton(int timeHeld) throws FileNotFoundException, InterruptedException {
        /** If device is powered OFF and has sufficient battery level: Power on device */
        if (this.state.equals("OFF") && this.batteryLevel > 0) {
            this.state = "ON";
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
        }
    }

    public void recordTemperature() throws FileNotFoundException, InterruptedException {
        if (this.state.equals("ON")) {

            /** Automatically run Self Test upon being powered on to ensure the device hardware is functioning properly
             * Send message to display to alert user that self test is being conducted.
             * */
            Display.show("Performing Self Test...");

            if (SelfTest.selfTest(this.batteryLevel)) {
                /** Device will emit 3 audio beeps to indicate a successful self test */
                for (int i = 0; i < 2; i++) Speaker.activate();

                /** Message sent to display to indicate device is recording temperature. */
                Display.show("---");

                /** If Device passed Self Test: Automatically begin recording temperature. */
                for (int i = 0; i < 3; i++) Speaker.activate();
                /** Upon successful recording of temperature, message with temp is sent to display */
                Display.show(Sensor.getAverageTemperature() + " F°");
            }
            else {
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

        /** User activates device power button */
        Thermometer.powerButton(1);
        /** User activates device power button for 5 seconds activating a reset */
        Thermometer.powerButton(5);
    }
}
