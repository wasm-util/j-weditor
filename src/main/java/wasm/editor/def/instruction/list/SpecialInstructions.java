package wasm.editor.def.instruction.list;

import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.InstructionSignature;
import wasm.editor.def.instruction.type.ExtendedOpArgumentType;
import wasm.editor.def.instruction.type.VectorOpArgumentType;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum SpecialInstructions implements InstructionSignature {

    EXTENDED_OP(0xFC, ExtendedOpArgumentType.class),
    VECTOR_ABSTRACT(0xFD, VectorOpArgumentType.class),
    ;

    private final int opcode;
    private final List<Class<? extends TypeAble>> typeClasses;

    @SafeVarargs
    SpecialInstructions(int opcode, Class<? extends TypeAble>... typeClasses) {
        this.opcode = opcode;
        this.typeClasses = List.of(typeClasses);
    }

    public int getOpcode() {
        return opcode;
    }

    @Override
    public List<Class<? extends TypeAble>> getTypeClasses() {
        return typeClasses;
    }
}
