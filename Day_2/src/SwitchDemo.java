public class SwitchDemo {
    public static void main(String[] args) {
        int day=3;
        //Old style - easy to forget break! Fall-through bug
        switch (day){
            case 1 :
                System.out.println("Monday");
                break;
            case 2 :
                System.out.println("Tuesday");
                break;
            case 3 :
                System.out.println("Wednesday");
                break;
            case 4 :
                System.out.println("Thursday");
                break;
            case 5 :
                System.out.println("Friday");
                break;
            case 6,7:
                System.out.println("Weekend : Saturday and Sunday");
                break;
            default:
                System.out.println("Invalid day");
        }

        //Modern switch expression(java 14+) - no break needed, no fall-through
        String dayName=switch(day){
            case 1 ->"Monday";
            case 2 ->"Tuesday";
            case 3 ->"Wednesday";
            case 4 ->"Thursday";
            case 5 ->"Friday";
            case 6,7 ->"Weekend : Saturday and Sunday"; //Multiple cases
            default -> "Invalid";
        };
        System.out.println(dayName);

        // Switch with blocks for multi-line logic
        String message = switch (day) {
            case 1, 2, 3, 4, 5 -> {
                System.out.println("Calculating...");
                yield "Weekday";      // 'yield' returns value from block
            }
            case 6, 7 -> "Weekend";
            default   -> "Unknown";
        };
        System.out.println(message);

        // Switch on Strings (very common in real apps)
        String command = "stop";
        switch (command) {
            case "start" -> System.out.println("Starting engine...");
            case "stop"  -> System.out.println("Stopping engine...");
            case "pause" -> System.out.println("Pausing...");
            default      -> System.out.println("Unknown command: " + command);
        }


    }
}