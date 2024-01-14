import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);

        try {
            System.out.println("Input:");
            String userExpression = userInputScanner.nextLine();
            int result = evaluateExpression(userExpression);
            System.out.println("Output: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            userInputScanner.close();
        }
    }

    private static int evaluateExpression(String expression) throws Exception {
        String[] tokens = expression.split("\\s+");

        if (tokens.length != 3 && tokens.length != 5) {
            throw new IllegalArgumentException("Invalid number of operands and operators.");
        }

        int operandA = validateAndParse(tokens[0]);
        int operandB = validateAndParse(tokens[2]);
        int operandC = tokens.length == 5 ? validateAndParse(tokens[4]) : 0;

        char operator = tokens[1].charAt(0);
        switch (operator) {
            case '+':
                return operandA + operandB + operandC;
            case '-':
                return tokens.length == 3 ? operandA - operandB : operandA - operandB + operandC;
            case '*':
                return operandA * operandB - operandC;
            case '/':
                return operandA / operandB * operandC;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static int validateAndParse(String token) throws Exception {
        int parsedNumber = Integer.parseInt(token);
        if (parsedNumber < 1 || parsedNumber > 10) {
            throw new IllegalArgumentException("Numbers must be between 1 and 10 inclusive.");
        }
        return parsedNumber;
    }
}
