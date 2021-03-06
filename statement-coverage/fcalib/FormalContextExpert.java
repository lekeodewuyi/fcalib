package de.tudresden.inf.tcs.fcalib;

import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.obsolete.ExpertAction;
import de.tudresden.inf.tcs.fcalib.action.*;
import junit.framework.TestCase;
import org.apache.log4j.BasicConfigurator;

import java.util.HashSet;
import java.util.Set;


public class FormalContextExpert extends TestCase {

    public FormalContextExpert() {
    }

    public void testFormalContext() throws IllegalObjectException, CloneNotSupportedException {
        BasicConfigurator.configure();

        FormalContext<String, String> context = new FormalContext<String, String>();
        NoExpertFull<String> expert = new NoExpertFull<String>(context);
        FullObject<String, String> o = new FullObject<>("object");

        context.addAttribute("a");
        context.addAttribute("b");
        context.addAttribute("c");
        try{
            context.removeObject(o);
        } catch (Exception e){
            e.getClass().equals(IllegalObjectException.class);
        }
        context.addObject(o);
        try{
            context.addObject(o);
        } catch (Exception e){
            e.getClass().equals(IllegalObjectException.class);
        }
        //cover when object does not have attribute
        context.objectHasAttribute(o, "a");
        context.getObjectAtIndex(0);
        System.out.println("Attributes: " + context.getAttributes());
        //test getters
        context.getAttributeAtIndex(1);
        context.getAttributeCount();
        context.getExpert();
        context.getConceptLattice();
        context.getObject(o.getIdentifier());
        context.getObject("a");
        context.removeObject(o);
        context.addObject(o);
        //successfully add attribute to object
        context.addAttributeToObject("a", o.getIdentifier());
        //fail when trying to add same attribute again
        try{
            context.addAttributeToObject("a", o.getIdentifier());
        } catch(Exception e){
            e.getClass().equals(IllegalAttributeException.class);
        }
        //fail when add attribute to object that does not exist
        try{
            context.addAttributeToObject("a", "object2");
        } catch(Exception e){
            e.getClass().equals(IllegalObjectException.class);
        }
        //cover when object does have attribute
        context.objectHasAttribute(o, "a");
        //successfully remove attribute from an object
        context.removeAttributeFromObject("a", o.getIdentifier());
        try{//fail when try to remove it again
            context.removeAttributeFromObject("a", o.getIdentifier());
        }catch(Exception e){
            e.getClass().equals(IllegalAttributeException.class);
        }
        try{//not a legal object
            context.removeAttributeFromObject("a", "object2");
        }catch(Exception e){
            e.getClass().equals(IllegalObjectException.class);
        }
        try{//not a legal attribute
            context.removeAttributeFromObject("z", "object");
        }catch(Exception e){
            e.getClass().equals(IllegalAttributeException.class);
        }
        try{//not in the object
            context.removeAttributeFromObject("e", "object");
        }catch(Exception e){
            e.getClass().equals(IllegalAttributeException.class);
        }
        try{//not a legal attribute
            context.addAttributeToObject("asdkfm3", "object");
        }catch(Exception e){
            e.getClass().equals(IllegalAttributeException.class);
        }
        context.removeObject("object");
        context.clearObjects();
        try{
            context.removeObject("a");
        }catch(Exception e){
            e.getClass().equals(NullPointerException.class);
        }
        context.getExtents();
        context.getDuquenneGuiguesBase();
        context.getImplications();
        context.getObjects();
        context.getStemBase();
        context.allClosures();
        context.getIntents();
        Set<String> a = new HashSet<String>();
        context.closure(a);
        a.add("string");
        context.isClosed(a);
        context.getAttributes();
        context.getCurrentQuestion();
        context.getConcepts();
        context.getObjectCount();
        context.getExtents();

        StartExplorationAction<String, String, FullObject<String, String>> action = new StartExplorationAction<>();
        action.setContext(context);

        //context.addObjects((new Set<FullObject<String, String>>() set) );
        try{
            expert.fireExpertAction(action);
        }catch(Exception e){
            e.getClass().equals(NullPointerException.class);
        }

        context.setExpert(expert);

        expert.addExpertActionListener(context);
        expert.fireExpertAction(action);
        //NOTE: HAD TO SET THIS TO PUBLIC FOR TEST
        //ADD THIS TO NOT POSSIBLE TO COVER
        ResetExplorationAction<String, String, FullObject<String, String>> action2 = new ResetExplorationAction<String, String, FullObject<String, String>>(context);
        action2.setContext(context);
        action2.setEnabled(true);
        assertTrue(action2.isEnabled());
        StopExplorationAction<String, String, FullObject<String, String>> action3 = new StopExplorationAction<String, String, FullObject<String, String>>();
        action3.setContext(context);
        assertTrue(action3.getContext().equals(context));
        assertTrue(action2.getContext().equals(context));
        action3.setEnabled(true);
        assertTrue(action3.isEnabled());
        action3.getContext();
        expert.fireExpertAction(action3);
        expert.fireExpertAction(action2);
        QuestionConfirmedAction<String, String, FullObject<String, String>> action4 = new QuestionConfirmedAction<String, String, FullObject<String, String>>();
        action4.setContext(context);
        assertTrue(action4.getContext().equals(context));
        //changed visibility for this as well
        action4.getQuestion();
        action4.setQuestion(context.getCurrentQuestion());
        assertTrue(action4.getQuestion()!=null);
        action4.getKeys();
        action4.setContext(context);
        expert.fireExpertAction(action4);
        ChangeAttributeOrderAction<String, String, FullObject<String, String>> action5 = new ChangeAttributeOrderAction<String, String, FullObject<String, String>>();
        action5.setContext(context);
        action5.setContext(context);
        action5.setEnabled(true);
        assertTrue(action5.isEnabled());
        assertTrue(action5.getContext()!=null);
        try{
            expert.fireExpertAction(action5);
        } catch (Exception e){
            e.getClass().equals(IndexOutOfBoundsException.class);
        }
        try{
            expert.fireExpertAction(action5);
        } catch (Exception e){
            e.getClass().equals(IndexOutOfBoundsException.class);
        }
        Set<String> list = new HashSet<String>();
        list.add("m");
        list.add("d");
        list.add("object");
        Set<FullObject<String,String>> fullObjectList = new HashSet<FullObject<String,String>>();
        fullObjectList.add(o);
        context.addAttributes(list);
        //context.clearObjects();
        context.addObjects(fullObjectList);
        Implication<String> imp = new Implication<String>(list,list);
        context.setCurrentQuestion(imp); //made this public to test it
        context.initializeExploration();
        ImplicationSet<String> set = (ImplicationSet<String>) context.getImplications();
        set.getContext();
        set.allClosures();
        set.isClosed(list);
        assertTrue(set.isEmpty());
        set.addAll(context.getImplications());
        //assertFalse(set.isEmpty());
        assertTrue(set.getContext() !=null);
        Implication<String> imp2 = new Implication<String>();
        set.add(imp2);
        set.closure(list);
        set.add(imp);
        set.closure(list);

        Set<String> attrs = new HashSet<String>();
        attrs.add("e");
        attrs.add("f");
        context.addAttributes(attrs);
        Set<String> attrs2 = new HashSet<String>();
        attrs2.add("g");

        o.getDescription().clone();
        o.getDescription().addAttributes(attrs);
        context.refutes(imp);
        context.refutes(imp2);

        Object x = new Object();
        ExpertAction<String, String> ex = new ExpertAction<String, String>(x, 2); //MADE CLASS PUBLIC
        ExpertAction<String, String> ex2 = new ExpertAction<String, String>(x, 2, imp); //MADE CLASS PUBLIC
        ExpertAction<String, String> ex3 = new ExpertAction<String, String>(x, 2, imp, "test"); //MADE CLASS PUBLIC
        ex3.getType();
        ex3.getQuestion();
        ex3.getCounterExample();
        assertFalse(imp.equals(imp2));
        Implication<String> imp3 = imp2;
        assertTrue(imp2.equals(imp3));




        CounterExampleProvidedAction<String,String,FullObject<String,String>> provided = new CounterExampleProvidedAction<String,String,FullObject<String,String>>(context,imp,o);
        CounterExampleProvidedAction<String,String,FullObject<String,String>> provided2 = new CounterExampleProvidedAction<String,String,FullObject<String,String>>(null,null,null);
        provided.getCounterExample();
        provided2.getCounterExample();



        //Mutation coverage
        Object y = new Object();
        ExpertAction<String, String> ex4 = new ExpertAction<String, String>(y, 2, imp, "test"); //MADE CLASS PUBLIC
        ex3.getType();
        ex3.getQuestion();
        ex3.getCounterExample();

        assertTrue(ex4.getType() == 2);
        assertTrue(ex4.getQuestion() == imp);
        assertTrue(ex4.getCounterExample() == "test");




    }

}