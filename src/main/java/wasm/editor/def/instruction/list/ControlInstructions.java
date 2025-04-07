package wasm.editor.def.instruction.list;

import wasm.editor.def.Index;
import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.instruction.Expression;
import wasm.editor.def.instruction.InstructionSignature;
import wasm.editor.def.instruction.type.BlockArgumentType;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum ControlInstructions implements InstructionSignature {

    UNREACHABLE(0x00),
    NOP(0x01),
    BLOCK(0x02, BlockArgumentType.class),
    LOOP(0x03, BlockArgumentType.class),
    IF(0x04, BlockArgumentType.class),
    ELSE(0x05, Expression.class),
    END(0x0B),
    BR(0x0C, Index.class),
    BR_IF(0x0D, Index.class),
    BR_TABLE(0x0E, Vector.class, Index.class, Index.class),
    RETURN(0x0F),
    CALL_FUNC(0x10, Index.class),
    CALL_INDIRECT(0x11, Index.class, Index.class),
    ;

    private final int opcode;
    private final List<Class<? extends TypeAble>> typeClasses;

    @SafeVarargs
    ControlInstructions(int opcode, Class<? extends TypeAble>... typeClasses) {
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
