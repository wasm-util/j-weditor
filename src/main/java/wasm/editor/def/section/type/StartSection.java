package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.value.type.Unsigned128LEB32Value;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class StartSection extends TypeAble {

    private Start start;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        start = parseTypeAble(inputStream, Start.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        start.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(start);
    }

    static class Start extends TypeAble {

        private int functionIndex;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            functionIndex = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(functionIndex));
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(functionIndex);
        }

        public int getFunctionIndex() {
            return functionIndex;
        }

        public void setFunctionIndex(int functionIndex) {
            this.functionIndex = functionIndex;
        }
    }

}
