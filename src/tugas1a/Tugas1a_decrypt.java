package tugas1a;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tugas1a_decrypt {

    public static void main(String[] args) {

        String encodedData = readEncodedDataFromFile("src/encoded_data_1a.txt");

        String decodedData = decodeData(encodedData);

        System.out.println("Encoded Data: " + encodedData);
        System.out.println("Decoded Data: " + decodedData);
    }

    public static String readEncodedDataFromFile(String fileName) {
        StringBuilder encodedData = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                encodedData.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading from file: " + e.getMessage());
        }

        return encodedData.toString();
    }

    public static String decodeData(String encodedData) {
        StringBuilder binaryData = new StringBuilder();

        for (char c : encodedData.toCharArray()) {
            String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binaryData.append(binaryChar);
        }

        StringBuilder decodedData = new StringBuilder();

        for (int i = 0; i < binaryData.length(); i += 6) {
            String substring6digit = binaryData.substring(i, Math.min(i + 6, binaryData.length()));

            String decodedChar = decodeBinary(substring6digit);
            decodedData.append(decodedChar);
        }

        return decodedData.toString();
    }

    public static String decodeBinary(String binaryChar) {
        switch (binaryChar) {
            case "000000":
                return "0";
            case "000001":
                return "1";
            case "000010":
                return "2";
            case "000011":
                return "3";
            case "000100":
                return "4";
            case "000101":
                return "5";
            case "000110":
                return "6";
            case "000111":
                return "7";
            case "001000":
                return "8";
            case "001001":
                return "9";
            case "010101":
                return "";
            case "100000":
                return "a";
            case "100001":
                return "b";
            case "100010":
                return "c";
            case "100011":
                return "d";
            case "100100":
                return "e";
            case "100101":
                return "f";
            case "100110":
                return "g";
            case "100111":
                return "h";
            case "101000":
                return "i";
            case "101001":
                return "j";
            case "101010":
                return "k";
            case "101011":
                return "l";
            case "101100":
                return "m";
            case "101101":
                return "n";
            case "101110":
                return "o";
            case "101111":
                return "p";
            case "110000":
                return "q";
            case "110001":
                return "r";
            case "110010":
                return "s";
            case "110011":
                return "t";
            case "110100":
                return "u";
            case "110101":
                return "v";
            case "110110":
                return "w";
            case "110111":
                return "x";
            case "111000":
                return "y";
            case "111001":
                return "z";
            default:
                return "";
        }
    }
}