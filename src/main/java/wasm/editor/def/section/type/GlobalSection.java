package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.instruction.Expression;
import wasm.editor.def.type.GlobalType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class GlobalSection extends TypeAble {

    private Vector<Global> globals;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        globals = parseTypeAble(inputStream, Vector.class, Global.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        globals.write(outputStream);
    }

    public Vector<Global> getGlobals() {
        return globals;
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(globals);
    }

    public static class Global extends TypeAble {

        private GlobalType globalType;
        private Expression expression;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            globalType = parseTypeAble(inputStream, GlobalType.class);
            expression = parseTypeAble(inputStream, Expression.class);
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            globalType.write(outputStream);
            expression.write(outputStream);
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(globalType, expression);
        }

        public Expression getExpression() {
            return expression;
        }

        public GlobalType getGlobalType() {
            return globalType;
        }

        public void setGlobalType(GlobalType globalType) {
            this.globalType = globalType;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }
    }
}
