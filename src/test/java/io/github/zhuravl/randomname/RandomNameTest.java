package io.github.zhuravl.randomname;

import io.github.zhuravl.randomname.enums.Gender;
import io.github.zhuravl.randomname.enums.Language;
import io.github.zhuravl.randomname.enums.NamePart;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RandomNameTest {

    @DataProvider(name = "validInputs")
    public Object[][] validInputs() {
        return new Object[][]{
                {Language.ENG, Gender.MALE, new NamePart[]{NamePart.FIRST}},
                {Language.ENG, Gender.FEMALE, new NamePart[]{NamePart.LAST}},
                {Language.UKR, Gender.MALE, new NamePart[]{NamePart.FIRST, NamePart.LAST}},
                {Language.UKR, Gender.FEMALE, new NamePart[]{}}
        };
    }

    @DataProvider(name = "invalidInputs")
    public Object[][] invalidInputs() {
        return new Object[][]{
                {null, Gender.MALE, new NamePart[]{NamePart.FIRST}},
                {Language.ENG, null, new NamePart[]{NamePart.LAST}},
                {Language.ENG, Gender.MALE, null}
        };
    }

    @Test(dataProvider = "validInputs")
    public void testGetNameWithValidInputs(Language language, Gender gender, NamePart[] nameParts) {
        String randomName = RandomName.getName(language, gender, nameParts);
        Assert.assertNotNull(randomName, "Generated name should not be null");
        Assert.assertFalse(randomName.isEmpty(), "Generated name should not be empty");
    }

    @Test(dataProvider = "invalidInputs", expectedExceptions = NullPointerException.class)
    public void testGetNameWithInvalidInputs(Language language, Gender gender, NamePart[] nameParts) {
        RandomName.getName(language, gender, nameParts);
    }

    @Test
    public void testGetNameWithDefaults() {
        String randomName = RandomName.getName();
        Assert.assertNotNull(randomName, "Generated name should not be null");
        Assert.assertFalse(randomName.isEmpty(), "Generated name should not be empty");
    }

    @Test
    public void testGetNameWithRandomLanguageAndGender() {
        String randomName = RandomName.getName(NamePart.FIRST);
        Assert.assertNotNull(randomName, "Generated name should not be null");
        Assert.assertFalse(randomName.isEmpty(), "Generated name should not be empty");
    }

    @Test
    public void testNormalizeNameParts() {
        NamePart[] nameParts = {};
        String[] normalizedParts = RandomName.getName(Language.ENG, Gender.MALE, nameParts).split(" ");
        Assert.assertEquals(normalizedParts.length, 2, "Default name parts should include LAST and FIRST");
    }
}
