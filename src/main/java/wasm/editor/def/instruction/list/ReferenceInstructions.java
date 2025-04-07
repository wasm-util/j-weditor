package wasm.editor.def.instruction.list;

import wasm.editor.def.Index;
import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.InstructionSignature;
import wasm.editor.def.type.ReferenceType;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum ReferenceInstructions implements InstructionSignature {

    REF_NULL(0xD0, ReferenceType.class),
    REF_IS_NULL(0xD1),
    REF_FUNC(0xD2, Index.class),
    ;

    private final int opcode;
    private final List<Class<? extends TypeAble>> typeClasses;

    @SafeVarargs
    ReferenceInstructions(int opcode, Class<? extends TypeAble>... typeClasses) {
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
