public class Student {
    String studentNo;
    String name;
    String serviceType;
    int estimatedTime;

    Student(String studentNo, String name, String serviceType, int estimatedTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedTime = estimatedTime;
    }

    void display() {
        System.out.println(studentNo + " | " + name + " | " 
                         + serviceType + " | " + estimatedTime + " min");
    }
}