# DSA521S Group Mini-Project 2026

**Group Number:** 2
submitted by AMANDA MSIPA 226014592 
## Group Members

| Name | Student Number |
|------|----------------|
| Amanda Msipa | 226014592 |
| Micheal Fillemon | 226142167 |
| Kondjashili Shatimwene |  225080370 |
| Franz Moussiessi  |226141861 |
|Utjiua Kahoro | 226141314 |
|Josef Elago  | 226047024|
## GitHub Repository

https://github.com/226014592-Msipa/DSA521S-Project

## Requirements

- Java JDK 21 or later (download from https://adoptium.net/)
- Any text editor or IDE (VS Code recommended)
- A terminal to run the compile and run commands

## Project Structure

| File             Purpose |
|------|---------|
| `Student.java` | Shared data class that holds student details (student number, name, service type, estimated time) |
| `Queue.java` | Part A1 — Waiting line (array-based queue) |
| `StudentLinkedList.java` | Part A2 — Student service records (singly linked list) |
| `Postfix.java` | Part A3 — Postfix expression evaluator (stack) |
| `Service.java` | Part A4 — Daily service statistics (array processing) |
| `PartB.java` | Part B — Demonstration of four sorting algorithms with trace output |
| `AlgorithmExperiment.java` | Part C — Sorting algorithm experiment across input sizes 20, 50, 100, 500 |
| `SortingAlgorithms.java` | Utility sorting methods used by the integrated system |
| `CampusServiceCentre.java` | Part D — Integrated campus service-centre menu system |

## How to Run

Each `.java` file contains its own `main()` method and can be compiled and run independently.

### Step 1 — Open a terminal in the project folder

In VS Code, press `Ctrl + \`` (Control + backtick) to open the terminal. It will already be in the project folder.

### Step 2 — Compile the file
javac FileName.java
For example:
javac Queue.java

### Step 3 — Run the file
java ClassName

txt

Note: When running, do NOT include the `.java` extension. Use only the class name.

For example:
java Queue
### Running each part

**Part A1 — Queue demonstration:**
javac Queue.java
java Queue

**Part A2 — Linked list demonstration:**
javac StudentLinkedList.java
java StudentLinkedList

**Part A3 — Postfix evaluator:**
javac Postfix.java
java Postfix  


**Part A4 — Daily statistics:**
javac Service.java
java Service


**Part B — Sorting demonstrations:**
javac PartB.java
java PartB


**Part C — Sorting experiment:**
javac AlgorithmExperiment.java
java AlgorithmExperiment


**Part D — Integrated service centre:**
javac CampusServiceCentre.java
java CampusServiceCentre

## Menu options and the structure each one uses

| Option | Structure / Operation |
|---|---|
| 1. Add student to waiting queue | Queue — `enqueue()` |
| 2. Serve next student | Queue — `dequeue()` |
| 3. Display waiting students | Queue — traversal |
| 4. Add student service record | Singly Linked List — `insertStudent()` |
| 5. Display student service records | Singly Linked List — traversal |
| 6. Search for student record | Singly Linked List — `searchStudent()` |
| 7. Remove student record | Singly Linked List — `deleteStudent()` |
| 8. Display daily statistics | Array — traversal |
| 9. Sort service times | Selection / Insertion / Merge / Quick Sort |
| 10. Run sorting experiment | Full Part C experiment |
| 11. Exit | Ends the program |

## Contribution Notes

The GitHub commit history shows AMANDA  as the author of most commits, because AMANDA handled the integration and upload process for group members who were unable to push directly. The actual authorship of each part is:

- Part A (Queue, Linked List, Postfix, Service) — AMANDA
- Part B (Sorting algorithm demonstrations) — JOSEF
- Part C (Algorithm Experiment) — UJTIUA
- Part D (Integrated Service-Centre System) — MICHAEL
- Part E (Pseudocode) — KONDJASHILI
- Part F (Project Report) — FRANZ



