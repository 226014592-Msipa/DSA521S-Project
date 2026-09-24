import java.util.Scanner;


public class CampusServiceCentre {

   static Scanner input = new Scanner(System.in);
   static Queue queue = new Queue();
   static StudentLinkedList records = new StudentLinkedList();
   static int[] serviceTimes = new int[100];
   static int serviceCount = 0;
  
   // MAIN PROGRAM

   public static void main(String[] args) {
       int choice;

       do {
           System.out.println("\n");
           System.out.println("       CAMPUS SERVICE CENTRE");
           

           System.out.println("1. Add student to waiting queue\n");
           System.out.println("2. Serve next student\n");
           System.out.println("3. Display waiting students\n");
           System.out.println("4. Add student service record\n");
           System.out.println("5. Display student service records\n");
           System.out.println("6. Search for student record\n");
           System.out.println("7. Remove student record\n");
           System.out.println("8. Display daily statistics\n");
           System.out.println("9. Sort service times\n");
           System.out.println("10. Run sorting experiment\n");
           System.out.println("11. Exit");

           System.out.print("Select option: ");
           choice = input.nextInt();

           switch (choice) {

               case 1:
                   addToQueue();
                   break;

               case 2:
                   serveStudent();
                   break;

               case 3:
                   queue.displayQueue();
                   break;

               case 4:
                   addRecord();
                   break;

               case 5:
                   records.displayStudents();
                   break;

               case 6:
                   searchRecord();
                   break;

               case 7:
                   deleteRecord();
                   break;

               case 8:
                   statistics();
                   break;

               case 9:
                   sortTimes();
                   break;

               case 10:
                   sortingExperiment();
                   break;

               case 11:
                   System.out.println("Exiting...");
                   break;

               default:
                   System.out.println("Invalid option.");
           }
        }     while (choice != 11);
   }
   // CREATE STUDENT
   static Student createStudent() {
       System.out.print("Student number: ");
       int number = input.nextInt();
       input.nextLine();
       System.out.print("Name: ");
       String name = input.nextLine();
       System.out.print("Service type: ");
       String service = input.nextLine();
       System.out.print("Estimated service time: ");
       int time = input.nextInt();

       return new Student(number,name,service,time);
   }

   // QUEUE

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
       serviceTimes[serviceCount] =student.serviceTime;
       serviceCount++;
   }


   // LINKED LIST

   static void addRecord() {

       Student student = createStudent();
       System.out.print("Enter position: ");
       int position = input.nextInt();
       records.insertStudent(student,position);

       System.out.println("Record added.");
   }

   static void searchRecord() {
       System.out.print("Student number: ");

       int number = input.nextInt();
       Student student = records.searchStudent(number);

       if (student == null) {
           System.out.println("Student not found.");
       }
 else {
      student.display();
       }
   }

   static void deleteRecord() {

       System.out.print("Student number: ");
       int number = input.nextInt();
       records.deleteStudent(number);

       System.out.println("Record removed if it existed.");
   }

   // STATISTICS

   static void statistics() {
           if (serviceCount == 0) {

          System.out.println("No students served.");

           return;
       }

       int total = 0;
       int highest = serviceTimes[0];
       int lowest = serviceTimes[0];
       int longerThan10 = 0;

       for (int i = 0;i < serviceCount;i++) {
           total += serviceTimes[i];

           if (serviceTimes[i] > highest) {
               highest = serviceTimes[i];
           }

           if (serviceTimes[i] < lowest) {
               lowest = serviceTimes[i];
           }

           if (serviceTimes[i] > 10) {
               longerThan10++;
           }
       }

       double average =
               (double) total / serviceCount;


       System.out.println("\nTotal students served: " + serviceCount);
       System.out.println("Total service time: " + total + " minutes");
       System.out.println("Average service time: " + average + " minutes");
       System.out.println("Highest service time: " + highest + " minutes");
       System.out.println("Lowest service time: " + lowest + " minutes");
       System.out.println("Services longer than 10 minutes: " + longerThan10);
   }

   // SORT SERVICE TIMES

   static void sortTimes() {

       if (serviceCount == 0) {
           System.out.println("No service times.");

           return;
       }
       int[] a = new int[serviceCount];

       for (int i = 0;i < serviceCount;i++) {
           a[i] = serviceTimes[i];
       }


       System.out.println("1. Selection Sort");
       System.out.println("2. Insertion Sort");
       System.out.println("3. Merge Sort");
       System.out.println("4. Quick Sort");
       System.out.print("Choose: ");
       int choice = input.nextInt();
       switch (choice) {

           case 1:
               SortingAlgorithms.selectionSort(a);
               break;

           case 2:
               SortingAlgorithms.insertionSort(a);
               break;

           case 3:
               SortingAlgorithms.mergeSort(a);
               break;

           case 4:
               SortingAlgorithms.quickSort(a,0,a.length - 1);
               break;

           default:
               System.out.println("Invalid choice.");
               return;
       }

       System.out.println("Sorted service times:");
       for (int value : a) {

           System.out.print(value + " ");
       }

       System.out.println();
   }

   // SORTING EXPERIMENT

   static void sortingExperiment() {
       int[] sizes = {20, 50, 100, 500};

       for (int size : sizes) {
           int[] original =new int[size];

           for (int i = 0;i < size;i++) {
               original[i] =(int)(Math.random() * 1000);
           }

           testSort(original,1,"Selection Sort");
           testSort(original,2,"Insertion Sort");
           testSort(original,3,"Merge Sort");
           testSort(original,4,"Quick Sort");
       }
   }

   static void testSort(int[] original,int algorithm,String name) {

       int[] copy =new int[original.length];
       for (int i = 0;i < original.length;i++) {
           copy[i] = original[i];
       }
       long start = System.nanoTime();

       if (algorithm == 1) {

           SortingAlgorithms.selectionSort(copy);
      }
        else if (algorithm == 2) {
           SortingAlgorithms.insertionSort(copy);
      }
        else if (algorithm == 3) {

           SortingAlgorithms.mergeSort(copy);
     }

       else {
           SortingAlgorithms.quickSort(copy,0,copy.length - 1);
       }

       long end = System.nanoTime();


         System.out.println(name + " | Size: " + original.length + " | Time: " + (end - start) +" ns");
   }
}
public class Student {

   int studentNumber;
   String name;
   String serviceType;
   int serviceTime;
   public Student(int studentNumber, String name, String serviceType, int serviceTime) {

      this.studentNumber = studentNumber;
      this.name = name;
      this.serviceType = serviceType;
      this.serviceTime = serviceTime;

   }

   public void display() {

       System.out.println(
          studentNumber + " | " +
          name + " | " +
          serviceType + " | " +
          serviceTime + " min"

      );

   }

}
public class Queue {


   class Node {
       Student student;
       Node next;

       Node(Student student) {
           this.student = student;
          this.next = null;

       }

   }

   Node front;
   Node rear;

   public boolean isEmpty() {
  return front == null;

   }

   public void enqueue(Student student) {

       Node newNode = new Node(student);

       if (rear == null) {
           front = rear = newNode;
       } else {
           rear.next = newNode;
           rear = newNode;
        }
   }

   public Student dequeue() {

       if (isEmpty()) {
           return null;
       }
       Student student = front.student;
       front = front.next;


       if (front == null) {
          rear = null;
      }
       return student;

      }

   public Student peek() {

       if (isEmpty()) {
          return null;
       }
      return front.student;

  }

   public void displayQueue() {
       if (isEmpty()) {
           System.out.println("Queue is empty.");
          return;
       }
        Node current = front;

       while (current != null) {
           current.student.display();

           current = current.next;
      }

   }

}
public class StudentLinkedList {

   class Node {
       Student student;
       Node next;

       Node(Student student) {
           this.student = student;
           this.next = null;
       }
   }
   Node head;
   
   public void insertStudent(Student student, int position) {
       Node newNode = new Node(student);
      
       // Insert at beginning
       if (position <= 1 || head == null) {
           newNode.next = head;
           head = newNode;
           return;
       }

       Node current = head;
       int count = 1;

       // Find the position
      while (current.next != null && count < position - 1) {
      current = current.next;
      count++;
       }
       newNode.next = current.next;
       current.next = newNode;
   }


   public void deleteStudent(int studentNumber) {
       if (head == null) {
           return;
       }

       // Delete first node

       if (head.student.studentNumber == studentNumber) {
           head = head.next;
           return;
       }
       Node current = head;
       while (current.next != null) {
           if (current.next.student.studentNumber == studentNumber) {
               current.next = current.next.next;
               return;
           }
           current = current.next;
       }
   }

   
   public Student searchStudent(int studentNumber) {
      Node current = head;
       while (current != null) {
           if (current.student.studentNumber == studentNumber) {
               return current.student;
           }
           current = current.next;
       }
       return null;
   }
   public void displayStudents() {
       if (head == null) {
           System.out.println("No student records.");
           return;
       }
       Node current = head;
       while (current != null) {
           current.student.display();
           current = current.next;
       }
   }

}
public class SortingAlgorithms {

   // Selection Sort
   public static void selectionSort(int[] a) {

       for (int i = 0; i < a.length - 1; i++) {
           int min = i;

           for (int j = i + 1; j < a.length; j++) {

               if (a[j] < a[min]) {
                   min = j;
               }
           }
           int temp = a[i];
           a[i] = a[min];
           a[min] = temp;
       }
   }

   // Insertion Sort
   public static void insertionSort(int[] a) {
       for (int i = 1; i < a.length; i++) {
           int key = a[i];
           int j = i - 1;

           while (j >= 0 && a[j] > key) {
               a[j + 1] = a[j];
               j--;
           }
           a[j + 1] = key;
       }
   }

   // Merge Sort
   public static void mergeSort(int[] a) {

       if (a.length <= 1) {
           return;
       }
       int mid = a.length / 2;
       int[] left = new int[mid];
       int[] right = new int[a.length - mid];

       for (int i = 0; i < mid; i++) {
           left[i] = a[i];
       }

       for (int i = mid; i < a.length; i++) {
           right[i - mid] = a[i];
       }

       mergeSort(left);
       mergeSort(right);
       merge(a, left, right);
   }

   private static void merge(int[] a, int[] left,int[] right) {

       int i = 0;
       int j = 0;
       int k = 0;

       while (i < left.length && j < right.length) {

             if (left[i] <= right[j]) {
               a[k++] = left[i++];
          } 
else {

        a[k++] = right[j++];
       }
     }

       while (i < left.length) {
           a[k++] = left[i++];
       }

      while (j < right.length) {
           a[k++] = right[j++];
       }

   }

   // Quick Sort
   public static void quickSort(int[] a,int low,int high) {

   if (low < high) {
           int p = partition(a, low, high);
           quickSort(a, low, p - 1);
           quickSort(a, p + 1, high);
       }
   }

   private static int partition(int[] a,int low,int high) {

       int pivot = a[high];
       int i = low - 1;
       for (int j = low; j < high; j++) {
           if (a[j] <= pivot) {
           i++;

          int temp = a[i];
               a[i] = a[j];
               a[j] = temp;
           }
       }
       int temp = a[i + 1];
       a[i + 1] = a[high];
       a[high] = temp;

       return i + 1;
   }
}
