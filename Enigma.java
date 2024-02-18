import java.util.Scanner;

public class EnigmaMachine {
    // Define the rotor wiring configurations
    private static final String rotorI = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    private static final String rotorII = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    private static final String rotorIII = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    
    // Define the reflector wiring configuration
    private static final String reflector = "YRUHQSLDPXNGOKMIEBFZCWVJAT";

    // Define the plugboard wiring configuration
    private static final String plugboard = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Define the rotor positions (initially set to 'A')
    private static char rotor1Position = 'A';
    private static char rotor2Position = 'A';
    private static char rotor3Position = 'A';

    // Function to rotate the rotor
    private static void rotateRotor(char[] rotor) {
        char temp = rotor[0];
        System.arraycopy(rotor, 1, rotor, 0, rotor.length - 1);
        rotor[rotor.length - 1] = temp;
    }

    // Function to encrypt or decrypt a single character
    private static char processChar(char c) {
        if (Character.isAlphabetic(c)) {
            // Convert to uppercase
            c = Character.toUpperCase(c);
            
            // Pass through plugboard
            c = plugboard.charAt(c - 'A');
            
            // Pass through rotor 1
            int index = c - 'A';
            c = rotorI.charAt((index + (rotor1Position - 'A')) % 26);
            
            // Pass through rotor 2
            index = c - 'A';
            c = rotorII.charAt((index + (rotor2Position - 'A')) % 26);
            
            // Pass through rotor 3
            index = c - 'A';
            c = rotorIII.charAt((index + (rotor3Position - 'A')) % 26);
            
            // Pass through reflector
            c = reflector.charAt(c - 'A');
            
            // Pass back through rotor 3
            index = rotorIII.indexOf(c);
            c = (char) ('A' + (index - (rotor3Position - 'A') + 26) % 26);
            
            // Pass back through rotor 2
            index = rotorII.indexOf(c);
            c = (char) ('A' + (index - (rotor2Position - 'A') + 26) % 26);
            
            // Pass back through rotor 1
            index = rotorI.indexOf(c);
            c = (char) ('A' + (index - (rotor1Position - 'A') + 26) % 26);
            
            // Pass back through plugboard
            c = plugboard.charAt(c - 'A');
            
            return c;
        }
        return c;
    }

    // Function to perform encryption or decryption on a string
    private static String processString(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            output.append(processChar(c));
            
            // Rotate rotors
            rotateRotor(rotorI.toCharArray());
            if (rotor1Position == 'R') {
                rotateRotor(rotorII.toCharArray());
                if (rotor2Position == 'F') {
                    rotateRotor(rotorIII.toCharArray());
                }
            }
            
            // Update rotor positions
            rotor1Position = (char) ('A' + (rotor1Position - 'A' + 1) % 26);
            if (rotor1Position == 'F') {
                rotor2Position = (char) ('A' + (rotor2Position - 'A' + 1) % 26);
                if (rotor2Position == 'V') {
                    rotor3Position = (char) ('A' + (rotor3Position - 'A' + 1) % 26);
                }
            }
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        System.out.println("Welcome to the Enigma Machine Simulator!");
        do {
            System.out.println("Press 1 to encode a message");
            System.out.println("Press 2 to decode an already encoded message");
            System.out.println("Press 0 to exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter plaintext: ");
                    String plaintext = scanner.nextLine();
                    String ciphertext = processString(plaintext);
                    System.out.println("Encrypted text: " + ciphertext);
                    break;
                case 2:
                    // Reset rotor positions
                    rotor1Position = 'A';
                    rotor2Position = 'A';
                    rotor3Position = 'A';
                    
                    System.out.print("Enter ciphertext: ");
                    String ciphertextInput = scanner.nextLine();
                    String decryptedText = processString(ciphertextInput);
                    System.out.println("Decrypted text: " + decryptedText);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 0, 1, or 2.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}


