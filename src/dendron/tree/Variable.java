/**
 Name - Arjun Thangaraju
 Language - JAVA 9
 Class name - Variable
 */
package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Variable class implements ExpressionNode.
 */
public class Variable implements  ExpressionNode{
    private String name;

    /**
     * Constructor to initialize the variables.
     * @param name - The name of the variable.
     */
    public Variable(String name)
    {
        this.name = name;
    }
    /**
     * Function to print output.
     */
    public void infixDisplay()
    {
        System.out.print("x");
    }

    /**
     * Fucntion to return the list of instructions.
     * @return - List of instructions.
     */
    public java.util.List<Machine.Instruction>emit()
    {
        Machine.Instruction obj = new Machine.Load(name);
        ArrayList<Machine.Instruction> M = new ArrayList<>();
        M.add(obj);
        return M ;
    }

    /**
     * Function to get the variable name.
     * @param symTab symbol table, if needed, to fetch variable values.
     * @return - returns the variable name.
     */
    public int evaluate(java.util.Map<String,Integer>symTab)
    {
      if(symTab.containsKey(name)==false)
      {
          Errors.report(Errors.Type.UNINITIALIZED,name);
          return -1;
      }
      else
      {
          return symTab.get(name);
      }
    }

}
