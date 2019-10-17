package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;
import java.util.*;

/**
 * This expression is equivalent to another expression that is syntactically identical up to renaming.
 * For example, "(x1 + x2) * x3 + 1.0 * x1" is equivalent to "(x3 + x1) * x2 + 1.0 * x3", but not
 * equivalent to "(x3 + x1) * x2 + 1.0 * x1".
 */
public class RenamingEquivDecorator extends ExpDecorator {

    public RenamingEquivDecorator(Exp e) {
        super(e);
    }

    @Override
    public boolean equiv(@NotNull Exp other) {
        // TODO implement this
        ExpVisitor<Boolean> visitor = new EquivalenceVisitor(other){
            private Map<Integer, Integer> convert;

            @Override
            public Boolean visitVariableExp(VariableExp cur){
                if(convert == null) convert = new HashMap<Integer, Integer>();
                if(convert.containsKey(cur.getName())){
                    Boolean rst = ((other instanceof VariableExp) && ((convert.get(cur.getName())) == ((VariableExp)other).getName()));
                    return rst;
                }
                convert.put(cur.getName(), ((VariableExp)other).getName());
                return true;
            }
        };
        return accept(visitor);
    }
}
