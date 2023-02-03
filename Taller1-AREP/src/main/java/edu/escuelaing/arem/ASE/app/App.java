package edu.escuelaing.arem.ASE.app;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        List<String> ls = new LinkedList<>();
        fromArrayToCollection(args, ls);

        Object[] objectArray = {"Hola", new Object()};
        List<Object> lo = new LinkedList<>();
        fromArrayToCollection(objectArray, lo);

    }

    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // Correct
        }
    }
}
