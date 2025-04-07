package wasm.editor.def.value.type;

import wasm.editor.def.value.ValueTypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class UnInterpretedInteger32Value extends ValueTypeAble<Integer> {

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        final byte[] bytes = new byte[4];
        if (inputStream.read(bytes) < 4) {
            value = -1;
            return;
        }
        value = (int) bytes[0];
        value |= ((int) bytes[1] << 8);
        value |= ((int) bytes[2] << 16);
        value |= ((int) bytes[3] << 24);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        outputStream.write(value & 0xff);
        outputStream.write((value >> 8) & 0xff);
        outputStream.write((value >> 16) & 0xff);
        outputStream.write((value >> 24) & 0xff);
    }
}
