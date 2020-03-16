import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class coursework 
{
	private static BigInteger publicKey;
	private static BigInteger privateKey;
	private static BigInteger modulus;
	private static BigInteger inputMessage;
	private static BigInteger encryptedMessage;
	private static BigInteger decryptedMessage;
	
	public static void main(String[] args)
	{
		userInput();
		generateKey();
		encrypt();
		decrypt();
		printResults();
	}
	
	private static void generateKey()
	{
		int length = 256;
		SecureRandom random = new SecureRandom();
		BigInteger p = BigInteger.probablePrime(length, random);
		BigInteger q = BigInteger.probablePrime(length, random);
		
		BigInteger n = p.multiply(q);
		
		BigInteger pSubtracted = p.subtract(BigInteger.ONE);
		BigInteger qSubtracted = q.subtract(BigInteger.ONE);
		BigInteger r = pSubtracted.multiply(qSubtracted);
		
		//BigInteger e = BigInteger.valueOf(65537);
		
		BigInteger randomBigInt;
		do{
			long randomNumber = random.nextLong();
			randomBigInt = BigInteger.valueOf(randomNumber);
		} while(!(r.gcd(randomBigInt).equals(BigInteger.ONE) && r.compareTo(randomBigInt) == 1));
		
		BigInteger e = randomBigInt;
		
		BigInteger d = e.modInverse(r);
		
		publicKey = e;
		privateKey = d;
		modulus = n;
	}
	
	private static void userInput()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter message: ");
		String input = scanner.nextLine();
		scanner.close();
		
		inputMessage = new BigInteger(1, input.getBytes()); //1 = positive number, getBytes = number version of String
	}
	private static void encrypt()
	{
		encryptedMessage = inputMessage.modPow(publicKey, modulus);
	}
	private static void decrypt()
	{
		decryptedMessage = encryptedMessage.modPow(privateKey, modulus);
	}
	private static void printResults()
	{
		String output = new String(decryptedMessage.toByteArray());
		System.out.println("Output:" + output);
	}
	
}
