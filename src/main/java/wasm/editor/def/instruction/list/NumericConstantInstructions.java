package wasm.editor.def.instruction.list;

import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.InstructionSignature;
import wasm.editor.def.value.type.FloatingPoint32Value;
import wasm.editor.def.value.type.FloatingPoint64Value;
import wasm.editor.def.value.type.Signed128LEB32Value;
import wasm.editor.def.value.type.Signed128LEB64Value;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum NumericConstantInstructions implements InstructionSignature {

    I32_CONST(0x41, Signed128LEB32Value.class),
    I64_CONST(0x42, Signed128LEB64Value.class),
    F32_CONST(0x43, FloatingPoint32Value.class),
    F64_CONST(0x44, FloatingPoint64Value.class),
    ;

    private final int opcode;
    private final List<Class<? extends TypeAble>> typeClasses;

    NumericConstantInstructions(int opcode, Class<? extends TypeAble> typeClass) {
        this.opcode = opcode;
        this.typeClasses = List.of(typeClass);
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
