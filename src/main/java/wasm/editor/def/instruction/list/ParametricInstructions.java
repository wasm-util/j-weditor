package wasm.editor.def.instruction.list;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.instruction.InstructionSignature;
import wasm.editor.def.type.ValueType;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum ParametricInstructions implements InstructionSignature {

    DROP(0x1A),
    SELECT(0x1B),
    SELECT_TYPE(0x1C, Vector.class, ValueType.class),
    ;


    private final int opcode;
    private final List<Class<? extends TypeAble>> typeClasses;

    @SafeVarargs
    ParametricInstructions(int opcode, Class<? extends TypeAble>... typeClasses) {
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
