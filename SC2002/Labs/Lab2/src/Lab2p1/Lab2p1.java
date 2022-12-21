package Lab2p1;

import java.util.Scanner;
public class Lab2p1 {
	public static void main(String[] args)
 {
int choice;
Scanner sc = new Scanner(System.in);
do {
System.out.println("Perform the following methods:");
System.out.println("1: miltiplication test");
System.out.println("2: quotient using division by subtraction");
System.out.println("3: remainder using division by subtraction");
System.out.println("4: count the number of digits");
System.out.println("5: position of a digit");
System.out.println("6: extract all odd digits");
System.out.println("7: quit");
choice = sc.nextInt();
switch (choice) {
 case 1: /* add mulTest() call */
	 mulTest();
	 break;
 case 2: /* add divide() call */
	 int m, n;
	 System.out.println("m= ");
	 m = sc.nextInt();
	 System.out.println("n= ");
	 n = sc.nextInt();
	 int answer = divide(m,n);
	 System.out.println(answer);
	 
	 break;
 case 3: /* add modulus() call */
	 System.out.println("m= ");
	 m = sc.nextInt();
	 System.out.println("n= ");
	 n = sc.nextInt();
	 System.out.println(modulus(m,n));
	 break;
 case 4: /* add countDigits() call */
	 System.out.println("n= ");
	 n = sc.nextInt();
	 int digits = countDigits(n);
	 if (digits == 0) {
		 System.out.println("Error");
	 }
	 else {
		 System.out.println("Count= " + digits);
	 }

	 break;
 case 5: /* add position() call */
	 System.out.println("n= ");
	 n = sc.nextInt();
	 System.out.println("digit= ");
	 m = sc.nextInt();
	 System.out.println("position= "+ position(n,m));
	 break;
 case 6: /* add extractOddDigits() call */
	 System.out.println("n= ");
	 n = sc.nextInt();
	 if (n<0) {
		 System.out.println("Error");
		 break;
	 }
	 System.out.println("Odd digits are = " + extractOddDigits(n));
	 break;
 case 7: System.out.println("Program terminating ….");
 }
 } while (choice < 7);{
	 sc.close();
 }
  }

 


	public static void mulTest() {
		 int count = 0;
		 for (int i = 0; i <5; i++) {
			 int randomNum1 = (int)(Math.random()*10+1);
			 int randomNum2 = (int)(Math.random()*10+1);
			 System.out.println("How much is " + randomNum1 + " times " + randomNum2 + "?");
			 Scanner sc1 = new Scanner(System.in);
			 int input, answer;
			 input = sc1.nextInt();
			 answer = randomNum1 * randomNum2;
			 if (input == answer) {
				 count++;
			 }
		 }
		 System.out.println(count + " answers out of 5 are correct.");
	}


	public static int divide(int m, int n) {
		 int count1 = 0;
		 while (m-n>=0) {
			 m = m -n;
			 count1++;
		 }
		 return count1;
	}

	public static int modulus(int m, int n) {
		 int mod = m%n;
		 return mod;
		 
		
	}
	public static int countDigits(int n) {
		int length = 0;
		if (n<=0) {
			return length;
		}
		else {
			length = String.valueOf(n).length();
			return length;
		}
	}
	public static int position(int n, int digit) {
		String string = String.valueOf(n);
		String number = String.valueOf(digit);
		String reverse = new StringBuffer(string).reverse().toString();
		int pos = reverse.indexOf(number);
		if (pos >=0) {
			return pos+1;
		}
		else {
			return pos;
		}

	}
	public static long extractOddDigits(long n) {
		String string = String.valueOf(n);
		String empt = "";
		for (int i = 0; i < string.length(); i++) {
			char s = string.charAt(i);
			if (s == '1'||s == '3'||s == '5'|| s=='7'||s=='9') {
				empt = empt + s;
			}
		}
		if (empt.isEmpty()){
			long ls = -1;
			return ls;
		}
		else {
			long l = Long.parseLong(empt);
			return l;			
		}

		
	}
}