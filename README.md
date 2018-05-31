# Students
A program that is parsing a JSON file content into a Java objects and storing the info in back up JSON file.

### Possible inputs ###:

Two arguments can be passed to the program. At least one argument should be passed in order the program to work correctly.
The first argument should be a valid path to a JSON file otherwise the program will indicate a problem.
The second parameter is optional - it should be a valid ID coresponding to an ID from the presented file.

### Possible outputs ###

If only one valid parameter is passed the program will print the list with the records from the file on the console.
If two parameters are passed the program will print the record coresponding to the ID passed as second parameter if is an existing 
ID(if there is a record with this ID). If the ID is not presented in the file(the passed path) the program will print that there is no such
element and will print the full list.
If the first parameter is incorectly passed(the path is invalid) the program will print a list with records if there is existing bkp file.
