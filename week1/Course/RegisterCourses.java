import java.util.ArrayList;
import java.util.Scanner;

class Student {

  String fullName;
  int studentID; // Change from String to int
  ArrayList<Integer> courseIds; // Change from String[] to ArrayList<String>
  int creditHours;

  public Student() {
    courseIds = new ArrayList<Integer>(); // Initialize the ArrayList
  }

  public Student(int studentID, String fullName) { // Change from String to int
    this.fullName = fullName;
    this.studentID = studentID;
    courseIds = new ArrayList<Integer>(); // Initialize the ArrayList
  }

  public void registerCourse(Course course) { // Add return type "void"
    this.courseIds.add(course.courseId);
    this.creditHours += course.creditHours;
  }

  public void print() {
    System.out.printf(
      "| %-15s | %-10s | %-20s | %-13s |\n",
      this.fullName,
      this.studentID,
      this.courseIds,
      this.creditHours
    );
  }

  public static void inputList(ArrayList<Student> students) {
    Scanner scanner = new Scanner(System.in);
    Student student = new Student();
    System.out.print("Enter full name: ");
    student.fullName = scanner.nextLine();
    student.studentID = students.size() + 1;
    students.add(student);
    scanner.close();
  }

  public static void printStudents(ArrayList<Student> students) {
    System.out.printf(
      "| %-15s | %-10s | %-20s | %-13s |\n",
      "Full Name",
      "Student ID",
      "Course IDs",
      "Credit Hours"
    );
    for (Student student : students) student.print();
  }
}

class Course {

  int courseId; // Change from String to int
  String courseName;
  int creditHours;
  int startTime;
  int endTime;
  String dayOfWeek;
  ArrayList<Integer> studentIDs;

  public Course() {
    studentIDs = new ArrayList<Integer>();
  }

  public Course(
    int courseId, // Change from String to int
    String courseName,
    int creditHours,
    int startTime,
    int endTime,
    String dayOfWeek
  ) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.creditHours = creditHours;
    this.startTime = startTime;
    this.endTime = endTime;
    this.dayOfWeek = dayOfWeek;
    studentIDs = new ArrayList<>();
  }

  public void studentRegister(Student student) {
    studentIDs.add((student.studentID)); // Convert int to String
    student.registerCourse(this);
  }

  public void print() {
    System.out.printf(
      "| %-10s | %-20s | %-13s | %-10s | %-8s |\n",
      this.courseId,
      this.courseName,
      this.creditHours,
      this.startTime,
      this.endTime
    );
  }

  public static void inputList(ArrayList<Course> courses) {
    Scanner scanner = new Scanner(System.in);
    Course course = new Course();
    System.out.print("Enter course name: ");
    course.courseName = scanner.nextLine();
    System.out.print("Enter credit hours: ");
    course.creditHours = scanner.nextInt();
    System.out.print("Enter start time: ");
    course.startTime = scanner.nextInt();
    System.out.print("Enter end time: ");
    course.endTime = scanner.nextInt();
    System.out.print("Enter day of week: ");
    course.dayOfWeek = scanner.nextLine();
    course.courseId = courses.size() + 1;
    courses.add(course);
    scanner.close();
  }

  public static void printCourses(ArrayList<Course> courses) {
    System.out.printf(
      "| %-10s | %-20s | %-13s | %-10s | %-8s |\n",
      "Course ID",
      "Course Name",
      "Credit Hours",
      "Start Time",
      "End Time"
    );
    for (Course course : courses) course.print();
  }
}

public class RegisterCourses {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Auto index for id of students and courses");

    // System.out.print("Enter the number of students: ");
    // int numStudents = scanner.nextInt();

    // System.out.print("Enter the number of courses: ");
    // int numCourses = scanner.nextInt();

    // Input student information
    ArrayList<Student> students = new ArrayList<>();
    students.add(new Student(1, "Nguyen Van A"));
    // for (int i = 0; i < numStudents; i++) Student.inputList(students);

    // Input course information
    ArrayList<Course> courses = new ArrayList<>();
    courses.add(new Course(1, "Math", 3, 7, 9, "Monday"));
    // for (int i = 0; i < numCourses; i++) Course.inputList(courses);

    int command = 0;
    while (command != 6) {
      System.out.println(
        "------------------------Menu------------------------"
      );
      System.out.println("1. List all students");
      System.out.println("2. List all courses");
      System.out.println(
        "3. Register a course for a student (Phải đăng ký cho đến khi số tín chỉ >= 14)"
      );
      System.out.println("6. Exit");
      System.out.println(
        "----------------------------------------------------"
      );
      switch (command) {
        case 1:
          Student.printStudents(students);

          break;
        case 2:
          Course.printCourses(courses);
          break;
        case 3:
          // register course for student
          System.out.print("Enter student ID: ");
          int studentID = scanner.nextInt();

          int index = -1;
          for (int i = 0; i < students.size(); i++) {
            if (students.get(i).studentID == studentID) { // Change from students.get(i).studentID.equals(Integer.toString(studentID)) to students.get(i).studentID == studentID
              index = i;
              break;
            }
          }
          if (index == -1) {
            System.out.println("Invalid student ID");
            continue;
          }

          // while (students.get(index).creditHours <= 14) {
          System.out.print("Enter course ID: ");
          int courseID = scanner.nextInt();

          Course selectedCourse = null;
          for (Course course : courses) {
            if (course.courseId == courseID) { // Change from course.courseId.equals(Integer.toString(courseID)) to course.courseId == courseID
              selectedCourse = course;
              break;
            }
          }
          if (selectedCourse == null) {
            System.out.println("Invalid course ID");
            continue;
          }
          selectedCourse.studentRegister(students.get(index));

          System.out.println("Course registered successfully");

          break;
        case 6:
          System.out.println("Exiting...");
          break;
        default:
          break;
      }
      command = scanner.nextInt();
    }

    scanner.close();
  }
}
