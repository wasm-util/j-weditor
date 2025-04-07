package wasm.editor.def.instruction.list;

import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.InstructionSignature;
import wasm.editor.def.instruction.type.MemoryArgumentType;
import wasm.editor.def.value.type.ByteValue;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum MemoryInstructions implements InstructionSignature {

    I32_LOAD(0x28, MemoryArgumentType.class),
    I64_LOAD(0x29, MemoryArgumentType.class),
    F32_LOAD(0x2A, MemoryArgumentType.class),
    F64_LOAD(0x2B, MemoryArgumentType.class),
    I32_LOAD_8_S(0x2C, MemoryArgumentType.class),
    I32_LOAD_8_U(0x2D, MemoryArgumentType.class),
    I32_LOAD_16_S(0x2E, MemoryArgumentType.class),
    I32_LOAD_16_U(0x2F, MemoryArgumentType.class),
    I64_LOAD_8_S(0x30, MemoryArgumentType.class),
    I64_LOAD_8_U(0x31, MemoryArgumentType.class),
    I64_LOAD_16_S(0x32, MemoryArgumentType.class),
    I64_LOAD_16_U(0x33, MemoryArgumentType.class),
    I64_LOAD_32_S(0x34, MemoryArgumentType.class),
    I64_LOAD_32_U(0x35, MemoryArgumentType.class),
    I32_STORE(0x36, MemoryArgumentType.class),
    I64_STORE(0x37, MemoryArgumentType.class),
    F32_STORE(0x38, MemoryArgumentType.class),
    F64_STORE(0x39, MemoryArgumentType.class),
    I32_STORE8(0x3A, MemoryArgumentType.class),
    I32_STORE16(0x3B, MemoryArgumentType.class),
    I64_STORE8(0x3C, MemoryArgumentType.class),
    I64_STORE16(0x3D, MemoryArgumentType.class),
    I64_STORE32(0x3E, MemoryArgumentType.class),
    MEMORY_SIZE(0x3F, ByteValue.class),
    MEMORY_GROW(0x40, ByteValue.class),
    ;

    public static final MemoryInstructions[] LOAD_I32 = { I32_LOAD, I32_LOAD_8_S, I32_LOAD_8_U, I32_LOAD_16_S, I32_LOAD_16_U };
    public static final MemoryInstructions[] LOAD_I64 = { I64_LOAD, I64_LOAD_8_S, I64_LOAD_8_U, I64_LOAD_16_S, I64_LOAD_16_U, I64_LOAD_32_S, I64_LOAD_32_U };
    public static final MemoryInstructions[] LOAD_F32 = { F32_LOAD };
    public static final MemoryInstructions[] LOAD_F64 = { F64_LOAD };

    public static final MemoryInstructions[] STORE_I32 = { I32_STORE, I32_STORE8, I32_STORE16 };
    public static final MemoryInstructions[] STORE_I64 = { I64_STORE, I64_STORE8, I64_STORE16, I64_STORE32 };
    public static final MemoryInstructions[] STORE_F32 = { F32_STORE };
    public static final MemoryInstructions[] STORE_F64 = { F64_STORE };

    private final int opcode;
    private final List<Class<? extends TypeAble>> typeClasses;

    @SafeVarargs
    MemoryInstructions(int opcode, Class<? extends TypeAble>... typeClasses) {
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
