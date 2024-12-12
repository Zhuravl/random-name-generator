package io.github.zhuravl.randomname;

import io.github.zhuravl.randomname.enums.Gender;
import io.github.zhuravl.randomname.enums.Language;
import io.github.zhuravl.randomname.enums.NamePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;

/**
 * Utility class for generating random names based on language, gender, and name part.
 */
public class RandomName {

    private static final Random RANDOM = new Random();

    /**
     * Generates a random first name.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @return a random first name
     */
    public static String getFirstName(Language language, Gender gender) {
        return getRandomName(language, gender, NamePart.FIRST);
    }

    /**
     * Generates a random last name.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @return a random last name
     */
    public static String getLastName(Language language, Gender gender) {
        return getRandomName(language, gender, NamePart.LAST);
    }

    /**
     * Generates a random middle name.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @return a random middle name
     */
    public static String getMiddleName(Language language, Gender gender) {
        return getRandomName(language, gender, NamePart.MIDDLE);
    }

    /**
     * Generates a full name (first, middle, and last name).
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @return a random full name
     */
    public static String getFullName(Language language, Gender gender) {
        return getFirstName(language, gender) + " " +
                getMiddleName(language, gender) + " " +
                getLastName(language, gender);
    }

    /**
     * Generates a reversed full name (last, middle, and first name - cyrillic languages style).
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @return a random full name
     */
    public static String getFullNameReversed(Language language, Gender gender) {
        return getLastName(language, gender) + " " +
                getFirstName(language, gender) + " " +
                getMiddleName(language, gender);
    }

    /**
     * Generates a name with first and last name.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @return a random first and last name
     */
    public static String getFirstLastName(Language language, Gender gender) {
        return getFirstName(language, gender) + " " + getLastName(language, gender);
    }

    /**
     * Generates a name with last and first name.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @return a random last and first name
     */
    public static String getLastFirstName(Language language, Gender gender) {
        return getLastName(language, gender) + " " + getFirstName(language, gender);
    }

    /**
     * Generates a random name based on the given parameters.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @param namePart the name part of the name
     * @return a random name
     */
    private static String getRandomName(Language language, Gender gender, NamePart namePart) {
        validateParameters(language, gender, namePart);

        String fileName = generateFileName(language, gender, namePart);

        try {
            int totalLines = countLines(fileName);
            if (totalLines == 0) {
                throw new IllegalStateException("The file '" + fileName + "' is empty!");
            }
            int randomLineNumber = RANDOM.nextInt(totalLines);
            return readLineAt(fileName, randomLineNumber);
        } catch (IOException e) {
            throw new RuntimeException("Error generating name from file: " + fileName, e);
        }
    }

    /**
     * Validates the given parameters.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @param namePart the name part of the name
     */
    private static void validateParameters(Language language, Gender gender, NamePart namePart) {
        Objects.requireNonNull(language, "Language must not be null.");
        Objects.requireNonNull(gender, "Gender must not be null.");
        Objects.requireNonNull(namePart, "NamePart must not be null.");
    }

    /**
     * Generates the file name based on the given parameters.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @param namePart the name part of the name
     * @return the file name
     */
    private static String generateFileName(Language language, Gender gender, NamePart namePart) {
        return language.toString().toLowerCase() + "_" +
                gender.toString().toLowerCase() + "_" +
                namePart.toString().toLowerCase() + ".txt";
    }

    /**
     * Returns the number of lines in the given file.
     *
     * @param fileName the file name
     * @return the number of lines
     * @throws IOException if an I/O error occurs
     */
    private static int countLines(String fileName) throws IOException {
        try (InputStream input = RandomName.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalArgumentException("File '" + fileName + "' not found in resources!");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                return (int) reader.lines().count();
            }
        }
    }

    /**
     * Returns the line at the given line number in the given file.
     *
     * @param fileName   the file name
     * @param lineNumber the line number
     * @return the line
     * @throws IOException if an I/O error occurs
     */
    private static String readLineAt(String fileName, int lineNumber) throws IOException {
        try (InputStream input = RandomName.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalArgumentException("File '" + fileName + "' not found in resources!");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                return reader.lines()
                        .skip(lineNumber)
                        .findFirst()
                        .orElseThrow(() -> new IOException("Line '" + lineNumber + "' not found in file!"));
            }
        }
    }
}
