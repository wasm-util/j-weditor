package wasm.editor.def;

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
public class Index extends TypeAble {

    private int value;

    public Index() {

    }

    public Index(int value) {
        this.value = value;
    }

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        value = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(value));
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int index) {
        this.value = index;
    }
}