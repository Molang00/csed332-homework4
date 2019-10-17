package edu.postech.csed332.homework4;

import java.util.Map;
import java.lang.Math;
import edu.postech.csed332.homework4.expression.*;

/**
 * A visitor to evaluate a value of an expression, given a valuation for each variable
 */
public class EvaluationVisitor implements ExpVisitor<Double> {
    private final Map<Integer, Double> valuation;

    public EvaluationVisitor(Map<Integer, Double> valuation) {
        this.valuation = valuation;
    }

    // TODO write and implement the visitor methods for EvaluationVisitor, satisfying
    //  the specification of Exp.eval.
    
    @Override
    public Double visitNumberExp(NumberExp cur){
        Double rst = cur.getValue();
        return rst;
    }

    @Override
    public Double visitVariableExp(VariableExp cur){
        Double rst = valuation.get(cur.getName());
        return rst;
    }
    
    @Override
    public Double visitBinaryExp(BinaryExp curExp, String operator){
        Double rstLeft = curExp.getLeft().accept(this);
        Double rstRight = curExp.getRight().accept(this);
        Double rst;
        switch (operator) {
            case "+":
                rst = rstLeft+rstRight;
                break;
            case "-":
                rst = rstLeft-rstRight;
                break;
            case "*":
                rst = rstLeft*rstRight;
                break;
            case "/":
                rst = rstLeft/rstRight;
                break;
            case "^":
                rst = Math.pow(rstLeft, rstRight);
                break;
            default:
                rst = rstLeft;
        }
        return rst;
    }
}
