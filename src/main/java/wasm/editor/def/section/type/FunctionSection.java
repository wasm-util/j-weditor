package wasm.editor.def.section.type;

import wasm.editor.def.Index;
import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class FunctionSection extends TypeAble {

    private Vector<Index> signatureIndices;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        signatureIndices = parseTypeAble(inputStream, Vector.class, Index.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        signatureIndices.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(signatureIndices);
    }

    public Vector<Index> getSignatureIndices() {
        return signatureIndices;
    }
}
