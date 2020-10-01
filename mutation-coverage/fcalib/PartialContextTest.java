package de.tudresden.inf.tcs.fcalib;

import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.change.ContextChange;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.utils.IndexedSet;
import de.tudresden.inf.tcs.fcalib.action.QuestionConfirmedAction;
import de.tudresden.inf.tcs.fcalib.change.HistoryManager;
import de.tudresden.inf.tcs.fcalib.change.NewImplicationChange;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PartialContextTest {

    /****************************
     *
     *
     * Part 3:
     * Mutation Coverage
     *
     *
     *****************************/

    @Test
        public void getObjects()
        {
            PartialContext<String, String, PartialObject<String, String>> t1 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");
            t1.getObjects();
        }

        @Test
        public void getObject() {
            PartialContext<String, String, PartialObject<String, String>> t2 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");
            t2.addObject(o);

            assertEquals(null, t2.getObject("not_an_object"));
            assertEquals(null, t2.getObject(null));
        }

        @Test
        public void getObject2() {

            // tests for addObject, objectHasAttribute, objectHasNegatedAttribute, getObject, getObjects, getObjectAtIndex, getObjectCount

            PartialContext<String, String, PartialObject<String, String>> t3 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            assertTrue(t3.addObject(o)); // return false
            t3.addObject(o);

            assertEquals(o, t3.getObjectAtIndex(0));
            assertEquals(o, t3.getObject("object"));
            assertEquals(1, t3.getObjectCount());


        }

        @Test
        public void removeObject() {
            PartialContext<String, String, PartialObject<String, String>> t4 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");
            PartialObject<String, String> x = new PartialObject<>("not_an_object");
            PartialObject<String, String> a = new PartialObject<>(" ");

            t4.addObject(o);
            t4.addObject(x);
            t4.addObject(a);

            // removeObject(I Index)
            try {
                t4.removeObject("object");
                t4.removeObject("not_an_object");
                t4.removeObject(" ");
            } catch ( IllegalObjectException e) {
                e.getClass().equals(IllegalObjectException.class);
            }

//
//            //removeObject(Partial Object)
//            assertTrue(t4.addObject(o)); // return true
//            try {
//                t4.removeObject(o);
//                assertTrue(t4.removeObject(o));
//            } catch ( IllegalObjectException e) {
//                e.getClass().equals(IllegalObjectException.class);
//            }

        }

        @Test (expected = NullPointerException.class)
        public void removeObject2() throws IllegalObjectException {
            PartialContext<String, String, PartialObject<String, String>> t5 = new PartialContext<>();
            PartialObject<String, String> o = null;

            t5.addObject(o);
            t5.removeObject(o);
        }

        @Test
        public void removeObject3() {
            PartialContext<String, String, PartialObject<String, String>> t6 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            //removeObject(Partial Object)
            assertTrue(t6.addObject(o)); // return true
            try {
                t6.removeObject(o);
                assertTrue(t6.removeObject(o));
            } catch ( IllegalObjectException e) {
                e.getClass().equals(IllegalObjectException.class);
            }
        }

        @Test
        public void refutes() {
            PartialContext<String, String, PartialObject<String, String>> t7 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");
            Implication<String> imp = null;

            assertFalse(t7.refutes(imp));
        }

        @Test
        public void refutes2() {
            PartialContext<String, String, PartialObject<String, String>> t8 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");
            Implication<String> imp = new Implication<>();

            t8.addObject(o);
            t8.refutes(imp);
            assertFalse(t8.refutes(imp));
        }

        @Test (expected = NullPointerException.class)
        public void isCounterExampleValid() {
            PartialContext<String, String, PartialObject<String, String>> t9 = new PartialContext<>();
            PartialObject<String, String> counterExample = null;
            Implication<String> imp = null;
//            Implication<String> imp = new Implication<>();

            assertFalse(t9.isCounterExampleValid(counterExample,imp));

        }

        @Test
        public void isCounterExampleValid2() {
                PartialContext<String, String, PartialObject<String, String>> t10 = new PartialContext<>();
                PartialObject<String, String> counterExample = new PartialObject<>("object");
                Implication<String> imp = new Implication<>();

                assertFalse(t10.isCounterExampleValid(counterExample, imp));
        }

        @Test (expected = NullPointerException.class)
        public void addObject() {
            PartialContext<String, String, PartialObject<String, String>> t11 = new PartialContext<>();
            PartialObject<String, String> o = null;
            t11.addObject(o);
        }

        @Test
        public void clearObjects() {
            PartialContext<String, String, PartialObject<String, String>> t12 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            t12.addObject(o);
            t12.clearObjects();
            assertEquals(0, t12.getObjectCount());
        }

        @Test
        public void addAttributeToObject() {
            PartialContext<String, String, PartialObject<String, String>> t13 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            try {
                t13.addObject(o);
                t13.addAttribute("a");
                t13.addAttributeToObject("b", "object");
            } catch (IllegalObjectException e) {
                e.getClass().equals(IllegalObjectException.class);
            } catch (IllegalAttributeException e2) {
                e2.getClass().equals(IllegalAttributeException.class);
            }
        }

        @Test
        public void addAttributeToObject2() {
            // illegalAttribute does not exist + illegal ObjectException
            PartialContext<String, String, PartialObject<String, String>> t14 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            try {
                t14.addObject(o);
                t14.addAttribute("a");
                t14.addAttributeToObject("a","object");
                t14.addAttributeToObject("a",null);
            } catch (IllegalObjectException e) {
                e.getClass().equals(IllegalObjectException.class);
            }

        }

        @Test
        public void addAttributeToObject3() {
            // already has attribute exception
            PartialContext<String, String, PartialObject<String, String>> t15 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            try {
                t15.addObject(o);
                t15.addAttribute("a");
                t15.addAttributeToObject("a","object");
                t15.addAttributeToObject("a","object");
            } catch (IllegalAttributeException e) {
                e.getClass().equals(IllegalAttributeException.class);
            } catch (IllegalObjectException e2) {
                e2.getClass().equals(IllegalAttributeException.class);
            }


            //remove objectAttribute
            // return getObject(id).getDescription().removeAttribute(attribute)
            try {
                t15.removeAttributeFromObject("a", "object");
            } catch (IllegalObjectException e) {
                e.getClass().equals(IllegalAttributeException.class);
            }

            //remove objectAttribute
            // Object id does not exist
            try {
                t15.removeAttributeFromObject("a", "object2");
            } catch (IllegalObjectException e) {
                e.getClass().equals(IllegalObjectException.class);
            }
        }

        @Test (expected = NullPointerException.class)
        public void removeAttributeFromObject() throws IllegalObjectException {
            PartialContext<String, String, PartialObject<String, String>> t16 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>(null);

            t16.addObject(o);
            t16.removeAttributeFromObject(null, null);
        }

        @Test
        public void removeAttributeFromObject2() {
            PartialContext<String, String, PartialObject<String, String>> t17 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            // Attribute does not exist
            try {
                t17.removeAttributeFromObject("b", "object");
            } catch (IllegalObjectException e) {
                e.getClass().equals(IllegalObjectException.class);
            } catch (IllegalAttributeException e2) {
                e2.getClass().equals(IllegalAttributeException.class);
            }

            // object does not have attribute
            try {
                t17.addAttribute("a");
                t17.addObject(o);
                t17.addAttributeToObject("a", "object");
                t17.objectHasAttribute(o, "a");
                t17.removeAttributeFromObject("x", "object");
            } catch (IllegalObjectException e) {
                e.getClass().equals(IllegalObjectException.class);
            } catch (IllegalAttributeException e2) {
                e2.getClass().equals(IllegalAttributeException.class);
            }
        }

        @Test
        public void removeAttributeFromObject3() {
            // Attribute does not exist IllegalAttributeException
            PartialContext<String, String, PartialObject<String, String>> t18 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            try {
                t18.removeAttributeFromObject("b", "object");
            } catch (IllegalObjectException e) {
                e.getClass().equals(IllegalObjectException.class);
            } catch (IllegalAttributeException e2) {
                e2.getClass().equals(IllegalAttributeException.class);
            }
        }

        @Test
        public void removeAttributeFromObject4() throws IllegalAttributeException, IllegalObjectException {
            // object does not have attribute
            PartialContext<String, String, PartialObject<String, String>> t19 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            t19.addObject(o);
            t19.addAttribute("a");
            t19.addAttributeToObject("a","object");
            t19.getObject("object");

            try {
                t19.removeAttributeFromObject("non_existent_attribute", o.getIdentifier());
                t19.removeAttributeFromObject("a", o.getIdentifier());
            } catch (IllegalAttributeException e) {
                e.getClass().equals(IllegalAttributeException.class);
            }

        }

        @Test (expected = NullPointerException.class)
        public void objectHasAttribute() {
            PartialContext<String, String, PartialObject<String, String>> t20 = new PartialContext<>();
            PartialObject<String, String> o = null;

            t20.addObject(o);
            t20.addAttribute("a");
            t20.objectHasAttribute(o,"a");
        }

        @Test
        public void objectHasAttribute2() {
            PartialContext<String, String, PartialObject<String, String>> t21 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            t21.addObject(o);
            t21.objectHasAttribute(o, null);

            assertFalse(t21.objectHasAttribute(o, null));
        }

        @Test
        public void objectHasAttribute3() throws IllegalObjectException {
            PartialContext<String, String, PartialObject<String, String>> t22 = new PartialContext<>();
            PartialObject<String, String> o = new PartialObject<>("object");

            t22.addObject(o);
            t22.addAttribute("a");
            t22.addAttributeToObject("a", "object");

            assertTrue(t22.objectHasAttribute(o, "a"));
        }

        @Test (expected = NullPointerException.class)
        public void objectHasNegatedAttribute() throws IllegalObjectException{
            PartialContext<String, String, PartialObject<String, String>> t23 = new PartialContext<>();
            PartialObject<String, String> o = null;

            t23.addObject(o);
            t23.addAttribute(null);
            t23.addAttributeToObject(null, null);
            t23.objectHasAttribute(o, null);
         }

         @Test (expected = NullPointerException.class)
        public void objectHasNegatedAttribute2() throws IllegalObjectException {
           PartialContext<String, String, PartialObject<String, String>> t24 = new PartialContext<>();
           PartialObject<String, String> o = new PartialObject<>("object");

           t24.addObject(o);
           t24.addAttributeToObject(null, "object");
           t24.objectHasNegatedAttribute(o, null);
    }

    @Test
    public void objectHasNegatedAttribute3() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> t25 = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");

        t25.addObject(o);
        t25.addAttribute("a");
        t25.addAttributeToObject("a", "object");

        assertFalse(t25.objectHasNegatedAttribute(o, "a"));
    }

    @Test
    public void doublePrime() {
        PartialContext<String, String, PartialObject<String, String>> t26 = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");
        HashSet<String> list = null;
        HashSet<String> list2 = new HashSet<>();

        assertEquals(list2 ,t26.doublePrime(list));
    }

    @Test
    public void doublePrime2() {
        PartialContext<String, String, PartialObject<String, String>> t27 = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");
        Set<String> list = new HashSet<>();
        Set<String> list2 = t27.doublePrime(list);

        assertEquals(list2, t27.doublePrime(list));
    }

    @Test
    public void followsFromBackgroundKnowledge() {
        PartialContext<String, String, PartialObject<String, String>> t28 = new PartialContext<>();
        Implication<String> imp = null;

        assertFalse(t28.followsFromBackgroundKnowledge(imp));
    }

    @Test
    public void followsFromBackgroundKnowledge2() {
        PartialContext<String, String, PartialObject<String, String>> t29 = new PartialContext<>();
        Implication<String> imp = new Implication<>();

        assertFalse(t29.followsFromBackgroundKnowledge(imp));
    }

        @Test
        public void getStemBase() {
            PartialContext<String, String, PartialObject<String, String>> t30 = new PartialContext<>();

            t30.getStemBase();
        }

        @Test
        public void getDuquenneGuiguesBase() {
            PartialContext<String, String, PartialObject<String, String>> t31 = new PartialContext<>();

            t31.getDuquenneGuiguesBase();
        }

        @Test
        public void setExpert() {
          PartialContext<String, String, PartialObject<String, String>> t32 = new PartialContext<>();
          PartialContextExpert<String> expert = null;

          t32.setExpert(expert);
        }

        @Test
        public void setExpert2() {
            PartialContext<String, String, PartialObject<String, String>> t33 = new PartialContext<>();
            PartialContextExpert<String> expert = new PartialContextExpert<>(t33);

            t33.setExpert(expert);
        }

        @Test
        public void getExpert() {
            PartialContext<String, String, PartialObject<String, String>> t34 = new PartialContext<>();

            t34.getExpert();
        }


    @Test
    public void doublePrime3() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");
        Set<String> list = new HashSet<>();
        test.addObject(o);
        test.addAttribute("a");
        test.addAttributeToObject("a","object");
        assertTrue(test.containsObject("object"));
        test.getAttributes();

        Set<String> list2 = test.doublePrime(list);
        assertEquals(list2, test.doublePrime(list));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void partialObject() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");
        Set<String> list = new HashSet<>();

        o.setName("obj");
        o.getName();
        o.toString();
        o.getDescription();
        PartialObjectDescription<String> o2 = new PartialObjectDescription<String>();

        exceptionRule.expect(IllegalAttributeException.class);
        exceptionRule.expectMessage("Attribute o2does not exist");

        o2.addAttribute("o");
        o2.addNegatedAttribute("o2");
        o.equals(o2);
        o2.equals(o);
        test.addAttributeToObject("o2", "object");
        o2.containsNegatedAttribute("o2");
        assertEquals("plus = ", o2.toString());
    }

    @Test
    public void abstractContext() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");
        PartialObject<String, String> o2 = new PartialObject<>("object2");
        Set<PartialObject<String,String>> set = new HashSet<>();
        Set<String> set2 = new HashSet<>();


        test.addObject(o);
        test.addObject(o2);
        test.addAttribute("a");
        test.addAttribute("b");

        test.addObjects(set);
        test.addAttributes(set2);
        assertTrue(test.addObjects(set));
        assertTrue(test.addAttributes(set2));
        set.add(o);
        set.add(o2);
        set2.add("a");
        set2.add("b");

        test.addObjects(set);
        test.clearObjects();
    }

    @Test
    public void attributeAlreadyAdded() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        Set<String> set = new HashSet<>();

        exceptionRule.expect(IllegalAttributeException.class);
        exceptionRule.expectMessage("Attribute a has already been added");

        test.addAttribute("a");
        test.addAttribute("b");

        test.addAttributes(set);
        assertTrue(test.addAttributes(set));

        set.add("a");
        set.add("b");
        test.addAttributes(set);
    }


    @Test
    public void partialObjectDescription() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObjectDescription<String> o2 = new PartialObjectDescription<>();

        PartialObjectDescription<Set> oo = new PartialObjectDescription<>();

        o2.addAttribute("o");
        o2.addNegatedAttribute("o2");
        o2.addNegatedAttribute("o2");

        o2.containsNegatedAttribute("o2");
        assertEquals("plus=[o] minus=[o2]", o2.toString());
    }

    @Test
    public void partialObjectDescription2() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();

        PartialObjectDescription<String> o2 = new PartialObjectDescription<>();


        o2.addAttribute("o");
        o2.addNegatedAttribute("o2");

        Set<String> set = o2.getAttributes();
        Set<String> set_n = o2.getNegatedAttributes();
        o2.containsAttributes(set);
        o2.containsNegatedAttributes(set_n);

    }

    @Test
    public void partialObject2() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        Set<String> list = new HashSet<>();
        PartialObject<String, String> o = new PartialObject<String, String>("object", list);
        PartialObject<String, String> o2 = new PartialObject<String, String>("object2", list, list);
        Implication<String> imp = new Implication<>();


        o.getDescription();
        o.getName();
        test.addObject(o);
        test.addObject(o2);
        test.addAttribute("object");
        test.addAttributeToObject("object", "object");
        test.addAttribute("object2");
        test.addAttributeToObject("object2", "object2");
        test.addAttributes(list);
    }

    @Test
    public void descriptionContainsAttribute() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObjectDescription<String> o2 = new PartialObjectDescription<>();

        exceptionRule.expect(IllegalAttributeException.class);
        exceptionRule.expectMessage("The description contains the attribute o");

        o2.addAttribute("o");
        o2.addNegatedAttribute("o2");
        o2.addNegatedAttribute("o");
    }

    @Test
    public void descriptionContainsNegation() throws IllegalAttributeException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObjectDescription<String> o2 = new PartialObjectDescription<>();

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("The description contains the negation of attribute o2");

        o2.addAttribute("o");
        o2.addNegatedAttribute("o2");
        o2.addAttribute("o2");
    }

    @Test
    public void objectNotRemoved() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("id");

        exceptionRule.expect(IllegalObjectException.class);
        exceptionRule.expectMessage("Objectidnot successfully removed");
        test.removeObject(o);
        assertFalse(test.removeObject(o));
    }


    @Test
    public void ObjectHasNoAttribute() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("id");

        test.addObject(o);
        test.addAttribute("attr");
        test.addAttributeToObject("attr", "id");
        assertFalse(test.getObject("id").getDescription().containsAttribute("a"));

        exceptionRule.expect(IllegalAttributeException.class);
        exceptionRule.expectMessage("Attribute adoes not exist");

        test.removeAttributeFromObject("a", "id");

    }

    /******************
     *
     *
     * Extra code for
     * Mutation Coverage
     *
     *
     ********************/

    @Test
    public void removeObjectMut() throws IllegalObjectException {
        /**
         * public boolean removeObject(I id)
         * if (!removed)
         * return true;
         */
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");
        PartialObject<String, String> x = new PartialObject<>("object_2");

        test.addObject(o);
        test.addObject(x);
        test.getObjects();
        assertTrue(test.removeObject("object"));
    }
    @Test
    public void removeObjectMut2() throws IllegalObjectException {
        /**
         * public boolean removeObject(o object)
         * return true;
         */
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");
        PartialObject<String, String> x = new PartialObject<>("object_2");

        test.addObject(o);
        test.addObject(x);
        test.getObjects();
        assertTrue(test.removeObject(o));
    }

    @Test
    public void oddObjectMut() {
        /**
         * public boolean addObject(O o)
         * return false;
         */
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");

        test.addObject(o);
        assertFalse(test.addObject(o));
    }

    @Test
    public void addAttributeToObjectMut() throws IllegalObjectException, IllegalAttributeException {
        /**
         * public boolean addAttributeToObject(A attribute, I id)
         * return getObject(id).getDescription().addAttribute(attribute);
         */
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");

        test.addObject(o);
        test.getObject("object");
        test.addAttribute("a");
        test.addAttribute("b");

        try {
            assertTrue(test.addAttributeToObject("a","object"));
        } catch (IllegalAttributeException e) {
            e.getClass().equals(IllegalAttributeException.class);
        }
    }

    @Test
    public void removeAttributeFromObjectMut() throws IllegalObjectException, IllegalAttributeException {
        /**
         * public boolean removeAttributeFromObject(A attribute, I id)
         * throw new IllegalAttributeException("Object does not have attribute " + attribute);
         * return getObject(id).getDescription().removeAttribute(attribute);
         */
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");

        test.addObject(o);
        test.getObject("object");
        test.addAttribute("a");
        test.addAttribute("b");
        test.addAttributeToObject("a", "object");

        try {
            test.removeAttributeFromObject("b","object");
        } catch (IllegalAttributeException e) {
            e.getClass().equals(IllegalAttributeException.class);
        }

        try {
            assertTrue(test.removeAttributeFromObject("a","object"));
        } catch (IllegalAttributeException e) {
            e.getClass().equals(IllegalAttributeException.class);
        }
    }

    @Test
    public void impToString() {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObjectDescription<String> o = new PartialObjectDescription<>();
        Set<String> p = new HashSet<>();
        Set<String> c = new HashSet<>();
        Implication<String> imp = new Implication<>(p,c);

        p.add("premise");
        c.add("conclusion");

        imp.getPremise();
        imp.getConclusion();


        imp.toString();
        test.toString();
        assertEquals("[premise] -> [conclusion]", imp.toString());
    }

    @Test
    public void toString2() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");

        test.addObject(o);
        test.addAttribute("a");
        test.addAttributeToObject("a", "object");

        o.toString();
        assertEquals("{id=object plus=[a] minus=[]}", o.toString());
    }

    @Test
    public void getName() {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");

        o.setName("name");
        o.getName();
        assertEquals("name", o.getName());
    }

    @Test
    public void addNegated() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObjectDescription<String> o = new PartialObjectDescription<>();
        Set<String> set = new HashSet<>();

        o.addAttribute("o");
        assertTrue(o.addNegatedAttribute("o2"));
        set.add("o2");
        assertTrue(o.containsNegatedAttributes(set));
    }

    @Test
    public void clone2() {
        PartialObjectDescription<String> o = new PartialObjectDescription<>();

        o.addAttribute("a");
        o.addNegatedAttribute("b");
        Set<String> a = o.getAttributes();
        Set<String> b = o.getNegatedAttributes();

        PartialObjectDescription<String> clone = new PartialObjectDescription<String>(a,b);
        clone.clone();
    }

    @Test
    public void test() throws IllegalObjectException {
        PartialContext<String, String, PartialObject<String, String>> test = new PartialContext<>();
        PartialObject<String, String> o = new PartialObject<>("object");
        PartialObject<String, String> o2 = new PartialObject<>("object2");

        test.addObject(o);
        test.addObject(o2);
        test.addAttribute("a");
        test.addAttribute("b");
        test.addAttributeToObject("a", "object");
        test.addAttributeToObject("b", "object2");

        test.getAttributes().clear();
        test.clearObjects();
    }


}




