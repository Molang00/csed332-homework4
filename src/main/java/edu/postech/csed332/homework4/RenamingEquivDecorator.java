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
            private Map<Integer, Integer> convert = new HashMap<Integer, Integer>();

            @Override
            public Boolean visitVariableExp(VariableExp cur){
                System.out.println("RenamingVisitor");
                System.out.println(convert);
                if(convert.containsKey(cur.getName())){
                    Boolean rst = ((other instanceof VariableExp) && ((convert.get(cur.getName())) == ((VariableExp)other).getName()));
                    return rst;
                }
                convert.put(cur.getName(), ((VariableExp)other).getName());
                return true;
            }

            @Override
            public Boolean visitBinaryExp(BinaryExp curExp, String operator){
                if(other instanceof BinaryExp){
                    BinaryExp otherBinaryExp = (BinaryExp)other;
                    Boolean rstLeft = (new RenamingEquivDecorator(curExp.getLeft())).equiv(otherBinaryExp.getLeft());
                    Boolean rstRight = (new RenamingEquivDecorator(curExp.getRight())).equiv(otherBinaryExp.getRight());
                    Boolean rst = rstLeft && rstRight;
                    switch (operator) {
                        case "+":
                            rst = rst && (otherBinaryExp instanceof PlusExp);
                            break;
                        case "-":
                            rst = rst && (otherBinaryExp instanceof MinusExp);
                            break;
                        case "*":
                            rst = rst && (otherBinaryExp instanceof MultiplyExp);
                            break;
                        case "/":
                            rst = rst && (otherBinaryExp instanceof DivideExp);
                            break;
                        case "^":
                            rst = rst && (otherBinaryExp instanceof ExponentiationExp);
                            break;
                    }
                    return rst;
                }
                else return false;
            }

            @Override
            public void sayHi(){
                System.out.println("renaming");
            }
        };
        
        visitor.sayHi();
        return accept(visitor);
    }
}
