package com.pluralsight;

import java.util.Scanner;

public class FinancialCalculators {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Financial Calculators ===");
        System.out.println("1) Mortgage calculator");
        System.out.println("2) CD future value calculator");
        System.out.println("3) Annuity present value calculator");
        System.out.print("Choose a calculator (1-3): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> mortgageCalculator(scanner);
            case 2 -> cdCalculator(scanner);
            case 3 -> annuityCalculator(scanner);
            default -> System.out.println("Please choose 1, 2, or 3.");
        }

        scanner.close();
    }

    // ---------- Calculator 1: Mortgage ----------

    public static void mortgageCalculator(Scanner scanner) {
        System.out.print("Loan amount: $");
        double principal = scanner.nextDouble();
        System.out.print("Annual interest rate (e.g. 7.625): ");
        double annualRate = scanner.nextDouble();
        System.out.print("Loan length in years: ");
        int years = scanner.nextInt();

        double monthlyPayment = calculateMonthlyPayment(principal, annualRate, years);
        double totalInterest = calculateTotalInterest(monthlyPayment, principal, years);

        System.out.printf("%nMonthly payment: $%.2f%n", monthlyPayment);
        System.out.printf("Total interest: $%.2f%n", totalInterest);
    }

    public static double calculateMonthlyPayment(double principal, double annualRate, int years) {
        double monthlyRate = (annualRate / 100) / 12;
        int numPayments = years * 12;
        return principal * (monthlyRate * Math.pow(1 + monthlyRate, numPayments))
                / (Math.pow(1 + monthlyRate, numPayments) - 1);
    }

    public static double calculateTotalInterest(double monthlyPayment, double principal, int years) {
        return (monthlyPayment * years * 12) - principal;
    }

    // ---------- Calculator 2: CD future value ----------

    public static void cdCalculator(Scanner scanner) {
        System.out.print("Deposit amount: $");
        double deposit = scanner.nextDouble();
        System.out.print("Annual interest rate (e.g. 1.75): ");
        double annualRate = scanner.nextDouble();
        System.out.print("Number of years: ");
        int years = scanner.nextInt();

        double futureValue = calculateFutureValue(deposit, annualRate, years);
        double interestEarned = futureValue - deposit;

        System.out.printf("%nEnding balance: $%.2f%n", futureValue);
        System.out.printf("Interest earned: $%.2f%n", interestEarned);
    }

    public static double calculateFutureValue(double deposit, double annualRate, int years) {
        double rate = annualRate / 100;
        int totalDays = 365 * years;
        return deposit * Math.pow(1 + (rate / 365), totalDays);
    }

    // ---------- Calculator 3: Annuity present value ----------

    public static void annuityCalculator(Scanner scanner) {
        System.out.print("Monthly payout: $");
        double monthlyPayout = scanner.nextDouble();
        System.out.print("Expected annual interest rate (e.g. 2.5): ");
        double annualRate = scanner.nextDouble();
        System.out.print("Number of years: ");
        int years = scanner.nextInt();

        double presentValue = calculatePresentValue(monthlyPayout, annualRate, years);

        System.out.printf("%nYou would need to invest $%.2f today.%n", presentValue);
    }

    public static double calculatePresentValue(double monthlyPayout, double annualRate, int years) {
        double monthlyRate = (annualRate / 100) / 12;
        int numPayments = years * 12;
        return monthlyPayout * (1 - Math.pow(1 + monthlyRate, -numPayments)) / monthlyRate;
    }
}