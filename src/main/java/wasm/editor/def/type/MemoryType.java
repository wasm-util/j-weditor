package wasm.editor.def.type;

import wasm.editor.def.TypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class MemoryType extends TypeAble {

    private LimitType memoryLimit;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        memoryLimit = parseTypeAble(inputStream, LimitType.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        memoryLimit.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(memoryLimit);
    }

    public LimitType getMemoryLimit() {
        return memoryLimit;
    }
}
