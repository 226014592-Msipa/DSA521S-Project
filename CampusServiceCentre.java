import java.util.Scanner;

public class CampusServiceCentre {

    static Scanner input = new Scanner(System.in);
    static Queue queue = new Queue(100);
    static StudentLinkedList records = new StudentLinkedList();
    static int[] serviceTimes = new int[100];
    static int serviceCount = 0;

    // MAIN PROGRAM
    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n");
            System.out.println("       CAMPUS SERVICE CENTRE");
            System.out.println("1. Add student to waiting queue");
            System.out.println("2. Serve next student");
            System.out.println("3. Display waiting students");
            System.out.println("4. Add student service record");
            System.out.println("5. Display student service records");
            System.out.println("6. Search for student record");
            System.out.println("7. Remove student record");
            System.out.println("8. Display daily statistics");
            System.out.println("9. Sort service times");
            System.out.println("10. Run sorting experiment");
            System.out.println("11. Exit");

            System.out.print("Select option: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: addToQueue(); break;
                case 2: serveStudent(); break;
                case 3: queue.displayQueue(); break;
                case 4: addRecord(); break;
                case 5: records.displayStudents(); break;
                case 6: searchRecord(); break;
                case 7: deleteRecord(); break;
                case 8: statistics(); break;
                case 9: sortTimes(); break;
                case 10: sortingExperiment(); break;
                case 11: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 11);
    }

    static Student createStudent() {
        System.out.print("Student number: ");
        String number = input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Service type: ");
        String service = input.nextLine();
        System.out.print("Estimated service time: ");
        int time = input.nextInt();
        input.nextLine();
        return new Student(number, name, service, time);
    }

    static void addToQueue() {
        Student student = createStudent();
        queue.enqueue(student);
        System.out.println("Student added to queue.");
    }

    static void serveStudent() {
        Student student = queue.dequeue();
        if (student == null) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.println("Serving:");
        student.display();
        serviceTimes[serviceCount] = student.estimatedTime;
        serviceCount++;
    }

    static void addRecord() {
        Student student = createStudent();
        System.out.print("Enter position: ");
        int position = input.nextInt();
        input.nextLine();
        records.insertAtPosition(student, position);
        System.out.println("Record added.");
    }

    static void searchRecord() {
        System.out.print("Student number: ");
        String number = input.nextLine();
        records.searchStudent(number);
    }

    static void deleteRecord() {
        System.out.print("Student number: ");
        String number = input.nextLine();
        records.deleteStudent(number);
    }

    static void statistics() {
        if (serviceCount == 0) {
            System.out.println("No students served.");
            return;
        }
        int total = 0;
        int highest = serviceTimes[0];
        int lowest = serviceTimes[0];
        int longerThan10 = 0;

        for (int i = 0; i < serviceCount; i++) {
            total += serviceTimes[i];
            if (serviceTimes[i] > highest) highest = serviceTimes[i];
            if (serviceTimes[i] < lowest) lowest = serviceTimes[i];
            if (serviceTimes[i] > 10) longerThan10++;
        }
        double average = (double) total / serviceCount;

        System.out.println("\nTotal students served: " + serviceCount);
        System.out.println("Total service time: " + total + " minutes");
        System.out.println("Average service time: " + average + " minutes");
        System.out.println("Highest service time: " + highest + " minutes");
        System.out.println("Lowest service time: " + lowest + " minutes");
        System.out.println("Services longer than 10 minutes: " + longerThan10);
    }

    static void sortTimes() {
        if (serviceCount == 0) {
            System.out.println("No service times.");
            return;
        }
        int[] a = new int[serviceCount];
        for (int i = 0; i < serviceCount; i++) a[i] = serviceTimes[i];

        System.out.println("1. Selection Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Merge Sort");
        System.out.println("4. Quick Sort");
        System.out.print("Choose: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1: SortingAlgorithms.selectionSort(a); break;
            case 2: SortingAlgorithms.insertionSort(a); break;
            case 3: SortingAlgorithms.mergeSort(a); break;
            case 4: SortingAlgorithms.quickSort(a, 0, a.length - 1); break;
            default: System.out.println("Invalid choice."); return;
        }

        System.out.println("Sorted service times:");
        for (int value : a) System.out.print(value + " ");
        System.out.println();
    }

    static void sortingExperiment() {
        int[] sizes = {20, 50, 100, 500};
        for (int size : sizes) {
            int[] original = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = (int)(Math.random() * 1000);
            }
            testSort(original, 1, "Selection Sort");
            testSort(original, 2, "Insertion Sort");
            testSort(original, 3, "Merge Sort");
            testSort(original, 4, "Quick Sort");
        }
    }

    static void testSort(int[] original, int algorithm, String name) {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) copy[i] = original[i];

        long start = System.nanoTime();
        if (algorithm == 1) SortingAlgorithms.selectionSort(copy);
        else if (algorithm == 2) SortingAlgorithms.insertionSort(copy);
        else if (algorithm == 3) SortingAlgorithms.mergeSort(copy);
        else SortingAlgorithms.quickSort(copy, 0, copy.length - 1);
        long end = System.nanoTime();

        System.out.println(name + " | Size: " + original.length
                + " | Time: " + (end - start) + " ns");
    }
}
