/**
 Name - Arjun Thangaraju
 Language - JAVA 9
 Class name - Constant
 */
package dendron.tree;
import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Constant Class implements the ExpressionNode interface.
 */
public class Constant implements ExpressionNode {
    public int value;

    /**
     * Constructor to initialize the values.
     * @param value - The value of the constant.
     */
    public Constant(int value)
    {
        this.value = value;
    }

    /**
     * Function to print output.
     */
    public void infixDisplay()
    {
        System.out.print(value);
    }

    /**
     * Function to return the value of the constant.
     * @param symTab symbol table, if needed, to fetch variable values.
     * @return - The value of the constant.
     */
    public int evaluate(java.util.Map<String,Integer>symTab)
    {
        return value;
    }

    /**
     * Function to return the list of instructions to be executed.
     * @return - List of Instructions.
     */
    public java.util.List<Machine.Instruction>emit()
    {
        Machine.Instruction obj = new Machine.PushConst(value);
        ArrayList<Machine.Instruction> M = new ArrayList<>();
        M.add(obj);
        return M ;
    }

}
