import java.util.Scanner;

public class Queue {
    Student[] queue;
    int front, rear, size;

  
    Queue(int size) {
        this.size = size;
        queue = new Student[size];
        front = -1;
        rear = -1;
    }
    boolean isEmpty() {
        return (front == -1 && rear == -1);
    }

    boolean isFull() {
        return (rear == size - 1);
    }


    void enqueue(Student s) {
        if (isFull()) {
            System.out.println("Queue is full!");
        } else if (isEmpty()) {
            front = 0;
            rear = 0;
            queue[rear] = s;

        } else {
            rear++;
            queue[rear] = s;
    
        }
    }

    Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        } else if (front == rear) {
            Student served = queue[front];
            front = -1;
            rear = -1;
            return served;
        } else {
            Student served = queue[front];
            front++;
            return served;
        }
    }
    Student peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        return queue[front];
    }
    void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
        } else {
            for (int i = front; i <= rear; i++) {
                queue[i].display();
            }
        }
    }

//main program//
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue q = new Queue(20);
        
        q.enqueue(new Student("221045678", "Maria",   "Registration", 12));
        q.enqueue(new Student("222034512", "Tomas",   "Student Card",  5));
        q.enqueue(new Student("223041876", "Ndapewa", "Fees",          8));
        q.enqueue(new Student("221067341", "Simon",   "Documents",     4));
        q.enqueue(new Student("224032199", "Lucia",   "Academic",     10));
        q.enqueue(new Student("225014700", "Peter",   "Fees",          6));

        q.displayQueue();


    
  
    q.dequeue(); 
    q.dequeue(); 
    q.dequeue(); 
    System.out.println("Served 3 students.");


    q.displayQueue();

       
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n===== SERVICE=====");
            System.out.println("1. Enqueue (add a student)");
            System.out.println("2. Dequeue (serve next student)");
            System.out.println("3. Peek (view next student)");
            System.out.println("4. Display queue");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Student No: ");
                    String no = input.nextLine();

                    System.out.println("Name: ");
                    String name = input.nextLine();

                    System.out.println("Service Type: ");
                    String service = input.nextLine();

                    System.out.println("Estimated Time (min): ");
                    int time = input.nextInt();
                    input.nextLine();

                    Student s = new Student(no, name, service, time);
                    q.enqueue(s);
                    break;

                case 2:
                    Student served = q.dequeue();
                    if (served != null) {
                        System.out.println("Served: " + served.name
                                + " (" + served.serviceType + ")");
                    }
                    break;

                case 3:
                    Student next = q.peek();
                    if (next != null) {
                        System.out.println("Next in line: " + next.name);
                    }
                    break;

                case 4:
                    q.displayQueue();
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        input.close();
    }
}