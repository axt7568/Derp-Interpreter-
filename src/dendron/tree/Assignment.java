/**
 Name - Arjun Thangaraju
 Language - JAVA 9
 Class name - Assignment
 */
package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.Map;

/**
 * Assignment class implements ActionNode.
 */
public class Assignment implements ActionNode {
    private String ident;
    private ExpressionNode rhs;

    /**
     * Assignment Constructor to initialize variables.
     * @param ident -  String variable.
     * @param rhs - variable of type Expression node.
     */
    public Assignment(String ident,ExpressionNode rhs)
    {
        this.ident = ident;
        this.rhs = rhs;
    }

    /**
     * Execute Function to add values.
     * @param symTab the table where variable values are stored
     */
    public void execute(java.util.Map<String,Integer>symTab)
    {
        symTab.put(ident,rhs.evaluate(symTab));

    }

    /**
     * Function to print output.
     */
    public void infixDisplay()
    {
        System.out.print(ident+" := ");
        rhs.infixDisplay();
    }

    /**
     * emit function to add instructions to a list.
     * @return - returns List of Instructions.
     */
    public java.util.List<Machine.Instruction>emit()
    {
        ArrayList<Machine.Instruction> M = new ArrayList<>();
        ArrayList<Machine.Instruction> N = new ArrayList<>();
        Machine.Instruction obj = new Machine.Store(ident);
        M.add(obj);
        N.addAll(rhs.emit());
        N.addAll(M);
        return N;
    }
}


