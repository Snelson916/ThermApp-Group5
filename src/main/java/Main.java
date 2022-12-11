import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Main {
    String state;
    int batteryLevel;

    public Main (int batteryLevel) {
        state = "OFF";
        this.batteryLevel = batteryLevel;
    }

    public void powerButton(int timeHeld) throws FileNotFoundException, InterruptedException {
        if (this.state == "OFF" && this.batteryLevel > 0) {
            this.state = "ON";
            this.recordTemperature();
        } else if (this.state == "ON" && timeHeld >= 5) {
            Speaker.activate();
            this.recordTemperature();
        } else {
            this.state = "OFF";
        }
    }

    public void recordTemperature() throws FileNotFoundException, InterruptedException {
        if (this.state == "ON") {

            /** Automatically run Self Test upon being powered on to ensure the device hardware is functioning properly
             * Send message to display to alert user that self test is being conducted.
             * */
            Display.show("Performing Self Test...");

            if (SelfTest.selfTest(this.batteryLevel)) {
                /** Device will emit 3 audio beeps to indicate a successful self test */
                for (int i = 0; i < 2; i++) Speaker.activate();

                Display.show("---");

                /** If Device passed Self Test: Automatically begin recording temperature. */
                for (int i = 0; i < 3; i++) Speaker.activate();
                Display.show(Sensor.getAverageTemperature() + " F°");
            } else {
                /** Device will emit 4 audio beeps to indicate an unsuccessful self test */
                for (int i = 0; i < 5; i++) Speaker.activate();
                this.state = "OFF";
            }
        }
    }

    public static void main (String[] args) throws FileNotFoundException, InterruptedException {
        var Battery = new Battery(75);
        /**
         * Instantiate mock Thermometer where:
         * batteryLevel = 75%
         */
        var Thermometer = new Main(Battery.level);

        /** User activates device power button */
        Thermometer.powerButton(1);
        /** User activates device power button for 5 seconds activating a reset */
        Thermometer.powerButton(5);
    }
}
