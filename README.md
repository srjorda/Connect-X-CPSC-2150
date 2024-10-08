# Project One
Part 1 due: 9-15 @ 11:59 PM \
Part 2 due: 9-20 @ 11:59 PM \
Final Submission due: 9-25 @ 11:59 PM \
This project should be runnable with JDK17
--------------------------------------------------------
# Project Two
Full Report due: 10-13 @ 11:59 PM
--------------------------------------------------------
# Project Three
Full Report due 11-3 @ 11:59 PM
--------------------------------------------------------
# Project Four
Full Report due 11-21 @ 11:59 PM
--------------------------------------------------------
## Contribution Statements:
Skyler Wolf (sWolf-7) 
- Wrote params and descriptions for all methods, also wrote user stories, and made minor edits to UML diagrams.
- Fixed some memory issues with driver
- Converted UML's to draw.io images for readability
- Wrote logic conversion to allow for variability in gameboard and number of players
- Wrote initial Makefile, and finished working Makefile
- Wrote Makefile directions located at end of file
- Updated user stories and non-functional requirements
- Updated UML diagrams with current code implementations (i.e. the addition of GameBoardMem.java)
- Updated Makefile for test compilation
- Helped write test cases + documentation for testGB

Robby Infinger (RobbyInfinger) 
- Wrote pre, post conditions, invariants, method descriptions mostly in GameBoard.java and completed GameBoard.java UML Diagram.
- Reformatted some comments to allow for easier readability and functionality.
- Rewrote our makefile to follow the guidelines in the project description (REMOVED REQUIREMENT).
- Editied the UML Diagrams located in our project report to display changes we've made in the code.
- Changed some of the inline if / else statements to {} statements to prevent issues in the future with accidentally adding bad encapsulations.
- Added whitespace in many of the functions to make readability better and spreading out different sections of the code so we can easilly see where to add data.
- Wrote initial implementation of memory efficiency vs speed checker.
- Edited our code to meet a more readable format and helped create a more stylistically consistent code environment.
- Added some additional functional and nonfunctional requirements to our list.
- Added JavaDocs.
- Created initial test functionality for drop token.
- Added testing functionality for check diagWin, check isPlayerAt, and droptoken.
- Edited document to explain the tests for diagonal win.

Nicholas Jordan (BallyhooAndBigTop)
- Revised UML diagrams to account for the interface and abstract class
- Edited contracts in GameBoard
- Cleaned up code by removing commented out functions/"dead code"
- Improved interface specification
- Wrote test and documentation for checkIfFree
- Revised tests and test documentation for checkDiagWin, isPlayerAtPos, and dropToken to test more diverse circumstances
  and better comply with preconditions/invariants
- Ironed out various discrepancies between tests, documentation, and preconditions/invariants

Samuel Jordan (srjorda)
- Wrote implementation for GameBoardMem
- Rewrote IGameBoard.toString() to meet new requirements
- Helped write the additions to main() that allow for multiple players and resizeable boards
- Revised some logic and cut down on variables in main()
- Made some necessary revisions to contracts after implementing GameBoardMem
- set up initial Test files and path structure such that the tests could easily be added and recognized by IntelliJ
- updated Makefile with proper test file paths
- wrote factory, matrix generation, and matrix-string conversion functions for use in testing
- wrote tests and documentation for Constructor, whatsAtPos, checkVertWin, and checkHorizWin
- helped rewrite existing tests to follow invariants and preconditions and redid documentation to match
- wrote documentation for isPlayerAtPos and dropToken
- added asserts to all necessary tests to confirm that the state of the board does not change when checking

HOW TO: Makefile
- To use the Makefile within our repository, there are a few necessary steps to take.
1. Make sure you have the repository stored on your local system (or stored in an environment easily accessible from the terminal).
2. Move to the directory where the Makefile is located, file path should look something like ~\project-1-team-betternamepending\ where the ~ represents your specific file path.
3. In your terminal, you will use make (which builds the program) then make run (which runs the program).
4. Once you are finished with the project, please use make clean (which cleans up excess files i.e. .class files generated by the Makefile).
----------------------------------------------------------------------------------------
HOW TO: Test cases
- To make the test cases, follow these steps along with the Makefile steps above
1. After you follow steps 1 and 3 in the Makefile steps, use make testGB or make testGBmem in your terminal
2. Watch as the test cases are ran, record the output (if needed)
3. Once you are finished with the project, please use make clean to clean up files created by the make command
