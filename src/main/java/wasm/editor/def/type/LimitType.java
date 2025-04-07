package wasm.editor.def.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.value.type.ByteValue;
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
public class LimitType extends TypeAble {

    private int flag;
    private int min;
    private int max;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        this.flag = parseTypeAble(inputStream, ByteValue.class).get();
        this.min = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
        if (flag == 0x01) {
            this.max = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
        }
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, ByteValue.class, flag);
        writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(min));
        if (flag == 0x01) {
            writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(max));
        }
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(flag, min, max);
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
