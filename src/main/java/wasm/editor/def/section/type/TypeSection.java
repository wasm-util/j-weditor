package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.type.FunctionType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class TypeSection extends TypeAble {

    private Vector<FunctionType> functionTypes;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        functionTypes = parseTypeAble(inputStream, Vector.class, FunctionType.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        functionTypes.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(functionTypes);
    }

    public Vector<FunctionType> getFunctionTypes() {
        return functionTypes;
    }
}