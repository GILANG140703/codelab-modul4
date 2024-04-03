import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Tugas1 {

    
    public static void main(String[] args) {
        String data = "ｧｪ ｮｮJ?";
        String encodedData = encodeData(data);

        System.out.println("Data as string: " + data);
        System.out.println("Data as binary: " + encodedData);

        // Save encoded data to a text file
        String asciiData = binaryToAscii(encodedData);
        saveEncodedDataToFile(asciiData, "encoded_data.txt");

        // Read encoded data from file and decode
        String decodedData = decodeData(readEncodedDataFromFile("encoded_data.txt"));
        System.out.println("Decoded data: " + decodedData);

        // Save decoded data to a text file
        saveDecodedDataToFile(decodedData, "decrypted_data.txt");
    }

    public static String binaryToAscii(String binaryData) {
        StringBuilder byteArray = new StringBuilder();
        int decimalValue;

        for (int i = 0; i < binaryData.length() / 8; i++) {
            decimalValue = Integer.parseInt(binaryData.substring(8 * i, 8 * (i + 1)), 2);
            byteArray.append(Character.toString((char) decimalValue));
        }

        return byteArray.toString();
    }

    // Enkripsi
    public static String encodeData(String data) {
        StringBuilder encodedString = new StringBuilder();

        for (char c : data.toCharArray()) {
            switch (c) {
                case '0':
                    encodedString.append("0000");
                    break;
                case '1':
                    encodedString.append("0001");
                    break;
                case '2':
                    encodedString.append("0010");
                    break;
                case '3':
                    encodedString.append("0011");
                    break;
                case '4':
                    encodedString.append("0100");
                    break;
                case '5':
                    encodedString.append("0101");
                    break;
                case '6':
                    encodedString.append("0110");
                    break;
                case '7':
                    encodedString.append("0111");
                    break;
                case '8':
                    encodedString.append("1000");
                    break;
                case '9':
                    encodedString.append("1001");
                    break;
                case 'a':
                    encodedString.append("10100000");
                    break;
                case 'b':
                    encodedString.append("10100001");
                    break;
                case 'c':
                    encodedString.append("10100010");
                    break;
                case 'd':
                    encodedString.append("10100011");
                    break;
                case 'e':
                    encodedString.append("10100100");
                    break;
                case 'f':
                    encodedString.append("10100101");
                    break;
                case 'g':
                    encodedString.append("10100111");
                    break;
                case 'h':
                    encodedString.append("10101001");
                    break;
                case 'i':
                    encodedString.append("10101011");
                    break;
                case 'j':
                    encodedString.append("10101100");
                    break;
                case 'k':
                    encodedString.append("10101101");
                    break;
                case 'l':
                    encodedString.append("10101110");
                    break;
                case 'm':
                    encodedString.append("101010100000");
                    break;
                case 'n':
                    encodedString.append("101010100001");
                    break;
                case 'o':
                    encodedString.append("101010100010");
                    break;
                case 'p':
                    encodedString.append("101010100011");
                    break;
                case 'q':
                    encodedString.append("101010100100");
                    break;
                case 'r':
                    encodedString.append("101010100101");
                    break;
                case 's':
                    encodedString.append("101010100111");
                    break;
                case 't':
                    encodedString.append("101010101001");
                    break;
                case 'u':
                    encodedString.append("101010101011");
                    break;
                case 'v':
                    encodedString.append("101010101100");
                    break;
                case 'w':
                    encodedString.append("101010101101");
                    break;
                case 'y':
                    encodedString.append("101010101110");
                    break;
                default:
                    // Handle unknown characters
                    break;
            }
        }

        if (encodedString.length() % 8 != 0) {
            encodedString.append("1111");
        } else {
            encodedString.append("10101111");
        }

        return encodedString.toString();
    }

    // Fungsi untuk menyimpan data terenkripsi ke dalam file
    public static void saveEncodedDataToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
            System.out.println("Encoded data saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    // Fungsi untuk membaca data terenkripsi dari file
    public static String readEncodedDataFromFile(String fileName) {
        StringBuilder encodedData = new StringBuilder();
        try {
            java.nio.file.Path path = java.nio.file.Paths.get(fileName);
            byte[] data = java.nio.file.Files.readAllBytes(path);
            for (byte b : data) {
                encodedData.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading from file: " + e.getMessage());
        }
        return encodedData.toString();
    }

    // Dekripsi
    public static String decodeData(String encodedData) {
        StringBuilder decodedData = new StringBuilder();

        while (encodedData.length() >= 8) {
            String byteStr = encodedData.substring(0, 8);
            int decimalValue = Integer.parseInt(byteStr, 2);
            if (decimalValue == 255) {
                break;  // End of file
            }
            decodedData.append((char) decimalValue);
            encodedData = encodedData.substring(8);
        }

        return decodedData.toString();
    }

    // Fungsi untuk menyimpan data terdekripsi ke dalam file
    public static void saveDecodedDataToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
            System.out.println("Decoded data saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error occurred while writing to file: " + e.getMessage());
        }
    }
}