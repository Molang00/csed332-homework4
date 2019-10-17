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
    
    @Override
    @NotNull
    public Double eval(@NotNull Map<Integer, Double> valuation)
}
