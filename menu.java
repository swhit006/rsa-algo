import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

// Modify the display content to suit your purposes...
public class menu {
	private static BigInteger publicKey;
	private static BigInteger privateKey;
	private static BigInteger modulus;
	private static BigInteger inputMessage;
	private static BigInteger encryptedMessage;
	private static BigInteger decryptedMessage;
	
	private static final String TITLE =
			"\n2910326 Computer Security coursework\n"+
					" by Sam-WHITE\n"+
					"\t********************\n"+
					"\t Declaration: No part of this coursework was copied from the Internet! \n" +
					"\t1. Calculate public and private keys \n"+
					"\t2. Encrypt a message \n"+
					"\t3. Decrypt the message \n"+
					"\t0. Exit \n"+
					"\t********************\n"+
					"Please input a single digit (0-4):\n";
	menu() {
		int selected=-1;
		while (selected!=0) {
			System.out.println(TITLE);
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			// selected = Integer.parseInt(in.readLine());
			try {
				selected = Integer.parseInt(in.readLine());
				switch(selected) {
				case 1: q1();
				break;
				case 2: q2();
				break;
				case 3: q3();
				break;
				case 4: q4();
				break;} }
			catch(Exception ex) {} } // end while
		System.out.println("Bye!");
	}
	// Modify the types of the methods to suit your purposes...
	private void q1() {
		
		int length = 128;
		SecureRandom random = new SecureRandom();
		BigInteger p = BigInteger.probablePrime(length, random);
		BigInteger q = BigInteger.probablePrime(length, random);
		
		System.out.println("p and q are random prime numbers of length " + length);
		System.out.println("");
		System.out.println("Prime number p = " + p);
		System.out.println("Prime number q = " + q);
		
		BigInteger n = p.multiply(q);
		
		System.out.println("");
		System.out.println("We get our modulus by multiplying the two values together");
		System.out.println("Modulus n = " + n);
		
		BigInteger pSubtracted = p.subtract(BigInteger.ONE);
		BigInteger qSubtracted = q.subtract(BigInteger.ONE);
		BigInteger r = pSubtracted.multiply(qSubtracted);
		
		System.out.println("");
		System.out.println("We get our value for Phi, which is smaller than the value of the modulus");
		System.out.println("by subtracting 1 from our p and q, then multiplying the two together");
		System.out.println("r = " + r);
		
		BigInteger randomBigInt;			
		long randomNumber;
		
		do{
			randomNumber = random.nextLong();	
			randomBigInt = BigInteger.valueOf(randomNumber);
		} while(!(r.gcd(randomBigInt).equals(BigInteger.ONE) && r.compareTo(randomBigInt) == 1 && randomNumber >= 1));
		
		BigInteger e = randomBigInt;
		System.out.println("");
		System.out.println("Public exponent, e, must be larger than 1, smaller than r as well as");
		System.out.println("co-prime with r");
		System.out.println("e = " + e);
		
		BigInteger d = e.modInverse(r);
		System.out.println("");
		System.out.println("Private exponent, d, must be the multiplicative inverse of e");
		System.out.println("d = " + d);
		
		publicKey = e;
		privateKey = d;
		modulus = n;
	}
	private void q2() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter message: ");
		String input = scanner.nextLine();
		scanner.close();
		
		inputMessage = new BigInteger(1, input.getBytes()); //1 = positive number, getBytes = number version of String
		
		encryptedMessage = inputMessage.modPow(publicKey, modulus);
		
		System.out.println(encryptedMessage);
		
		
	}
	private int q3() {
		decryptedMessage = encryptedMessage.modPow(privateKey, modulus);
		String output = new String(decryptedMessage.toByteArray());
		System.out.println("Output:" + output);
		return 1;
	}
	private boolean q4() {
		System.out.println("in q4");
		return true;
	}
	public static void main(String[] args) {
		new menu();
	}
}
