package com.mycompany.projectfinalcode;
import java.util.*;
class Student {
    int id;
    String name;
    int marks;
    Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
}
class Node {
    Student data;
    Node left, right;

    Node(Student s) {
        data = s;
        left = right = null;
    }
}
public class ProjectDS {
    static Student[] arr = new Student[100];
    static int count = 0;
    static Node root = null;
    static Stack<Student> stack = new Stack<>();
    static Queue<Student> queue = new LinkedList<>();
    static PriorityQueue<Student> heap =
        new PriorityQueue<>((a, b) -> b.marks - a.marks);
    static Scanner sc = new Scanner(System.in);
    static Node insert(Node root, Student s) {
        if (root == null)
            return new Node(s);
        if (s.id < root.data.id)
            root.left = insert(root.left, s);
        else
            root.right = insert(root.right, s);

        return root;
    }
    static Student search(Node root, int id) {
        if (root == null)
            return null;
        if (root.data.id == id)
            return root.data;

        if (id < root.data.id)
            return search(root.left, id);

        return search(root.right, id);
    }
    static void sort() {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (arr[j].marks < arr[j + 1].marks) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();
        Student s = new Student(id, name, marks);
        arr[count++] = s;
        root = insert(root, s);
        stack.push(s);
        queue.add(s);
        heap.add(s);
        System.out.println("Student Added Successfully!");
    }
    static void displayStudents() {
        sort();
        System.out.println("\nSorted Students:");
        for (int i = 0; i < count; i++) {

            System.out.println(
                arr[i].id + " " +
                arr[i].name + " " +
                arr[i].marks
            );
        }
    }
    static void searchStudent() {
        System.out.print("\nEnter ID to Search: ");
        int id = sc.nextInt();
        Student s = search(root, id);
        if (s != null) {
            System.out.println("\nStudent Found:");
            System.out.println("ID: " + s.id);
            System.out.println("Name: " + s.name);
            System.out.println("Marks: " + s.marks);

        } else {
            System.out.println("Student Not Found!");
        }
    }
    static void top3() {
        System.out.println("\nTOP 3 STUDENTS:");
        PriorityQueue<Student> temp =
            new PriorityQueue<>((a, b) -> b.marks - a.marks);
        temp.addAll(heap);
        for (int i = 0; i < 3 && !temp.isEmpty(); i++) {

            Student s = temp.poll();

            System.out.println(s.name + " : " + s.marks);
        }
    }
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Student Result Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Show Top 3");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    top3();
                    break;
                case 5:
                    System.out.println("Program Ended.");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);
    }
}