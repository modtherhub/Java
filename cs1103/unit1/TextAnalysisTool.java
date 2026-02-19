import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Text Analysis Tool
 * 
 * This program performs various string operations on a user-provided text.
 * It analyzes characters and words to provide meaningful insights.
 * This assignment was completed under the supervision of Instructor Hiralben H Amin
 *
 * Author: Modther Abalhag
 * Date: Feb 2026
 * Programming Assignment Unit 1
 */
public class TextAnalysisTool {

    // Method to get valid text input
    public static String getTextInput(Scanner scanner) {
        String text;
        while (true) {
            System.out.println("Enter a paragraph or long text:");
            text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            } else {
                System.out.println("Input cannot be empty. Please try again.\n");
            }
        }
    }

    // Character count
    public static int characterCount(String text) {
        return text.length();
    }

    // Word count
    public static int wordCount(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    // Most common character (case-insensitive)
    public static char mostCommonCharacter(String text) {
        text = text.toLowerCase();
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (c != ' ') {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }

        char mostCommon = ' ';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommon = entry.getKey();
            }
        }

        return mostCommon;
    }

    // Frequency of a specific character
    public static int characterFrequency(String text, Scanner scanner) {
        while (true) {
            System.out.print("Enter a character to find its frequency: ");
            String input = scanner.nextLine().trim();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                char targetChar = Character.toLowerCase(input.charAt(0));
                int count = 0;

                for (char c : text.toLowerCase().toCharArray()) {
                    if (c == targetChar) {
                        count++;
                    }
                }
                return count;
            } else {
                System.out.println("Please enter exactly ONE alphabet character (a-z or A-Z).\n");
            }
        }
    }

    // Frequency of a specific word
    public static int wordFrequency(String text, Scanner scanner) {
        String word;
        while (true) {
            System.out.print("Enter a word to find its frequency: ");
            word = scanner.nextLine().trim();
            if (!word.isEmpty()) {
                String[] words = text.toLowerCase().split("\\s+");
                int count = 0;
                for (String w : words) {
                    if (w.equals(word.toLowerCase())) {
                        count++;
                    }
                }
                return count;
            } else {
                System.out.println("Word cannot be empty.\n");
            }
        }
    }

    // Count unique words (case-insensitive)
    public static int uniqueWordsCount(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word);
        }
        return uniqueWords.size();
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Text Analysis Tool\n");

        String text = getTextInput(scanner);

        System.out.println("\nText Analysis Results");
        System.out.println("------------------------------");
        System.out.println("Total Characters: " + characterCount(text));
        System.out.println("Total Words: " + wordCount(text));
        System.out.println("Most Common Character: '" + mostCommonCharacter(text) + "'");
        System.out.println("Character Frequency: " + characterFrequency(text, scanner));
        System.out.println("Word Frequency: " + wordFrequency(text, scanner));
        System.out.println("Unique Words Count: " + uniqueWordsCount(text));

        System.out.println("\nAnalysis Complete. Thank you!");

        scanner.close();
    }
}