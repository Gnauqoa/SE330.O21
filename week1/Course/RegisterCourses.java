import java.util.ArrayList;
import java.util.Scanner;

class Student {

  String fullName;
  String studentID;
  ArrayList<String> courseIds; // Change from String[] to ArrayList<String>
  int creditHours;

  public Student() {
    courseIds = new ArrayList<>(); // Initialize the ArrayList
  }

  public Student(String studentID, String fullName) {
    this.fullName = fullName;
    this.studentID = studentID;
    courseIds = new ArrayList<>(); // Initialize the ArrayList
  }

  public void registerCourse(Course course) { // Add return type "void"
    this.courseIds.add(course.courseId);
    this.creditHours += course.creditHours;
  }
}

class Course {

  String courseId;
  String courseName;
  int creditHours;
  int startTime;
  int endTime;
  String dayOfWeek;
  ArrayList<String> studentIDs;

  public Course() {
    studentIDs = new ArrayList<>();
  }

  public Course(
    String courseId,
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
    studentIDs.add(student.studentID);
    student.registerCourse(this);
  }
}

public class RegisterCourses {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Input the number of students and courses
    System.out.println("Auto index for id of students and courses");
    System.out.print("Enter the number of students: ");
    int numStudents = scanner.nextInt();

    System.out.print("Enter the number of courses: ");
    int numCourses = scanner.nextInt();

    // Input student information
    ArrayList<Student> students = new ArrayList<>();
    for (int i = 0; i < numStudents; i++) {
      Student student = new Student();
      System.out.println("Enter information for Student " + (i + 1) + ":");
      System.out.print("Full Name: ");
      student.fullName = scanner.next();
      student.studentID = Integer.toString(i);
      students.add(student);
    }

    // Input course information
    ArrayList<Course> courses = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      Course course = new Course();
      System.out.println("Enter information for Course " + (i + 1) + ":");
      course.courseId = Integer.toString(i);
      System.out.print("Course Name: ");
      course.courseName = scanner.next();
      System.out.print("Credit Hours: ");
      course.creditHours = scanner.nextInt();
      System.out.print("Start Time (in hours): ");
      course.startTime = scanner.nextInt();
      System.out.print("End Time (in hours): ");
      course.endTime = scanner.nextInt();
      System.out.print("Day of Week: ");
      course.dayOfWeek = scanner.next();
      courses.add(course);
    }

    int command = 0;
    while (command != 6) {
      System.out.println(">>>>Menu<<<<");
      System.out.println("1. List all students");
      System.out.println("2. List all courses");
      System.out.println("3. Register a course for a student");
      System.out.println("6. Exit");

      switch (command) {
        case 1:
          System.out.println("Students List:");
          for (Student student : students) {
            System.out.println("Student ID: " + student.studentID);
            System.out.println("Full Name: " + student.fullName);
            System.out.println("Credit Hours: " + student.creditHours);
            System.out.println("Registered Courses: " + student.courseIds);
            System.out.println("------------------------");
          }

          break;
        case 2:
          for (Course course : courses) {
            System.out.println("Course ID: " + course.courseId);
            System.out.println("Course Name: " + course.courseName);
            System.out.println("Credit Hours: " + course.creditHours);
            System.out.println("Start Time: " + course.startTime);
            System.out.println("End Time: " + course.endTime);
            System.out.println("Day of Week: " + course.dayOfWeek);
            System.out.println("------------------------");
          }
          break;
        case 3:
          // register course for student
          System.out.print("Enter student ID: ");
          int studentID = scanner.nextInt();

          int index = -1;
          for (int i = 0; i < students.size(); i++) {
            if (students.get(i).studentID.equals(Integer.toString(studentID))) {
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
              if (course.courseId.equals(Integer.toString(courseID))) {
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
          // }

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
