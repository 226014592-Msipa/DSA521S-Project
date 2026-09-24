import java.util.Scanner;

public class StudentLinkedList {

    
    class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

   
    Node head;

    
    StudentLinkedList() {
        head = null;
    }

    
    boolean isEmpty() {
        return (head == null);
    }

  
    void insertAtBeginning(Student s) {
        Node newNode = new Node(s);
        newNode.next = head;
        head = newNode;
        System.out.println(s.name + " inserted at the beginning.");
    }

   
    void insertAtEnd(Student s) {
        Node newNode = new Node(s);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println(s.name + " inserted at the end.");
    }

    void insertAtPosition(Student s, int pos) {
        Node newNode = new Node(s);

        if (pos < 1) {
            System.out.println("Invalid position!");
            return;
        }

        if (pos == 1) {
            newNode.next = head;
            head = newNode;
            System.out.println(s.name + " inserted at position 1.");
            return;
        }

        Node temp = head;
        int i = 1;
        while (i < pos - 1 && temp != null) {
            temp = temp.next;
            i++;
        }

        if (temp == null) {
            System.out.println("Position out of range!");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        System.out.println(s.name + " inserted at position " + pos + ".");
    }

  
    void deleteStudent(String studentNo) {
        if (head == null) {
            System.out.println("List is empty! Nothing to delete.");
            return;
        }

        if (head.data.studentNo.equals(studentNo)) {
            System.out.println(head.data.name + " deleted from list.");
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null && !temp.next.data.studentNo.equals(studentNo)) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Student not found!");
        } else {
            System.out.println(temp.next.data.name + " deleted from list.");
            temp.next = temp.next.next;
        }
    }

    void searchStudent(String studentNo) {
        Node temp = head;
        int position = 1;

        while (temp != null) {
            if (temp.data.studentNo.equals(studentNo)) {
                System.out.println("Found at position " + position + ":");
                temp.data.display();
                return;
            }
            temp = temp.next;
            position++;
        }

        System.out.println("Student " + studentNo + " not found.");
    }

  
    void displayStudents() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        System.out.println("--- Student Service Records ---");
        Node temp = head;
        while (temp != null) {
            temp.data.display();
            temp = temp.next;
        }
    }

    //mainprogram//
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StudentLinkedList list = new StudentLinkedList();

        
       
        
        list.insertAtEnd(new Student("221045678", "Maria",   "Registration", 12));
        list.insertAtEnd(new Student("222034512", "Tomas",   "Student Card",  5));
        list.insertAtEnd(new Student("223041876", "Ndapewa", "Fees",          8));
        list.insertAtEnd(new Student("221067341", "Simon",   "Documents",     4));
        list.insertAtEnd(new Student("224032199", "Lucia",   "Academic",     10));
        list.insertAtEnd(new Student("225014700", "Peter",   "Fees",          6));
       
        list.displayStudents();

        
        int choice = 0;

        while (choice != 7) {
            System.out.println("\n=====menu====");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at end");
            System.out.println("3. Insert at position");
            System.out.println("4. Delete by student number");
            System.out.println("5. Search by student number");
            System.out.println("6. Display all students");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                case 2:
                case 3:
                    System.out.print("Student No: ");
                    String no = input.nextLine();

                    System.out.print("Name: ");
                    String name = input.nextLine();

                    System.out.print("Service Type: ");
                    String service = input.nextLine();

                    System.out.print("Estimated Time (min): ");
                    int time = input.nextInt();
                    input.nextLine();

                    Student s = new Student(no, name, service, time);

                    if (choice == 1) {
                        list.insertAtBeginning(s);
                    } else if (choice == 2) {
                        list.insertAtEnd(s);
                    } else {
                        System.out.print("Insert at position: ");
                        int pos = input.nextInt();
                        input.nextLine();
                        list.insertAtPosition(s, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter student number to delete: ");
                    String delNo = input.nextLine();
                    list.deleteStudent(delNo);
                    break;

                case 5:
                    System.out.print("Enter student number to search: ");
                    String searchNo = input.nextLine();
                    list.searchStudent(searchNo);
                    break;

                case 6:
                    list.displayStudents();
                    break;

                case 7:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        input.close();
    }
}