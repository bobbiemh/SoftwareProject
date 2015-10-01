cd acceptance

javac -cp .;..\build\classes\main Keywords.java HKeywords.java

java -cp .;..\build\classes\main;C:\Users\Shay\Documents\RobotFramework\robotframework-2.9.jar org.robotframework.RobotFramework AcceptanceTest.txt HKeywords.txt
cd ..
