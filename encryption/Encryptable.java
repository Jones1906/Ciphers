package encryption;
/* A brief description of the role of this class:

 *The required methods for all the encryption classes


 * Student Name: Jeffin Tom

 * Student Number:041094535

 * Course: CST8132 Object Oriented Programming

 * Program: CET-CS-Level 2

 * Professor:  James Mwangi PhD.

 */



public interface Encryptable {
    int START_CHAR = 32;  // ASCII value of the space character
    int END_CHAR = 122;   // ASCII value of the z character
    int RANGE = END_CHAR - START_CHAR + 1;
 /**
  * 
  * @param input
  * @return
  */
    
    String encrypt(String input); //Encrypts the given input string.
    String decrypt(String input); //Decrypts the given input string.
		
	
}
