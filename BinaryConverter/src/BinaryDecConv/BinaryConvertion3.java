package BinaryDecConv;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BinaryConvertion3 {
	
	public static final int MAX_BITS = 64;
		public static final int LONG = 64;
		public static final int INT = 32;
		public static final int SHORT = 16;
		public static final int BYTE = 8;
		//Data Type Codes...
		public static final int STRING_TYPE = 0;
		public static final int INT_TYPE = 1;
		public static final int FLOAT_TYPE = 2;
	
	public static void main(String[] args) {
		
		int dataType = -1;
		Scanner s = null;
		boolean allGood = false;
		try {
			s = new Scanner(new File("testClass.txt"));
			//System.out.println(It's open");
			allGood = true;
		} catch( Exception e ) {
			System.out.println("It's not open. There is an issue opening your file.");
		}
		
		if(allGood) {
			while(s != null && s.hasNextLine()) {
				String nextNumIn = s.nextLine();
				
				if(itsNotANumber (nextNumIn)) {
					//check for bad input
					System.out.println(nextNumIn + " is bad input");
					dataType = STRING_TYPE;
				} else if(isADecimal(nextNumIn)) {
					//check for a decimal
					System.out.println(nextNumIn + " is a decimal");
					dataType = FLOAT_TYPE;
				} else {
					//it's an integer
					System.out.println(nextNumIn + " is an integer");
					dataType = INT_TYPE;
				}
				
				//now process the numbers....
				if(dataType == INT_TYPE) {
					String binInt = "";
					try {
						long x = Long.parseLong(nextNumIn);
						//This is my conversion method...
						binInt = binConvertInt(x);
						System.out.println(binInt);
						
					} catch (Exception e) {
						System.out.println(nextNumIn + " -- Number is too large");
						binInt = "" + nextNumIn + " is too big for Long";
					}
					
				} else if (dataType == FLOAT_TYPE) {
					//create the 32 bit float method
				} else {
					//Strings??
				}
			}
			s. close();
		}
	}
	
	public static boolean itsNotANumber(String nextNumIn) {
		boolean itsNotANumber = false;
		String nonNums = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()_+= {[}]|\\:;\"'<,>?/";
		
		for(int l= 0; l < nonNums.length(); l++) {
			if(nextNumIn.contains("" + nonNums.charAt(l))) {
				itsNotANumber = true;
			}
		}
		return itsNotANumber;
	}
	
	public static boolean isADecimal(String nextNumIn) {
		boolean itsADecimal = false;
		if(nextNumIn.contains(".")) {
			itsADecimal = true;
		}
		return itsADecimal;
	}
	
	
	
	public static int findBits(double num) {
		
		if(num >= -128 && num <= 127) return 8;
		if(num >=  -32768 && num <= 32767) return 16;
		if(num >= -Math.pow(2, 31) && num <= Math.pow(2, 31) - 1) return 32;
		if(num >= -Math.pow(2, 63) && num <= Math.pow(2, 63) - 1) return 64;
		
		return -1;
	}
		
		
		
	public static String binConvertDec(double num) {
		// Set up for the return
		String binString = "";
		char[] binary = new char[MAX_BITS];
		
		
		//Now check to see if you have a negative
		//We will use 2's complement later
		boolean isNeg = false;
		
		if(num < 0) {
			isNeg = true;
			num *= -1;
		}
		if(isNeg == false) {
			binary[0] = '0';
		} else {
			binary[0] = '1';
		}
		
		
		//For exponent
		//places 1 - 12
		
		
		//For Number/Decimal
		//places 13 - 63
		
		
	}
	
	public static String binConvertInt(double num) {
		// Set up for the return
		String binString = "";
		
		//Now check to see if you have a negative
		//We will use 2's complement later
		boolean isNeg = false;
		boolean isDec = false;
		
		int mySize = findBits(num);  
		System.out.println("Back from findBits and mySize is " + mySize);
		

		if(num < 0) {
			isNeg = true;
			num *= -1;
		}
		
		/*if(num%1 > 1||num%-1 > 1) {
			isDec = true;
		}
		
		double decVarb = num;
		
		int myDecSize = 0;
		//find decimal binary length
		if(isDec == true) {
			while(decVarb > 0) {
				myDecSize++;
				decVarb = (int)(decVarb/2);
			}
			System.out.println("The decimal binary length is: " + myDecSize);
		}
		*/
		
		//Now release bits
		
		char[] binary = new char[mySize];
		double varb = num;
		
		if(isNeg == false) {
			binary[0] = '0';
		} else {
			binary[0] = '1';
		}
		
		//this checks for integer-ness
		if((long)num == num) {
				if(isNeg == false) {
				//positive numbers
					int c = 0;
					for(int bits = mySize-1;bits >= 0; bits--) {
						if(varb >= Math.pow(2.0, (double)bits)) {
							binary[c++] = '1';
							varb = varb-(Math.pow(2.0, (double)bits));
						} else {
							binary[c++] = '0';
						}
					}
			} else { 
				//negative numbers
				int c = 0;
				for(int bits = mySize-1;bits >= 0; bits--) {
					if(varb >= Math.pow(2.0, (double)bits)) {
						binary[c++] = '0';
						varb = varb-(long)(Math.pow(2.0, (double)bits));
					} else {
						binary[c++] = '1';
					}
				}
				
				//this is to add 1 as part of 2's complement
				int start = binary.length-1;
				
				while(start >=0 && binary[start] == '1') {
					binary[start--] = '0';	
				} 
				
				if(start >= 0) {
					binary[start] = '1';
				}
			}
		}
		
		
		System.out.println(Arrays.toString(binary));
			
		System.out.println("The bits for " + num + " is " + mySize);
		
		return binString;
		
	}
	
}
