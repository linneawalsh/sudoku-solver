# sudoku-solver
Attempting to solve sudoku puzzles by programming human logic

Some notes:  
Puzzles are imported via a 2D array in the main class  
Can solve pretty much any easy or medium difficulty level puzzle  
There is a spot where possibilities in each square (sometimes called "penciled in", I call it a "mini grid") are stored

Functionality to be added next:  
Hard puzzle solving (probably a naive search)  
A way to input puzzles other than directly into the source code (terminal?)

Situations it can solve-

* 8/9 numbers in a row are filled in (emptySpotRow())

* 8/9 numbers in a column are filled in (emptySpotCol())

* 8/9 numbers in a box are filled in (emptySpotBox())

* Only one possible location for the number in a particular box (onlyOneInBox())

* Only one possible location for the number in a particular row (onlyOneInRow())

* Only one possible location for the number in a particular column (onlyOneInCol())

* 8/9 numbers cannot fill a square because they are in the same row, box, or column (miniGridLengthOne())


Situations it cannot solve-

* "Locked candidate" (all possibilities for a number in a box are in the same row/column, so no other square in that row/column can have that number, OR all possibilities for a number in a row/column are in the same box, so no other square in that box can have that number)

* Advanced techniques: X-Wing, Swordfish, Forced Chain, XY-Wing, or Unique Rectangle

## Running the solver

Either use IDE run button, or from the command line:

    del *.class && javac *.java && java Sudoku
