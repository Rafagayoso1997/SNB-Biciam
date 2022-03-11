package operators.initialSolution;

import operators.heuristics.HeuristicOperatorType;
import operators.interfaces.ILongShortSeries;
import problem.definition.State;

import java.util.ArrayList;

public class LSSSimpleRoundSolution extends SimpleRoundSolution implements ILongShortSeries {
    @Override
    public State generateCalendar(ArrayList<HeuristicOperatorType> heuristics){
        State state = super.generateCalendar(heuristics);

        //DEBUG
        System.out.println("\tsate dates size: "+state.getCode().size());

        splitSeries(state);

        //DEBUG
        System.out.println("\tsate dates size: "+state.getCode().size());

        return state;
    }
}