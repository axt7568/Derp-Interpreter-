/**
 Name - Arjun Thangaraju
 Language - JAVA 9
 Class name - Print
 */
package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/**
 * Print class implements ActionNode.
 */
public class Print implements ActionNode {
    private ExpressionNode printee;

    /**
     * Constructor to initialize the ExpressionNode Variable.
     * @param printee - Expression Node Variable.
     */
    public Print(ExpressionNode printee)
    {
        this.printee = printee;
    }

    /**
     * Execute Function to print out the computed.
     * @param symTab the table where variable values are stored.
     */
    public void execute(java.util.Map<String,Integer>symTab)
    {
        System.out.println("=== "+printee.evaluate(symTab));
    }
    /**
     * Function to print output.
     */
    public void infixDisplay()
    {
        System.out.print("print");
        printee.infixDisplay();
    }
    /**
     * emit function that returns the list of instructions.
     * @return - returns the list of instructions.
     */
    public java.util.List<Machine.Instruction>emit()
    {
        ArrayList<Machine.Instruction> M = new ArrayList<>();
        ArrayList<Machine.Instruction> N = new ArrayList<>();
        Machine.Instruction obj = new Machine.Print();
        M.add(obj);
        N.addAll(printee.emit());
        N.addAll(M);
        return N;
        }



}


