package com.mycompany.mavenproject1;

import java.util.Scanner;

public class Mavenproject2 {

    public static int k;
    
    
    public static int sameNumberOfFactors(int n1, int n2) {
        if (n1 < 0 || n2 < 0) {
            return -1;
        }
        int f1 = countFactors(n1);
        int f2 = countFactors(n2);
        return (f1 == f2) ? 1 : 0;
    }

    public static int countFactors(int n) {
        if (n == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                count++;
            }
        }
        return count;
    }

    public static int isPairedN(int[] a, int n) {
        int len = a.length;

        if (len < 2) {
            return 0;
        }

        if (n < 1 || n > 2 * len - 3) {
            return 0;
        }

        for (int i = 0; i < len - 1; i++) {
            int j = n - i;
            if (j <= i || j >= len) {
                continue;
            }
            if (a[i] + a[j] == n) {
                System.out.println("a[" + i + "]" + "+" + "a[" + j + "]" + "=" + n);
                return 1;
            }
        }
        return 0;
    }

    private int count = 0;          
    private double sum = 0;         
    private double squareSum = 0;   
    private double max;             
    private double min;             

    public void enter(double num) {
        sum += num;
        squareSum += num * num;
        count++;

        if (count == 1) {
            max = num;
            min = num;
        } else {
            if (num > max) max = num;
            if (num < min) min = num;
        }
    }

    public int getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getMean() {
        return (count == 0) ? 0 : sum / count;
    }

    public double getStandardDeviation() {
        if (count == 0) return 0;
        double mean = getMean();
        return Math.sqrt(squareSum / count - mean * mean);
    }

    public double getMax() {
        if (count == 0) throw new IllegalStateException("No data entered.");
        return max;
    }

    public double getMin() {
        if (count == 0) throw new IllegalStateException("No data entered.");
        return min;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
         do {
             
             System.out.println("question number 1 ");
             System.out.println("question number 2 ");
             System.out.println("question number 3 ");
             System.out.println("question number 4 ");
             
        System.out.println("for what question do you want to see answers ");
        int z = scanner.nextInt();

        switch (z) {
            case 1:
                Mavenproject2 calc = new Mavenproject2();
                System.out.println("Enter numbers (enter 0 to finish):");

                while (true) {
                    System.out.print("Enter number: ");
                    double num = scanner.nextDouble();
                    if (num == 0) break;
                    calc.enter(num);
                }

                if (calc.getCount() == 0) {
                    System.out.println("No data entered.");
                } else {
                    System.out.println("Count: " + calc.getCount());
                    System.out.println("Sum: " + calc.getSum());
                    System.out.println("Mean: " + calc.getMean());
                    System.out.println("Standard Deviation: " + calc.getStandardDeviation());
                    System.out.println("Maximum: " + calc.getMax());
                    System.out.println("Minimum: " + calc.getMin());
                }
                break;

            case 2:
                System.out.println("how many size do you want to enter");

                int size = scanner.nextInt();

                if (size == 0) {
                    System.out.println("it can not accept 0");
                } else {
                    int[] arr = new int[size];
                    for (int i = 0; i < size; i++) {
                        System.out.println(" enter the number at index " + i);
                        arr[i] = scanner.nextInt();
                    }
                    int temp = arr[0];
                    int second = Integer.MIN_VALUE;

                    for (int i = 0; i < size; i++) {
                        if (arr[i] > temp) {
                            second = temp;
                            temp = arr[i];
                        } else if (arr[i] < temp) {
                            if (arr[i] > second) {
                                second = arr[i];
                            }
                        }
                    }
                    if (second == Integer.MIN_VALUE) {
                        System.out.println("No second largest found.");
                    } else {
                        System.out.println("the second largest number is " + second);
                    }
                }
                break;

            case 3:
                System.out.print("Enter first integer: ");
                int n1 = scanner.nextInt();
                System.out.print("Enter second integer: ");
                int n2 = scanner.nextInt();

                int result = sameNumberOfFactors(n1, n2);

                System.out.println("Result: " + result);

                break;

            case 4:
                System.out.print("Enter number of elements: ");
                int len = scanner.nextInt();
                int[] a = new int[len];

                System.out.println("Enter " + len + " integers (space-separated):");
                for (int t = 0; t < len; t++) {
                    a[t] = scanner.nextInt();
                }

                System.out.print("Enter target N: ");
                int n = scanner.nextInt();

                int ressult = isPairedN(a, n);
                System.out.println("Result: " + ressult);
                break;

            default:
                System.out.println("there is no " + z + " choose 1-4");
                break;
        } System.out.println("do you want to continue enter one otherwise another number");
        k = scanner.nextInt();
        
       }while( k == 1);
        
    }
}


