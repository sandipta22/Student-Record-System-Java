public class Student {
    int regd_no;
    String name;
    float mark;

    public Student(int regd_no, String name, float mark) {
        this.regd_no = regd_no;
        this.name = name;
        this.mark = mark;
    }

    public void display() {
        System.out.println("Regd No: " + regd_no + ", Name: " + name + ", Mark: " + mark);
    }
}
