package calculator;

import java.util.Scanner;
import java.util.Stack;

public class priority {

	public static double calculate(double num1, double num2, char op, double result) {
		if (op == '+') {
			result = num2 + num1;

		} else if (op == '-') {
			result = num2 - num1;

		} else if (op == '*') {
			result = num2 * num1;

		} else if (op == '/') {
			result = num2 / num1;

		}
		return result;

	}

	public static void main(String[] args) {
		Stack<Double> dataStack = new Stack<Double>();
		Stack<Character> opStack = new Stack<Character>();

		String data;
		char tmp;
		char op;
		double dataNum, num1, num2, result = 0;

		Scanner scan = new Scanner(System.in);

		System.out.print("입력: ");
		data = scan.nextLine();

		for (int i = 0; i < data.length(); i++) {

			tmp = data.charAt(i);

			if (Character.isDigit(tmp)) { // 숫자라면

				dataStack.push(Double.valueOf(Character.toString(tmp)));

			} else if (tmp == '+' || tmp == '-' || tmp == '*' || tmp == '/' || tmp == '=') { // 연산자라면

				if (!opStack.isEmpty()) {

					op = opStack.pop();

					if ((op == '*' || op == '/' && tmp == '+' || tmp == '-')) {
						num1 = dataStack.pop();
						num2 = dataStack.pop();

						System.out.println("num1: " + num1);
						System.out.println("num2: " + num2);

						result = calculate(num1, num2, op, result);

						System.out.println("result: " + result);

						dataStack.push(result);

					} else if (op == '+' || op == '-' && tmp == '*' || tmp == '/') { // op < tmp
						opStack.push(op);
						opStack.push(tmp);

					}

				} else { // opStack.isEmpty()

					opStack.push(tmp);

				}
			}
		}

	}

}
