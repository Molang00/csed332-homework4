package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;
import org.jetbrains.annotations.NotNull;

/**
 * A visitor to check whether a given expression is syntactically equivalent to another expression.
 */
public class EquivalenceVisitor implements ExpVisitor<Boolean> {
    Exp other;
    public EquivalenceVisitor(@NotNull Exp other) {
        // TODO: implement this
        this.other = other;
    }

    // TODO write and implement the visitor methods for EquivalenceVisitor, satisfying
    //  the specification of Exp.equiv. You may need to add more member variables.
    
    @Override
    public Boolean visitNumberExp(NumberExp cur){
        Boolean rst = ((other instanceof NumberExp) && 
            (Double.compare(cur.getValue(), ((NumberExp)other).getValue()) == 0));
        return rst;
    }

    @Override
    public Boolean visitVariableExp(VariableExp cur){
        Boolean rst = ((other instanceof VariableExp) && ((cur.getName()) == ((VariableExp)other).getName()));
        return rst;
    }
    
    @Override
    public Boolean visitBinaryExp(BinaryExp curExp, String operator){
        if(other instanceof BinaryExp){
            BinaryExp otherBinaryExp = (BinaryExp)other;
            Boolean rstLeft = curExp.getLeft().equiv(otherBinaryExp.getLeft());
            Boolean rstRight = curExp.getRight().equiv(otherBinaryExp.getRight());
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
    public void sayHi(){}
}
