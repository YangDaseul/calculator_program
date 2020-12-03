package calculator;

import java.util.Scanner;
import java.util.Stack;

public class Priority {

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

	public static int opCheck(String s) { // 연산자 순위
		int r = 0;

		if ("+".equals(s) || "-".equals(s)) {
			r = 1;
		} else if ("*".equals(s) || "/".equals(s)) {
			r = 2;
		}
		return r;
	}
	
	public static boolean numCheck(char c) {
		if(c == '+' || c == '-' || c == '*' || c == '/') {
			return false;
		}else
			return true;
	}

	public static void main(String[] args) {
		Stack<Double> stack1 = new Stack<Double>();
		Stack<Character> opStack = new Stack<Character>();

		String data;
		char tmp;
		char preCheck, nextCheck;
		char op;
		double dataNum, num1, num2, result = 0;

		Scanner scan = new Scanner(System.in);

		System.out.print("입력: ");
		data = scan.nextLine();

		for (int i = 0; i < data.length(); i++) {

			tmp = data.charAt(i);

			if (Character.isDigit(tmp)) { // 숫자라면
			
				preCheck = data.charAt(i-1);
				
				if(numCheck(preCheck) == false) { // tmp 이전이 연산자라면
					stack1.push(Double.valueOf(Character.toString(tmp)));
				
				}else { //숫자라면
					dataNum = stack1.pop();
					dataNum = (dataNum * 10) + Double.valueOf(Character.toString(tmp));

					stack1.push(dataNum);
				}
				

			

			} else if (tmp == '+' || tmp == '-' || tmp == '*' || tmp == '/') { // 연산자라면


				if (opStack.isEmpty()) {
					// opStack이 비어있으면 tmp 저장
					opStack.push(tmp);
				} else {

					while (!opStack.isEmpty()) {
	
						op = opStack.pop();
	
						if (opCheck(String.valueOf(op)) < opCheck(String.valueOf(tmp))) {
	
							// op push
							opStack.push(op);
							// tmp push
							opStack.push(tmp);
							
							break;
	
						} else if (opCheck(String.valueOf(op)) >= opCheck(String.valueOf(tmp))) {
							// tmp 대기
							// op로 계산
	
							// stack1에서 숫자 두개 꺼내서 num1, num2에 저장한다.
							// op 연산 후 stack1에 저장한다.
							num1 = stack1.pop();
							num2 = stack1.pop();
	
							result = calculate(num1, num2, op, result);
							System.out.println("result: " + result);
	
							stack1.push(result);
							
						}
					}if(opStack.isEmpty()) {
						opStack.push(tmp);
					}
					
					
				}
				
			} else if (tmp == '=') {
				while (!opStack.isEmpty()) {
					op = opStack.pop();
					num1 = stack1.pop();
					num2 = stack1.pop();

					result = calculate(num1, num2, op, result);

					stack1.push(result);
				}
				result = stack1.pop();

				System.out.println("최종 결과: " + result);
			}
		}

	}
}
