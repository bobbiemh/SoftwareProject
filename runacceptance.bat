cd acceptance

javac -cp .;..\build\classes\main Keywords.java

java -cp .;..\build\classes\main;\robotframework-2.9.jar org.robotframework.RobotFramework AcceptanceTest.txt

cd ..
