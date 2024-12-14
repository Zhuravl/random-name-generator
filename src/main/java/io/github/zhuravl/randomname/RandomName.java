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
 * This class is not meant to be instantiated.
 */
public class RandomName {

    private static final Random RANDOM = new Random();

    /**
     * Private constructor to prevent instantiation.
     */
    private RandomName() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Generates a random name in the random available language and random gender based on the name parts.
     *
     * @param nameParts the name parts to use
     * @return a random name, based on the given name parts (or 'last first' name, by default)
     */
    public static String getName(NamePart... nameParts) {
        return getName(getRandomEnum(Language.class), getRandomEnum(Gender.class), nameParts);
    }

    /**
     * Generates a random name with random gender based on the given parameters.
     *
     * @param language the language to use
     * @param nameParts the name parts to use
     * @return a random name, based on the given parameters
     */
    public static String getName(Language language, NamePart... nameParts) {
        return getName(language, getRandomEnum(Gender.class), nameParts);
    }

    /**
     * Generates a random name in the random available language based on the given parameters.
     *
     * @param gender   the gender to use
     * @param nameParts the name parts to use
     * @return a random name, based on the given parameters
     */
    public static String getName(Gender gender, NamePart... nameParts) {
        return getName(getRandomEnum(Language.class), gender, nameParts);
    }

    /**
     * Generates a random name based on the given parameters.
     *
     * @param language the language to use
     * @param gender   the gender to use
     * @param nameParts the name parts to use
     * @return a random name, based on the given parameters
     */
    public static String getName(Language language, Gender gender, NamePart... nameParts) {
        NamePart[] finalNameParts = normalizeNameParts(nameParts);

        validateParameters(language, gender, finalNameParts);

        StringBuilder result = new StringBuilder();

        try {
            for (NamePart namePart : finalNameParts) {
                String fileName = generateFileName(language, gender, namePart);
                int totalLines = countLines(fileName);
                if (totalLines == 0) {
                    throw new IllegalStateException("The file '" + fileName + "' is empty!");
                }
                int randomLineNumber = RANDOM.nextInt(totalLines);
                result.append(readLineAt(fileName, randomLineNumber)).append(" ");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error is happened while generating a random name!", e);
        }
        return result.toString().trim();
    }

    /**
     * Returns random enum value
     *
     * @param clazz enum class to return instance from
     * @param <T>   return class type
     */
    private static <T extends Enum<?>> T getRandomEnum(Class<T> clazz) {
        int x = RANDOM.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     * Sets the default name parts if none are provided.
     *
     * @param nameParts the name parts
     * @return the normalized name parts
     */
    private static NamePart[] normalizeNameParts(NamePart[] nameParts) {
        if (nameParts.length == 0) {
            return new NamePart[]{NamePart.LAST, NamePart.FIRST};
        }
        return nameParts;
    }

    /**
     * Validates the given parameters.
     *
     * @param language the language of the name
     * @param gender   the gender of the name
     * @param namePart the name part of the name
     */
    private static void validateParameters(Language language, Gender gender, NamePart... namePart) {
        Objects.requireNonNull(language, "Language must not be null!");
        Objects.requireNonNull(gender, "Gender must not be null!");
        Objects.requireNonNull(namePart, "NamePart must not be null!");
        Objects.requireNonNull(namePart[0], "NamePart must have at least one element!");
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
