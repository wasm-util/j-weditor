package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.instruction.Expression;
import wasm.editor.def.type.ValueType;
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

public class CodeSection extends TypeAble {

    private Vector<Code> code;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        code = parseTypeAble(inputStream, Vector.class, Code.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        code.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(code);
    }

    public Vector<Code> getCode() {
        return code;
    }

    public static class Code extends TypeAble {

        private int size;
        private Func func;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            size = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
            func = parseTypeAble(inputStream, Func.class);
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            final ByteArrayOutputStream funcOutputStream = new ByteArrayOutputStream();
            func.write(funcOutputStream);
            writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(size = funcOutputStream.size()));
            funcOutputStream.writeTo(outputStream);
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(size, func);
        }

        public int getSize() {
            return size;
        }

        public Func getFunc() {
            return func;
        }

        public void setFunc(Func func) {
            this.func = func;
        }
    }

    public static class Func extends TypeAble {

        private Vector<Local> locals;
        private Expression expression;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            locals = parseTypeAble(inputStream, Vector.class, Local.class);
            expression = parseTypeAble(inputStream, Expression.class);
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            locals.write(outputStream);
            expression.write(outputStream);
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(locals, expression);
        }

        public Vector<Local> getLocals() {
            return locals;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setLocals(Vector<Local> locals) {
            this.locals = locals;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }
    }

    public static class Local extends TypeAble {

        private int count;
        private ValueType valType;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            count = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
            valType = parseTypeAble(inputStream, ValueType.class);
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(count));
            valType.write(outputStream);
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(count, valType);
        }

        public int getCount() {
            return count;
        }

        public ValueType getValType() {
            return valType;
        }

        public void setValType(ValueType valType) {
            this.valType = valType;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
