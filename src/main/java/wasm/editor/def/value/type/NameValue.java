package wasm.editor.def.value.type;

import wasm.editor.def.value.ValueTypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class NameValue extends ValueTypeAble<String> {

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        final int nameLength = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
        final byte[] nameBytes = new byte[nameLength];
        if (nameLength > 0 && inputStream.read(nameBytes) < nameLength) {
            throw new IOException(String.format("Failed to read name with length=%d.", nameLength));
        }
        value = new String(nameBytes, StandardCharsets.UTF_8);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        final byte[] nameBytes = value.getBytes(StandardCharsets.UTF_8);
        writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(nameBytes.length));
        if (nameBytes.length > 0) {
            outputStream.writeBytes(nameBytes);
        }
    }
}
