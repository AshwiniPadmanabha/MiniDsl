MiniDsl is an Xtext project that accepts simple English-like rules to process a Person object. It parses, validates and generates a java class.
The way to test your DSL statements end-to-end.
 * Use the existing unit tests 
 * Add your own DSL statements into test files
 * Example:
 * if age > 18 then repeat name 4 times otherwise repeat name 2 times.
 * if gender == "Male" then repeat name 3 times otherwise repeat name 1 times.
 * if age >= 60 then repeat name 5 times.
 * Run specific tests or all tests
 * Run main class at com.automotive.minidsl.App and sample java class for the statement used in App is displayed in console.
