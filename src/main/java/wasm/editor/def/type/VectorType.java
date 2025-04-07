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
public class VectorType extends TypeAble {

    public static final int VEC_FLAG = 0x7B;
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
