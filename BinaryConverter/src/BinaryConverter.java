

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BinaryConverter {
	
	public static final int FINAL_IEEE_BITS = 23;
	/*public static final int MAX_BITS = 64;
	public static final int MANTISSA = 52;
	public static final int MAX_EXPONENT_BITS = 11;*/
	public static final int MAX_BITS = 32;
	public static final int MANTISSA = 64;
	public static final int MAX_EXPONENT_BITS = 8;
		public static final int BIAS = 127;
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
			s = new Scanner( new File("testClass.txt"));
			//System.out.println(It's open");
			allGood = true;
		} catch( Exception e ) {
			System.out.println("It's not open. There is an issue opening your file. %s" + e);
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
					binConvertDec(Double.parseDouble(nextNumIn));
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
		
		
		
	public static String binConvertDec(double dNum) {
		
		String bitString = "";
		double startNum = dNum;
		
		
		// Now get negative bit
		String negBit = "";
		
		if(dNum >= 0) {
			negBit = "0";
		} else {
			negBit = "1";
			dNum *= -1;
		}
		System.out.println("The negative bit is: " + negBit);
		
		
		//separating int. and dec. part of input number
		long intNum = (long)dNum;
		double decNum = dNum - intNum;
		
		System.out.println(decNum);
		
		
		//This could be your character array....
				
		for(int b = MANTISSA; b >= -MANTISSA; --b)	{	 
			//System.out.println("b is " + b);
			//deal with the integer part....
			if(b >=0) {
				if( (double)(Math.pow(2.0, (double)b)) <= intNum) {
					bitString += "1";
					intNum -= (double)(Math.pow(2.0, (double)b));
				} else {
					bitString += "0";
				}
				
			} else {
				
				if(b == -1) {
					bitString += ".";
				}
				//decimal part
				double dnt = 1.0/ (double)(Math.pow(2.0, (double)(b*-1)));
				if( dnt <= decNum) {
					bitString += "1";
					decNum -= dnt;
				} else {
					bitString += "0";
				}
				
			}

		}
		int exponent = 0;
		String finalScientific = "";
		String outerTempScientific = "";
		System.out.println("Bit string is: " + bitString);
		int firstOne = bitString.indexOf('1');
		System.out.println("The first 1 is at " + firstOne);
		int decimalPoint = bitString.indexOf('.');
		System.out.println("The decimal is at " + decimalPoint);
		
		
		//For exponent = 0
		 if(firstOne < 0) {
				exponent = BIAS;
				String scientificF = ".";
				outerTempScientific = scientificF;
				System.out.println("Bias exponent is " + exponent);
				
		//for positive exponent
		} else if(firstOne > decimalPoint) {
			exponent = decimalPoint - firstOne - 1 + BIAS;
			String tempString = bitString.substring(firstOne);
			String scientificF = "";
			for(int b = 0; b < tempString.length(); ++ b) {
				if(tempString.charAt(b) != '.') {
					scientificF += tempString.charAt(b);
				}
				
				if(b == 0 ) {
					scientificF += ".";
				}
				
			}
			outerTempScientific = scientificF;
			System.out.println("Sci Format: " + scientificF);
			System.out.println("Bias exponent is " + exponent);
			
			
			//For negative exponent
		} else if(firstOne < decimalPoint) {
			exponent = decimalPoint - firstOne + BIAS;
			String tempString = bitString.substring(firstOne);
			String scientificF = "";
			for(int b = 0; b < tempString.length(); ++ b) {
				if(tempString.charAt(b) != '.') {
					scientificF += tempString.charAt(b);
				}
				
				if(b == 0 ) {
					scientificF += ".";
				}
				
		}
			outerTempScientific = scientificF;
			System.out.println("Sci Format: " + scientificF);
			System.out.println("Bias exponent is " + exponent);
			
			
		} else {
			System.out.println("ERROR");
		}
		
		
		
		// Now get this into scientific notation form....
		 //check that it should be "++ b" instead of "b++"
		for(int b = 2; b < FINAL_IEEE_BITS + 2; ++ b) {
				finalScientific += outerTempScientific.charAt(b);
		}
		System.out.println("the final scientific IEEE bits are " + finalScientific);
		
		// Now get exponent bits
		String IEEE_exponent = binIEEEConverter(exponent, MAX_EXPONENT_BITS);
		System.out.println("exponent bin is :" + IEEE_exponent);
		
		
		
		bitString = negBit + " " + IEEE_exponent + " " + finalScientific;
		System.out.println("The IEEE format is of " + startNum + " is " + bitString);
		
		return bitString;
		
	}
	
	
	
	
	//for IEEE exponent
	//places 1-12
	//length: 11 (0-10)
	public static String binIEEEConverter(int num, int size) {
		String binString = "";
		char[] binary = new char[size];
		double varb = num;
		
		for(int b = size-1; b >= 0; --b)	{	
			//System.out.println("b is " + b);
			//deal with the integer part....
				if( (double)(Math.pow(2.0, (double)b)) <= num) {
					binString += "1";
					num -= (double)(Math.pow(2.0, (double)b));
				} else {
					binString += "0";
				}
			}
		return binString;
	}
	
	
	
	
	
	
	
	
	
	
	//This converts integer numbers to binary
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
