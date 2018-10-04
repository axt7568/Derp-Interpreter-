/**
 Name - Arjun Thangaraju
 Language - JAVA 9
 Class name - Program
 */
package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.List;

/**
 * Program class implements the ActionNode.
 */
public class Program implements ActionNode {
    List<ActionNode> L;
    ArrayList<Machine.Instruction> ins = new ArrayList<Machine.Instruction>();

    /**
     * Constructor to make an ArrayList of type Action Node.
     */
    public Program() {
        L = new ArrayList<ActionNode>();
    }

    /**
     * addAction function to add an Action Node to an Array List.
     * @param newNode
     */

    public void addAction(ActionNode newNode) {
        L.add(newNode);
    }

    /**
     * Function to call the overall execute function for each element.
     * @param symTab the table where variable values are stored.
     */
    public void execute(java.util.Map<String, Integer> symTab) {
        for (int i = 0; i < L.size(); i++) {
            L.get(i).execute(symTab);
        }
    }

    /**
     * infixDisplay Function that prints the computed output in a specific order.
     */
    public void infixDisplay() {

        for (int i = 0; i < L.size(); i++) {
            System.out.println("");
            L.get(i).infixDisplay();
        }
    }
    /**
     * emit function that returns the list of instructions.
     * @return - returns the list of instructions.
     */
    public java.util.List<Machine.Instruction> emit() {

        for (int i = 0; i < L.size(); i++) {
            ins.addAll(L.get(i).emit());


        }
        return ins;


    }
}







