package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.instruction.Expression;
import wasm.editor.def.value.type.ByteValue;
import wasm.editor.def.value.type.Unsigned128LEB32Value;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class DataSection extends TypeAble {

    private Vector<Data> data;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        data = parseTypeAble(inputStream, Vector.class, Data.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        data.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(data);
    }

    public static class Data extends TypeAble {

        private int opcode;
        private int memoryIndex;
        private Expression expression;
        private Vector<ByteValue> buffer;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            opcode = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
            if (opcode == 0x2) {
                memoryIndex = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
            }
            if (opcode == 0x0 || opcode == 0x2) {
                expression = parseTypeAble(inputStream, Expression.class);
            }
            buffer = parseTypeAble(inputStream, Vector.class, ByteValue.class);
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(opcode));
            if (opcode == 0x2) {
                writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(memoryIndex));
            }
            if (opcode == 0x0 || opcode == 0x2) {
                expression.write(outputStream);
            }
            buffer.write(outputStream);
        }

        @Override
        protected Collection<Object> parsedFields() {
            final List<Object> list = new ArrayList<>();
            list.add(opcode);
            if (opcode == 0x2) {
                list.add(memoryIndex);
            }
            if (opcode == 0x0 || opcode == 0x2) {
                list.add(expression);
            }
            list.add(buffer);
            return list;
        }

        public int getOpcode() {
            return opcode;
        }

        public void setOpcode(int opcode) {
            this.opcode = opcode;
        }

        public int getMemoryIndex() {
            return memoryIndex;
        }

        public void setMemoryIndex(int memoryIndex) {
            this.memoryIndex = memoryIndex;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

        public Vector<ByteValue> getBuffer() {
            return buffer;
        }

        public void setBuffer(Vector<ByteValue> buffer) {
            this.buffer = buffer;
        }
    }
}
