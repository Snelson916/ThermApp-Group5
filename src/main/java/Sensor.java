import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Sensor {
    /** Class for sudo temperature sensor on device */
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

    public static double getAverageTemperature() throws FileNotFoundException, InterruptedException {
        //Scanner input = new Scanner("temperature");
        List input = doubletListInRange();
//        TimeUnit.SECONDS.sleep(10);

        Iterator<Double> iterator = doubletListInRange().iterator();
        double sum = 0.0;
        int count = 0;
        while (iterator.hasNext()) {
            double next = iterator.next();
            count++;
            sum += next;
        }
        double average = Math.round((sum / count)* 100.0) / 100.0;
        return average;
    }
}
