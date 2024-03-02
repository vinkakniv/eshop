# Advanced Programming - Tutorial 


------------
## Overview

This repository contains the code and reflections for Tutorial in Advanced Programming course by Vinka Alrezky As (NPM: 2206820200)❄️
- [Tutorial 1](#tutorial-1)
- [Tutorial 2](#tutorial-2)
- [Tutorial 3](#tutorial-3)
- [Tutorial 4](#tutorial-4)

Click here to see the deployed [ADV Shop](https://eshop-adpro-tutorial.koyeb.app/) app!

------------
# Tutorial 1
- [Reflection 1](#reflection-1)
- [Reflection 2](#reflection-2)

### Reflection 1

After examining my code, I noticed that I followed some important coding rules.
**Meaningful names** were assigned to variables and functions, which helps make the code easy to understand. 
Each **function** in my code performs just one task, keeping them **simple**. **Comments** are used only when absolutely **necessary**. 
I used **objects and data structures effectively**. Objects were created that keep their data hidden and provide functions to work with that data, helping to keep everything organized and secure. 

Upon further review of my code, I found some areas that could be improved. In some places, null is returned when an item cannot be found. 
It might be clearer to throw an exception instead. 

Even though my code works, there are things that could be done to make it better. I plan to continue working on it to ensure it follows the best practices of clean coding

### Reflection 2

1. Writing the unit test made me feel good about how solid and dependable my code is. The number of unit tests we need can change based on how complicated the class is. Every method should have at least one test, and we should write more tests for different inputs and special cases. To make sure we have enough unit tests, we can use tools that measure code coverage. Code coverage tells us what percentage of our code is tested. But even if we have 100% code coverage, it doesn’t mean our code is perfect. It just means that all the lines of code were run during testing. There could still be mistakes in the logic or special cases we didn’t think of that aren’t covered by the tests.
2. Making a new functional test suite to check the number of items in the product list is a good idea because it helps make sure our program is working right. But if the new test suite is a lot like the existing ones and has the same setup procedures and instance variables, it could make the code quality worse because it means we’re repeating code. To make the code cleaner, we could think about refactoring, or reorganizing, the common setup procedures and instance variables into a separate method or class that can be reused in different test suites. This would make our code easier to maintain and understand.

------------

# Tutorial 2
### Reflection 

1. During the exercise, i encountered and fixed several code quality issues. Here's a list of them and the strategies i used to fix them:
- **MethodNamingConventions**: Some methods were not named according to standard conventions. I renamed these methods to follow the camelCase convention, which improves readability and consistency in the codebase.
   ```java
   // Before
   void main_startsSpringApplication() { ... }

   // After
   void mainStartsSpringApplication() { ... }
- **UncommentedEmptyMethodBody**: There were methods with empty bodies and no comments explaining their purpose. I added comments to these methods to clarify their intended functionality. 
    ```java
    // Before
    void setUp() {
    }

    // After
    void setUp() {
        // No setup required for tests
    }
- **LiteralsFirstInComparisons**: There were instances where literals were not placed first in comparisons, which can lead to null pointer exceptions. I refactored these comparisons to put literals first, which avoids potential null pointer exceptions.
   ```java
   // Before
   assert(view.equals("createProduct"));

   // After
   assert("createProduct".equals(view));
- **UselessParentheses**: Some expressions had unnecessary parentheses that did not contribute to operator precedence or code clarity. I removed these parentheses to simplify the expressions and improve readability.
   ```java
   // Before
   assert("redirect:/product/list".equals(view));

   // After
   assert "redirect:/product/list".equals(view);
- **UnnecessaryModifier**: Some code elements had unnecessary modifiers. For example, interface methods being declared public or final variables being declared static in a singleton class. I removed these unnecessary modifiers to clean up the code and adhere to best practices.
   ```java
   // Before
   public Product update(Product product);

   // After
   Product update(Product product);

2. Indeed, my current use of GitHub Actions for CI/CD workflows effectively meets the definitions of Continuous Integration and Continuous Deployment. With Continuous Integration, every time i make a commit, it triggers a workflow. This workflow automatically builds and tests the code, making sure that any new changes i make work well with the existing code, reducing the chance of problems when combining the changes.
For Continuous Deployment, as soon as all tests pass, the codebase is automatically sent to the production environment. This means that any new features, updates, or fixes i create are quickly and efficiently delivered to the end-users. This not only speeds up the delivery of new features to users but also cuts down on the time and effort usually needed for manual deployment processes.

------------

# Tutorial 3

1. Here are the SOLID principles that I have applied to this project:

- **_Single Responsibility Principle (SRP)_**: Previously, the `CarController` class violated the SRP by extending `ProductController`, resulting in a dual responsibility. This meant that `CarController` had the task of handling mapping related to both `Car` and `Product` objects due to the inheritance from `ProductController`. 
To fix this, I have separated these two controllers. The `ProductController` class is now solely responsible for managing the mapping for `Product`, and the `CarController` class is solely responsible for managing the mapping for `Car`.
Additionally, the `ProductRepository` and `CarRepository` classes were previously responsible for both data access operations and generating UUIDs for `Product` and `Car` entities. This also violated the SRP. To fix this, I moved the UUID generation to the `ProductService` and `CarService` classes, ensuring that each class now has a single responsibility and doesn't mix different tasks.
- **_Open-Closed Principle (OCP)_**: The code has been written in a way that it is open for extension but closed for modification. This means that new functionality can be added by creating new classes, without needing to modify existing classes. For example, adding a new mapping for a new entity can be done by creating a new controller for the new entity, without needing to modify the existing controllers.

  In addition, although I'm not entirely sure if this counts, I made a change in the `update` method in `CarRepository`. I replaced:
  ```
  car.setCarName(updatedCar.getCarName());
  car.setCarColor(updatedCar.getCarColor());
  car.setCarQuantity(updatedCar.getCarQuantity()); 
  ```
  with:
  ```
  productData.set(i, newProduct);
  ```
  This change means that if a new attribute is added to the `Car` object, there's no need to add it to the `update` method.
- **_Liskov Substitution Principle (LSP)_**: This principle is all about ensuring that a child class can substitute its parent class without causing any unexpected behavior. In this project, as there are no inheritance relationships in the code, the principle has been fulfilled by default.
- **_Interface Segregation Principle (ISP)_**: This principle ensures that implementing classes are only burdened with methods pertinent to their functionality. In this project, the principle is maintained by creating small, specific interfaces so that a class uses only the methods that are required for it. For example, `CarService` is implemented by `CarServiceImpl` and `ProductService` is implemented by `ProductServiceImpl`. Each of these interfaces contains only the methods relevant to the respective classes, ensuring that the classes are not burdened with unnecessary methods. 
- **_Dependency Inversions Principle (DIP)_**: - **_Dependency Inversion Principle (DIP)_**: This principle emphasizes the use of abstraction, such as abstract classes and interfaces, over concrete implementations. It suggests that high-level modules should not rely on low-level modules directly; instead, both should depend on abstraction. In the `CarController`, I replaced `private CarServiceImpl carService` with `private CarService carService`. This change ensures that `CarController` depends on the `CarService` abstraction rather than the concrete `CarServiceImpl` implementation.

2. Advantages of Applying SOLID Principles:
- **_Maintainability_**: By applying the SOLID principles, code becomes more modular and easier to maintain. Each class has a single responsibility, making it simpler to understand, modify, and debug. For example, the `Car` class is responsible only for car-related functionality, and the `Product` class is responsible for product-related functionality.
- **_Flexibility and Extensibility_**: Following SOLID principles allows for easier extension of functionality without modifying existing code. New features can be added by creating new classes or extending existing ones. For example, if we need to add new mapping related to a new entity, we can simply add a new controller for that entity without modifying the existing controllers.
- **_Readability and Understandability_**: Code that follows SOLID principles tends to be more readable and understandable. It’s easier to understand the functionality of each class and its purpose. For example, the purpose of the `Car` class is clearly different from the purpose of the `Product` class, making it easier to understand the role of each class in the system.

3. Disadvantages of Not Applying SOLID Principles:

- **Increased Complexity**: Without applying the SOLID principles, classes could become overly complex, handling multiple responsibilities. For example, if the `CarController` class, which extends `ProductController`, were also responsible for managing the mapping for both `Product` and `Car`, it would be harder to understand, modify, and debug.
- **Reduced Flexibility**: The code could become rigid and difficult to extend. Any new functionality could require modifying existing code, which could introduce bugs and make the code harder to maintain. For example, without applying the SOLID principles, adding a new mapping for a new entity might require modifying existing controller classes.
- **Tight Coupling**: The classes in the project could become tightly coupled, making it difficult to change one part of the code without affecting others. This would reduce the flexibility and maintainability of the code. For example, without using interfaces, the `@Autowired` annotation creates tight coupling between controllers (`CarController` and `ProductController`) and their corresponding services. 


------------

# Tutorial 4

### Reflection 1

1. **Test-Driven Development Workflow**: Based on Percival's proposed self-reflective questions, I found the TDD flow to be extremely useful. It helped me to think about the functionality of my code before writing it, which led to more robust and reliable code. However, I found it challenging to write tests for complex functionality. Next time, I plan to break down complex features into smaller, more manageable parts and write tests for these smaller parts.

2. **F.I.R.S.T Principle**: In the tutorial, I created unit tests that mostly followed the F.I.R.S.T principles. My tests were fast, isolated, and repeatable. Each unit test was isolated from the others, so they did not affect each other's results. However, I noticed that my tests were not written in a timely manner. I wrote tests after implementing the code. Next time, I will ensure that I write tests before implementing the code to fully embrace the principles of Test-Driven Development.