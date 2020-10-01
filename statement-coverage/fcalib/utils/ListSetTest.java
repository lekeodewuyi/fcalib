package de.tudresden.inf.tcs.fcalib.utils;

import de.tudresden.inf.tcs.fcalib.action.CounterExampleProvidedAction;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.junit.Assert.*;

public class ListSetTest {

    /*******************************
     *
     * Part 2
     * Statement Coverage
     *
     *******************************/

    @Test
    public void add() {
        ListSet t1 = new ListSet();
        Object o = 10;
        assertTrue(t1.add(o));
        assertEquals(1, t1.size());

    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void add2() {
        ListSet t2 = new ListSet();
        Object o = null;
        exceptionRule.expect(NullPointerException.class);
        t2.add(o);
    }

    @Test
    public void add3() {
        ListSet t3 = new ListSet();
        Object o = 10;
        t3.add(o);
        Object o2 = 10;
        assertFalse(t3.add(o2));
        assertEquals(1, t3.size()); //the second object (o2) is not added to the list
    }

    @Test
    public void addAll() {
        ListSet t4 = new ListSet();
        Collection collection = new ArrayList();
        assertFalse(t4.addAll(collection));
        assertEquals(0, t4.size()); //collection size is 0
    }

    @Test
    public void addAll2() {
        ListSet t5 = new ListSet();
        Collection collection = new ArrayList();
        Object o = 10;
        Object o2 = 11;
        collection.add(o);
        collection.add(o2);
        t5.addAll(collection);
        assertTrue(t5.addAll(collection));
        assertEquals(2, t5.size());
    }

    @Test
    public void clear() {
        ListSet t6 = new ListSet();
        Collection collection = new ArrayList();
        collection.add(10);
        collection.add(11);
        t6.addAll(collection);
        collection.clear();
        assertEquals(0, collection.size());
    }

    @Test
    public void contains() {
        ListSet t7 = new ListSet();
        Object o = null;
        exceptionRule.expect(NullPointerException.class);
        t7.add(o);
        assertEquals(NullPointerException.class, t7.contains(o));
    }

    @Test
    public void contains2() {
        ListSet t8 = new ListSet();
        t8.add(5);
        t8.add(10);
        t8.add(15);
        assertTrue(t8.contains(5));
    }

    @Test
    public void contains3() {
        ListSet t9 = new ListSet();
        t9.add(5);
        t9.add(10);
        t9.add(15);
        assertFalse(t9.contains(7));
    }

    @Test
    public void containsAll() {
        ListSet t10 = new ListSet();
        Collection collection = new ArrayList();
        assertEquals(0,collection.size());
    }

    @Test
    public void containsAll2() {
        ListSet t11 = new ListSet();
        Collection collection = new ArrayList();
        collection.add(10);
        collection.add(11);
        t11.addAll(collection);
        assertTrue(t11.addAll(collection));
        assertTrue(t11.containsAll(collection));
        assertEquals(2, t11.size());
    }

    @Test
    public void containsAll3() {
        ListSet t12= new ListSet();
        Collection collection = new ArrayList();
        collection.add(5);
        collection.add(10);
        collection.add(15);
        assertFalse(t12.containsAll(collection));
    }

    @Test
    public void equals() {
        ListSet t13= new ListSet();
        Collection collection = new ArrayList();
        assertEquals(0, collection.size());
        assertEquals (0, t13.size());
        assertTrue(t13.equals(collection));
    }

    @Test
    public void equals2() {
        ListSet t14 = new ListSet();
        Collection collection = new ArrayList();
        collection.add(5);
        collection.add(10);
        t14.addAll(collection);
        assertEquals(2, collection.size());
        assertTrue(t14.addAll(collection));
        assertEquals(2, t14.size());
        assertTrue(t14.equals(collection));
        assertTrue(t14.containsAll(collection));
    }

    @Test
    public void equals3() {
        ListSet t15 = new ListSet();
        Collection collection = new ArrayList();
        collection.add(5);
        collection.add(10);
        collection.add(15);
        t15.addAll(collection);
        assertEquals(3, collection.size());
        assertTrue(t15.addAll(collection));
        assertEquals(2, t15.size());
        assertFalse(t15.equals(collection));
        assertFalse(t15.containsAll(collection));
    }

    @Test
    public void isEmpty() {
        ListSet t16 = new ListSet();
        assertTrue(t16.isEmpty());
    }

    @Test
    public void isEmpty2() {
        ListSet t17 = new ListSet();
        t17.add(5);
        assertFalse(t17.isEmpty());
    }

    @Test
    public void iterator() {
        ListSet t18 = new ListSet();
        assertNotNull(t18.iterator());
    }

    @Test
    public void hasNext() {
        ListSet t19= new ListSet();
        t19.add(5);
        t19.add(6);
        t19.add(7);
        Iterator i = t19.iterator();
        assertTrue(i.hasNext());
    }

    @Test
    public void hasNext2() {
        ListSet t20= new ListSet();
        Iterator i = t20.iterator();
        assertFalse(i.hasNext());
    }

    @Test
    public void next() {
        ListSet t21 = new ListSet();
        Iterator i = t21.iterator();
        t21.add(5);
        assertEquals(5, i.next());
    }

    @Test
    public void next2() {
        ListSet t22 = new ListSet();
        Iterator i = t22.iterator();
        exceptionRule.expect(IndexOutOfBoundsException.class);
        i.next();
    }

    @Test
    public void remove() {
        ListSet t23 = new ListSet();
        Object o = null;
        exceptionRule.expect(NullPointerException.class);
        t23.remove(o);
    }

    @Test
    public void remove2() {
        ListSet t24 = new ListSet();
        t24.add(5);
        assertTrue(t24.remove(5));
    }

    @Test
    public void remove3() {
        ListSet t25 = new ListSet();
        t25.add(5);
        assertFalse(t25.remove(2));
    }

    @Test
    public void removeAll() {
        ListSet t26 = new ListSet();
        Collection collection = new ArrayList();
        exceptionRule.expect(NullPointerException.class);
        t26.add(null);
    }

    @Test
    public void removeAll2() {
        ListSet t27 = new ListSet();
        Collection collection = new ArrayList();
        collection.add(2);
        collection.add(3);
        collection.add(4);
        t27.addAll(collection);
        assertEquals(3, collection.size());
    }

    @Test
    public void removeAll3() {
        ListSet t28 = new ListSet();
        Collection collection = new ArrayList();
        assertEquals(0,collection.size());
        assertFalse(t28.removeAll(collection));
    }

    @Test
    public void retainAll() {
        ListSet t29 = new ListSet();
        exceptionRule.expect(NullPointerException.class);
        t29.add(null);
    }

    @Test
    public void retainAll2() {
        ListSet t30 = new ListSet();
        Collection collection = new ArrayList();
        t30.add(1);
        t30.add(2);
        t30.add(3);
        t30.addAll(collection);
        assertTrue(t30.retainAll(collection));
    }

    @Test
    public void retainAll3() {
        ListSet t31 = new ListSet();
        Collection collection = new ArrayList();
        assertFalse(t31.retainAll(collection));
    }

    @Test
    public void size() {
        ListSet t32 = new ListSet();
        t32.add(1);
        t32.add(2);
        assertEquals(2, t32.size());
    }

    @Test
    public void toArray() {
        ListSet t33 = new ListSet();
        Object[] arr = new Object[]{1,2,3};
        t33.add(1);
        t33.add(2);
        t33.add(3);
        assertEquals(arr, t33.toArray());
    }

    @Test
    public void testToArray() {
        ListSet t34 = new ListSet();
        Object[] arr = new Object[]{null};
        assertEquals(arr, t34.toArray(arr));
    }

    @Test
    public void testToArray2() {
        ListSet t35 = new ListSet();
        Object[] arr = new Object[]{1,2,3};
        assertEquals(arr, t35.toArray(arr));
    }

    @Test
    public void getIndexOf() {
        ListSet t36 = new ListSet();
        Object e = null;
        assertEquals(-1,t36.getIndexOf(e));
    }

    @Test
    public void getIndexOf2() {
        ListSet t37 = new ListSet();
        Object e = 10;
        t37.add(e);
        assertEquals(0,t37.getIndexOf(e));
    }

    @Test
    public void getElementAt() {
        ListSet t38 = new ListSet();
        exceptionRule.expect(IndexOutOfBoundsException.class);
        t38.getElementAt(0);
    }

    @Test
    public void getElementAt2() {
        ListSet t39 = new ListSet();
        t39.add(5);
        assertEquals(5, t39.getElementAt(0));
    }


    @Test
    public void changeOrder() {
        ListSet t40 = new ListSet();
        t40.add(1);
        t40.add(2);
        assertEquals(1, t40.getElementAt(0));
        assertEquals(2, t40.getElementAt(1));
        t40.changeOrder();
        assertEquals(2, t40.getElementAt(0));
        assertEquals(1, t40.getElementAt(1));
    }

    @Test
    public void changeOrder2() {
        ListSet t40 = new ListSet();
        t40.add(1);
        assertEquals(1, t40.getElementAt(0));
        t40.changeOrder();
        assertEquals(1, t40.getElementAt(0)); //no change because only 1 element
    }

    @Test
    public void testToString() {
        ListSet t41 = new ListSet();
        assertEquals("{ }\n", t41.toString());
    }

    @Test
    public void testToString2() {
        ListSet t42 = new ListSet();
        t42.add(5);
        t42.add(6);
        assertEquals("{ 5 6 }", t42.toString());
    }


    /***************************
     *
     *
     * Extra code for
     * Statement Coverage
     *
     *
     ***************************/

    @Test
    public void test() {
        ListSet test = new ListSet();
        Collection collection = new ArrayList();
        test.add(1);
        test.add(2);
        test.add(3);
        test.addAll(collection);
        assertEquals(2,  test.getElementAt(1));
        assertEquals(1, test.getIndexOf(2));
    }

    @Test
    public void test1() {
        ListSet test = new ListSet();
        Collection collection = new ArrayList();
        collection.add(2);
        test.addAll(collection);
        assertEquals(1, collection.size());
        assertEquals(1, test.size());
        test.removeAll(collection);
        assertEquals(0, test.size());
    }

    @Test (expected = NullPointerException.class)
    public void test2() {
        ListSet test = new ListSet();
        test.contains(null);
    }

    @Test
    public void test3() {
        Collection collection = new ArrayList();
        ListSet test = new ListSet(collection);
        test.add(6);
        collection.add(6);
        assertEquals(test.size(), collection.size());
        assertTrue(test.containsAll(collection));
        assertTrue(test.equals(collection));
    }

}