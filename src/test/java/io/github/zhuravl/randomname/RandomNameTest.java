package io.github.zhuravl.randomname;

import io.github.zhuravl.randomname.enums.Gender;
import io.github.zhuravl.randomname.enums.Language;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class RandomNameTest {

    @Test
    public void testGetFirstName() {
        String firstName = RandomName.getFirstName(Language.ENG, Gender.MALE);
        assertNotNull(firstName, "First name should not be null.");
        assertFalse(firstName.isEmpty(), "First name should not be empty.");
    }

    @Test
    public void testGetLastName() {
        String lastName = RandomName.getLastName(Language.ENG, Gender.FEMALE);
        assertNotNull(lastName, "Last name should not be null.");
        assertFalse(lastName.isEmpty(), "Last name should not be empty.");
    }

    @Test
    public void testGetMiddleName() {
        String middleName = RandomName.getMiddleName(Language.UKR, Gender.MALE);
        assertNotNull(middleName, "Middle name should not be null.");
        assertFalse(middleName.isEmpty(), "Middle name should not be empty.");
    }

    @Test
    public void testGetFullName() {
        String fullName = RandomName.getFullName(Language.UKR, Gender.FEMALE);
        assertNotNull(fullName, "Full name should not be null.");
        assertFalse(fullName.isEmpty(), "Full name should not be empty.");
        assertTrue(fullName.split(" ").length == 3, "Full name should have three parts.");
    }

    @Test
    public void testGetFirstLastName() {
        String firstLastName = RandomName.getFirstLastName(Language.ENG, Gender.MALE);
        assertNotNull(firstLastName, "First and last name should not be null.");
        assertFalse(firstLastName.isEmpty(), "First and last name should not be empty.");
        assertTrue(firstLastName.split(" ").length == 2, "First and last name should have two parts.");
    }

    @Test
    public void testGetLastFirst() {
        String lastFirstName = RandomName.getLastFirstName(Language.UKR, Gender.FEMALE);
        assertNotNull(lastFirstName, "Last and first name should not be null.");
        assertFalse(lastFirstName.isEmpty(), "Last and first name should not be empty.");
        assertTrue(lastFirstName.split(" ").length == 2, "Last and first name should have two parts.");
    }

    @Test
    public void testNameGenerationExceptionMessage() {
        RuntimeException exception = new RuntimeException("Test exception message");
        assertEquals(exception.getMessage(), "Test exception message", "Exception message should match input message.");
    }

    @Test
    public void testNameGenerationExceptionCause() {
        IOException cause = new IOException("IO Error");
        RuntimeException exception = new RuntimeException("Test exception with cause", cause);
        assertEquals(exception.getMessage(), "Test exception with cause", "Exception message should match input message.");
        assertEquals(exception.getCause(), cause, "Exception cause should match input cause.");
    }
}
