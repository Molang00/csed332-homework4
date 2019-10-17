package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * When evaluating the expression, use the default value for variables not present in the valuation.
 */
public class DefaultValueExpDecorator extends ExpDecorator {
    @NotNull
    private final Double defaultValue;

    public DefaultValueExpDecorator(@NotNull Exp e, @NotNull Double defaultValue) {
        super(e);
        this.defaultValue = defaultValue;
    }

    @Override
    @NotNull
    public Double eval(@NotNull Map<Integer, Double> valuation) {
        // TODO implement this
        ExpVisitor<Double> visitor = new EvaluationVisitor(valuation){
            @Override
            public Double visitVariableExp(VariableExp cur){
                System.out.println("defaultValueVisitor");
                System.out.println(valuation);
                if(valuation.containsKey(cur.getName())){
                    return valuation.get(cur.getName());
                }
                return defaultValue;
            }

            @Override
            public void sayHi(){
                System.out.println("defaultValue");
            }
        };
        visitor.sayHi();
        return accept(visitor);
    }
}
