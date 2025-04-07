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
public class FloatingPoint64Value extends ValueTypeAble<Double> {

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        final byte[] bytes = new byte[8];
        if (inputStream.read(bytes) < 8) {
            throw new IOException("Failed to read floating point 64.");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        value = buffer.getDouble();
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putDouble(value);
        outputStream.write(buffer.array());
    }
}
