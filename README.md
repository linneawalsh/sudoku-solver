# sudoku-solver
Attempting to solve sudoku puzzles by programming human logic

The current sitch:
Puzzles are imported via a 2D array in the main class

Situations it can solve-

*8/9 numbers in a row are filled in

*8/9 numbers in a column are filled in

*8/9 numbers in a box are filled in

*Only one possible location for the number in a particular box

*Only one possible location for the number in a particular row

*Only one possible location for the number in a particular column

*8/9 numbers cannot fill a square because they are in the same row, box, or column

*There is a spot where possibilities in each square (sometimes called "penciled in", I call it a "mini grid") are stored


Situations it cannot solve-

*Locked candidate (all possibilities for a number in a box are in the same row/column, so no other square in that row/column can have that number, OR all possibilities for a number in a row/column are in the same box, so no other square in that box can have that number)

## Running the solver

Either use IDE run button, or from the command line:

    del *.class && javac *.java && java Sudoku
