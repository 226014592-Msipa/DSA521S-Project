import java.util.Scanner;

public class Service {
    public static void main(String[] args) {

        int[] times = new int[100];
        int count = 0;
        int i, sum = 0, highest, lowest, average, longCount = 0;
        int numberOfStudents, time;

        Scanner input = new Scanner(System.in);

        
        System.out.print("How many students were served? ");
        numberOfStudents = input.nextInt();
        input.nextLine();

       
        for (i = 0; i < numberOfStudents; i++) {
            System.out.print("Time " + (i + 1) + ": ");
            time = input.nextInt();
            input.nextLine();
            times[i] = time;
            count++;
        }

     
        for (i = 0; i < count; i++) {
            sum = sum + times[i];
        }

        average = sum / count;

      
        highest = times[0];
        for (i = 1; i < count; i++) {
            if (times[i] > highest) {
                highest = times[i];
            }
        }

       
        lowest = times[0];
        for (i = 1; i < count; i++) {
            if (times[i] < lowest) {
                lowest = times[i];
            }
        }

  
        for (i = 0; i < count; i++) {
            if (times[i] > 10) {
                longCount++;
            }
        }

        

        System.out.println("Total students:  " + count);
        System.out.println("Total time:      " + sum + " min");
        System.out.println("Average: " + average + " min");
        System.out.println("Highest:" + highest + " min");
        System.out.println("Lowest:" + lowest + " min");
        System.out.println("Longer than 10:" + longCount);

        input.close();
    }
}