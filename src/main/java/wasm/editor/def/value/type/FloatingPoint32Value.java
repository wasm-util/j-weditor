package wasm.editor.def.value.type;

import wasm.editor.def.value.ValueTypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class FloatingPoint32Value extends ValueTypeAble<Float> {

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        final byte[] bytes = new byte[4];
        if (inputStream.read(bytes) < 4) {
            throw new IOException("Failed to read floating point 32.");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        value = buffer.getFloat();
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putFloat(value);
        outputStream.write(buffer.array());
    }
}
