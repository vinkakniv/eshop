# Advanced Programming - Tutorial 


------------
## Overview

This repository contains the code and reflections for Tutorial in Advanced Programming course by Vinka Alrezky As (NPM: 2206820200)❄️
- [Tutorial 1](#tutorial-1)
- [Tutorial 2](#tutorial-2)

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
