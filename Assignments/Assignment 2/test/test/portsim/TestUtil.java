package portsim;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.fail;

/**
 * Utility methods for assignment unit testing.
 */
public class TestUtil {

    // Dummy test that always passes to avoid "No runnable methods" error for this class
    @Test
    public void dummyTest() {}

    /**
     * Fails the current test if the given class has not overridden the Object#equals(Object)
     * method.
     *
     * @param clazz class to assert has overridden equals()
     */
    public static void assertEqualsOverridden(Class<?> clazz) {
        // equals() takes a single Object parameter
        Class<?>[] arguments = new Class[1];
        arguments[0] = Object.class;

        try {
            assertMethodOverridden(clazz, clazz.getMethod("equals", arguments));
        } catch (NoSuchMethodException ignored) {
            // equals will always exist either in Object or overridden in clazz
        }
    }

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

    /**
     * Fails the current test if the given class has not overridden the given method.
     *
     * @param clazz class to check for overriding method
     * @param method method that must be overridden in clazz
     */
    private static void assertMethodOverridden(Class<?> clazz, Method method) {
        if (method.getDeclaringClass() != clazz) {
            fail(method.getName() + " has not been overridden in " + clazz.getSimpleName()
                    + ", so cannot be tested.");
        }
    }
}
