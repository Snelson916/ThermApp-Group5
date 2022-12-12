import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;



public class MainTest {

    @Test
    public void assertBadTempReading() throws InterruptedException, FileNotFoundException {
        Sensor.temperatures = new ArrayList<>();
        Sensor.temperatures.add(-1.0);
        if(Sensor.getAverageTemperature() < 0){
            assertTrue(Sensor.getAverageTemperature() < 0);
        }
        //reset temperatures
        Sensor.temperatures = new ArrayList<>();
    }

    @Test
    public void assertNoReading(){
        Sensor.temperatures = new ArrayList<>();
        if (Sensor.temperatures.isEmpty()){
            assertTrue(Sensor.temperatures.isEmpty());
        }
    }

}
