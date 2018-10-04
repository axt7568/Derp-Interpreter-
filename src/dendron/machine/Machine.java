/**
 * Name - Arjun Thangaraju
 * Email - axt7568@g.rit.edu
 * Class name - Machine
 * Language - Java
 * Project 1 Part 1
 */

package dendron.machine;

import java.util.List;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

import dendron.Errors;

/**
 * An abstraction of a computing machine that reads instructions
 * and executes them. It has an instruction set, a symbol table
 * for variables (instead of general-purpose memory), and a
 * value stack on which calculations are performed.
 * <p>
 * (Everything is static to avoid the need to master the subtleties
 * of nested class instantiation or to pass the symbol table and
 * stack into every instruction when it executes.)
 * <p>
 * @author James Heliotis
 * @author ARJUN THANGARAJU
 */
public class Machine {

    /**
     * Do not instatiate this class.
     */
    private Machine() {
    }

    public static interface Instruction {
        /**
         * Run this instruction on the Machine, using the Machine's
         * value stack and symbol table.
         */
        void execute();

        /**
         * Show the instruction using text so it can be understood
         * by a person.
         *
         * @return a short string describing what this instruction will do
         */
        @Override
        String toString();
    }

    private static Map<String, Integer> table = null;
    private static Stack<Integer> stack = null;

    /**
     * Reset the Machine to a pristine state.
     *
     * @see Machine#execute
     */
    private static void reset() {
        stack = new Stack<>();
        table = new HashMap<>();
    }

    /**
     * Generate a listing of a program on standard output by
     * calling the toString() method on each instruction
     * contained therein, in order.
     *
     * @param program the list of instructions in the program
     */
    public static void displayInstructions(
            List<Machine.Instruction> program) {
        System.out.println("\nCompiled code:");
        for (Machine.Instruction instr : program) {
            System.out.println(instr);
        }
        System.out.println();
    }

    /**
     * Run a "compiled" program by executing in order each instruction
     * contained therein.
     * Report on the final size of the stack (should normally be empty)
     * and the contents of the symbol table.
     *
     * @param program a list of Machine instructions
     */
    public static void execute(List<Instruction> program) {
        reset();
        System.out.println("Executing compiled code...");
        for (Instruction instr : program) {
            instr.execute();
        }
        System.out.println("Machine: execution ended with " +
                stack.size() + " items left on the stack.");
        System.out.println();
        Errors.dump(table);
    }

    /**
     * The ADD instruction
     */
    public static class Add implements Instruction {
        /**
         * Run the microsteps for the ADD instruction. own_comments - using machine's stack and symbol to perform the ADD function.
         */
        @Override
        public void execute() {
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push(op1 + op2);
        }

        /**
         * Show the ADD instruction as plain text.
         *
         * @return "ADD"
         */
        @Override
        public String toString() {
            return "ADD";
        }
    }

    /**
     * The STORE instruction
     */
    public static class Store implements Instruction {
        /**
         * stores name of target variable
         */
        private String name;

        /**
         * Create a STORE instruction
         *
         * @param ident the name of the target variable
         */
        public Store(String ident) {
            this.name = ident;
        }

        /**
         * Run the microsteps for the STORE instruction.
         */
        @Override
        public void execute() {
            table.put(this.name, stack.pop());
        }

        /**
         * Show the STORE instruction as plain text.
         *
         * @return "STORE" followed by the target variable name
         */
        @Override
        public String toString() {
            return "STORE " + this.name;
        }
    }

    /**
     * The Divide  instruction
     */

    public static class Divide implements Instruction {
        /**
         * Run the microsteps for the Divide instruction. own_comments - using machine's stack and symbol to perform the required division.
         */
        @Override
        public void execute() {
            int op2 = stack.pop();
            int op1 = stack.pop();
            if(op2==0)
            {
                Errors.report(Errors.Type.DIVIDE_BY_ZERO,null);
            }
            stack.push((int) op1 / op2);
        }
        /**
         * Show the Divide instruction as plain text.
         *
         * @return "DIV"
         */
        @Override
        public String toString() {
            return "DIV";
        }

    }

    /**
     * The Multiply instruction
     */
    public static class Multiply implements Instruction {

        /**
         * Run the microsteps for the Multiply instruction. own_comments - using machine's stack and symbol to perform the required Multiply.
         */

        @Override
        public void execute() {
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push(op1 * op2);
        }
        /**
         * Show the Multiply instruction as plain text.
         *
         * @return "MUL"
         */

        @Override
        public String toString() {
            return "MUL";
        }

    }

    /**
     * The Negate instruction
     */
    public static class Negate implements Instruction {
        /**
         * Run the microsteps for the Negate instruction. own_comments - using machine's stack and symbol to perform the required Negate.
         */
        @Override
        public void execute() {
            int op1 = stack.pop();
            stack.push(-1 * op1);
        }

        /**
         * Show the Negate instruction as plain text.
         *
         * @return "NEG"
         */

        @Override
        public String toString() {
            return "NEG";
        }

    }

    /**
     * The PushConst instruction
     */
    public static class PushConst implements Instruction {

        /**
         *
         * Run the microsteps for the PushConst instruction. own_comments - using machine's stack and symbol to perform the required PushConst.
         *@param - constant - the value of the constant.
         */
        private int constant;


        public PushConst(int constant) {
            this.constant = constant;
        }

        @Override
        public void execute() {
            int op1 = constant;
            stack.push(op1);
        }
        /**
         * Show the PushConst instruction as plain text.
         *
         * @return "PUSH + CONSTANT VALUE"
         */
        @Override
        public String toString() {
            return "PUSH   " + constant;
        }
    }
    /**
     * The SquareRoot instruction
     */

    public static class SquareRoot implements Instruction {

        /**
         * Run the microsteps for the SquareRoot instruction. own_comments - using machine's stack and symbol to perform the required SquareRoot.
         */
        @Override
        public void execute() {
            int op1 = stack.pop();
            stack.push((int) (Math.sqrt(op1)));
        }

        /**
         * Show the SquareRoot instruction as plain text.
         *
         * @return "SQRT"
         */
        @Override
        public String toString() {
            return "SQRT";
        }

    }
    /**
     * The Print instruction
     */
    public static class Print implements Instruction {

        /**
         * Run the microsteps for the Print instruction. own_comments - using machine's stack and symbol to perform the required Print function.
         */
        private String oi;

        @Override
        public void execute() {
            int op1 = stack.pop();
            String out = "*** " + op1;
            System.out.println(out);
        }
        /**
         * Show the SquareRoot instruction as plain text.
         *
         * @return "PRINT"
         */
        @Override
        public String toString() {
            return "PRINT";
        }
    }
    /**
     * The Load instruction
     */
    public static class Load implements Instruction {
        /**
         * Run the microsteps for the Load instruction. own_comments - using machine's stack and symbol to perform the required Load function.
         *@param  - ident - The name of the variable.
         */
        private String ident;
        private int op;

        public Load(String ident) {
            this.ident = ident;
        }

        @Override
        public void execute() {
            int op1 = table.get(ident);
            stack.push(op1);
            op = op1;
        }
        /**
         * Show the LOAD instruction as plain text.
         *
         * @return "LOAD VARIABLE NAME"
         */
        @Override
        public String toString() {
            return "LOAD  " + ident;
        }

    }
    public static class Subtract implements Instruction {
        /**
         * Run the microsteps for the Subtract  instruction. own_comments - using machine's stack and symbol to perform the required subtract function.
         */
        @Override
        public void execute() {
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push(op1 - op2);
        }
        /**
         * Show the Subtract instruction as plain text.
         *
         * @return "SUB"
         */
        @Override
        public String toString() {
            return "SUB";
        }
    }
}
