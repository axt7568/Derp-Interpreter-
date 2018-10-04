/**
 Name - Arjun Thangaraju
 Language - JAVA 9
 Class name - BinaryOperation
 */
package dendron.tree;
import dendron.Errors;
import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;

/**
 * BinaryOperation class implements the Expression Node Interface.
 */
public class BinaryOperation implements ExpressionNode {
    private ExpressionNode leftChild;
    private ExpressionNode rightChild;
    String operator;
    public static String ADD = "+";
    public static String DIV = "/";
    public static String MUL = "*";
    public static String SUB = "-";
    public static final java.util.Collection<String> OPERATORS = new ArrayList<String>() {
        {
            add(ADD);
            add(DIV);
            add(MUL);
            add(SUB);
        }

    };


    /**
     * BinaryOperation Constructor to initialize the variables.
     * @param operator - String that stores the operator.
     * @param leftChild - Stores the leftChild .
     * @param rightChild - Stores the rightChild.
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {
        if (OPERATORS.contains(operator) && leftChild != null & rightChild != null) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.operator = operator;
        }
    }

    /**
     * Evaluate Function to return the computed output.
     * @param symTab symbol table, if needed, to fetch variable values.
     * @return - returns the output after working with left,right child and the specified operator.
     */
    public int evaluate(java.util.Map<String, Integer> symTab) {
        if (operator.equals(ADD)) {
            return leftChild.evaluate(symTab) + rightChild.evaluate(symTab);
        } else if (operator.equals(SUB)) {
            return leftChild.evaluate(symTab) - rightChild.evaluate(symTab);
        } else if (operator.equals(DIV)) {
            if(rightChild.evaluate(symTab)==0)
            {
                Errors.report(Errors.Type.DIVIDE_BY_ZERO,null);
            }
            return leftChild.evaluate(symTab) / rightChild.evaluate(symTab);
        } else if (operator.equals(MUL)) {
            return leftChild.evaluate(symTab) * rightChild.evaluate(symTab);
        }
        return -1;

    }

    /**
     * infixDisplay Function that prints the computed output in a specific order.
     */
    public void infixDisplay() {
        System.out.print("( ");
        leftChild.infixDisplay();
        System.out.print(" "+ operator+" ");
        rightChild.infixDisplay();
        System.out.print(" )");
    }

    /**
     * emit function that returns the list of instructions.
     * @return - returns the list of instructions.
     */

    public java.util.List<Machine.Instruction> emit() {
        ArrayList<Machine.Instruction> M = new ArrayList<>();
        ArrayList<Machine.Instruction> N = new ArrayList<>();
        if (operator.equals(ADD)) {
            Machine.Instruction obj = new Machine.Add();
            M.add(obj);
        } else if (operator.equals(SUB)) {
            Machine.Instruction obj = new Machine.Subtract();
            M.add(obj);
        } else if (operator.equals(DIV)) {
            Machine.Instruction obj = new Machine.Divide();
            M.add(obj);
        } else if(operator.equals(MUL)) {
            Machine.Instruction obj = new Machine.Multiply();
            M.add(obj);

        }
        N.addAll(leftChild.emit());
        N.addAll(rightChild.emit());
        N.addAll(M);
        return N;



    }
}
