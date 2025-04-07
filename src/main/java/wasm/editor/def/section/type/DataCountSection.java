package wasm.editor.def.section.type;

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
public class DataCountSection extends TypeAble {

    private int dataCount;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        dataCount = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(dataCount));
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(dataCount);
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
}
