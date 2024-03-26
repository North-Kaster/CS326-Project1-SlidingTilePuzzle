package search_problems;

import core_search.Problem;
import core_search.Tuple;

import java.util.*;

import static java.util.Collections.swap;
public class SlidingTilePuzzle implements Problem<List<Integer>,String> {
    // 0 means empty cell

    //8-tile test case
//    private final List<Integer> INITIAL_STATE =
//            new ArrayList<>(List.of(7,2,4, 5,0,6, 8,3,1));
//    private final List<Integer>  GOAL_STATE =
//            new ArrayList<>(List.of(0,1,2, 3,4,5, 6,7,8));

    // 15-tile test case
    private final List<Integer> INITIAL_STATE =
            new ArrayList<>(List.of(12,1,2,15, 11,6,5,8, 7,10,9,4, 0,13,14,3));
    private final List<Integer>  GOAL_STATE =
            new ArrayList<>(List.of(1,2,3,4, 5,6,7,8, 9,10,11,12, 13,14,15,0));
    private final int BOARD_SIZE;
    public SlidingTilePuzzle(){
        BOARD_SIZE = (int) Math.sqrt(INITIAL_STATE.size());
    }

    public List<Tuple<List<Integer>, String>> execution(List<Integer> s)
    {
        //Step 1: Find the position of the empty cell
        int emptyCellPosition = -1;
        for (int i=0; i< s.size(); i++ ) {
            if(s.get(i) == 0){
                emptyCellPosition = i;
                break;
            }
        }
        //Step 2: find the row# and column# of the empty cell
        int row = emptyCellPosition/BOARD_SIZE;
        int column = emptyCellPosition % BOARD_SIZE;
        List<Tuple<List<Integer>, String>> returnedStates = new ArrayList<>();
        List<Integer> copyState = new ArrayList<>(s);

        if(row > 0) { // We can move up,
            List<Integer> newState = new ArrayList<>(copyState);
            swap(newState, emptyCellPosition, emptyCellPosition - BOARD_SIZE);
            returnedStates.add(new Tuple<>(newState, "up", 1));
        }
        if(row < BOARD_SIZE-1) { // We can move down
            List<Integer> newState = new ArrayList<>(copyState);
            swap(newState, emptyCellPosition, emptyCellPosition + BOARD_SIZE);
            returnedStates.add(new Tuple<>(newState, "down", 1));
        }
        if(column > 0) { // We can move left
            List<Integer> newState = new ArrayList<>(copyState);
            swap(newState, emptyCellPosition, emptyCellPosition - 1);
            returnedStates.add(new Tuple<>(newState, "left", 1));
        }
        if(column < BOARD_SIZE-1) { // We can move right
            List<Integer> newState = new ArrayList<>(copyState);
            swap(newState, emptyCellPosition, emptyCellPosition + 1);
            returnedStates.add(new Tuple<>(newState, "right", 1));
        }
        return returnedStates;
    }

    public List<Integer> initialState() {
        return INITIAL_STATE;
    }
    public List<Integer> goalState() {
        return GOAL_STATE;
    }
    public void printState(List<Integer> state){
        // Formatting the print to look like a board instead of an array
        int count = 0;
        for (Integer integer : state) {
            System.out.print(integer + " ");
            count++;
            if (count % BOARD_SIZE == 0) {
                System.out.println(); // Print new line based on board size
            }
        }
        System.out.println(); // Add a new line at the end to separate prints
    }

    // Heuristic builders
    public int buildSumOfDistances(List<Integer> state) {
        int sumOfDistances = 0;
        for (int i = 0; i < state.size(); i++) {
            int currentValue = state.get(i);
            if (currentValue != 0) {
                int goalIndex = GOAL_STATE.indexOf(currentValue);
                int currentRow = i % BOARD_SIZE;
                int currentColumn = i / BOARD_SIZE;
                int goalRow = goalIndex % BOARD_SIZE;
                int goalColumn = goalIndex / BOARD_SIZE;
                sumOfDistances += Math.abs(currentRow - goalRow) + Math.abs(currentColumn - goalColumn);
            }
        }
        return sumOfDistances;
    }

    public int buildMisplacedTiles(List<Integer> state) {
        int numberOfMisplacedTiles = 0;
        for (int i = 0; i < state.size(); i++) {
            if (!Objects.equals(state.get(i), GOAL_STATE.get(i))){
                numberOfMisplacedTiles++;
            }
        }
        return numberOfMisplacedTiles;
    }
}
