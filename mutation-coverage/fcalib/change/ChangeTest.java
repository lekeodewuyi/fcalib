package de.tudresden.inf.tcs.fcalib.change;

import de.tudresden.inf.tcs.fcaapi.Context;
import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.FCAObject;
import de.tudresden.inf.tcs.fcaapi.action.ExpertAction;
import de.tudresden.inf.tcs.fcaapi.change.ContextChange;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalContextException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.utils.IndexedSet;
import de.tudresden.inf.tcs.fcalib.FormalContext;
import de.tudresden.inf.tcs.fcalib.FullObject;
import de.tudresden.inf.tcs.fcalib.Implication;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

import static org.junit.Assert.assertTrue;

public class ChangeTest {

    /**
     * Tests cover the methods in HistoryManager,
     * and ALSO covers the other classes in the change directory
     */

    /****************************
     *
     *
     * Part 3:
     * Mutation Coverage
     *
     *
     *****************************/

    @Test
    public void push() {
        HistoryManager test1 = new HistoryManager();

        ContextChange change = new ContextChange() {
            @Override
            public void undo() {
            }

            @Override
            public int getType() {
                return 0;
            }
        };
        test1.add(change);
        test1.push(change);
    }

    @Test
    public void undo() {

        /**
         * Covers undo(int), undo(ContextChange)
         * ALSO covers the other classes in the change directory
         */

        HistoryManager test2 = new HistoryManager();

        FormalContext formal_context_1 = new FormalContext();
        FormalContext formal_context_2 = new FormalContext();

        FullObject o = new FullObject("object");

        ContextChange change = new ContextChange() {
            @Override
            public void undo() {
                try {
                    formal_context_1.addObject(o);
                    formal_context_1.getObject("object");
                    formal_context_1.removeObject(o);
                } catch (IllegalObjectException e) {
                    e.getClass().equals(IllegalObjectException.class);
                    System.exit(-1);
                }
            }

            @Override
            public int getType() {
                return 0;
            }
        };
        ContextChange change_2 = new ContextChange() {
            @Override
            public void undo() {

            }

            @Override
            public int getType() {
                return 0;
            }
        };

        Implication i = new Implication();
        NewImplicationChange i_change = new NewImplicationChange(formal_context_1, i);

        NewObjectChange o_change = new NewObjectChange(formal_context_2, o);
        ObjectHasAttributeChange a_change = new ObjectHasAttributeChange(o, "attribute");

        formal_context_1.initializeExploration();
        i_change.undo();

        assertTrue(test2.add(a_change));
        try {
            assertTrue(formal_context_1.addAttribute("attribute_2"));
            assertTrue(formal_context_1.addAttributeToObject("attribute_2","object"));
        } catch (IllegalObjectException e) {
            e.printStackTrace();
        }


        // undo(ContextChange)
        a_change.undo();
        test2.undo(a_change);

        //undo(int)
        test2.add(change);
        test2.add(change_2);
        test2.get(1);
        test2.undo(1);


        /**
         * Extra test statements to covers Other Classes in the change directory
         */

        i_change.getImplication();
        i_change.getType();
        assertTrue(i_change.getType() == ContextChange.NEW_IMPLICATION_MODIFICATION);


        a_change.getAttribute(); // getAttribute
        assertTrue(o_change.getObject() == o);
        assertTrue(a_change.getObject() == o);
        assertTrue(o_change.getType() == ContextChange.NEW_OBJECT_MODIFICATION);
        assertTrue(a_change.getType() == ContextChange.OBJECT_HAS_ATTRIBUTE_MODIFICATION);
    }

    @Test
    public void undo2() throws IllegalObjectException {
        FormalContext<String, String> c = new FormalContext<>();
        FullObject<String, String> o = new FullObject<>("object");
        c.addObject(o);
        NewObjectChange o_change = new NewObjectChange(c, o);
        o_change.undo();
    }

    @Test
    public void undoLast() {
        HistoryManager test3 = new HistoryManager();
        ContextChange change = new ContextChange() {
            @Override
            public void undo() {

            }

            @Override
            public int getType() {
                return 0;
            }
        };

        test3.add(change);
        test3.get(0);

        test3.undoLast();
    }

    @Test
    public void undoLastN() {
        HistoryManager test4 = new HistoryManager();
        ContextChange change = new ContextChange() {
            @Override
            public void undo() {

            }

            @Override
            public int getType() {
                return 0;
            }
        };

        test4.add(change);
        test4.get(0);

        test4.get(0);
        test4.undoLastN(0);
        test4.undoAll();
    }

    @Test
    public void undoAll() {
        HistoryManager test5 = new HistoryManager();
        test5.undoAll();
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
    public void undo3() {

        /**
         * Covers undo(int), undo(ContextChange)
         * ALSO covers the other classes in the change directory
         */

        HistoryManager test2 = new HistoryManager();

        FormalContext formal_context_1 = new FormalContext();
        FormalContext formal_context_2 = new FormalContext();

        FullObject o = new FullObject("object");

        ContextChange change = new ContextChange() {

            @Override
            public void undo() {
                try {
                    formal_context_1.addObject(o);
                    formal_context_1.getObject("object");
                    formal_context_1.removeObject(o);
                } catch (IllegalObjectException e) {
                    e.getClass().equals(IllegalObjectException.class);
                    System.exit(-1);
                }
            }

            @Override
            public int getType() {
                return 0;
            }
        };
        change.undo();
        test2.undo(change);



        ContextChange change_2 = new ContextChange() {
            @Override
            public void undo() {

            }

            @Override
            public int getType() {
                return 0;
            }
        };
        change_2.undo();
        test2.undo(change_2);


        Implication i = new Implication();
        NewImplicationChange i_change = new NewImplicationChange(formal_context_1, i);

        NewObjectChange o_change = new NewObjectChange(formal_context_2, o);
        ObjectHasAttributeChange a_change = new ObjectHasAttributeChange(o, "attribute");

        formal_context_1.initializeExploration();
        i_change.undo();

        assertTrue(test2.add(a_change));
        try {
            assertTrue(formal_context_1.addAttribute("attribute_2"));
            assertTrue(formal_context_1.addAttributeToObject("attribute_2","object"));
        } catch (IllegalObjectException e) {
            e.printStackTrace();
        }

        change.undo();
        change_2.undo();
        // undo(ContextChange)
        a_change.undo();
        test2.undo(a_change);

        //undo(int)
        test2.add(change);
        test2.add(change_2);
        test2.get(1);
        test2.undo(1);
        test2.get(0);
        test2.undo(0);


        /**
         * Extra test statements to covers Other Classes in the change directory
         */

        i_change.getImplication();
        i_change.getType();
        assertTrue(i_change.getType() == ContextChange.NEW_IMPLICATION_MODIFICATION);


        a_change.getAttribute(); // getAttribute
        assertTrue(o_change.getObject() == o);
        assertTrue(a_change.getObject() == o);
        assertTrue(o_change.getType() == ContextChange.NEW_OBJECT_MODIFICATION);
        assertTrue(a_change.getType() == ContextChange.OBJECT_HAS_ATTRIBUTE_MODIFICATION);

        assertTrue(a_change.getAttribute() == "attribute");  //mutation coverage
        assertTrue(i_change.getImplication() == i); //mutation coverage
    }


}