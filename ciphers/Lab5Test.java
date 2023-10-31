

import java.util.ArrayList;
import java.util.Scanner;

import ciphers.CaesarCipher;
import encryption.Encryptable;
import encryption.ciphers.CaesarCipher;;

class Lab5Test {
    private Scanner input; // Scanner object for user input
 // Constructor to initialize the Scanner
    public Lab5Test() {
        input = new Scanner(System.in);
    }
/**
 * 
 * @param options
 * @return
 */
 // Method to display menu and get user's selection 
    public int getMenuItem(String... options) {
        int choice = 0; // variable that store users choice
        boolean validChoice = false;// for checking if choice is true
        while (!validChoice) { // while loop to loop until true choice is made
        	 // Display menu options
            for (int i = 0; i < options.length; i++) {
                System.out.println("     "+(i + 1) + " - " + options[i]);
                
            }System.out.print("Select action:");
            try {
            	 // Read user input as integer
                choice = Integer.parseInt(input.nextLine());
                //Integer.parseInt(...) is a method that converts a string representation of an integer 
                //into an actual integer value. It takes the string obtained from input.nextLine() as input.
                
             // Checks whether the choice is within the valid range
                if (choice < 1 || choice > options.length) {
                    System.out.println("Please select a number in the range");
                } else {
                    validChoice = true;// if true then loops exits
                }
            } catch (NumberFormatException e) {
            	System.out.println();
                System.out.println("Please enter an integer");
            }
        }
        return choice; // Return the user's choice
    }
/**
 * 
 * @param prompt
 * @return
 */
    // Method to get a string input from the user
    public String getString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();  // Reads and then returns the user's input
    }
/**
 * 
 * @param prompt
 * @param min
 * @param max
 * @return
 */
    
    // Method to get an integer input from the user within a specified range
    public int getInt(String prompt, int min, int max) {
        int value = 0; // variable which stores the users input
        boolean validInput = false; //  checks if value entered by user is true
        while (!validInput) {
            System.out.print(prompt);
            try {
            	// Read user input as integer
                value = Integer.parseInt(input.nextLine());
             // Check if the input is within the valid range
                if (value < min || value > max) {
                  //System.out.println("The input value must be between " + min + " and " + max);
                    System.out.println("Please select a number in the range");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter an integer");
            }
        }
        return value;
    }
/**
 * 
 * @return
 */
    // Method to get  encryption method from the user
    public Encryptable getMethod() {
        System.out.println("Encryption method:");
        System.out.println("1 "
        		+ "- Caesar");
        System.out.println("2 - Vigenere");
       // System.out.println("Select action:");
        
        // Calls the getInt method to get the user's choice within the specified range
        int choice = getInt("Select action :",1,2);
        if (choice == 1) {
            // If the choice is 1, get the shift value for Caesar cipher class

            int shift = getInt("Shift value:", 0, 26);
            return new CaesarCipher(shift);// Returns new instance of CaesarCipher with the specified shift value
        } else { 
        	// If the choice is 2, get the password for Vigenere cipher
            String password = getString("Password:");
            return new VigenereCipher(password);// returns new instance of vigenere cipher
        }
    }
/**
 * 
 * @param args
 */
    public static void main(String[] args) {
        System.out.println("Encryption tester");
        

        ArrayList<String> encryptedText = new ArrayList<>();// ArrayList to store encrypted texts
        Lab5Test lab5Test = new Lab5Test();  // Create an instance of Lab5Test
        boolean exit = false;
// main loop
        while (!exit) {
        	  // Get the user's choice from the menu
            int choice = lab5Test.getMenuItem("Encrypt text", "Decrypt text", "Display encrypted list", "Exit");

            switch (choice) {
                case 1:
                	  // Encryption menu option
                    String textToEncrypt = lab5Test.getString("Please enter text to be encrypted:");
                    Encryptable encryptionMethod = lab5Test.getMethod();
                    String encryptedTextValue = encryptionMethod.encrypt(textToEncrypt);
                    encryptedText.add(encryptedTextValue);
                    System.out.println("Encrypted value #" + encryptedText.size() + " is: " + encryptedTextValue);
                    break;
                case 2:
                	// decryption menu option
                    if (encryptedText.isEmpty()) {
                        System.out.println("Nothing to decrypt");
                    } else {
                        int messageNumber = lab5Test.getInt("Message number you want to decrypt:", 1, encryptedText.size());
                        encryptionMethod = lab5Test.getMethod();
                        String decryptedText = encryptionMethod.decrypt(encryptedText.get(messageNumber - 1));
                        System.out.println("Decrypted value #" + messageNumber + " is: " + decryptedText);
                    }
                    break;
                case 3:
                	 // Display encrypted list menu option
                    if (encryptedText.isEmpty()) {
                        System.out.println("Nothing to display");
                    } else {
                        System.out.println("------------");
                        for (int i = 0; i < encryptedText.size(); i++) {
                            System.out.println("#" + (i + 1) + ": " + encryptedText.get(i));
                        }
                        System.out.println("------------");
                    }
                    break;
                case 4:
                	// encrypted list menu option
                    exit = true;
                    break;
            }
        }

        System.out.println("Good bye");
    }
}