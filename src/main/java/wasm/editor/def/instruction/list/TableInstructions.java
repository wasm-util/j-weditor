package wasm.editor.def.instruction.list;

import wasm.editor.def.Index;
import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.InstructionSignature;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum TableInstructions implements InstructionSignature {

    TABLE_GET(0x25, Index.class),
    TABLE_SET(0x26, Index.class),
    ;

    private final int opcode;
    private final List<Class<? extends TypeAble>> typeClasses;

    @SafeVarargs
    TableInstructions(int opcode, Class<? extends TypeAble>... typeClasses) {
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
