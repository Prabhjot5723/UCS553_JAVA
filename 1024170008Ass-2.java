public class Prabhjot Singh {

    // 1, 2, 5, 9, 10
    static class Student {

        // 3: Access modifiers
        private String name;       // private
        int age;                   // default
        protected String course;   // protected
        public int marks;          // public

       // 9: Static variable
        static int count = 0;

        // 5: No-argument constructor
        Student() {
            name = "Unknown";
            age = 18;
            course = "Java";
            marks = 0;
            count++;
        }

        // 5: Parameterized constructor
        Student(String name, int age) {
            this.name = name;
            this.age = age;
            course = "Java";
            marks = 0;
            count++;
        }

        // 5: Constructor overloading
        Student(String name, int age, String course, int marks) {
            this.name = name;
            this.age = age;
            this.course = course;
            this.marks = marks;
            count++;
        }

        // 4: Getter
        public String getName() {
            return name;
        }

        // 4: Setter
        public void setName(String name) {
            this.name = name;
        }

        void show() {
            System.out.println(name + " | Age: " + age
                    + " | Course: " + course
                    + " | Marks: " + marks);
        }
    }


    // 6: Interface
    interface Printable {
        void print();
    }


    // 7: Second interface
    interface Showable {
        void showMessage();
    }


    // 6 and 7: Implementing two interfaces
    static class Result implements Printable, Showable {

        public void print() {
            System.out.println("Result printed.");
        }

        public void showMessage() {
            System.out.println("This class implements two interfaces.");
        }
    }

    // 8: Abstract class
    static abstract class Animal {

        abstract void sound();

        void eat() {
            System.out.println("Animal eats.");
        }
    }
    

    // Class extending abstract class
    static class Dog extends Animal {

        void sound() {
            System.out.println("Dog barks.");
        }
    }


    public static void main(String[] args) {

        // 1: Creating objects
        Student s1 = new Student();
        Student s2 = new Student("Rahul", 20);
        Student s3 = new Student("Anita", 21, "Java", 90);

        System.out.println("1 & 2: Objects and Constructors");
        s1.show();
        s2.show();
        s3.show();


        // 4: Getter and Setter
        System.out.println("\n4: Encapsulation");
        System.out.println("Name: " + s2.getName());

        s2.setName("Amit");
        System.out.println("New Name: " + s2.getName());


        // 3: Access modifiers
        System.out.println("\n3: Access Modifiers");
        System.out.println("Default age: " + s1.age);
        System.out.println("Protected course: " + s1.course);
        System.out.println("Public marks: " + s1.marks);

        // private name is accessed using getter
        System.out.println("Private name: " + s1.getName());


        // 6 & 7: Interfaces
        System.out.println("\n6 & 7: Interfaces");
        Result r = new Result();
        r.print();
        r.showMessage();


        // 8: Abstract class
        System.out.println("\n8: Abstract Class");
        Dog d = new Dog();
        d.sound();
        d.eat();


        // 9: Static variable
        System.out.println("\n9: Number of Objects Created");
        System.out.println("Objects created: " + Student.count);


        // 10: Instance variables
        System.out.println("\n10: Instance Variables");
        System.out.println("s1 name: " + s1.getName());
        System.out.println("s2 name: " + s2.getName());
        System.out.println("s3 name: " + s3.getName());
    }
} 


    // Class extending abstract class
    static class Dog extends Animal {

        void sound() {
            System.out.println("Dog barks.");
        }
    }


    public static void main(String[] args) {

        // 1: Creating objects
        Student s1 = new Student();
        Student s2 = new Student("Rahul", 20);
        Student s3 = new Student("Anita", 21, "Java", 90);

        System.out.println("1 & 2: Objects and Constructors");
        s1.show();
        s2.show();
        s3.show();


        // 4: Getter and Setter
        System.out.println("\n4: Encapsulation");
        System.out.println("Name: " + s2.getName());

        s2.setName("Amit");
        System.out.println("New Name: " + s2.getName());


        // 3: Access modifiers
        System.out.println("\n3: Access Modifiers");
        System.out.println("Default age: " + s1.age);
        System.out.println("Protected course: " + s1.course);
        System.out.println("Public marks: " + s1.marks);

        // private name is accessed using getter
        System.out.println("Private name: " + s1.getName());


        // 6 & 7: Interfaces
        System.out.println("\n6 & 7: Interfaces");
        Result r = new Result();
        r.print();
        r.showMessage();


        // 8: Abstract class
        System.out.println("\n8: Abstract Class");
        Dog d = new Dog();
        d.sound();
        d.eat();


        // 9: Static variable
        System.out.println("\n9: Number of Objects Created");
        System.out.println("Objects created: " + Student.count);


        // 10: Instance variables
        System.out.println("\n10: Instance Variables");
        System.out.println("s1 name: " + s1.getName());
        System.out.println("s2 name: " + s2.getName());
        System.out.println("s3 name: " + s3.getName());
    }
} 
 