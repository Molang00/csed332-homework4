package edu.postech.csed332.homework4;
import edu.postech.csed332.homework4.expression.*;

/**
 * A visitor interface for expressions
 *
 * @param <T> type of return values
 */
public interface ExpVisitor<T> {
    // TODO define the visit methods for Exp

    public T visitNumberExp(NumberExp cur);
    public T visitVariableExp(VariableExp cur);
    public T visitBinaryExp(BinaryExp curExp, String operator);
    public void sayHi();
}
