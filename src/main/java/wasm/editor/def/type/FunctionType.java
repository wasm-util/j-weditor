package wasm.editor.def.type;

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
public class FunctionType extends TypeAble {

    public static final int FUNC_TYPE_FLAG = 0x60;

    private ResultType parameterTypes;
    private ResultType resultTypes;
    private int flag;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        flag = parseTypeAble(inputStream, ByteValue.class).get();
        parameterTypes = parseTypeAble(inputStream, ResultType.class);
        resultTypes = parseTypeAble(inputStream, ResultType.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, ByteValue.class, flag);
        parameterTypes.write(outputStream);
        resultTypes.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(flag, parameterTypes, resultTypes);
    }

    public ResultType getParameterTypes() {
        return parameterTypes;
    }

    public ResultType getResultTypes() {
        return resultTypes;
    }

    public int getFlag() {
        return flag;
    }

    public void setParameterTypes(ResultType parameters) {
        this.parameterTypes = parameters;
    }

    public void setResultTypes(ResultType resultTypes) {
        this.resultTypes = resultTypes;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
