package wasm.editor.def.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.value.type.ByteValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class NumberType extends TypeAble {

    public static final int UNINTERPRETED_INT_32 = 0x7F;
    public static final int UNINTERPRETED_INT_64 = 0x7E;
    public static final int UNINTERPRETED_FLOAT_32 = 0x7D;
    public static final int UNINTERPRETED_FLOAT_64 = 0x7C;
    private int flag;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        flag = parseTypeAble(inputStream, ByteValue.class).get();
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, ByteValue.class, flag);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(flag);
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
