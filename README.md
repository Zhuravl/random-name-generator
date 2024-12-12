# RandomName Generator

This project provides a utility for generating random names based on language, gender, and name parts (first, middle,
and last names). It can be used for generating realistic names in various formats for testing, randomization, or
simulation purposes.

## Features

- Generate a random **first name** based on language and gender.
- Generate a random **last name** based on language and gender.
- Generate a random **middle name** based on language and gender.
- Generate a **full name** (first, middle, and last names).
- Generate a **reversed full name** (last, middle, and first names, typically used in Cyrillic-style languages).
- Generate a **first and last name** combination.
- Generate a **last and first name** combination.

## Example Usage

```java
Language language = Language.ENGLISH;
Gender gender = Gender.MALE;

String firstName = RandomName.getFirstName(language, gender);
String lastName = RandomName.getLastName(language, gender);
String fullName = RandomName.getFullName(language, gender);

System.out.

println("First Name: "+firstName);
System.out.

println("Last Name: "+lastName);
System.out.

println("Full Name: "+fullName);
```

## Requirements

- Java 8 or later
- The necessary name files (stored as `.txt` files) for each combination of language, gender, and name part should be
  included in the resources folder.

## How it Works

The `RandomName` class provides various methods to generate random names by reading from pre-defined text files. These
files contain lists of names for different languages and genders, with each name part (first, middle, last) stored in
separate files. The class randomly selects a name from the appropriate file based on the parameters provided.

## Contributing

If you wish to contribute to the project, feel free to submit a pull request. Please make sure to add any missing name
files or enhance the code with new features as needed.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
