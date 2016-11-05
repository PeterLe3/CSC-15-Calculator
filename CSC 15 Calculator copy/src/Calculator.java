import java.util.Scanner;
/**
 * This program is a simple calculator that uses operands between 0-9 and 
 * supports operations such as +, -, *, /, ^, %. Format of expression is that
 * it that each token seperated by one space and expression ends with ;
 * @author Peter Le | peterle3@csus.edu
 * @version October 2015
 */
public class Calculator {

public static void main(String[] args) {

	Scanner kb = new Scanner(System.in);
	char semi = 0;
	System.out.print("Welcome to my calculator website");
	
	description();
	for (int count = 1; count <= 10; count++) {
	System.out.print("Enter your expression:");
	String exp = kb.nextLine();
	if(!exp.isEmpty()) {
		semi = exp.charAt(exp.length() - 1);
	}
	if (validFormat(exp) && semi != -1) {
	String op1 = token(exp);
	// int operand1 = Integer.parseInt(op1);
	
	exp = removeToken(exp);
	
	String operation = token(exp);
	exp = removeToken(exp);
	
	String op2 = token(exp).substring(0, exp.length() - 1);
	// first we need to check if it is a valid character 0-9
	if (validCharacter(op1) && validCharacter(op2)) {
		int operand1 = Integer.parseInt(op1);
		int operand2 = Integer.parseInt(op2);
	// checking to see if it is a single digit number
		if (validOp(operand1) && validOp(operand2) && validOperation(operation)) {
			int result = calculate(operand1, operand2, operation);
			output(operand1, operand2, operation, result);
		}
	else {
		System.out.println("please check your expression");
		description();
	}
	} 
	else {
		System.out.println("please check your expression");
		description();
	}
	
	}
	else {
		System.out.println("please check your expression.");
		description();
	}
	}
	}
	
	/**
	 * This method checks if the character of the expression 
	 * is a valid operand between 0-9. Prints out Invalid Format
	 * if operand is not a valid character.
	 * @param operand is a token of expression
	 */
	public static boolean validCharacter(String operand) {
		char letter = operand.charAt(0); 
		if(letter>='0' && letter <='9') {
			return true;
		}
		else {
			System.out.println("Invalid Format"); 
			return false;
		}
	}

	/**
	 * Prints out description of the calculator and how it can be used.
	 */
	public static void description() {
		System.out.println();
		System.out.println("********************************");
		System.out.println("Your operands must be between 0-9");
		System.out.println("The operations can only be +, -, *, /, ^, %");
		System.out.println("Each token must be separated with exactly one space");
		System.out.println("No space at the beginning of your expression");
		System.out.println("You must end your expression with a ;");
		System.out.println();
		System.out.println("********************************");
	}
	/**
	 * This method checks if the expression is exactly 5 characters long and
	 * if it ends with a semi-colon. If it is valid, call ValidCharacter method
	 * to check if op1 and op2 are valid operands and 
	 * call validOperation method to check if operator is valid.
	 * 
	 * op1 will be initliazed to first index of expression.
	 * op2 will be inatlized to fourth index of expression.
	 * operator will be initalized to second index of expression.
	 * @param expression is the expression that user enters.
	 * @return true if validFormat of expression.
	 */
	public static boolean validFormat(String expression) {
		String op1= ""; // I initialize op1
		String op2= ""; // I initialize op2
		String operator = ""; // I initialize operator
		if(expression.length() - 1 == 5 && expression.endsWith(";")) {
			op1 = "" + expression.charAt(0); // I set op1 to equal the character at index 0
			op2 = "" + expression.charAt(4); // I set op 2 to equal the character at index 4
			operator = "" + expression.charAt(2); // set operator to character at index 2
			if(validCharacter(op1) && validCharacter(op2)&& validOperation(operator)) {
				return true;
			}			 	
		} else {
			System.out.println("Invalid Format");
			return false;
		}		
		return false;
	}
	
	/**
	 * I take the index of 0, which is the first character, and then I take index of space to 
	 * create a token.
	 * @param s is expression that user inputs.
	 * @return
	 */
	public static String token(String s) {
		String token = "";
		if(s.contains(" ")) {
			token = s.substring(0, s.indexOf(" "));
		} else{
			token = s.substring(0, s.indexOf(";")); // If string doesn't contain space, It will return first token before semicolon
			
		}
		return token;
			
			
	}
	
	
	public static String removeToken(String s) {
		String str = "";
		if (s.contains(" ")) {
			String token = s.substring(0, s.indexOf(" ")+ 1);	 // I want the token to include the space
			str = s.replace(token, ""); 						 // I'm replacing the first token and space with empty string
		}
		else { 
			str = s.replace(";",""); 							 //If String s doesn't contain space,  replace ; with empty string
		}
		return str; 												 // Should return + 7;
	}
	
	/**
	 * This method checks if a valid Operator was used.
	 * e.g. +, -, *, /, ^, %
	 * @param num is the operand of expression
	 * @return True if valid operand, Invalid Format if false.
	 */
	public static boolean validOp(int num) {
		if(num>=0 && num<=9)	
			return true;
		else {
			System.out.println("Invalid Format");	
		return false;
		}
	}
	
	/**
	 * In this method, I used switch case to check if it was
	 * a valid operation. Prints out invalid format if isn't valid.
	 * @param operation is the operation of experssion stored as string.
	 * @return If one of the case is valid, return true, if not, return false.
	 */
	public static boolean validOperation(String operation) {
	
	boolean valid = false;
	
	switch(operation) {
	case "+":
	valid = true;
	break;
	case "-":
	valid = true;
	break;
	
	case "%":
	valid = true;
	break;
	
	case "/":
	valid = true;
	break;
	
	case "^":
	valid = true;
	break;
	case "*":
	valid = true;
	break;
	
	default:
	System.out.println("Invalid Operation");
	valid = false;
	break;
	
	
	}
	
	return valid;
	}
	
	/**
	 * This method calculates the value of expression and stores it into 
	 * int result. If second operand is 0 and the operation is / or %, immiediately
	 * return 0 so that it does not go through the cases and cause a crash.
	 * @param a is first operand
	 * @param b is second operand
	 * @param operation is opeartion of expression
	 * @return
	 */
	public static int calculate(int a, int b, String operation) {
	int result = 0;
	if(b==0 && operation.equals("/") || b==0 && operation.equals("%"))
		return result;
	switch(operation){
	case "+": //if the string operation is "+" result = a + b and it breaks
	result = a + b;
	break;
	case "-":
	result = a - b;
	break;
	case "*":
	result = a * b;
	break;
	case "/":
	result = a / b;
	break;
	case "%":
	result = a % b;
	break;
	case "^":
	result = (int)Math.pow(a,b);
	break;
	default:
	break;
	}
	return result;
	}
	
	/**
	 * Convert the operand into String representation.
	 * @param op is the operands of expression
	 * @return String represtion of the operand.
	 */
	public static String operandDescription(int op) {
	switch(op) { 			// need to switch int to string
	case 0:					// If int is 0, returns "Zero". Return acts as a break.
	return "Zero";
	case 1:
	return "One";
	case 2:
	return "Two";
	case 3:
	return "Three";
	case 4:
	return "Four";
	case 5:
	return "Five";
	case 6:
	return "Six";
	case 7:
	return "Seven";
	case 8:
	return "Eight";
	case 9:
	return "Nine";
	default:
	break;
	}
	return "";
	}
	
	/**
	 * This method converts the String operator into the word represantion
	 * of the operator.
	 * @param operator String operator of expression such as + or -.
	 * @return String repesentation of the expression such as plus or minus.
	 */
	public static String operationDescription(String operator) {
	if(operator.equals("+"))  
	return " Plus ";			//If the String operator is +, should return " Plus "
	if(operator.equals("-"))
	return " Minus ";
	if(operator.equals("*"))
	return " Mutiply ";
	if(operator.equals("/"))
	return " Divide ";
	if(operator.equals("%"))
	return " Modulus ";
	if(operator.equals("^"))
	return " To The Power Of ";
	return "";
	}
	
	/**
	 * This method prints out the word representation of the expression. If attempt to divide
	 * or modulus by 0, which print out that value is UNDEFINED.
	 * @param op1 is first operator
	 * @param op2 is second operator
	 * @param operation is operation of expression
	 * @param result is the value of expression
	 */
	public static void output(int op1, int op2, String operation, int result) {					
	   String operand1 = operandDescription(op1);	//I call operandDescrition to check if op1 and op2 is valid operand
	   String operand2 = operandDescription(op2);	
	   String operator= operationDescription(operation); // I call operationDescrition to check if operatino is valid
	   	if(op2==0 && operation.equals("/") || op2==0 && operation.equals("%")) {	
	   		System.out.println(operand1 + operator + operand2 + " = UNDEFINED"); // Anything divided or modulus 0 should print the inputed expression = UNDEFINED
	   	}
	   	else {
	   		System.out.println(operand1 + operator + operand2 + " = " + result);
	   	}
	
	
	}
}





