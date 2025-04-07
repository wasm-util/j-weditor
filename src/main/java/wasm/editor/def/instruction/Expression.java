package wasm.editor.def.instruction;

import wasm.editor.Parser;
import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.list.ControlInstructions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class Expression extends TypeAble {

    private List<Instruction> instructions = new ArrayList<>();

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        int opcode;
        while(true) {
            opcode = inputStream.read();
            final InstructionSignature signature = InstructionList.typeOf(opcode);
            if (signature == null) {
                throw new IOException(String.format("Unsupported instruction: %s", Parser.toHex(opcode)));
            }
            final Instruction instruction = new Instruction(signature);
            instruction.parse(inputStream);
            instructions.add(instruction);
            if (isLastInstruction(signature)) {
                break;
            }
        }
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        for (Instruction instruction : instructions) {
            outputStream.write(instruction.getSignature().getOpcode());
            instruction.write(outputStream);
        }
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(instructions);
    }

    private boolean isLastInstruction(InstructionSignature signature) {
        return Instruction.equalType(signature, InstructionList.END_INSTRUCTION, ControlInstructions.ELSE);
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Instruction getEndInstruction() {
        return instructions.get(instructions.size() - 1);
    }
}
