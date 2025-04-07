package wasm.editor.def.instruction.type;

import wasm.editor.def.Index;
import wasm.editor.def.TypeAble;
import wasm.editor.def.value.type.ByteValue;
import wasm.editor.def.value.type.Unsigned128LEB32Value;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class ExtendedOpArgumentType extends TypeAble {

    public static final int I32_TRUNC_SAT_F32_S = 0;
    public static final int I32_TRUNC_SAT_F32_U = 1;
    public static final int I32_TRUNC_SAT_F64_S = 2;
    public static final int I32_TRUNC_SAT_F64_U = 3;
    public static final int I64_TRUNC_SAT_F32_S = 4;
    public static final int I64_TRUNC_SAT_F32_U = 5;
    public static final int I64_TRUNC_SAT_F64_S = 6;
    public static final int I64_TRUNC_SAT_F64_U = 7;
    public static final int MEMORY_INIT = 8;
    public static final int DATA_DROP = 9;
    public static final int MEMORY_COPY = 10;
    public static final int MEMORY_FILL = 11;
    public static final int TABLE_INIT = 12;
    public static final int ELEMENT_DROP = 13;
    public static final int TABLE_COPY = 14;
    public static final int TABLE_GROW = 15;
    public static final int TABLE_SIZE = 16;
    public static final int TABLE_FILL = 17;

    private int opcode;
    private TypeAble[] arguments;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        opcode = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
        switch(opcode) {
            case I32_TRUNC_SAT_F32_S:
            case I32_TRUNC_SAT_F32_U:
            case I32_TRUNC_SAT_F64_S:
            case I32_TRUNC_SAT_F64_U:
            case I64_TRUNC_SAT_F32_S:
            case I64_TRUNC_SAT_F32_U:
            case I64_TRUNC_SAT_F64_S:
            case I64_TRUNC_SAT_F64_U:
                break;
            case MEMORY_INIT:
                arguments = new TypeAble[2];
                arguments[0] = parseTypeAble(inputStream, Index.class);
                arguments[1] = parseTypeAble(inputStream, ByteValue.class);
                break;
            case MEMORY_COPY:
                arguments = new TypeAble[2];
                arguments[0] = parseTypeAble(inputStream, ByteValue.class);
                arguments[1] = parseTypeAble(inputStream, ByteValue.class);
                break;
            case TABLE_INIT:
            case TABLE_COPY:
                arguments = new TypeAble[2];
                arguments[0] = parseTypeAble(inputStream, Index.class);
                arguments[1] = parseTypeAble(inputStream, Index.class);
                break;
            case MEMORY_FILL:
                arguments = new TypeAble[1];
                arguments[0] = parseTypeAble(inputStream, ByteValue.class);
                break;
            case DATA_DROP:
            case ELEMENT_DROP:
            case TABLE_GROW:
            case TABLE_SIZE:
            case TABLE_FILL:
                arguments = new TypeAble[1];
                arguments[0] = parseTypeAble(inputStream, Index.class);
                break;
            default:
                throw new IOException(String.format("Unknown opcode: %d", opcode));
        }
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(opcode));
        switch(opcode) {
            case I32_TRUNC_SAT_F32_S:
            case I32_TRUNC_SAT_F32_U:
            case I32_TRUNC_SAT_F64_S:
            case I32_TRUNC_SAT_F64_U:
            case I64_TRUNC_SAT_F32_S:
            case I64_TRUNC_SAT_F32_U:
            case I64_TRUNC_SAT_F64_S:
            case I64_TRUNC_SAT_F64_U:
                break;
            case MEMORY_INIT:
            case MEMORY_COPY:
            case TABLE_INIT:
            case TABLE_COPY:
                if (arguments == null || arguments.length == 2) {
                    throw new IOException(String.format("Expected 2 arguments for opcode: %s", opcode));
                }
                arguments[0].write(outputStream);
                arguments[1].write(outputStream);
                break;
            case DATA_DROP:
            case MEMORY_FILL:
            case ELEMENT_DROP:
            case TABLE_GROW:
            case TABLE_SIZE:
            case TABLE_FILL:
                if (arguments == null || arguments.length == 1) {
                    throw new IOException(String.format("Expected 1 argument for opcode: %s", opcode));
                }
                arguments[0].write(outputStream);
                break;
            default:
                throw new IOException(String.format("Unknown opcode: %d", opcode));
        }
    }

    @Override
    protected Collection<Object> parsedFields() {
        final List<Object> list = new ArrayList<>();
        list.add(opcode);
        if (arguments != null) {
            list.add(arguments);
        }
        return list;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public TypeAble[] getArguments() {
        return arguments;
    }

    public void setArguments(TypeAble[] arguments) {
        this.arguments = arguments;
    }
}
