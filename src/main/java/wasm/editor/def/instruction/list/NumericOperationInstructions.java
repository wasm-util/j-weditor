package wasm.editor.def.instruction.list;

import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.InstructionSignature;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum NumericOperationInstructions implements InstructionSignature {

    I32_EQZ(0x45),
    I32_EQ(0x46),
    I32_NE(0x47),
    I32_LT_S(0x48),
    I32_LT_U(0x49),
    I32_GT_S(0x4A),
    I32_GT_U(0x4B),
    I32_LE_S(0x4C),
    I32_LE_U(0x4D),
    I32_GE_S(0x4E),
    I32_GE_U(0x4F),
    I64_EQZ(0x50),
    I64_EQ(0x51),
    I64_NE(0x52),
    I64_LT_S(0x53),
    I64_LT_U(0x54),
    I64_GT_S(0x55),
    I64_GT_U(0x56),
    I64_LE_S(0x57),
    I64_LE_U(0x58),
    I64_GE_S(0x59),
    I64_GE_U(0x5A),
    F32_EQ(0x5B),
    F32_NE(0x5C),
    F32_LT(0x5D),
    F32_GT(0x5E),
    F32_LE(0x5F),
    F32_GE(0x60),
    F64_EQ(0x61),
    F64_NE(0x62),
    F64_LT(0x63),
    F64_GT(0x64),
    F64_LE(0x65),
    F64_GE(0x66),
    I32_CLZ(0x67),
    I32_CTZ(0x68),
    I32_POPCNT(0x69),
    I32_ADD(0x6A),
    I32_SUB(0x6B),
    I32_MUL(0x6C),
    I32_DIV_S(0x6D),
    I32_DIV_U(0x6E),
    I32_REM_S(0x6F),
    I32_REM_U(0x70),
    I32_AND(0x71),
    I32_OR(0x72),
    I32_XOR(0x73),
    I32_SHL(0x74),
    I32_SHR_S(0x75),
    I32_SHR_U(0x76),
    I32_ROTL(0x77),
    I32_ROTR(0x78),
    I64_CLZ(0x79),
    I64_CTZ(0x7A),
    I64_POPCNT(0x7B),
    I64_ADD(0x7C),
    I64_SUB(0x7D),
    I64_MUL(0x7E),
    I64_DIV_S(0x7F),
    I64_DIV_U(0x80),
    I64_REM_S(0x81),
    I64_REM_U(0x82),
    I64_AND(0x83),
    I64_OR(0x84),
    I64_XOR(0x85),
    I64_SHL(0x86),
    I64_SHR_S(0x87),
    I64_SHR_U(0x88),
    I64_ROTL(0x89),
    I64_ROTR(0x8A),
    F32_ABS(0x8B),
    F32_NEG(0x8C),
    F32_CEIL(0x8D),
    F32_FLOOR(0x8E),
    F32_TRUNC(0x8F),
    F32_NEAREST(0x90),
    F32_SQRT(0x91),
    F32_ADD(0x92),
    F32_SUB(0x93),
    F32_MUL(0x94),
    F32_DIV(0x95),
    F32_MIN(0x96),
    F32_MAX(0x97),
    F32_COPYSIGN(0x98),
    F64_ABS(0x99),
    F64_NEG(0x9A),
    F64_CEIL(0x9B),
    F64_FLOOR(0x9C),
    F64_TRUNC(0x9D),
    F64_NEAREST(0x9E),
    F64_SQRT(0x9F),
    F64_ADD(0xA0),
    F64_SUB(0xA1),
    F64_MUL(0xA2),
    F64_DIV(0xA3),
    F64_MIN(0xA4),
    F64_MAX(0xA5),
    F64_COPYSIGN(0xA6),
    I32_WRAP_I64(0xA7),
    I32_TRUNC_F32_S(0xA8),
    I32_TRUNC_F32_U(0xA9),
    I32_TRUNC_F64_S(0xAA),
    I32_TRUNC_F64_U(0xAB),
    I64_EXTEND_I32_S(0xAC),
    I64_EXTEND_I32_U(0xAD),
    I64_TRUNC_F32_S(0xAE),
    I64_TRUNC_F32_U(0xAF),
    I64_TRUNC_F64_S(0xB0),
    I64_TRUNC_F64_U(0xB1),
    F32_CONVERT_I32_S(0xB2),
    F32_CONVERT_I32_U(0xB3),
    F32_CONVERT_I64_S(0xB4),
    F32_CONVERT_I64_U(0xB5),
    F32_DEMOTE_F64(0xB6),
    F64_CONVERT_I32_S(0xB7),
    F64_CONVERT_I32_U(0xB8),
    F64_CONVERT_I64_S(0xB9),
    F64_CONVERT_I64_U(0xBA),
    F64_PROMOTE_F32(0xBB),
    I32_REINTERPRET_F32(0xBC),
    I64_REINTERPRET_F64(0xBD),
    F32_REINTERPRET_I32(0xBE),
    F64_REINTERPRET_I64(0xBF),
    I32_EXTEND8_S(0xC0),
    I32_EXTEND16_S(0xC1),
    I64_EXTEND8_S(0xC2),
    I64_EXTEND16_S(0xC3),
    I64_EXTEND32_S(0xC4),
    ;

    public static final NumericOperationInstructions[] SINGLE_POP_I32 = { I32_EQZ, I32_CLZ, I32_CTZ, I32_POPCNT, I32_TRUNC_F32_S, I32_TRUNC_F32_U, I32_TRUNC_F64_S, I32_TRUNC_F64_U, I32_REINTERPRET_F32, I32_EXTEND8_S, I32_EXTEND16_S };
    public static final NumericOperationInstructions[] SINGLE_POP_I64 = { I64_EQZ, I64_CLZ, I64_CTZ, I64_POPCNT, I64_TRUNC_F32_S, I64_TRUNC_F32_U, I64_TRUNC_F64_S, I64_TRUNC_F64_U, I64_REINTERPRET_F64, I64_EXTEND8_S, I64_EXTEND16_S, I64_EXTEND32_S };
    public static final NumericOperationInstructions[] SINGLE_POP_F32 = { F32_ABS, F32_NEG, F32_CEIL, F32_FLOOR, F32_TRUNC, F32_NEAREST, F32_SQRT, F32_CONVERT_I32_S, F32_CONVERT_I32_U, F32_CONVERT_I64_S, F32_CONVERT_I64_U, F32_DEMOTE_F64, F32_REINTERPRET_I32 };
    public static final NumericOperationInstructions[] SINGLE_POP_F64 = { F64_ABS, F64_NEG, F64_CEIL, F64_FLOOR, F64_TRUNC, F64_NEAREST, F64_SQRT, F64_CONVERT_I32_S, F64_CONVERT_I32_U, F64_CONVERT_I64_S, F64_CONVERT_I64_U, F64_PROMOTE_F32, F64_REINTERPRET_I64 };

    public static final NumericOperationInstructions[] DOUBLE_POP_I32 = { I32_ADD, I32_SUB, I32_MUL, I32_DIV_S, I32_DIV_U, I32_REM_S, I32_REM_U, I32_AND, I32_OR, I32_XOR, I32_SHL, I32_SHR_S, I32_SHR_U, I32_ROTL, I32_ROTR, I32_GT_U, I32_GT_S, I32_EQ, I32_NE, I32_LT_S, I32_LT_U, I32_LE_S, I32_LE_U, I32_GE_S, I32_GE_U };
    public static final NumericOperationInstructions[] DOUBLE_POP_I64 = { I64_ADD, I64_SUB, I64_MUL, I64_DIV_S, I64_DIV_U, I64_REM_S, I64_REM_U, I64_AND, I64_OR, I64_XOR, I64_SHL, I64_SHR_S, I64_SHR_U, I64_ROTL, I64_ROTR, I64_GT_U, I64_GT_S, I64_EQ, I64_NE, I64_LT_S, I64_LT_U, I64_LE_S, I64_LE_U, I64_GE_S, I64_GE_U };
    public static final NumericOperationInstructions[] DOUBLE_POP_F32 = { F32_ADD, F32_SUB, F32_MUL, F32_DIV, F32_MIN, F32_MAX, F32_COPYSIGN, F32_EQ, F32_NE, F32_LT, F32_GT, F32_LE, F32_GE };
    public static final NumericOperationInstructions[] DOUBLE_POP_F64 = { F64_ADD, F64_SUB, F64_MUL, F64_DIV, F64_MIN, F64_MAX, F64_COPYSIGN, F64_EQ, F64_NE, F64_LT, F64_GT, F64_LE, F64_GE };


    private final int opcode;

    NumericOperationInstructions(int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public List<Class<? extends TypeAble>> getTypeClasses() {
        return null;
    }
}
