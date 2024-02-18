# World-War-II-Enigma-clone

# Enigma Machine Simulator

This is a Java-based simulator for the Enigma machine, a historical encryption device used during World War II. The simulator allows you to encrypt and decrypt messages using a simplified version of the Enigma algorithm.

## Usage

1. Clone or download this repository to your local machine.

2. Compile the Java source file `EnigmaMachine.java`.

    ```bash
    javac Enigma.java
    ```

3. Run the compiled Java program.

    ```bash
    java Enigma
    ```

4. Follow the prompts in the console:
   
    - Press `1` to encode a message.
    - Press `2` to decode an already encoded message.
    - Press `0` to exit.

5. Enter your choice and follow the subsequent prompts to enter the plaintext or ciphertext accordingly.

## Rotor Configuration

The simulator uses a fixed rotor configuration by default. However, you can modify the code to select a different rotor configuration by changing the `selectedRotor` variable in the `main` method or by implementing a mechanism to allow the user to choose the rotor configuration interactively.

## Error Fix

If you encounter issues with decoding messages, such as incorrect outputs, it may be due to the handling of rotor positions. When decoding a message, the rotor positions should be reset to their initial state before decryption. To fix this issue, the rotor positions are reset before processing the ciphertext.

