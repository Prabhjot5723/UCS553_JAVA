# UCS553 – Enterprise Web Application
## Lab Experiment 1 – Basic Programming Using Java

### Student Details
- **Name:** Prabhjot Singh
- **Roll No.:** 1024170008
- **Class/Section:** 3rd year CSE
- **Subject:** UCS553 – Enterprise Web Application

---

## Objective
To understand the basics of Java programming by implementing simple programs using methods in a single class.

---

## Programs Included

1. Check Leap Year
2. Check Whether a Character is Alphabet or Not
3. Find Factorial of a Number
4. Display Fibonacci Series
5. Find GCD of Two Numbers
6. Find LCM of Two Numbers
7. Count Number of Digits in an Integer
8. Reverse a Number
9. Calculate the Power of a Number
10. Check Palindrome Number

---

## Software Requirements

- Java JDK 8 or above
- Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans) or VS Code
- Command Prompt/Terminal

---

## How to Run

1. Save the file as `Lab1.java`.
2. Open Command Prompt/Terminal.
3. Compile the program:
   ```
   javac Lab1.java
   ```
4. Run the program:
   ```
   java Lab1
   ```
5. Uncomment the required method in the `main()` function before running.

Example:
```java
obj.factorial();
```

---

## Features

- Single Java class (`Lab1`)
- Separate method for each program
- Easy to understand
- Beginner-friendly code
- Uses Scanner class for user input

---

## Learning Outcomes

- Understand Java syntax.
- Learn methods and objects.
- Practice loops and conditional statements.
- Perform basic mathematical operations.
- Improve problem-solving skills.



# UCS553 Assignment 3 — Unit 1
## Java Object-Oriented Programming Projects

This repository contains Java solutions for **Assignment 3, UCS553, Unit 1**, covering **Question 1 and Question 2**.

The projects demonstrate core Object-Oriented Programming concepts in Java, including abstraction, encapsulation, inheritance, interfaces, polymorphism, method overriding, constructors, static members, arrays, packages, and validation.

---

# Question 1 — Food Order Management System

## Project Overview

This project is a Java application developed for an online food delivery company.

It manages different types of food orders, including **Regular Orders** and **Premium Orders**.

### Packages

- `food.model`
- `food.service`
- `food.utility`
- `food.main`

## Main Components

### `FoodOrder`
An abstract class containing:

- `orderId`
- `customerName`
- `amount`
- Static `restaurantName`
- Abstract method `calculateDeliveryCharge()`

### `Discountable`
An interface containing:

- `double applyDiscount()`

### `RegularOrder`

- Extends `FoodOrder`
- Implements `Discountable`
- Delivery charge: **Rs. 80**
- Discount: **10%**

### `PremiumOrder`

- Extends `FoodOrder`
- Implements `Discountable`
- Delivery charge: **Rs. 50**
- Discount: **15%**

### `OrderUtility`

Provides:

- Amount validation
- Customer name validation
- Order summary generation

## Requirements Implemented

- Private data members
- Getters and setters
- Constructors
- Static object counter
- Static method to display total orders
- Method overriding
- Polymorphism
- Arrays of objects
- Utility class validation
- At least six orders stored in a `FoodOrder` array
- Bill amount display
- Discount calculation
- Delivery charge calculation
- Final payable amount calculation

## Project Structure

```text
FoodOrder
│
├── pom.xml
│
├── src
│   └── main
│       └── java
│           └── food
│               ├── main
│               │   └── Main.java
│               │
│               ├── model
│               │   └── FoodOrder.java
│               │
│               ├── service
│               │   ├── Discountable.java
│               │   ├── RegularOrder.java
│               │   └── PremiumOrder.java
│               │
│               └── utility
│                   └── OrderUtility.java
│
└── README.md
```

---

# Question 2 — Library Resource Management System

## Project Overview

This project implements a **Smart Library Resource Management System** in Java.

### Packages

- `library.model`
- `library.service`
- `library.util`
- `library.main`

## Main Components

### `LibraryResource`

An abstract class containing:

- `resourceId`
- `title`
- `author`
- Static `libraryName`
- Constructor
- Getters and setters
- Static resource counter
- `displayTotalResources()`
- `calculateFine()`

### `Printable`

An interface containing:

- `void printDetails()`

### `Book`

- Extends `LibraryResource`
- Implements `Printable`
- Fine: **Rs. 5 per overdue day**

### `DigitalResource`

- Extends `LibraryResource`
- Implements `Printable`
- Fine: **Rs. 2 per overdue day**

### `InputValidator`

Validates:

- Resource ID greater than `0`
- Overdue/fine days greater than or equal to `0`

### `Main`

The main program:

- Creates five resources
- Stores them in a `LibraryResource` array
- Displays resource details
- Calculates fines
- Displays the total fine
- Displays the total number of resources created

## Resources

| ID | Type | Title | Overdue Days |
|---:|---|---|---:|
| 101 | Book | Java Programming | 5 |
| 102 | Digital Resource | Data Structures E-Book | 10 |
| 103 | Book | Object Oriented Programming | 0 |
| 104 | Digital Resource | Database Systems | 7 |
| 105 | Book | Computer Networks | 3 |

## Fine Calculation

| Resource ID | Calculation | Fine |
|---:|---:|---:|
| 101 | 5 × Rs. 5 | Rs. 25 |
| 102 | 10 × Rs. 2 | Rs. 20 |
| 103 | 0 × Rs. 5 | Rs. 0 |
| 104 | 7 × Rs. 2 | Rs. 14 |
| 105 | 3 × Rs. 5 | Rs. 15 |
| **Total** | | **Rs. 74** |

## Expected Output

```text
Total Fine of All Resources: Rs. 74.0
Total Resources Created: 5
```

---

# Java Concepts Demonstrated

The projects demonstrate the following Java and OOP concepts:

- **Abstraction**
- **Encapsulation**
- **Inheritance**
- **Interfaces**
- **Polymorphism**
- **Method Overriding**
- **Constructors**
- **Static Variables and Methods**
- **Arrays of Objects**
- **Packages**
- **Validation**
- **Utility Classes**

---

# How to Open the Projects

## IntelliJ IDEA

1. Extract the project ZIP file.
2. Open **IntelliJ IDEA**.
3. Select **File → Open**.
4. Select the required project folder.
5. Allow IntelliJ IDEA to load the Maven project if applicable.
6. Open the corresponding `Main.java` file.
7. Run the `main()` method.

### Question 1

Open:

```text
src/main/java/food/main/Main.java
```

### Question 2

Open:

```text
src/main/java/library/main/Main.java
```

---

# Assignment Information

**Course:** UCS553  
**Assignment:** Assignment 3  
**Unit:** Unit 1  
**Questions Included:** Question 1 and Question 2

---

## Author

Java OOP Assignment Solutions
---

## Author

**Name:** ____________________  
**Roll Number:** ____________________
