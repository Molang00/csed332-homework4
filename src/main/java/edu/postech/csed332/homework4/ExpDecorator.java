package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

/**
 * A base decorator class
 */
public class ExpDecorator extends Exp {
    private final Exp expression;

    ExpDecorator(Exp e) {
        expression = e;
    }

    // TODO implement all the methods of ExpDecorator

    /**
     * Performs an operation given an implementation of ExpVisitor
     *
     * @param visitor a visitor
     * @return the result
     */
    @Override
    public <T> T accept(ExpVisitor<T> visitor){
        return expression.accept(visitor);
    }

    /**
     * Returns the string representation of this expression.
     *
     * @return the string representation of this expression.
     */
    @Override
    public String toString() {
        return expression.toString();
    }

    /**
     * Evaluates the value of expression, provided that the value of each variable is given in the valuation.
     *
     * @param valuation a valuation
     * @return the value of the expression under the valuation
     * @throws NoSuchElementException if there is a variable not in valuation
     */
    @Override
    @NotNull
    public Double eval(@NotNull Map<Integer, Double> valuation) {
        return expression.eval(valuation);
    }

    /**
     * Checks if a given expression is syntactically the same as this expression.
     *
     * @param other an expression
     * @return true if other is syntactically the same as this expression
     */
    @Override
    public boolean equiv(@NotNull Exp other) {
        return expression.equiv(other);
    }
}
