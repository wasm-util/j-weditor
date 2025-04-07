package wasm.editor.def.instruction.list;

import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.InstructionSignature;
import wasm.editor.def.value.type.Unsigned128LEB32Value;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum VariableInstructions implements InstructionSignature {

    LOCAL_GET(0x20),
    LOCAL_SET(0x21),
    LOCAL_TEE(0x22),
    GLOBAL_GET(0x23),
    GLOBAL_SET(0x24),
    ;

    private final int opcode;

    private static final List<Class<? extends TypeAble>> typeClasses = List.of(Unsigned128LEB32Value.class);

    VariableInstructions(int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public List<Class<? extends TypeAble>> getTypeClasses() {
        return typeClasses;
    }
}
