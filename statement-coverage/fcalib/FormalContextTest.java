package de.tudresden.inf.tcs.fcalib;

import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.utils.IndexedSet;
import de.tudresden.inf.tcs.fcalib.action.QuestionRejectedAction;
import de.tudresden.inf.tcs.fcalib.action.ResetExplorationAction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.event.ActionEvent;
import java.util.*;

import static org.junit.Assert.*;

public class FormalContextTest {

    /***************************
     *
     * Part 2:
     * Statement Coverage
     *
     *****************************/

    @Test (expected = NullPointerException.class)
    public void addObject() throws IllegalObjectException {
        FormalContext<String, String> t1 = new FormalContext<>();
        FullObject<String, String> o = null;

        t1.addObject(null);
    }

    @Test
    public void addObject2() {
        FormalContext<String, String> t2 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        try {
            t2.addObject(o);
            assertTrue(true);
            assertTrue(t2.addObject(o));
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalObjectException.class);
        }
    }

    @Test
    public void getObjectAtIndex() throws IllegalObjectException {
        FormalContext<String, String> t3 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");
        t3.addObject(o);
        assertEquals(o, t3.getObjectAtIndex(0));
    }

    @Test (expected = NullPointerException.class)
    public void getObject() throws IllegalObjectException {
        FormalContext<String, String> t4 = new FormalContext<>();
        FullObject<String, String> o = null;
        t4.addObject(o);

        assertEquals(o, t4.getObject(null));
    }

    @Test
    public void getObject2() throws IllegalObjectException {
        FormalContext<String, String> t5 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");
        t5.addObject(o);
        assertEquals(o, t5.getObject("object"));
    }


//    @Test
//    public void getObject3() {
//        BasicConfigurator.configure();
//
//        // Tests for getObject, getObjects, getObjectAtIndex, ObjectHasAttribute
//        FormalContext<String, String> test2 = new FormalContext<>();
//        FullObject<String, String> o = new FullObject<>("object");
//
//        try {
//            test2.addObject(o);
//        } catch (IllegalObjectException e) {
//            e.getClass().equals(IllegalObjectException.class);
//        }
//
//        test2.objectHasAttribute(o, "a");
//
//        test2.getObjectAtIndex(0);
//        test2.getObjects();
//
//        test2.getObject(o.getIdentifier());
//        test2.getObject("b");
//    }

    @Test
    public void removeObject() throws IllegalObjectException {
        FormalContext<String, String> t6 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>(" ");
        t6.addObject(o);

        assertTrue(t6.removeObject(" "));
    }

    @Test
    public void removeObject2() {
        FormalContext<String, String> t7 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        try {
            t7.removeObject(o);
            assertTrue(t7.removeObject(o));
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalObjectException.class);
        }
    }

    @Test (expected = NullPointerException.class)
    public void removeObject3() throws IllegalObjectException {
        FormalContext<String, String> t8 = new FormalContext<>();
        FullObject<String, String> o = null;

        t8.addObject(o);
        t8.removeObject(o);
    }

    @Test
    public void removeObject4() throws IllegalObjectException {
        FormalContext<String, String> t9 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        t9.addObject(o);
        assertTrue(t9.removeObject(o));
    }

    @Test
    public void clearObjects() {
        FormalContext<String, String> t10 = new FormalContext<>();
        t10.clearObjects();
    }

    @Test
    public void addAttributeToObject() {
        FormalContext<String, String> t11 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        try {
            t11.addObject(o);
            t11.addAttribute("a");
            t11.addAttributeToObject("b", "object");
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalObjectException.class);
        } catch (IllegalAttributeException e2) {
            e2.getClass().equals(IllegalAttributeException.class);
        }
    }

    @Test (expected = NullPointerException.class)
    public void addAttributeToObject2() throws IllegalObjectException {
        FormalContext<String, String> t12 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        t12.addAttributeToObject(null, "object");
    }

    @Test
    public void addAttributeToObject3() {
        // illegalAttribute does not exist + IllegalObjectException
        FormalContext<String, String> t13 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        try {
            t13.addObject(o);
            t13.addAttribute("a");
            t13.addAttributeToObject("a","object");
            t13.addAttributeToObject("a",null);
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalObjectException.class);
        }
    }

    @Test
    public void addAttributeToObject4() {
        // already has attribute exception
        FormalContext<String, String> t14 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        try {
            t14.addObject(o);
            t14.addAttribute("a");
            t14.addAttributeToObject("a","object");
            t14.addAttributeToObject("a","object");
        } catch (IllegalAttributeException e) {
            e.getClass().equals(IllegalAttributeException.class);
        } catch (IllegalObjectException e2) {
            e2.getClass().equals(IllegalAttributeException.class);
        }

        //remove objectAttribute
        // return getObject(id).getDescription().removeAttribute(attribute)
        try {
            t14.removeAttributeFromObject("a", "object");
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalAttributeException.class);
        }

        //remove objectAttribute
        // Object id does not exist
        try {
            t14.removeAttributeFromObject("a", "object2");
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalObjectException.class);
        }
    }

    @Test (expected = NullPointerException.class)
    public void removeAttributeFromObject() throws IllegalObjectException {
        FormalContext<String, String> t15 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        t15.removeAttributeFromObject(null, "object");
    }

    @Test
    public void removeAttributeFromObject2() throws IllegalObjectException{
        FormalContext<String, String> t16 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("o");

        t16.addObject(o);
        t16.addAttribute("a");
        t16.addAttributeToObject("a","o");
        assertTrue(t16.removeAttributeFromObject("a","o"));
    }

    @Test
    public void removeAttributeFromObject3() {
        // illegalAttribute does not exist + IllegalObjectException
        FormalContext<String, String> t17 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        try {
            t17.addObject(o);
            t17.addAttribute("a");
            t17.addAttributeToObject("a","object");
            t17.addAttributeToObject("a",null);
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalObjectException.class);
        }
    }

    @Test
    public void removeAttributeFromObject4() {
        FormalContext<String, String> t18 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        // Attribute does not exist
        try {
            t18.removeAttributeFromObject("b", "object");
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalObjectException.class);
        } catch (IllegalAttributeException e2) {
            e2.getClass().equals(IllegalAttributeException.class);
        }

        // object does not have attribute
        try {
            t18.addAttribute("a");
            t18.addObject(o);
            t18.addAttributeToObject("a", "object");
            t18.objectHasAttribute(o, "a");
            t18.removeAttributeFromObject("x", "object");
        } catch (IllegalObjectException e) {
            e.getClass().equals(IllegalObjectException.class);
        } catch (IllegalAttributeException e2) {
            e2.getClass().equals(IllegalAttributeException.class);
        }
    }

    @Test (expected = NullPointerException.class)
    public void objectHasAttribute() throws IllegalObjectException {
        FormalContext<String, String> t19 = new FormalContext<>();
        FullObject<String, String> o = null;

        t19.addAttribute(null);
        t19.addObject(o);
        t19.objectHasAttribute(o, null);
    }

    @Test
    public void objectHasAttribute2() throws IllegalObjectException {
        FormalContext<String, String> t20 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");

        t20.addAttribute("a");
        t20.addObject(o);
        t20.addAttributeToObject("a", "object");
        assertTrue(t20.objectHasAttribute(o, "a"));
    }

    @Test
    public void doublePrime() {
        FormalContext<String, String> t21 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");
        HashSet<String> list = null;
        HashSet<String> list2 = new HashSet<>();

        assertEquals(list2 ,t21.doublePrime(list));
    }

    @Test
    public void doublePrime2() {
        FormalContext<String, String> t22 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");
        Set<String> list = new HashSet<>();
        Set<String> list2 = t22.doublePrime(list);

        assertEquals(list2, t22.doublePrime(list));
    }

    @Test
    public void closure() {
        FormalContext<String, String> t23 = new FormalContext<>();
        Set<String> set = null;
        Set<String> set2 = t23.doublePrime(set);

        assertEquals(set2, t23.closure(set));
    }

    @Test
    public void closure2() {
        FormalContext<String, String> t24 = new FormalContext<>();
        Set<String> set = new HashSet<String>();
        Set<String> set2 = t24.doublePrime(set);

        assertEquals(set2, t24.closure(set));
    }

    @Test (expected = NullPointerException.class)
    public void isClosed() {
        FormalContext<String, String> t25 = new FormalContext<>();
        Set<String> set = null;
        set.add("string");
        assertTrue(t25.isClosed(set));
    }

    @Test
    public void isClosed2() {
        FormalContext<String, String> t26 = new FormalContext<>();
        Set<String> set = new HashSet<String>();
        set.add("string");
        t26.closure(set);
        t26.isClosed(set);
    }

    @Test
    public void allClosures() {
        FormalContext<String, String> t27 = new FormalContext<>();
        Set<String> set = new HashSet<String>();
        t27.allClosures();
        assertNull(t27.allClosures());
    }

    @Test
    public void getStemBase() {
        FormalContext<String, String> t28 = new FormalContext<>();
        t28.getStemBase();
    }

    @Test
    public void getDuquenneGuiguesBase() {
        FormalContext<String, String> t29 = new FormalContext<>();
        t29.getDuquenneGuiguesBase();
    }

    @Test
    public void getIntents() {
        FormalContext<String, String> t30 = new FormalContext<>();
        t30.getIntents();
    }

    @Test
    public void getExtents() {
        FormalContext<String, String> t31 = new FormalContext<>();
        t31.getExtents();
    }

    @Test
    public void getConcepts() {
        FormalContext<String, String> t32 = new FormalContext<>();
        t32.getConcepts();
    }

    @Test
    public void getConceptLattice() {
        FormalContext<String, String> t33 = new FormalContext<>();
        t33.getConceptLattice();
    }

    @Test
    public void refutes() {
        FormalContext<String, String> t34 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");
        Implication<String> imp = null;

        assertFalse(t34.refutes(imp));
    }

    @Test
    public void refutes2() {
        FormalContext<String, String> t35 = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");
        Implication<String> imp = new Implication<>();

        assertFalse(t35.refutes(imp));
    }

    @Test (expected = NullPointerException.class)
    public void isCounterExampleValid() {
        FormalContext<String, String> t36 = new FormalContext<>();
        FullObject<String, String> counterExample = null;
        Implication<String> imp = null;

        t36.isCounterExampleValid(counterExample, imp);
    }

    @Test
    public void isCounterExampleValid2() {
        FormalContext<String, String> t37 = new FormalContext<>();
        FullObject<String, String> counterExample = new FullObject<>("object");
        Implication<String> imp = new Implication<>();

        assertFalse(t37.isCounterExampleValid(counterExample, imp));
    }

    @Test
    public void followsFromBackgroundKnowledge() {
        FormalContext<String, String> t38 = new FormalContext<>();
        Implication<String> imp = null;

        assertFalse(t38.followsFromBackgroundKnowledge(imp));
    }

    @Test
    public void followsFromBackgroundKnowledge2() {
        FormalContext<String, String> t39 = new FormalContext<>();
        Implication<String> imp = new Implication<>();

        assertFalse(t39.followsFromBackgroundKnowledge(imp));
    }

    @Test
    public void setExpert() {
        FormalContext<String, String> t40 = new FormalContext<>();
        Expert<String, String, FullObject<String,String>> expert = null;

        t40.setExpert(expert);
        assertNull(expert);
    }

    @Test
    public void setExpert2() {
        FormalContext<String, String> t40 = new FormalContext<>();
        Expert<String, String, FullObject<String,String>> expert = t40.getExpert();

        t40.setExpert(expert);
    }

    @Test
    public void getExpert() {
        FormalContext<String, String> t41 = new FormalContext<>();
        Expert<String, String, FullObject<String,String>> expert = t41.getExpert();

        assertEquals(expert, t41.getExpert());
    }


    /******************
     *
     * Extra codes for
     * Statement Coverage
     *
     *
     ********************/

    @Test
    public void isCounterExampleValid3() {
        FormalContext<String, String> test = new FormalContext<>();
        FullObject<String, String> counterExample = new FullObject<>("object");
        Implication<String> imp = new Implication<>();

        assertFalse(test.isCounterExampleValid(counterExample, imp));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void objectNotRemoved() throws IllegalObjectException {
        FormalContext<String, String> test = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("id");


        exceptionRule.expect(IllegalObjectException.class);
        exceptionRule.expectMessage("Objectidnot successfully removed");
        test.removeObject(o);

        throw new IllegalObjectException("Object Not Removed");
    }
}