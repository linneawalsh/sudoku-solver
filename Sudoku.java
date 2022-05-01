import java.util.*;
//import java.util.stream.*;

public class Sudoku{
static int[][] OverallGrid;
static ArrayList<ArrayList<ArrayList<Integer>>> MiniGrid;
   public static void main(String[] args){
      OverallGrid = new int[][] {{0, 0, 9, 0, 5, 8, 0, 0, 0},
                                 {6, 0, 0, 7, 4, 3, 0, 9, 5},
                                 {0, 0, 8, 0, 9, 0, 4, 0, 0},
                                 {0, 0, 3, 5, 8, 6, 7, 0, 0},
                                 {0, 0, 0, 0, 7, 0, 3, 8, 0},
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {2, 7, 6, 8, 1, 0, 0, 0, 0},
                                 {0, 0, 5, 9, 0, 2, 0, 0, 0},
                                 {0, 0, 1, 0, 0, 0, 0, 0, 2}};


      MiniGrid = new ArrayList<>();
      fillMiniGrid();
      updateMiniGrid();

      System.out.println("Inputted Grid:");
      printGrid(); 
      System.out.println();

      boolean didAnything = false;
      while(!isSolved()){
         didAnything = false;
         updateMiniGrid();
         if(emptySpotRow() || emptySpotCol() || emptySpotBox() || MiniGridLengthOne() || onlyOneInRow() || onlyOneInCol() || onlyOneInBox()){
            didAnything = true;
         }
         if(!didAnything){
            break;
         }
      }

      if(isSolved()){
         System.out.println("Solved Grid:");
      }
      else{
         System.out.println("This is as far as I could solve:");
      }
      printGrid();
   }
   

   public static void printGrid(){
      for(int i = 0; i < 9; i++){
         if(i % 3 == 0 && i != 0){
            System.out.println("-----------------------");
         }
         for(int j = 0; j < 9; j++){
            if(j % 3 == 0 && j != 0){
               System.out.print("| ");
            }
            if(OverallGrid[i][j] == 0){
               System.out.print("  ");
            }
            else{
               System.out.print(OverallGrid[i][j] + " ");
            }
         }
         System.out.println();
      }
   }
   

   public static boolean isSolved(){ 
      ArrayList<Integer> allNums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
      for(int i = 0; i < 9; i++){ //check over each row to see that it has all 9 nums
         ArrayList<Integer> theseNums = new ArrayList<>(allNums);
         for(int j = 0; j < 9; j++){
            Integer theNum = OverallGrid[i][j];
            if(theNum == 0){ //if this spot is empty
               return false;
            }   
            else if(!theseNums.contains(theNum)){ //if another spot in this row has the same number
               System.out.println("There is an error!");
               System.exit(0);
               return false;
            }
            else{
               theseNums.remove(theNum);
            }
         }
         //don't think this bit is ever actually used?
         // if(theseNums.size() != 0){ //if not all numbers were in the row
         //    return false;
         // }
      }
      for(int i = 0; i < 9; i++){ //check over each col to see that it has all 9 nums
         ArrayList<Integer> theseNums = new ArrayList<>(allNums);
         for(int j = 0; j < 9; j++){
            Integer theNum = OverallGrid[j][i];
            if(theNum == 0){ //if this spot is empty
               return false;
            }   
            else if(!theseNums.contains(theNum)){ //if another spot in this row has the same number
               System.out.println("There is an error!");
               System.exit(0);
               return false;
            }
            else{
               theseNums.remove(theNum);
            }
         }
         //don't think this bit is ever actually used?
         // if(theseNums.size() != 0){ //if not all numbers were in the row
         //    return false;
         // }
      }
      for(int i = 0; i < 3; i++){ //check over each box
         for(int j = 0; j < 3; j++){
            ArrayList<Integer> theseNums = new ArrayList<>(allNums);
            for(int k = i*3; k < (i*3)+3; k++){
               for(int l = j*3; l < (j*3)+3; l++){
                  Integer theNum = OverallGrid[k][l];
                  if(theNum == 0){ //if this spot is empty
                     return false;
                  }   
                  else if(!theseNums.contains(theNum)){ //if another spot in this row has the same number
                     System.out.println("There is an error!");
                     System.exit(0);
                     return false;
                  }
                  else{
                     theseNums.remove(theNum);
                  }
               }
            }
            //don't think this bit is ever actually used?
            // if(theseNums.size() != 0){ //if not all numbers were in the row
            //    return false;
            // }
         }
      }
      return true;
   }
   

   //Fills each position in miniGrid with 1-9
   public static void fillMiniGrid(){
      for(int i = 0; i < 9; i++){
         MiniGrid.add(new ArrayList<>());
         for(int j = 0; j < 9; j++){
            MiniGrid.get(i).add(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
         }
      }
   }


   //Removes impossible values from MiniGrid
   public static void updateMiniGrid(){
      //Part 1 makes a mini grid null if the sudoku is solved there
      for(int i = 0; i < 9; i++){
         for(int j = 0; j < 9; j++){
            if(OverallGrid[i][j] != 0){
               MiniGrid.get(i).set(j, null);
            }
         }
      }
      //Part 2 removes numbers already in that row
      for(int i = 0; i < 9; i++){
         for(int j = 0; j < 9; j++){
            if(MiniGrid.get(i).get(j) == null){
               for(int k = 0; k < 9; k++){
                  if(MiniGrid.get(i).get(k) != null){
                     MiniGrid.get(i).get(k).remove(Integer.valueOf(OverallGrid[i][j]));
                  }
               }
            }
         }
      }
      //Part 3 removes numbers already in that column
      for(int i = 0; i < 9; i++){
         for(int j = 0; j < 9; j++){
            if(MiniGrid.get(j).get(i) == null){
               for(int k = 0; k < 9; k++){
                  if(MiniGrid.get(k).get(i) != null){
                     MiniGrid.get(k).get(i).remove(Integer.valueOf(OverallGrid[j][i]));
                  }
               }
            }
         }
      }
      //Part 4 removes numbers already in that box
      for(int i = 0; i < 3; i++){ 
         for(int j = 0; j < 3; j++){
            for(int k = i*3; k < (i*3)+3; k++){
               for(int l = j*3; l < (j*3)+3; l++){
                  if(MiniGrid.get(k).get(l) == null){
                     for(int m = i*3; m < (i*3)+3; m++){
                        for(int n = j*3; n < (j*3)+3; n++){
                           if(MiniGrid.get(m).get(n) != null){
                              MiniGrid.get(m).get(n).remove(Integer.valueOf(OverallGrid[k][l]));
                           }
                        }
                     }
                  }
               }
            }
         }         
      }
   }
   

   //Checks for the case where there is only one empty spot in the row, then fills it in
   //Returns true if it found something to add, false otherwise
   public static boolean emptySpotRow(){
      for(int i = 0; i < 9; i++){ //loop through each row, check how many numbers are present
         ArrayList<Integer> numsLeft = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

         int position = -1; //will keep track of the empty square
         for(int j = 0; j < 9; j++){
            Integer thisValue = OverallGrid[i][j];
            if(thisValue == 0){ //update position, if needed
               position = j;
            }
            numsLeft.remove(thisValue);
         }

         if(numsLeft.size() == 1){ //there is actually something to be added
            OverallGrid[i][position] = numsLeft.get(0);
            return true;
         }
      }
      return false;
   }


   //Checks for the case where there is only one empty spot in the column, then fills it in
   //Returns true if it found something to add, false otherwise
   public static boolean emptySpotCol(){
      for(int i = 0; i < 9; i++){ //loop through each column, check how many numbers are present
         ArrayList<Integer> numsLeft = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

         int position = -1; //will keep track of the empty square
         for(int j = 0; j < 9; j++){
            Integer thisValue = OverallGrid[j][i];
            if(thisValue == 0){ //update position, if needed
               position = j;
            }
            numsLeft.remove(thisValue);
         }

         if(numsLeft.size() == 1){ //there is actually something to be added
            OverallGrid[position][i] = numsLeft.get(0);
            return true;
         }
      }
      return false;
   }
   

   //Checks for the case where there is only one empty spot in the box, then fills it in
   //Returns true if it found something to add, false otherwise
   public static boolean emptySpotBox(){
      for(int i = 0; i < 3; i++){ //loop through each box, check how many numbers are present
         for(int j = 0; j < 3; j++){
            ArrayList<Integer> numsLeft = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

            int[] position = {-1, -1}; //will keep track of the coordinates of the empty square
            for(int k = i*3; k < (i*3)+3; k++){
               for(int l = j*3; l < (j*3)+3; l++){
                  Integer thisValue = OverallGrid[k][l];
                  if(thisValue == 0){ //update position, if needed
                     position[0] = k;
                     position[1] = l;
                  }
                  numsLeft.remove(thisValue);
               }
            }

            if(numsLeft.size() == 1){ //there is actually something to be added
               OverallGrid[position[0]][position[1]] = numsLeft.get(0);
               return true;
            }
         }
      }
      return false;
   }
   

   //Checks for the case when the mini grid says there is only one possibility, then adds it to the overall grid
   //Returns true if it found something to add, false otherwise
   public static boolean MiniGridLengthOne(){
      for(int i = 0; i < 9; i++){
         for(int j = 0; j < 9; j++){
            if(OverallGrid[i][j] == 0 && MiniGrid.get(i).get(j).size() == 1){
               OverallGrid[i][j] = MiniGrid.get(i).get(j).get(0);
               return true;
            }
         }
      }
      return false;
   }
   

   //Checks for the condition where a value can only be in one place in the row, then adds it to the overall grid
   //Returns true if it found something to add, false otherwise
   public static boolean onlyOneInRow(){
      for(int i = 0; i < 9; i++){ //loop through each row
         ArrayList<Integer> numsLeft = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
         for(int j = 0; j < 9; j++){
            numsLeft.remove(Integer.valueOf(OverallGrid[i][j])); //allNums now only contains the missing numbers in the row
         }
         for(Integer num : numsLeft){ //for each missing number
            int pos = -1;
            Boolean onlyOne = true;
            for(int k = 0; k < 9; k++){ //look through each spot in that row
               if(OverallGrid[i][k] == 0 && MiniGrid.get(i).get(k).contains(num)){
                  if(pos == -1){
                     pos = k;
                  }
                  else{ //found a second one
                     onlyOne = false;
                     break;
                  }
               }
            }

            if(pos != -1 && onlyOne){
               OverallGrid[i][pos] = num;
               return true;
            }
         }
      }  
      return false;
   }


   //Checks for the condition where a value can only be in one place in the column, then adds it to the overall grid
   //Returns true if it found something to add, false otherwise
   public static boolean onlyOneInCol(){
      for(int i = 0; i < 9; i++){ //loop through each column
         ArrayList<Integer> numsLeft = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
         for(int j = 0; j < 9; j++){
            numsLeft.remove(Integer.valueOf(OverallGrid[j][i])); //allNums now only contains the missing numbers in the column
         }
         for(Integer num : numsLeft){ //for each missing number
            int pos = -1;
            Boolean onlyOne = true;
            for(int k = 0; k < 9; k++){ //look through each spot in that row
               if(OverallGrid[k][i] == 0 && MiniGrid.get(k).get(i).contains(num)){
                  if(pos == -1){
                     pos = k;
                  }
                  else{ //found a second one
                     onlyOne = false;
                     break;
                  }
               }
            }

            if(pos != -1 && onlyOne){
               OverallGrid[pos][i] = num;
               return true;
            }
         }
      }  
      return false;
   }
   

   //Checks for the condition where there is only one possible location in a box, then adds it to the overall grid
   //Returns true if it found something to add, false otherwise
   public static boolean onlyOneInBox(){
      for(int i = 0; i < 3; i++){ //loop through each row of boxes
         for(int j = 0; j < 3; j++){ //loop through each box in that row
            ArrayList<Integer> numsLeft = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            for(int k = i*3; k < (i*3)+3; k++){
               for(int l = j*3; l < (j*3)+3; l++){
                  numsLeft.remove(Integer.valueOf(OverallGrid[k][l])); //allNums now only contains the missing numbers in the box
               }
            }
            for(Integer num : numsLeft){ //for each missing number
               int[] pos = {-1, -1};
               Boolean onlyOne = true;
               outerloop: //used so that we can break out of two loops at once
               for(int m = i*3; m < (i*3)+3; m++){ //look through each spot in that box
                  for(int n = j*3; n < (j*3)+3; n++){
                     if(OverallGrid[m][n] == 0 && MiniGrid.get(m).get(n).contains(num)){
                        if(pos[0] == -1){ //only need to check the first element, as they'll be changed at the same time
                           pos[0] = m;
                           pos[1] = n;
                        }
                        else{ //found a second one
                           onlyOne = false;
                           break outerloop;
                        }
                     }
                  }
               }

               if(pos[0] != -1 && onlyOne){
                  OverallGrid[pos[0]][pos[1]] = num;
                  return true;
               }
            }
         }
      }
      return false;
   }
   

   /*
   //Checks for a locked candidate in a box, where all possibilities are in the same row or column
   public static boolean lockedBox(){
      return false;
   }
   */
}
