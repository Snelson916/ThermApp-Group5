import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Sensor {
    public static double randomDoubleGen(double min, double max){
        return (Math.random() * (max - min)) + min;
    }

    public static List doubletListInRange() throws FileNotFoundException {
        List<Double> l = new ArrayList<>();
        for (int i = 0; i<10; i++){ //can modify i to desired number of readings
            double randomNum = randomDoubleGen(97.0, 100.4);//for human temp normal range
            l.add(randomNum);
        }
        //PrintStream file = new PrintStream(new File("temperatures"));
        //l.forEach(System.out::println);
        return l;
    }

    public static double getAverageTemperature() throws FileNotFoundException {
        //Scanner input = new Scanner("temperature");
        List input = doubletListInRange();
        Iterator<Double> iterator = doubletListInRange().iterator();
        double sum = 0.0;
        int count = 0;
        while (iterator.hasNext()) {
            double next = iterator.next();
            count++;
            sum += next;
        }
        double average = sum / count;
        for(int i = 0; i <= 4; i++){
            System.out.println("beep");
        }
        System.out.println("Temperature =" + average);
        return average;
    }
    public static void sendTemperature() throws FileNotFoundException {
        getAverageTemperature();
    }

    // Call in Main to get temperature reading?
}
