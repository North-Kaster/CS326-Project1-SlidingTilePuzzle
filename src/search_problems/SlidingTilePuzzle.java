package search_problems;

import core_search.Problem;
import core_search.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.swap;
public class SlidingTilePuzzle implements Problem<List<Integer>,String> {
    // empty cell is 0
    private final List<Integer> INITIAL_STATE =
            new ArrayList<>(List.of(7,2,4, 5,0,6, 8,3,1));
    private final List<Integer>  GOAL_STATE =
            new ArrayList<>(List.of(0,1,2, 3,4,5, 6,7,8));
    private final int BOARD_SIZE;

    private final Map<String, Integer> estimatedDistances = new HashMap<>();
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

    // Heuristics

    public int buildSumOfDistances(List<Integer> state) {
        int sum = 0;
        for (int i = 0; i < state.size(); i++) {
            int value = state.get(i);
            if (value != 0) {
                int goalIndex = state.indexOf(i);
                int currentRow = i % BOARD_SIZE; // current row
                int currentColumn = i / BOARD_SIZE; // current column
                int goalRow = goalIndex % BOARD_SIZE; // goal row
                int goalColumn = goalIndex / BOARD_SIZE; // goal column
                sum += Math.abs(currentRow - goalRow) + Math.abs(currentColumn - goalColumn);
            }
        }
        return sum;
    }
}
