

package vm;

public class Instruction {

    private Instructions _byteCode;
    private int _instructionIndex;
    private int _operand;

    public Instructions getByteCode() {
        return _byteCode;
    }

    public int getInstructionIndex() {
        return _instructionIndex;
    }

    public int getOperand() {
        return _operand;
    }


    public Instruction(Instructions byteCode, int instructionIndex, int operand) {
        _byteCode = byteCode;
        _instructionIndex = instructionIndex;
        _operand = operand;
    }
}