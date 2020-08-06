package application;

import entities.Cryptography;

public class Main {

	public static void main(String[] args) {
		Cryptography crypt = new Cryptography();
		System.out.println("Content in the file: ");
		crypt.readFile();
		System.out.println(crypt.getList());
		
		System.out.println();
		System.out.println("Content encrypted: ");
		crypt.rot13();
		
		System.out.println();
		System.out.println();
		System.out.println("encrypted content saved to the file successfully:");
		
		System.out.println();
		System.out.println("Content decrypted: ");
		crypt.rot13Decrypt();
		
		System.out.println();
		System.out.println();
		System.out.println("decrypted content saved to the file successfully:");
	}

}
