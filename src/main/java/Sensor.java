import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Sensor {
    /** Class for pseudo temperature sensor on device */
    static ArrayList<Double> temperatures = new ArrayList<>();
    private static final int NUMBER = 10;//number of temperature readings
    public static double randomDoubleGen(double min, double max){
        return (Math.random() * (max - min)) + min;
    }

    public static List doubletListInRange() throws FileNotFoundException {
        temperatures = new ArrayList<>();
        for (int i = 0; i<NUMBER; i++){ //can modify i to desired number of readings
            double randomNum = randomDoubleGen(97.0, 100.4);//for human temp normal range
            temperatures.add(randomNum);
        }
        //PrintStream file = new PrintStream(new File("temperatures"));
        //l.forEach(System.out::println);
        return temperatures;
    }

    public static double getAverageTemperature() throws FileNotFoundException, InterruptedException {
//        Scanner input = new Scanner("temperature");
//        List input = doubletListInRange();
//        TimeUnit.SECONDS.sleep(10);

        double sum = 0.0;
        int count = 0;
        for (int i = 0; i < temperatures.size(); i++) {
            double next = temperatures.get(i);
            count++;
            sum += next;
        }
        if (count == 0) {
            return 0.0;
        }
        double average = Math.round((sum / (double)count));
//        System.out.println(average);
        return average;
    }
}
