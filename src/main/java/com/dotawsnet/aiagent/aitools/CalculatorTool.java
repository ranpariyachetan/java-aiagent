package com.dotawsnet.aiagent.aitools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTool {
    @Tool(description = "Performs basic arithmetic operations: add, subtract, multiply, divide, mod, and power.")
    public double calculate(
        @ToolParam (description = "The arithmetic operation to perform: add, subtract, multiply, divide, mod, or power.")
        String operation, 
        @ToolParam (description = "The first number for the operation.")
        double a, 
        @ToolParam (description = "The second number for the operation.")
        double b) {

        System.out.println("CalculatorTool called with operation: " + operation + ", a: " + a + ", b: " + b);
        
        switch (operation.toLowerCase()) {
            case "add":
                return a + b;
            case "subtract":
                return a - b;
            case "multiply":
                return a * b;
            case "divide":
                if (b == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero.");
                }
                return a / b;
            case "mod":
                if (b == 0) {
                    throw new IllegalArgumentException("Cannot perform modulus by zero.");
                }
                return a % b;
            case "power":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }
}