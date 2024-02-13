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

# Tutorial 2
