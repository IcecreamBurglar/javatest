package vm;

import java.util.Stack;

public class VM {

    /*
    This is a very, very basic stack-machine.
    In a more complete vm, we'd probably want a stack/heap
    hybrid. At the very least, we'd need memory addressing, and a more
    consice instruction set. Overall a better ISA altogether.
    We also probably would want to write-up a quick little Pratt Parser
    - or some other form of lexical analysis - so we can
    transpile from a custom language to our bytecode.
    */
    
    private static Instruction[] _byteCode;
    private static Stack _stack;
    
    public static void main(String[] args) {
        // TODO code application logic here
        init();
        execute();        
    }
    
    private static void init() {
        _byteCode = new Instruction[50];
        _stack = new Stack();
        loadCode();
        execute();
    }
    
    private static void loadCode() {
        /*
        LOAD 0x0002
        LOAD 0x0002
        ADD
        
        LOAD 0x0003
        MUL
        
        LOAD 0x000C
        COP
        EQL
        
        JEZ 0x0009
        END
        
        PRINT
        END
        */
        _byteCode[0] = new Instruction(Instructions.LOAD, 0, 2);
        _byteCode[1] = new Instruction(Instructions.LOAD, 1, 3);
        _byteCode[2] = new Instruction(Instructions.ADD, 2, 0);
        
        _byteCode[3] = new Instruction(Instructions.LOAD, 3, 2);
        _byteCode[4] = new Instruction(Instructions.MUL, 4, 0);
        
        _byteCode[5] = new Instruction(Instructions.LOAD, 5, 10);
        _byteCode[6] = new Instruction(Instructions.COP, 6, 0);
        _byteCode[7] = new Instruction(Instructions.EQL, 7, 0);
        
        _byteCode[8] = new Instruction(Instructions.JEQ, 8, 10);
        _byteCode[9] = new Instruction(Instructions.END, 9, 0);
        
        _byteCode[10] = new Instruction(Instructions.PRINT, 10, 0);
        _byteCode[11] = new Instruction(Instructions.END, 11, 0);
    }
    
    private static void execute() {
        int op1 = 0;
        int op2 = 0;
        for(int i = 0; i < _byteCode.length; i++) {
            Instruction item = _byteCode[i];
            switch (item.getByteCode()) {
                case LOAD:
                    _stack.push(item.getOperand());
                    break;
                case ADD:
                    op1 = (int)_stack.pop();
                    op2 = (int)_stack.pop();
                    _stack.push(op1 + op2);
                    break;
                case SUB:
                    op1 = (int)_stack.pop();
                    op2 = (int)_stack.pop();
                    _stack.push(op1 - op2);
                    break;
                case MUL:
                    op1 = (int)_stack.pop();
                    op2 = (int)_stack.pop();
                    _stack.push(op1 * op2);
                    break;
                case DIV:
                    op1 = (int)_stack.pop();
                    op2 = (int)_stack.pop();
                    _stack.push(op1 / op2);
                    break;
                case JMP:
                    i = item.getOperand() - 1;
                    break;
                case EQL:
                    op1 = (int)_stack.pop();
                    op2 = (int)_stack.pop();
                    if(op1 == op2) {
                        _stack.push(1);
                    }
                    else {
                        _stack.push(0);
                    }
                    break;
                case NEQL:
                    op1 = (int)_stack.pop();
                    op2 = (int)_stack.pop();
                    if(op1 == op2) {
                        _stack.push(0);
                    }
                    else {
                        _stack.push(1);
                    }
                    break;
                case JEQ:
                    op1 = (int)_stack.pop();
                    if(op1 == 1) {
                        i = item.getOperand() - 1;
                    }
                    break;
                case PRINT:
                    System.out.println(_stack.pop());
                    break;
                case COP:
                    op1 = (int)_stack.pop();
                    _stack.push(op1);
                    _stack.push(op1);
                    break;
                case END:
                    return;
            }
        }
    }    
}



