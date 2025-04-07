package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.value.type.NameValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class CustomSection extends TypeAble {

    private String name;
    private byte[] extraBytes;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        name = parseTypeAble(inputStream, NameValue.class).get();

        //remainder of bytes are read until not possible
        final List<Byte> byteList = new ArrayList<>();
        int b;
        while((b = inputStream.read()) != -1) {
            byteList.add((byte) b);
        }
        extraBytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            extraBytes[i] = byteList.get(i);
        }
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, NameValue.class, name);
        outputStream.write(extraBytes);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(name);
    }
}
