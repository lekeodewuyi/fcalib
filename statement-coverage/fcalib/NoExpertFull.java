//package de.tudresden.inf.tcs.fcalib;
//
//public class NoExpertFull {
//}

package de.tudresden.inf.tcs.fcalib;

import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.ObjectDescription;
import de.tudresden.inf.tcs.fcaapi.action.ExpertAction;
import de.tudresden.inf.tcs.fcalib.action.CounterExampleProvidedAction;
import de.tudresden.inf.tcs.fcalib.action.QuestionRejectedAction;
import org.apache.log4j.Logger;

public class NoExpertFull<A> extends AbstractExpert<A,String,FullObject<A,String>> {

    private int name = 0;

    private static final Logger logger = Logger.getLogger(NoExpertFull.class);

    private final FormalContext<A,String> theContext;

    public NoExpertFull(FormalContext<A,String> context) {
        super();
        theContext = context;
    }

    @Override
    public synchronized void requestCounterExample(FCAImplication<A> question) {
        FullObject<A,String> counterExample = new FullObject<>(name + "", question.getPremise());
        ++name;
        counterExample.setName("");
        counterExample.getDescription();
        counterExample.getIdentifier();
        counterExample.getName();
        FullObject<A, String> anotherExample = new FullObject<>("name");
        anotherExample.getName();
        ExpertAction action =
                new CounterExampleProvidedAction<>(theContext, question, counterExample);
        action.getContext();
        fireExpertAction(action);
    }

    @Override
    public synchronized void askQuestion(FCAImplication<A> question) {
        QuestionRejectedAction<A,String,FullObject<A,String>> action =
                new QuestionRejectedAction<>();
        action.setContext(theContext);
        action.setQuestion(question);
        action.getContext();
        action.getQuestion();
        action.getKeys();
        action.getPropertyChangeListeners();
        action.getValue(null);
        question.getConclusion();
        question.getPremise();
        fireExpertAction(action);
    }

    @Override
    public void counterExampleInvalid(FullObject<A,String> counterExample, int reason) {
        switch (reason) {
            case Expert.COUNTEREXAMPLE_EXISTS:
                logger.error("An object with name " + counterExample.getName() + " already exists");
                break;
            case Expert.COUNTEREXAMPLE_INVALID:
                logger.error("The object " + counterExample.getName() + " is not a valid counter example");
                break;
        }
    }

    public <D extends ObjectDescription<A>> D getCounterExampleDescription() {
        return null;
    }

    public void explorationFinished() {
        logger.info("=== End of exploration ===");
    }

    public void forceToCounterExample(FCAImplication<A> implication) {
    }

    public void implicationFollowsFromBackgroundKnowledge(FCAImplication<A> imp) {

    }
}