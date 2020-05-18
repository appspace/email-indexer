What you would need to re-build the project: 
- Java JDK 8 or later. Doesn't matter if it's Sun/Oracle original or OpenJDK. 
- Gradle (gradle.org)

To run the project, you would need: 
- Java JDK or JVM 8 or higher. If you were able to build it, then you already have it.

1. To (re)compile the project, use: `./compile` command. 
It simply calls `gradle classes` which compiles all classes from src/main/java into /build/classes folder. 
You can also use `gradle test` to run all unit-tests.

2. To run indexing, use `./index {path to folder} {path to database file}`. You can use relative or absolute paths.
This command calls `java` with DatabaseBuilder referred as starting point. It also adds /build/classes/ to classpath 
so that java can find the main class.

3. To execute search, use `./search {path to database file} {word to find}`.