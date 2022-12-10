public class Display {
    // Empty Display Class
    public static void display (boolean currentState){
        // Reject if display is off
        if(currentState == false){
            System.out.println("Please turn system on");
        // Continue operations if display is on
        }else{
            // message
            String message = "Welcome. Ready to take reading";
            System.out.println(message);
            // batteryLevel
            double batteryLevel;
            // print batteryLevel
            System.out.println("Current battery level: " + batteryLevel);

        }
    } 
}
