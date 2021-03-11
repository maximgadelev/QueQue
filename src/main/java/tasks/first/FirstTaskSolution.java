package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        boolean[] isVisited = new boolean[adjacencyMatrix.length];
        deque.add(startIndex);
        isVisited[startIndex] = true;
        ArrayList<Integer> elements = new ArrayList<>();
        while (!deque.isEmpty()) {
            elements.add(deque.getFirst());
            int firstElementInTheDeque = deque.getFirst();
            deque.removeFirst();
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[firstElementInTheDeque][i] == true && (isVisited[i] == false)) {
                    deque.add(i);
                    isVisited[i] = true;
                }
            }
        }
        return elements.toString();
    }

    @Override
    public Boolean validateBrackets(String s) {
        if (s == null) {
            return false;
        }
        String openBrackets = "([{";
        String closeBrakets = ")]}";
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char charElement = s.charAt(i);
            String stringElement = Character.toString(charElement);
            int intElement = (int) charElement;
            if (openBrackets.contains(stringElement)) {
                stack.push(charElement);
            } else {
                if (intElement > 50) {
                    intElement -= 2;
                } else {
                    intElement -= 1;
                }
            }
            if (closeBrakets.contains(stringElement) && stack.isEmpty()) {
                return false;
            }
            if (closeBrakets.contains(stringElement) && (int) stack.peek() == intElement) {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
        ArrayDeque<String> elements = new ArrayDeque<>();
        String[] splitString = s.split(" ");
        String operator;
        String operators = "+*-/";
        for (int i = 0; i < splitString.length; i++) {
            if (!operators.contains(splitString[i])) {
                elements.push(splitString[i]);
                continue;
            } else {
                try {
                    operator = splitString[i];
                    int operandOne = Integer.parseInt(elements.pop());
                    int operandTwo = Integer.parseInt(elements.pop());
                    switch (operator) {
                        case "+":
                            elements.push(String.valueOf(operandOne + operandTwo));
                            break;
                        case "-":
                            elements.push(String.valueOf(operandOne - operandTwo));
                            break;
                        case "*":
                            elements.push(String.valueOf(operandOne * operandTwo));
                            break;
                        case "/":
                            elements.push(String.valueOf(operandOne / operandTwo));
                            break;
                        default:
                            continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid string");
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Invalid String");
                    break;
                }
            }
        }
        try {
            return Long.parseLong(elements.peek());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
