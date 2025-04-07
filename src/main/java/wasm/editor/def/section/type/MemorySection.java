package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.type.MemoryType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class MemorySection extends TypeAble {

    private Vector<MemoryType> memoryTypes;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        memoryTypes = parseTypeAble(inputStream, Vector.class, MemoryType.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        memoryTypes.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(memoryTypes);
    }

    public Vector<MemoryType> getMemoryTypes() {
        return memoryTypes;
    }

}