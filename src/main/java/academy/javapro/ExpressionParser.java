package academy.javapro;

import java.util.StringBuilder;

class ExpressionParser {
    private final String input;
    private int position;

    public ExpressionParser(String input) {
        this.input = input;
        this.position = 0;
    }

    // expr → expr + term
    public double parseExpression() {
        double result = parseTerm();
        while (position < input.length() && input.charAt(position) == '+') {
            position++;
            result += parseTerm();
        }
        return result;
        //throw new UnsupportedOperationException("Implement parseExpression");
    }

    // term → term * factor
    private double parseTerm() {
        double result = parseFactor();
        while (position < input.length() && input.charAt(position) == '*') {
            position++;
            result *= parseFactor();
        }
        return result;
       // throw new UnsupportedOperationException("Implement parseTerm");
    }

    // factor → ( expr )
    private double parseFactor() {
    
        if (input.charAt(position) == '(' && position < input.length()) {
            position++;
            double result = parseExpression();
            if (input.charAt(position) == ')' && position < input.length()) {
                position++;
            }
            return result;
        } else {
           return parseNumber();
        }

       // throw new UnsupportedOperationException("Implement parseFactor");
    }

    // Parse a numeric value
    private double parseNumber() {
        StringBuilder digits = new StringBuilder();
        while (this.position < digits.length() && 
        Character.isDigit(this.input.charAt(position))  || input.charAt(position) == '.') {
            digits.append(this.input.charAt(position));
            this.position++;
        }
      //  throw new UnsupportedOperationException("Implement parseNumber");

        return Double.parseDouble(digits.toString());
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "2 + 3 * (4 + 5)",    // Complex expression with parentheses
                "2 + 3 * 4",          // Basic arithmetic with precedence
                "(2 + 3) * 4",        // Parentheses changing precedence
                "2 * (3 + 4) * (5 + 6)", // Multiple parentheses
                "1.5 + 2.5 * 3"       // Decimal numbers
        };

        // Process each test case
        for (String expression : testCases) {
            System.out.println("\nTest Case: " + expression);
            try {
                ExpressionParser parser = new ExpressionParser(expression.replaceAll("\\s+", "")); // Remove spaces
                double result = parser.parseExpression();
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}