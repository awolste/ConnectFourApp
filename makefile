default: cpsc2150/connectX/Connect4Game.java cpsc2150/connectX/Gameboard.java cpsc2150/connectX/IGameboard.java cpsc2150/connectX/GameBoardMem.java
	javac cpsc2150/connectX/Connect4Game.java cpsc2150/connectX/Gameboard.java cpsc2150/connectX/IGameboard.java cpsc2150/connectX/GameBoardMem.java

run: cpsc2150/connectX/Connect4Game.class cpsc2150/connectX/Gameboard.class cpsc2150/connectX/IGameboard.class cpsc2150/connectX/GameBoardMem.class
	java cpsc2150.connectX.Connect4Game cpsc2150.connectX.Gameboard cpsc2150.connectX.IGameboard cpsc2150.connectX.GameBoardMem

test: cpsc2150/connectX/Connect4Game.java cpsc2150/connectX/Gameboard.java cpsc2150/connectX/IGameboard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/TestIGameBoard.java
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/connectX/Connect4Game.java cpsc2150/connectX/Gameboard.java cpsc2150/connectX/IGameboard.java cpsc2150/connectX/Gameboard.java cpsc2150/connectX/TestIGameBoard.java

runtest: cpsc2150/connectX/Connect4Game.class cpsc2150/connectX/Gameboard.class cpsc2150/connectX/IGameboard.class cpsc2150/connectX/GameBoardMem.class cpsc2150/connectX/TestIGameBoard.class
	java  -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.connectX.TestIGameBoard

clean:
	rm -f *.class