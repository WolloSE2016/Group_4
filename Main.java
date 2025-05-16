/*

package exam;




import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Main {  // Fixed class name to follow Java conventions
    
    public static int i;
    
     static int f(int[] arr) {
        int X = 0; // Sum of odd numbers
        int Y = 0; // Sum of even numbers

        for (int num : arr) {
            if (num % 2 == 0) {
                Y += num;
            } else {
                X += num;
            }
        }

        return X - Y;
    }
    
    
    
    
    static int isStepped(int[] a) {
        if (a == null || a.length == 0) return 0;

        int count = 1;

        for (int i = 1; i < a.length; i++) {
            // Check if array is in ascending (non-decreasing) order
            if (a[i] < a[i - 1]) {
                return 0;
            }

            if (a[i] == a[i - 1]) {
                count++;
            } else {
                if (count < 3) return 0; // Previous number didn't appear at least 3 times
                count = 1; // Reset count for new number
            }
        }

        // Final group check
        if (count < 3) return 0;

        return 1;
    

                
              

}
    
    
    
    // Static method for case 4
    public static int findLargestValue(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty");
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Player class for case 8
    static class Player {
        private String name;
        private int score;

        public Player() {
            this.name = "";
            this.score = 0;
        }

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Player [name=" + name + ", score=" + score + "]";
        }
    }

    //counter class for case 9 and 10
    static class Counter {
        private int value;

        public Counter() {
            this.value = 0;
        }

        public void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }
    }

    //employee class for case 11
    static class Employee {
        String lastName;
        String firstName;
        double hourlyWage;
        int yearsWithCompany;
    }

    //PairOfDice class for case 12
    static class PairOfDice {
        private int die1;
        private int die2;
        private Random random;

        public PairOfDice() {
            random = new Random();
            roll(); // roll once when object is created
        }

        // Rolls both dice
        public void roll() {
            die1 = random.nextInt(6) + 1; // 1 to 6
            die2 = random.nextInt(6) + 1;
        }

        // Getter methods
        public int getDie1() {
            return die1;
        }

        public int getDie2() {
            return die2;
        }

        public int getTotal() {
            return die1 + die2;
        }
    }
    // use question 13 or case 13


    public static int closestFibonacci(int n) {
        if (n < 1) {
            return 0;
        }

        int a = 1;
        int b = 1;

        while (true) {
            int next = a + b;
            if (next > n) {
                break;  // Exit loop when next Fibonacci number is greater than n
            }
            a = b;
            b = next;
        }

        return b;
    }

    // Methods for case 14
    public static int isPrimeHappy(int n) {
        if (n <= 2) {
            return 0; // No primes less than 2
        }

        int sum = 0;
        boolean hasPrimes = false;

        // Calculate sum of all primes less than n
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                sum += i;
                hasPrimes = true;
            }
        }

        // Check if there are primes and sum is divisible by n
        return (hasPrimes && sum % n == 0) ? 1 : 0;
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static void printExplanation(int n, int result) {
        if (n <= 2) {
            System.out.println("Because there are no primes less than " + n + ".");
            return;
        }

System.out.print("Because ");
        if (result == 1) {
            // Print primes and sum
            System.out.print(getPrimesList(n) + " are the primes less than " + n +
                    ", their sum is " + sumPrimes(n) +
                    " and " + n + " evenly divides " + sumPrimes(n));
        } else {
            // Print primes and sum
            System.out.print(getPrimesList(n) + " are the primes less than " + n +
                    ", their sum is " + sumPrimes(n) +
                    " and " + n + " does not evenly divide " + sumPrimes(n));
        }
        System.out.println();
    }

    private static String getPrimesList(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(i);
            }
        }
        return sb.toString();
    }

    private static int sumPrimes(int n) {
        int sum = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }
    // case 15

    public static int ff(int[] a) {
        int sumOdd = 0;
        int sumEven = 0;

        for (int num : a) {
            if (num % 2 != 0) {
                sumOdd += num;  // Sum of odd numbers
            } else {
                sumEven += num;  // Sum of even numbers
            }
        }
        return sumOdd - sumEven;
    }

    public static void main(String[] args) { 
        
    
        do {
 
    System.out.println("choose the number to see");
        Scanner scanner = new Scanner(System.in);
        int choice;


        System.out.println("1: What is the main difference between a while loop and a do...while loop?");
        System.out.println("2: Write for loop that will print out all the multiples of 3 from 3 to 36");
        System.out.println("3: Show the exact output that would be produced by the following main() routine:");
        System.out.println("4: Find largest value in an array of ints");
        System.out.println("5: Find average of non-zero numbers in an array");
        System.out.println("6: What is a constructor? What is the purpose of a constructor in a class?");
        System.out.println("7: What is meant by the terms instance variable and instance method?");
        System.out.println("8: Demonstrate the modified Player class with getters and setters");
        System.out.println("9: For this problem, you should write a very simple but complete class. The class \n" +
                "represents a counter that counts 0, 1, 2, 3, 4 ... The name of the class should be \n" +
                "Counter. It has one private instance variable representing the value of the counter. It \n" +
                "has two instance methods: increment() adds one to the counter value, and getValue() \n" +
                "returns the current counter value. Write a complete definition for the class, Counter ");
        System.out.print("10: This problem uses the Counter class from the previous question. The following \n" +
                "program segment is meant to simulate tossing a coin 100 times. It should use two \n" +
                "Counter objects, headCount and tailCount, to count the number of heads and the \n" +
                "number of tails.");
        System.out.print("11: Suppose that a class, Employee, is defined as follows: \n" +
                "class Employee { \n" +
                "String lastName; \n" +
                "String firstName; \n" +
                "double hourlyWage; \n" +
                "int yearsWithCompany; \n" +
                " } \n" +
                "Suppose that data about 100 employees is already stored in an array: \n" +
                "Employee[] employeeData = new Employee[100]; \n" +
                "Write a code segment that will output the first name, last name, and hourly wage of \n" +
                "each employee who has been with the company for 20 years or more. ");


System.out.print("\n12: Sure! Here's a version of the PairOfDice class where the die1 and die2 instance variables are private, and I've included getter methods to allow access to their values. " +
                "I will also provide a short program to test how many times the dice are rolled before the total is equal to two. ");
        System.out.print("\n13: Write a Java method int closestFibonacci(int n) that returns the largest Fibonacci number less than or equal to n. The Fibonacci sequence starts with 1, 1, and each number afterward is the sum of the two preceding ones. If n is less than 1, return 0. Do not use recursion.\n" +
                "\n" +
                "Examples:\n" +
                "\n" +
                "closestFibonacci(12) → 8\n" +
                "\n" +
                "closestFibonacci(33) → 21\n" +
                "\n" +
                "closestFibonacci(34) → 34\n" +
                "\n" +
                "closestFibonacci(0) → 0\n" +
                "\n ");
        System.out.print("\n14: Define a function isPrimeHappy(int n) that returns 1 if the number n is prime-happy, and 0 otherwise. A number n is considered prime-happy if:\n" +
                "\n" +
                "1.There is at least one prime less than n.\n" +
                "\n" +
                "2.The sum of all primes less than n is divisible by n.");
        System.out.println("\n15: Sum of odds minus sum of evens");
        
        System.out.print("\nChoose an option: ");

        choice = scanner.nextInt();
       
        switch (choice) {
            case 1:
                System.out.println("\nMain difference between while and do...while loops:");
                System.out.println("1. while Loop:");
                System.out.println("   - Condition is checked first before executing the loop body");
                System.out.println("   - If the condition is false initially, the loop body never executes");
                System.out.println("   - Syntax:");
                System.out.println("     while (condition) {");
                System.out.println("         // Loop body");
                System.out.println("     }");
                System.out.println("\n2. do...while Loop:");
                System.out.println("   - Loop body executes at least once before checking the condition");
                System.out.println("   - The condition is evaluated after the first execution");
                System.out.println("   - Syntax:");
                System.out.println("     do {");
                System.out.println("         // Loop body");
                System.out.println("     } while (condition);");
                break;

            case 2:
                System.out.println("\nMultiples of 3 from 3 to 36:");
                for (int i = 3; i <= 36; i += 3) {
                    if (i % 3 == 0) {
                        System.out.print(i + " ");  // Removed redundant condition check
                    }
                }
                System.out.println();
                break;

            case 3:
                System.out.println("\nExact output of the given routine:");
                int N = 1;
                while (N <= 32) {
                    N = 2 * N;
                    System.out.println(N);
                }
                break;

            case 4:
                int[] testArray = {5, 8, 2, 10, 3, 1};
                System.out.println("\nLargest value in array: " + findLargestValue(testArray));
                break;

            case 5:
                double[] A = new double[20];

                // Read 20 values from keyboard
                System.out.println("Enter 20 double values:");
                for (int i = 0; i < A.length; i++) {
                    System.out.print("Value " + (i + 1) + ": ");
                    A[i] = scanner.nextDouble();
                }

                double sum = 0.0;
                int count = 0;


// Calculate sum and count of non-zero numbers
                for (int i = 0; i < A.length; i++) {
                    if (A[i] != 0.0) {
                        sum += A[i];
                        count++;
                    }
                }

                // Display results
                if (count > 0) {
                    double average = sum / count;
                    System.out.println("\nSum of non-zero numbers: " + sum);
                    System.out.println("Average of non-zero numbers: " + average);
                } else {
                    System.out.println("\nNo non-zero numbers were entered.");
                }
                break;
            case 6:
                System.out.println("\nWhat is a Constructor?");
                System.out.println("A constructor is a special method that:");
                System.out.println("   - Has the same name as the class.");
                System.out.println("   - No return type (not even void).");
                System.out.println("   - Runs automatically when an object is created (new ClassName()).");
                System.out.println("\nPurpose of a Constructor:");
                System.out.println("   - Initialize object state (set default values for variables).");
                System.out.println("   - Allocate memory for the object.");
                System.out.println("   - Support multiple initialization ways (constructor overloading).");
                System.out.println("\nExample:");
                System.out.println("class Player {");
                System.out.println("    String name;");
                System.out.println("    int score;");
                System.out.println("    // Constructor");
                System.out.println("    public Player(String name, int score) {");
                System.out.println("        this.name = name;");
                System.out.println("        this.score = score;");
                System.out.println("    }");
                System.out.println("}");
                break;

            case 7:
                System.out.println("\n1. Instance Variable");
                System.out.println("Definition: A variable declared inside a class but outside any method.");
                System.out.println("Purpose: Stores the state (data) of an object.");
                System.out.println("Key Points:");
                System.out.println("   - Each object gets its own copy of instance variables.");
                System.out.println("   - Exists as long as the object exists.");
                System.out.println("   - Also called \"object-level variables\".");
                System.out.println("\nExample:");
                System.out.println("public class Player {");
                System.out.println("    private String name;  // Instance variable");
                System.out.println("    private int score;    // Instance variable");
                System.out.println("}");


System.out.println("\n2. Instance Method");
                System.out.println("Definition: A method defined inside a class that operates on instance variables.");
                System.out.println("Purpose: Defines the behavior (actions) of an object.");
                System.out.println("Key Points:");
                System.out.println("   - Must be called on an object (object.method()).");
                System.out.println("   - Can access and modify instance variables.");
                System.out.println("   - Also called \"object-level methods\".");
                System.out.println("\nExample:");
                System.out.println("public class Player {");
                System.out.println("    private String name;");
                System.out.println("    public void setName(String newName) {");
                System.out.println("        this.name = newName;");
                System.out.println("    }");
                System.out.println("    public String getName() {");
                System.out.println("        return name;");
                System.out.println("    }");
                System.out.println("}");
                break;

            case 8:
                // Demonstrate the Player class
                System.out.println("\nDemonstrating the Player class with getters and setters:");

                // Create a player
                Player player = new Player("John Doe", 100);
                System.out.println("Created player: " + player);

                // Use getters
                System.out.println("\nUsing getters:");
                System.out.println("Player name: " + player.getName());
                System.out.println("Player score: " + player.getScore());

                // Use setters
                System.out.println("\nUsing setters to update values:");
                player.setName("Jane Smith");
                player.setScore(150);
                System.out.println("Updated player: " + player);

                break;
            case 9:
                System.out.println("\nDemonstrating the Counter class:");

                // Create a counter
                Counter counter = new Counter();
                System.out.println("Counter sequence from 0 to 4:");

                // Show initial value (0)
                System.out.println("Current value: " + counter.getValue());

                // Increment and show values up to 4
                while (counter.getValue() < 4) {
                    counter.increment();
                    System.out.println("Current value: " + counter.getValue());
                }
                break;
            case 10:
                // Create counters for heads and tails
                Counter headCount = new Counter();
                Counter tailCount = new Counter();

                // Create random number generator
                Random random = new Random();

                // Simulate 100 coin tosses
                for (int i = 0; i < 100; i++) {
                    // Generate random boolean (true for heads, false for tails)
                    boolean isHeads = random.nextBoolean();

                    if (isHeads) {
                        headCount.increment();
                    } else {
                        tailCount.increment();
                    }
                }

                // Print results
                System.out.println("After 100 coin tosses:");
                System.out.println("Heads: " + headCount.getValue());
                System.out.println("Tails: " + tailCount.getValue());

                // Verify total count
                System.out.println("Total tosses: " + (headCount.getValue() + tailCount.getValue()));
                break;
            case 11:
                Employee[] employeeData = new Employee[100];


                for (int i = 0; i < employeeData.length; i++) {
                    employeeData[i] = new Employee();
                    System.out.println("Enter data for Employee #" + (i + 1));
                    System.out.print("First Name: ");
                    employeeData[i].firstName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    employeeData[i].lastName = scanner.nextLine();
                    System.out.print("Hourly Wage: ");
                    employeeData[i].hourlyWage = Double.parseDouble(scanner.nextLine());

                    System.out.println("-------------------------------------");
                }

                // Output employees with 20+ years of service
                System.out.println("\nEmployees with 20 or more years at the company:");
                for (Employee emp : employeeData) {
                    if (emp.yearsWithCompany >= 20) {
                        System.out.println("First Name: " + emp.firstName);
                        System.out.println("Last Name: " + emp.lastName);
                        System.out.println("Hourly Wage: $" + emp.hourlyWage);
                        System.out.println("-------------------------------------");
                    }
                }

                break;

            case 12:
                PairOfDice dice = new PairOfDice();
                int rollCount = 0;

                // Keep rolling until the total is 2
                while (dice.getTotal() != 2) {
                    dice.roll();
                    rollCount++;
                    System.out.println("Roll " + rollCount + ": die1 = " + dice.getDie1() +
                            ", die2 = " + dice.getDie2() +
                            ", total = " + dice.getTotal());
                }

                System.out.println("It took " + rollCount + " rolls to get a total of 2.");
                break;

            case 13:
                // Test cases
                System.out.println("closestFibonacci(12): " + closestFibonacci(12)); // Expected: 8
                System.out.println("closestFibonacci(33): " + closestFibonacci(33)); // Expected: 21
                System.out.println("closestFibonacci(34): " + closestFibonacci(34)); // Expected: 34
                System.out.println("closestFibonacci(0): " + closestFibonacci(0));   // Expected: 0
                System.out.println("closestFibonacci(1): " + closestFibonacci(1));   // Expected: 1
                break;

            case 14:

                System.out.println("\nPrime-Happy Number Checker");
                System.out.println("Enter numbers to c heck (enter 0 to exit):");

                while (true) {
                    System.out.print("Enter a number: ");
                    int n = scanner.nextInt();

                    if (n == 0) {
                        break; // Exit condition
                    }

                    int result = isPrimeHappy(n);
                    System.out.println("isPrimeHappy(" + n + ") returns " + result);
                    printExplanation(n, result);
                }
                System.out.println("Program ended.");
                break;
                case 15:
              


    
       

        // Prompt user for array size
        System.out.print("Enter the size of elements: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];

        // Read elements from the user
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Call the function and display the result
        int result = ff(arr);
        System.out.println("X - Y = " + result);
        
        scanner.close();
    

    // Function to compute X - Y
    


                
                break;
                
            case 16:
                 


       

        // Prompt user for array size
        System.out.print("Enter the size of elements: ");
        int p = scanner.nextInt();

        int[] a = new int[p];

        // Read elements from the user
        System.out.println("Enter " + p + " integers:");
        for (int i = 0; i < p; i++) {
            a[i] = scanner.nextInt();
        }

        // Call the function and display the result
        int resu = isStepped(a);
        System.out.println("Is the array stepped? " + resu);

      break;
      
      default:
                System.out.println("Invalid choice. Please try again.");
                    
                
        } 
        System.out.println("do you want to continue enter 1 otherwise enter another number");
        
         i = scanner.nextInt();
    // Function to check if the array is stepped
        } while (i == 1 );
}
}


    



*/