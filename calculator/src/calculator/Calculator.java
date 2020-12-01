package calculator;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

	public static double calculate(double num1, char op2, double num2, double result ) {
		if (op2 == '+') {
			result =  num1 + num2;

		} else if (op2 == '-') {
			result = num1 - num2;
			
		} else if (op2 == '*') {
			result = num1 * num2;

		} else if (op2 == '/') {
			result = num1 / num2;
		}
		
		return result;
	}


	public static void main(String[] args) {

		Stack<Double> dataStack = new Stack<Double>();
		Stack<Double> resultStack = new Stack<Double>();
		Stack<Character> operator = new Stack<Character>();

		String data;
		double num1, num2, dataNum = 0, result = 0;
		char op, tmp, op2;

		Scanner scan = new Scanner(System.in);

		System.out.print("입력: ");
		data = scan.nextLine();

		for (int i = 0; i < data.length(); i++) {

			tmp = data.charAt(i); // 숫자
			op = data.charAt(i); // 연산자

			if (Character.isDigit(tmp)) { // tmp가 숫자라면

				if (dataStack.isEmpty()) {
					dataStack.push(Double.valueOf(Character.toString(tmp)));

				} else {// dataStack이 비어있지 않으면
					dataNum = dataStack.pop();
					dataNum = (dataNum * 10) + Double.valueOf(Character.toString(tmp));

					dataStack.push(dataNum);

				}
			} else if (op == '+' || op == '-' || op == '*' || op == '/') { // 연산자라면

				if (!operator.isEmpty()) { // op에 연산자 존재
					num1 = resultStack.pop();
					num2 = dataStack.pop();
					op2 = operator.pop();

					
					result = calculate(num1, op2, num2, result);

					System.out.println("result: " + result);
					System.out.println();

					resultStack.push(result);

					operator.push(op);

				} else { // 연산자 없으면
					operator.push(op);

					dataNum = dataStack.pop();
					resultStack.push(dataNum);
				}

			} else if (op == '=') {

				num1 = resultStack.pop();
				num2 = dataStack.pop();

				op2 = operator.pop();
				
				result = calculate(num1, op2, num2, result);

				System.out.println();
				System.out.println("최종 결과 : " + result);

			}else {
				System.out.println("잘못된 연산입니다.");
				
			}
		}
	}
}