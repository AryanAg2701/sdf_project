
## Introduction

This project features a Java tool called MyInfArith, which performs arithmetic on extremely large numbers, beyond the limits of standard Java types. It supports operations like addition, subtraction, multiplication, and division for both integers and floating-point numbers of any size. The numbers are stored as strings within the program. 

### Project Layout

Key files in the project include:
- **MyInfArith.java**: The main program that handles user inputs and calculations.
- **AInteger.java**: A class for arithmetic with large integers.
- **AFloat.java**: A class for arithmetic with large floating-point numbers.
- **build.xml**: An Ant script to compile, package, and run the code.
- **run_build.py**: A Python script to simplify running the Ant commands.

## Building and Running

### Using Ant
I use Apache Ant to automate everything. The `build.xml` file has these targets:

- `clean`: Deletes previous build files.
- `compile`: Compiles all the Java files.
- `jar`: Packages the compiled classes into `dist/MyInfArith.jar`.
- `run`: Runs the program with your inputs.

To build and run in one step, just type:

ant run -Dargs="int add 123 456"

### Using the Python Script
If you prefer Python, you can also do:

python3 run_build.py --args "float mul 3.14 2.5"

This runs clean, compile, jar, and run for you.

## Using the Program

### Using Java
First, compile the Java source files directly:

javac arbitraryarithmetic/*.java
javac MyInfArith/*.java

Then run the main class from the build directory without a JAR:

java MyInfArith <type> <operation> <num1> <num2>

### Using Python
If you prefer to use Python to run the program, you can use the following:

python3 run_build.py --args "<type> <operation> <num1> <num2>"

This will automatically compile, package, and run the program with your given inputs.
