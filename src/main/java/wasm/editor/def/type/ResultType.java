package wasm.editor.def.type;

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
public class ResultType extends TypeAble {

    private Vector<ValueType> valueTypes;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        valueTypes = parseTypeAble(inputStream, Vector.class, ValueType.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        valueTypes.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(valueTypes);
    }

    public Vector<ValueType> getValueTypes() {
        return valueTypes;
    }

    public void setValueTypes(Vector<ValueType> valueTypes) {
        this.valueTypes = valueTypes;
    }
}
