# Software-Project

## Contributors
* amariamari23
* alexteeter
* k-williamson92
* smadden8
* bobbiemh

## Tools Used
* Java SDK
* RobotFramework
* Gradle

## Project Description
This project manages arguments from the command line and stores them.

## Features
* Help Command:
  * The help command can be accessed by using the argument "--help" or "-h" in the command line. When used, the program throws a message about you can or cannot do.
* Argument Class:
  * This class was used to set and retrieve different values for an object, such as:
    * decription (what was the purpose of the object)
    * value 
    * type (String, int, boolean, and float)
    * the shorthand (example: multiplication, m)
    * required
  * This class is a parent class with two child classes:
    * Positional Argument
      * These arguments are required in the program. 
    * Optional Argument
      * These arguments can be called by their shorthand. The user is required to give an Optional Argument a default value.
 * XMLParse Class:
  * The project allows the user to make arguments from XML code. The class uses a SAX Parser to read through the code. An example of how the XML code should be formatted can be seen from the file [VolumeCalculator.xml](https://github.com/amariamari23/SoftwareProject/blob/master/src/test/java/softwareproject/VolumeCalculator.xml)
  * The user can do this by calling the readXML function. The function only takes one argument, the file's name.
 * ParseArgs Class:
  * This class is the only one the user should have to interact with. This class allows the user to create a program with the arguments they desire. Through this class, they can do the following:
    * set the program name and description
    * create arguments
    * user can call the readXML function through a ParseArgs variable
    * sorts and stores all arguments
    * can create an XML file to store the arguments in
  * The main funciton of this class is parse(). This function sorts through all the arguments the user adds and stores them.
 * Exceptions
  * There are 4 custom exceptions:
    * HelpMessageException: prints out how the descriptions and arguments the user has set and how to use them.
    * TooLittleArgumentsException: when adding a new argument, this exception will be thrown if the user forgot a value needed
    * TooManyArgumentsException: when adding a new argument, this exception will be thrown if the user gives to many values
    * WrongArgumentException: this exception is thrown if the user gives type "boolean" and value "No"
 * JavaDoc API
  * This project has it's own API if needed.
