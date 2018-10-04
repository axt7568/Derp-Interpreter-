/**
 Name - Arjun Thangaraju
 Language - JAVA 9
 Class name - UnaryOperation
 */
package dendron.tree;

import dendron.machine.Machine;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * UnaryOperation class implements the Expression Node Interface.
 */
public class UnaryOperation implements ExpressionNode {
    public static String NEG = "_";
    public static String SQRT = "#";
    public static java.util.Collection<String> OPERATORS;
    private String operator;
    private ExpressionNode expr;


    /**
     * UnaryOperation Constructor to initialize the variables.
     * @param operator - String that stores the operator.
     * @param expr - Stores the Expression Node variable.
     */
    public UnaryOperation(String operator, ExpressionNode expr) {
        OPERATORS = new ArrayList<String>();
        OPERATORS.add(NEG);
        OPERATORS.add(SQRT);
        if (OPERATORS.contains(operator) && (expr != null)) {
            this.operator = operator;
            this.expr = expr;
        }
    }

    /**
     * Evaluate Function to return the computed output.
     * @param symTab symbol table, if needed, to fetch variable values.
     * @return - returns the output after working with the Expression node variable expr and the  specified operator.
     */
    public int evaluate(java.util.Map<String, Integer> symTab) {
        if (operator.equals(SQRT)) {
            return (int) Math.sqrt((expr.evaluate(symTab)));
        } else if (operator.equals(NEG)) {
            return ((int) -1 * (int) expr.evaluate(symTab));
        }
        return -1;
    }

    /**
     * infixDisplay Function that prints the computed output in a specific order.
     */
    public void infixDisplay() {
        System.out.print(operator+" ");
        expr.infixDisplay();
    }

    /**
     * emit function that returns the list of instructions.
     * @return - returns the list of instructions.
     */
    public java.util.List<Machine.Instruction> emit() {
        ArrayList<Machine.Instruction> M = new ArrayList<>();
        ArrayList<Machine.Instruction> N = new ArrayList<>();
        if (operator.equals(SQRT)) {
            Machine.Instruction obj = new Machine.SquareRoot();
            M.add(obj);
        } else if (operator.equals(NEG)) {
            Machine.Instruction obj = new Machine.Negate();
            M.add(obj);
        }
        N.addAll(expr.emit());
        N.addAll(M);
        return N;


    }
}

