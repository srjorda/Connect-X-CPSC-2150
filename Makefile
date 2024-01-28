#The following makefile will compile and run our gameBoard.
#Skyler Wolf (s-Wolf7)
#Robby Infinger (RobbyInfinger)
#Samuel Jordan (srjorda)
#Nicholas Jordan (BallyhooAndBigTop)

default: 
        javac cpsc2150/extendedConnects/*.java cpsc2150/extendedConnectX/models/*.java 

run: cpsc2150/extendedConnects/*.class
        java cpsc2150.extendedConnects.GameScreen

test:
        javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedConnectX/tests/*.java cpsc2150/extendedConnectX/models/*.java

testGB: cpsc2150/extendedConnectX/tests/TestGameBoard.class cpsc2150/extendedConnectX/models/*.java
        java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.tests.TestGameBoard

testGBmem: cpsc2150/extendedConnectX/tests/TestGameBoardMem.class cpsc2150/extendedConnectX/models/*.java
        java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.tests.TestGameBoardMem

clean: 
        rm -f cpsc2150/extendedConnects/*.class cpsc2150/extendedConnectX/models/*.class cpsc2150/extendedConnectX/tests/*.class
