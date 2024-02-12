package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

public class SlidingTileAStarSum extends BaseSearch<List<Integer>, String> {

    public SlidingTileAStarSum() {
        super(new SlidingTilePuzzle(),
                new SortedQueue<>(new SlidingTileAStarSum.CompareEstimates(new SlidingTilePuzzle())));
    }
    public static void main(String[] args) {
        SlidingTileAStarSum agent = new SlidingTileAStarSum();
        agent.search();
    }
    public static class CompareEstimates implements Comparator<Node<List<Integer>, String>> {

        private final SlidingTilePuzzle problem;
        public CompareEstimates(SlidingTilePuzzle problem) {
            this.problem = problem;
        }
        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            if(problem.buildSumOfDistances(o1.getState()) + o1.getPathCost() <
                    problem.buildSumOfDistances(o2.getState()) + o2.getPathCost()){
                return -1;
            }else if(problem.buildSumOfDistances(o1.getState()) + o1.getPathCost() ==
                    problem.buildSumOfDistances(o2.getState()) + o2.getPathCost()){
                return 0;
            }else{
                return 1;
            }
        }
    }
}
