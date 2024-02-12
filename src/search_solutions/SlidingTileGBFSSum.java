package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

public class SlidingTileGBFSSum extends BaseSearch<List<Integer>, String> {
    public SlidingTileGBFSSum() {
        super(new SlidingTilePuzzle(),
                new SortedQueue<>(new CompareEstimates(new SlidingTilePuzzle())));
    }
    public static void main(String[] args) {
        SlidingTileGBFSSum agent = new SlidingTileGBFSSum();
        agent.search();
    }
    public static class CompareEstimates implements Comparator<Node<List<Integer>, String>> {

        private final SlidingTilePuzzle problem;
        public CompareEstimates(SlidingTilePuzzle problem) {
            this.problem = problem;
        }
        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            if(problem.buildSumOfDistances(o1.getState()) <
                    problem.buildSumOfDistances(o2.getState())){
                return -1;
            }else if(problem.buildSumOfDistances(o1.getState()) ==
                    problem.buildSumOfDistances(o2.getState())){
                return 0;
            }else{
                return 1;
            }
        }
    }
}
