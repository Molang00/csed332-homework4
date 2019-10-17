package edu.postech.csed332.homework4;

import edu.postech.csed332.homework4.expression.*;

import java.math.BigDecimal;

import org.jetbrains.annotations.NotNull;

/**
 * The string representation is given without exponents of double values. For example,
 * 12345678, not 1.2345678E7. (Hint: use java.math.BigDecimal)
 */
public class PrettyPrintExpDecorator extends ExpDecorator {

    public PrettyPrintExpDecorator(Exp e) {
        super(e);
    }

    @NotNull
    @Override
    public String toString() {
        // TODO implement this
        ExpVisitor<String> visitor = new ToStringVisitor(){
            @Override
            public String visitNumberExp(NumberExp cur){
                BigDecimal big = new BigDecimal(cur.getValue());
                return big.toPlainString();
            }
        };
        return accept(visitor);
    }

}