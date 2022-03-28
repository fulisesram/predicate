import org.jetbrains.annotations.NotNull;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/** Los predicados son Interfaces funcionales con un boolean llamado test que evalua el parametro
 * que se pasa y devuelve un boolean de acuerdo a la implentacion y tiene varios por default como
 * and, negate, or etc y todos esos devuelven predicados */

public class predicate {

    /* <T> tipo-valor con el que vamos a trabajar, devuelve un boolean
    la funcion usingPredicate recibe un parametro de tipo T y tambien
    recibe un predicado de tipo T
     */
    private static <T> boolean usingPredicate(
            T value, @NotNull Predicate<T> predicate)
    {
        return predicate.test(value);
    }

    /* funcion con 3 predicados */
    private static  <T> boolean usingPredicate(
            T value, @NotNull Predicate<T> predicate, Predicate<T> andPredicate, Predicate<T> orPredicate)
    {
        return predicate
                .and(andPredicate)
                .or(orPredicate)
                .test(value);
    }

    public static void main(String[] args)
    {   /* Predicado: si el elemento-valor "e" es instancia de Stirng */
        Predicate<Object> isString = e -> e instanceof String;
        var result=usingPredicate(123, isString)?"Es un string":"No es un String";
        System.out.println(result);

        /* Usando los diferentes tipos de predicados */
        Predicate<Float> isGreterThanZero = value -> value >  0;
        Predicate<Float> isLessThan1000 = value -> value < 1000;
        Predicate<Float> orPredicate =  Float::isFinite;
        Predicate<Float> notPredicate = Predicate.not(isGreterThanZero);
        var valide = usingPredicate(5000f,isGreterThanZero,isLessThan1000, orPredicate);
        var result2 = valide ? "Valor aceptado" : "Valor rechazado";
        System.out.println(result2);

        /* BiPredicate acepta 2 valores y regresa un boolean*/
        BiPredicate<Integer,Integer> isGreater = MainPredicate::isGreater;
        System.out.println("Es mayor el primer numero? : " + isGreater.negate().test(123,321));
    }
    public final class MainPredicate
    {
        public static boolean isGreater (Integer greater, Integer less)
        {
            return greater > less;
        }
    }


}
