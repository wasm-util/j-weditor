package wasm.editor.def.instruction.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.value.type.Unsigned128LEB32Value;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class MemoryArgumentType extends TypeAble {

    private int align; //exponent of power of 2 e.g. align=2 means value is aligned with 2^2=4 bytes
    private int offset;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        align = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
        offset = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(align));
        writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(offset));
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(align, offset);
    }

    public static MemoryArgumentType createMemoryArgumentType(int align, int offset) {
        final MemoryArgumentType memArg = new MemoryArgumentType();
        memArg.align = align;
        memArg.offset = offset;
        return memArg;
    }

    public int getAlign() {
        return align;
    }

    public int getOffset() {
        return offset;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
