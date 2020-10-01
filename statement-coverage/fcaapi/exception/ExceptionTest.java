package de.tudresden.inf.tcs.fcaapi.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ExceptionTest {

    /****************************
     *
     *
     * Part 2:
     * Statement Coverage
     *
     *
     *****************************/

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void IllegalContextException() throws IllegalContextException {
        exceptionRule.expect(IllegalContextException.class);
        throw new IllegalContextException();
    }

    @Test
    public void IllegalContextExceptionString() throws IllegalContextException {
        exceptionRule.expect(IllegalContextException.class);
        throw new IllegalContextException("IllegalContextException");
    }

    @Test
    public void IllegalAttributeException() throws IllegalAttributeException {
        exceptionRule.expect(IllegalAttributeException.class);
        throw new IllegalAttributeException();
    }

    @Test
    public void IllegalAttributeExceptionString() throws IllegalAttributeException {
        exceptionRule.expect(IllegalAttributeException.class);
        throw new IllegalAttributeException("IllegalAttributeException");
    }

    @Test
    public void IllegalExpertException() throws IllegalExpertException {
        exceptionRule.expect(IllegalExpertException.class);
        throw new IllegalExpertException();
    }

    @Test
    public void IllegalExpertExceptionString() throws IllegalExpertException {
        exceptionRule.expect(IllegalExpertException.class);
        throw new IllegalExpertException("IllegalExpertException");
    }

}