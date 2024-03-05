import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

  public boolean registerCourse(Course course) {
    if (this.creditHours + course.creditHours > 24) {
      System.out.println("Số lượng tín chỉ tối đa là 24");
      return false;
    }
    if (this.courseIds.contains(course.courseId)) {
      System.out.println("Course already registered");
      return false;
    }
    this.courseIds.add(course.courseId);
    this.creditHours += course.creditHours;
    System.out.println(
      "Course registered successfully, " +
      "current credit hours: " +
      this.creditHours
    );
    return true;
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

  public static void printField() {
    System.out.printf(
      "| %-15s | %-10s | %-20s | %-13s |\n",
      "Full Name",
      "Student ID",
      "Course IDs",
      "Credit Hours"
    );
  }

  public static void printStudents(ArrayList<Student> students) {
    printField();
    for (Student student : students) student.print();
  }

  public static Student findStudent(
    ArrayList<Student> students,
    int studentID
  ) {
    for (Student student : students) {
      if (student.studentID == studentID) {
        return student;
      }
    }
    return null;
  }

  public static Student registeringMostCourse(ArrayList<Student> students) {
    Student student = students.get(0);
    for (Student s : students) if (
      s.courseIds.size() > student.courseIds.size()
    ) student = s;
    return student;
  }
}

class Course {

  int courseId; // Change from String to int
  String courseName;
  int creditHours;
  int startTime;
  int endTime;
  int dayOfWeek;
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
    int dayOfWeek
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
    if (student.registerCourse(this)) studentIDs.add((student.studentID));
  }

  public void print() {
    System.out.printf(
      "| %-10s | %-20s | %-13s | %-15s | %-10s | %-8s |\n",
      this.courseId,
      this.courseName,
      this.creditHours,
      this.dayOfWeek,
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
    course.dayOfWeek = scanner.nextInt();
    course.courseId = courses.size() + 1;
    courses.add(course);
    scanner.close();
  }

  public static void printField() {
    System.out.printf(
      "| %-10s | %-20s | %-13s | %-15s | %-10s | %-8s |\n",
      "Course ID",
      "Course Name",
      "Credit Hours",
      "Day of Week",
      "Start Time",
      "End Time"
    );
  }

  public static void printCourses(ArrayList<Course> courses) {
    printField();
    for (Course course : courses) course.print();
  }

  public static Course findCourse(ArrayList<Course> courses, int courseId) {
    for (Course course : courses) if (
      course.courseId == courseId
    ) return course;

    return null;
  }

  public static Course mostRegisteredCourse(ArrayList<Course> courses) {
    Course mostRegisteredCourse = courses.get(0);
    for (Course course : courses) if (
      course.studentIDs.size() > mostRegisteredCourse.studentIDs.size()
    ) mostRegisteredCourse = course;
    return mostRegisteredCourse;
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
    courses.add(new Course(1, "Math", 3, 7, 9, 2));
    courses.add(new Course(2, "English", 4, 10, 12, 4));
    courses.add(new Course(3, "Science", 3, 13, 15, 6));
    courses.add(new Course(4, "History", 3, 9, 11, 3));
    courses.add(new Course(5, "Physics", 4, 14, 16, 5));
    courses.add(new Course(6, "Computer Science", 3, 9, 11, 2));
    courses.add(new Course(7, "Art", 2, 13, 15, 4));
    courses.add(new Course(8, "Music", 2, 10, 12, 6));
    courses.add(new Course(9, "Chemistry", 4, 8, 10, 3));
    courses.add(new Course(10, "Physical Education", 1, 15, 16, 2));
    courses.add(new Course(11, "Biology", 3, 11, 13, 5));
    courses.add(new Course(12, "Literature", 3, 14, 16, 3));
    courses.add(new Course(13, "Geography", 2, 9, 11, 6));
    courses.add(new Course(14, "Economics", 3, 13, 15, 4));
    // for (int i = 0; i < numCourses; i++) Course.inputList(courses);

    int command = 0;
    while (command != 7) {
      System.out.println(
        "------------------------Menu------------------------"
      );
      System.out.println("1. List all students");
      System.out.println("2. List all courses");
      System.out.println(
        "3. Register a course for a student (-1 để thoát) (Phải đăng ký cho đến khi số tín chỉ >= 14)"
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
          int input = scanner.nextInt();

          Student student = Student.findStudent(students, input);
          while (student == null) {
            System.out.println("Invalid student ID");
            System.out.print("Enter student ID: ");
            input = scanner.nextInt();
            if (input == -1) break;
            student = Student.findStudent(students, input);
          }

          while (true) {
            System.out.print("Enter course ID: ");
            input = scanner.nextInt();

            if (input == -1) if (student.creditHours >= 14) break; else {
              System.out.println("Chưa đăng ký đủ số tín chỉ");
              continue;
            }

            Course course = Course.findCourse(courses, input);
            while (course == null) {
              System.out.println("Invalid course ID");
              System.out.print("Enter course ID: ");
              input = scanner.nextInt();
              if (input == -1) break;
              course = Course.findCourse(courses, input);
            }
            if (course != null) course.studentRegister(student);
            if (student.creditHours >= 24) break;
          }
          break;
        case 4:
          Course mostRegisteredCourse = Course.mostRegisteredCourse(courses);

          Course.printField();
          mostRegisteredCourse.print();

          System.out.println("Students registered for this course:");
          Student.printField();
          for (int studentID : mostRegisteredCourse.studentIDs) {
            Student.findStudent(students, studentID).print();
          }
          break;
        case 5:
          Student mostRegisteredStudent = Student.registeringMostCourse(
            students
          );

          Student.printField();
          mostRegisteredStudent.print();

          System.out.println("Courses registered for this student:");
          Course.printField();
          for (int courseId : mostRegisteredStudent.courseIds) {
            Course.findCourse(courses, courseId).print();
          }
          break;
        case 6:
          System.out.print("Enter student ID: ");
          input = scanner.nextInt();

          Student st = Student.findStudent(students, input);
          while (st == null) {
            System.out.println("Invalid student ID");
            System.out.print("Enter student ID: ");
            input = scanner.nextInt();
            if (input == -1) break;
            student = Student.findStudent(students, input);
          }
          ArrayList<Course> coursesRegistered = new ArrayList<Course>();
          for (int courseId : st.courseIds) {
            coursesRegistered.add(Course.findCourse(courses, courseId));
          }

          // Sort coursesRegistered by dayOfWeek and startTime
          Collections.sort(
            coursesRegistered,
            new Comparator<Course>() {
              @Override
              public int compare(Course c1, Course c2) {
                if (Integer.valueOf(c1.dayOfWeek).equals(c2.dayOfWeek)) {
                  return Integer.compare(c1.startTime, c2.startTime);
                } else {
                  return Integer.valueOf(c1.dayOfWeek).compareTo(c2.dayOfWeek);
                }
              }
            }
          );

          Course.printCourses(coursesRegistered);
          break;
        default:
          break;
      }
      System.out.println(
        "------------------------Menu------------------------"
      );
      System.out.println("1. List all students");
      System.out.println("2. List all courses");
      System.out.println(
        "3. Register a course for a student (-1 để thoát) (Phải đăng ký cho đến khi số tín chỉ >= 14)"
      );
      System.out.println("6. Exit");
      System.out.println(
        "----------------------------------------------------"
      );
      command = scanner.nextInt();
    }

    scanner.close();
  }
}
