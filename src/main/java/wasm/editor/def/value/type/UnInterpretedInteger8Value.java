package wasm.editor.def.value.type;

import wasm.editor.def.value.ValueTypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class UnInterpretedInteger8Value extends ValueTypeAble<Integer> {

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        value = inputStream.read();
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        outputStream.write(value & 0xff);
    }
}
