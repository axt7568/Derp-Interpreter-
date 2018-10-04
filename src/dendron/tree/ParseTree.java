/**
 Name - Arjun Thangaraju
 Language - JAVA 9
 Class name - ParseTree
 */
package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;
import dendron.tree.ActionNode;
import dendron.tree.ExpressionNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dendron.tree.BinaryOperation.*;

/**
 * Operations that are done on a Dendron code parse tree.
 *
 *
 * @author ARJUN THANGARAJU
 */
public class ParseTree {

    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param program the token list (Strings)
     */
    Program x;
    Map<String,Integer> symTab;
    public ParseTree( List< String > program ) {
        x = new Program();
        symTab  = new HashMap<String,Integer>();
        while(program.size() != 0){
            x.addAction(parseAction(program));
        }
        }


    /**
     * Parse the next action (statement) in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for the action
     */
    private ActionNode parseAction( List< String > program ) {

       if(program.size() == 0)
       {
           Errors.report(Errors.Type.PREMATURE_END,null);
       }
       String temp = program.remove(0);
       if(temp.equals("@"))
       {
           return new Print(parseExpr(program));
       }
       else if(temp.equals(":="))
       {
           if(program.size() == 0)
           {
               Errors.report(Errors.Type.PREMATURE_END,null);
           }
           String temp1 = program.remove(0);
           return  new Assignment(temp1,parseExpr(program));
       }
       else
       {
           Errors.report(Errors.Type.ILLEGAL_VALUE,temp);
           return null;
       }
    }

    /**
     * Parse the next expression in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for this expression
     */
    private ExpressionNode parseExpr( List< String > program ) {
        if(program.size() == 0)
        {
            Errors.report(Errors.Type.PREMATURE_END,null);
        }
        String temp = program.remove(0);
        if(temp.equals("#"))//change
        {
            return  new UnaryOperation(temp,parseExpr(program));
        }
        else if(temp.equals("_"))
        {
            return new UnaryOperation(temp,parseExpr(program));
        }

        else if((OPERATORS.contains(temp)))
        {
            return new BinaryOperation((temp),parseExpr(program),parseExpr(program));

        }


        else if(temp.matches("^[a-zA-Z].*"))
        {
            return new Variable(temp);
        }
        else if(temp.matches("[-+]?\\d+"))
        {
            return new Constant(Integer.parseInt(temp));
        }
        else
        {
            Errors.report(Errors.Type.ILLEGAL_VALUE,temp);
            return null;
        }

    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see dendron.tree.ActionNode#infixDisplay()
     */

    public void displayProgram() {
        System.out.println('\n'+"The Program, with expressions in infix notation:"+'\n');
        x.infixDisplay();



    }

    /**
     * Run the program represented by the tree directly
     * @see dendron.tree.ActionNode#execute(Map)
     */
    public void interpret() {
        System.out.println('\n'+"Interpreting the parse tree...");
        x.execute(symTab);
        //System.out.println("");
        System.out.println("Interpretation complete."+'\n');
        Errors.dump(symTab);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {
        return x.emit();
    }

}
