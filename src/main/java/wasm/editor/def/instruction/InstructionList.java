package wasm.editor.def.instruction;

import wasm.editor.def.instruction.list.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class InstructionList {

    public static final InstructionSignature END_INSTRUCTION = ControlInstructions.END;

    private static final List<InstructionSignature> INSTRUCTION_TYPE_LIST = new ArrayList<>();

    static {
        INSTRUCTION_TYPE_LIST.addAll(Arrays.asList(ControlInstructions.values()));
        INSTRUCTION_TYPE_LIST.addAll(Arrays.asList(MemoryInstructions.values()));
        INSTRUCTION_TYPE_LIST.addAll(Arrays.asList(NumericConstantInstructions.values()));
        INSTRUCTION_TYPE_LIST.addAll(Arrays.asList(NumericOperationInstructions.values()));
        INSTRUCTION_TYPE_LIST.addAll(Arrays.asList(ParametricInstructions.values()));
        INSTRUCTION_TYPE_LIST.addAll(Arrays.asList(ReferenceInstructions.values()));
        INSTRUCTION_TYPE_LIST.addAll(Arrays.asList(TableInstructions.values()));
        INSTRUCTION_TYPE_LIST.addAll(Arrays.asList(VariableInstructions.values()));
    }

    public static InstructionSignature typeOf(int opcode) {
        return INSTRUCTION_TYPE_LIST.stream().filter(type -> type.getOpcode() == opcode).findFirst().orElse(null);
    }
}
