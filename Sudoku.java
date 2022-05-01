import java.util.*;
import java.util.stream.*;

public class Sudoku{
static int[][] OverallGrid;
static ArrayList<ArrayList<ArrayList<Integer>>> MiniGrid;
   public static void main(String[] args){
      OverallGrid = new int[][] {{0, 0, 0, 0, 0, 0, 8, 0, 0},
                                 {2, 9, 0, 0, 0, 1, 0, 0, 0},
                                 {0, 0, 0, 0, 7, 4, 0, 0, 2},
                                 {0, 2, 9, 4, 0, 0, 3, 8, 0},
                                 {7, 0, 8, 0, 0, 0, 5, 0, 0},
                                 {5, 0, 6, 0, 0, 2, 0, 0, 0},
                                 {0, 0, 0, 1, 4, 7, 0, 0, 0},
                                 {4, 0, 0, 0, 0, 3, 0, 1, 0},
                                 {0, 1, 0, 2, 6, 0, 0, 4, 0}};
      MiniGrid = new ArrayList<>();
      fillMiniGrid();
      updateMiniGrid();
      System.out.println("Inputted Grid:");
      printGrid();
      boolean didAnything = false; 
      System.out.println();
      while(!isSolved()){
         didAnything = false;
         updateMiniGrid();
         if(emptySpotRow() || emptySpotCol() || emptySpotBox() || MiniGridLengthOne() || onlyOneInBox() || onlyOneInRow() || onlyOneInCol()){
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
               System.out.print(" | ");
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
     int[] allNums = new int[]{1,2,3,4,5,6,7,8,9}; 
     for(int i = 0; i < 9; i++){ //check over each row to see that it has all 9 nums
      ArrayList<Integer> theseNums = new ArrayList<>();
         for(int num : allNums){
            theseNums.add(num);
         }
      for(int j = 0; j < 9; j++){
         Integer theNum = OverallGrid[i][j];
         if(theNum == 0){
            return false;
         }   
         else if(!theseNums.contains(theNum)){
            System.out.println("There is an error!");
            System.exit(0);
            return false;
         }
         else{
            theseNums.remove(theNum);
         }
      }
      if(theseNums.size() != 0){
         return false;
      }
     }
     for(int i = 0; i < 9; i++){ //check over each col to see that it has all 9 nums
      ArrayList<Integer> theseNums = new ArrayList<>();
         for(int num : allNums){
            theseNums.add(num);
         }
      for(int j = 0; j < 9; j++){
         Integer theNum = OverallGrid[j][i];
         if(theNum == 0){
            return false;
         }   
         else if(!theseNums.contains(theNum)){
            System.out.println("There is an error!");
            System.exit(0);
            return false;
         }
         else{
            theseNums.remove(theNum);
         }
      }
      if(theseNums.size() != 0){
         return false;
      }
     }
     for(int i = 0; i < 3; i++){ //check over each box
      for(int j = 0; j < 3; j++){
         ArrayList<Integer> theseNums = new ArrayList<>();
            for(int num : allNums){
               theseNums.add(num);
            }
         for(int k = i*3; k < (i*3)+3; k++){
            for(int l = j*3; l < (j*3)+3; l++){
               Integer theNum = OverallGrid[k][l];
               if(theNum == 0){
                  return false;
               }   
               else if(!theseNums.contains(theNum)){
                  System.out.println("There is an error!");
                  System.exit(0);
                  return false;
               }
               else{
                  theseNums.remove(theNum);
               }
            }
         }
         if(theseNums.size() != 0){
            return false;
         }
      }
     }
     return true;
   }
   
   //fixes condition where there is only one empty spot in the row
   public static boolean emptySpotRow(){
      for(int i = 0; i < 9; i++){ //loop through each row, check if it has more than one 0
         int zeroes = 0;
         int zeroPos = -1;
         for(int j = 0; j < 9; j++){
            if(OverallGrid[i][j] == 0){
               if(zeroes == 0){
                  zeroes++;
                  zeroPos = j;
               }
               else{
                  zeroPos = -1;
               }
            }
         }
         if(zeroPos >= 0){
            Set<Integer> allNums = new HashSet<>();
            allNums.add(0); allNums.add(1); allNums.add(2); allNums.add(3); allNums.add(4); allNums.add(5); allNums.add(6); allNums.add(7); allNums.add(8); allNums.add(9);
            for(int k = 0; k < 9; k++){
               allNums.remove(OverallGrid[i][k]);
            }
            int theMissingNum = -1;
            for(Integer val : allNums){
               theMissingNum = val;
            }
            OverallGrid[i][zeroPos] = theMissingNum;
            return true;
         }
      }
      return false;
   }
   
   //fixes condition where there is only one empty spot in the column
   public static boolean emptySpotCol(){
      for(int i = 0; i < 9; i++){ //loop through each column, check if it has more than one 0
         int zeroes = 0;
         int zeroPos = -1;
         for(int j = 0; j < 9; j++){
            if(OverallGrid[j][i] == 0){
               if(zeroes == 0){
                  zeroes++;
                  zeroPos = j;
               }
               else{
                  zeroPos = -1;
               }
            }
         }
         if(zeroPos >= 0){
            Set<Integer> allNums = new HashSet<>();
            allNums.add(0); allNums.add(1); allNums.add(2); allNums.add(3); allNums.add(4); allNums.add(5); allNums.add(6); allNums.add(7); allNums.add(8); allNums.add(9);
            for(int k = 0; k < 9; k++){
               allNums.remove(OverallGrid[k][i]);
            }
            int theMissingNum = -1;
            for(Integer val : allNums){
               theMissingNum = val;
            }
            OverallGrid[zeroPos][i] = theMissingNum;
            return true;
         }
      }
      return false;
   }
   
   //fixes condition where there is only one empty spot in the box
   public static boolean emptySpotBox(){
      for(int i = 0; i < 3; i++){ //loop through each box, check if it has more than one 0
         for(int j = 0; j < 3; j++){
            int zeroes = 0;
            for(int k = i*3; k < (i*3)+3; k++){
               for(int l = j*3; l < (j*3)+3; l++){
                  if(OverallGrid[k][l] == 0){
                     zeroes++;
                  }
               }
            }
            if(zeroes == 1){
               int emptyPosX = 0;
               int emptyPosY = 0;
               ArrayList<Integer> nums = new ArrayList<>();
               nums.add(1); nums.add(2); nums.add(3); nums.add(4); nums.add(5); nums.add(6); nums.add(7); nums.add(8); nums.add(9);
               for(int m = i*3; m < (i*3)+3; m++){
                  for(int n = j*3; n < (j*3)+3; n++){
                     if(OverallGrid[m][n] == 0){
                        emptyPosX = m;
                        emptyPosY = n;
                     }
                     else{
                        nums.remove(Integer.valueOf(OverallGrid[m][n]));
                     }
                  }
               }
               OverallGrid[emptyPosX][emptyPosY] = nums.get(0);
               return true;
            }
         }
      }
      return false;
   }
   
   //fills mini grid with all possible numbers
   public static void fillMiniGrid(){
      List<Integer> allNums = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList());
      for(int i = 0; i < 9; i++){
         MiniGrid.add(new ArrayList<>());
         for(int j = 0; j < 9; j++){
            MiniGrid.get(i).add(new ArrayList<>(allNums));
         }
      }
   }
   
   //removes impossible values from Mini Grid
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
   
   //Checks for the case when the mini grid says there is only one possibility
   public static boolean MiniGridLengthOne(){
      for(int i = 0; i < 9; i++){
         for(int j = 0; j < 9; j++){
            if(MiniGrid.get(i).get(j) != null && MiniGrid.get(i).get(j).size() == 1){
               OverallGrid[i][j] = MiniGrid.get(i).get(j).get(0);
               return true;
            }
         }
      }
      return false;
   }
   
   //Checks for the condition where there is only one possible location in a box
   public static boolean onlyOneInBox(){
      for(int i = 0; i < 3; i++){
         for(int j = 0; j < 3; j++){
            ArrayList<Integer> allNums = new ArrayList<>();
            allNums.add(1); allNums.add(2); allNums.add(3); allNums.add(4); allNums.add(5); allNums.add(6); allNums.add(7); allNums.add(8); allNums.add(9); 
            for(int k = i*3; k < (i*3)+3; k++){
               for(int l = j*3; l < (j*3)+3; l++){
                  allNums.remove(Integer.valueOf(OverallGrid[k][l])); //allNums now only contains the missing numbers in the box
               }
            }
            for(Integer num : allNums){ //for each missing number
               int occ = 0;
               for(int m = i*3; m < (i*3)+3; m++){ //checks to see if there is only one possible place for that number to go
                  for(int n = j*3; n < (j*3)+3; n++){
                     if(MiniGrid.get(m).get(n) != null && MiniGrid.get(m).get(n).contains(num)){
                        occ++;
                     }
                  }
               }
               if(occ == 1){ //if there is only one place, we need to find it
                  for(int m = i*3; m < (i*3)+3; m++){
                     for(int n = j*3; n < (j*3)+3; n++){
                        if(MiniGrid.get(m).get(n) != null && MiniGrid.get(m).get(n).contains(num)){
                           OverallGrid[m][n] = num;
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }
      return false;
   }
   
   //Checks for the condition where a value can only be in one place in the row
   public static boolean onlyOneInRow(){
      for(int i = 0; i < 9; i++){
         ArrayList<Integer> allNums = new ArrayList<>();
         allNums.add(1); allNums.add(2); allNums.add(3); allNums.add(4); allNums.add(5); allNums.add(6); allNums.add(7); allNums.add(8); allNums.add(9);
         for(int j = 0; j < 9; j++){
            allNums.remove(Integer.valueOf(OverallGrid[j][i])); //allNums now only contains the missing numbers in the row
         }
         for(Integer num : allNums){
            int occ = 0;
            for(int k = 0; k < 9; k++){
               if(MiniGrid.get(i).get(k) != null && MiniGrid.get(i).get(k).contains(num)){
                  occ++;
               }
            }
            if(occ == 1){
               for(int l = 0; l < 9; l++){
                  if(MiniGrid.get(i).get(l) != null && MiniGrid.get(i).get(l).contains(num)){
                     OverallGrid[i][l] = num;
                     return true;
                  }
               }
            }
         }
      }  
      return false;
   }
   
   //Checks for the condition where a value can only be in one place in the column
   public static boolean onlyOneInCol(){
      for(int i = 0; i < 9; i++){
         ArrayList<Integer> allNums = new ArrayList<>();
         allNums.add(1); allNums.add(2); allNums.add(3); allNums.add(4); allNums.add(5); allNums.add(6); allNums.add(7); allNums.add(8); allNums.add(9);
         for(int j = 0; j < 9; j++){
            allNums.remove(Integer.valueOf(OverallGrid[j][i])); //allNums now only contains the missing numbers in the column
         }
         for(Integer num : allNums){
            int occ = 0;
            for(int k = 0; k < 9; k++){
               if(MiniGrid.get(k).get(i) != null && MiniGrid.get(k).get(i).contains(num)){
                  occ++;
               }
            }
            if(occ == 1){
               for(int l = 0; l < 9; l++){
                  if(MiniGrid.get(l).get(i) != null && MiniGrid.get(l).get(i).contains(num)){
                     OverallGrid[l][i] = num;
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }
   
   //Checks for a locked candidate in a box, where all possibilities are in the same row or column
   public static boolean lockedBox(){
      return false;
   }
}
