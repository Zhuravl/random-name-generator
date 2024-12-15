# Random Name

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://example.com/build-status) [![Maven Central](https://img.shields.io/maven-central/v/io.github.zhuravl/randomname)](https://search.maven.org/artifact/io.github.zhuravl/randomname) [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://mit-license.org/)

A utility library for generating random but real names based on language, gender, and name parts. This library is
designed to be
lightweight and extensible for use in test data generation, simulations, or other applications requiring randomized
names.

## Features

- Generate random names with configurable:
  - Language
  - Gender
  - Name parts (first, last, etc.)
- Support for multiple languages and genders
- Customizable file-based name data storage

## Installation

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.zhuravl</groupId>
    <artifactId>randomname</artifactId>
  <version>1.0.1</version>
</dependency>
```

Check out the '[Maven Central / randomname](https://central.sonatype.com/artifact/io.github.zhuravl/randomname)'
repository to get the latest version.
## Usage

### Basic Name Generation

```java
public class Main {
  public static void main(String[] args) {
    System.out.println("Generated names:");
    for (int i = 0; i < 5; i++) {
      String randomName = RandomName.getName();
      System.out.println(randomName);
    }
  }
}
```

Result output:

```
Generated names:
Крикун Сюзанна
Онищенко Тетяна
Будяк Віра
Perez Jacob
Калиненко Катерина
```

### Advanced Configuration

```java
import io.github.zhuravl.randomname.enums.Gender;
import io.github.zhuravl.randomname.enums.Language;
import io.github.zhuravl.randomname.enums.NamePart;

public class Main {
  public static void main(String[] args) {
    System.out.println("Custom generated names:");
    for (int i = 0; i < 5; i++) {
      String customName = RandomName.getName(Language.ENG, Gender.MALE, NamePart.FIRST, NamePart.LAST);
      System.out.println(customName);
    }
  }
}
```

Result output:

```
Custom generated names:
Felix Diaz
Charles Martinez
Vincent Richardson
Aaron Rogers
Walter Campbell
```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository;
2. Create a feature branch;
3. Commit your changes;
4. Create a pull request.

## License

This project is licensed under the [MIT License](https://mit-license.org).

---

Happy testing!
