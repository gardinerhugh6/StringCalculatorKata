package main.java;
import java.util.ArrayList;

/**
 * Created by Hugh on 3/26/2017.
 */
public class StringCalculator {

    public int add(String numbers) {
        String[] numberArray = extractNumbers(numbers);

        ArrayList<Integer> negativeNumbers = new ArrayList<>();
        int sum = calculateSumAndDetermineNegatives(numberArray, negativeNumbers);
        if (negativeNumbers.size() > 0) {
            String negativeNumberString = determineNegativeNumberString(negativeNumbers);
            throw new IllegalArgumentException("Negatives not allowed:" + negativeNumberString);
        }
        return sum;
    }

    public int calculateSumAndDetermineNegatives(String[] numberArray, ArrayList<Integer> negativeNumbers) {
        int sum = 0;
        for (String num : numberArray){
            if (!num.equals("")) {
                int nextNum = Integer.parseInt(num);
                if (nextNum > -1 && nextNum < 1001) {
                    sum += nextNum;
                }

                else if (nextNum < 0) {
                    negativeNumbers.add(nextNum);
                }
            }
        }
        return sum;
    }

    public String determineNegativeNumberString(ArrayList<Integer> negativeNumbers) {
        String negativeNumberString = ""    ;
        for (Integer negNum : negativeNumbers) {
            negativeNumberString += " " + negNum.toString();
        }
        return negativeNumberString;
    }

    public String[] extractNumbers(String numbers) {
        String[] characters = numbers.split("");
        if(characters[0].equals("/")) {
            String[] lines = numbers.split("\n");
            String delimiter = determineDelimiters(lines[0], characters);
            return lines[1].split("[\\Q" + delimiter + "\\E]");
        }
        else {
            return numbers.split("[,\n\r]");
        }
    }

    public String determineDelimiters(String numbers, String[] characters) {
        if(characters[2].equals("[")) {
            String[] delimiterArray = numbers.split("[/\\[\\]]");
            String delimiter = "";
            for (String nextDelimeter : delimiterArray) {
                if (!nextDelimeter.equals("")) {
                    delimiter += nextDelimeter;
                }
            }
            return delimiter;
        }
        else {
            return characters[2];
        }
    }
}
