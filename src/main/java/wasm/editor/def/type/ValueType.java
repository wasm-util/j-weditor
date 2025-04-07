package wasm.editor.def.type;

import wasm.editor.Parser;
import wasm.editor.def.TypeAble;
import wasm.editor.def.value.type.ByteValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class ValueType extends TypeAble {

    private int flag;
    private TypeAble valueType;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        setValuesByFlag(parseTypeAble(inputStream, ByteValue.class).get());
        if (valueType == null) {
            throw new IOException(String.format("Unknown value type %s", Parser.toHex(flag)));
        }
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, ByteValue.class, flag);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(valueType, flag);
    }

    public void setValuesByFlag(int flag) {
        this.flag = flag;
        switch(flag) {
            case ReferenceType.FUNC_REF_FLAG:
            case ReferenceType.EXTERN_REF_FLAG:
                final ReferenceType referenceType = new ReferenceType();
                referenceType.setFlag(flag);
                this.valueType = referenceType;
                break;
            case VectorType.VEC_FLAG:
                final VectorType vectorType = new VectorType();
                vectorType.setFlag(flag);
                this.valueType = vectorType;
                break;
            case NumberType.UNINTERPRETED_INT_32:
            case NumberType.UNINTERPRETED_INT_64:
            case NumberType.UNINTERPRETED_FLOAT_32:
            case NumberType.UNINTERPRETED_FLOAT_64:
                final NumberType numberType = new NumberType();
                numberType.setFlag(flag);
                this.valueType = numberType;
                break;
            default:
                break;
        }
    }

    public TypeAble getValueType() {
        return valueType;
    }

    public int getFlag() {
        return flag;
    }
}
