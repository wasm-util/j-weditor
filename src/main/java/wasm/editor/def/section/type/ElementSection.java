package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.instruction.Expression;
import wasm.editor.def.type.ReferenceType;
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
public class ElementSection extends TypeAble {

    private Vector<Element> elements;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        elements = parseTypeAble(inputStream, Vector.class, Element.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        elements.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(elements);
    }

    public static class Element extends TypeAble {

        private int opcode;
        private Expression expression;
        private Unsigned128LEB32Value tableIndex;
        private ReferenceType refType;
        private Vector<Unsigned128LEB32Value> funcIndexVector;
        private Vector<Expression> expressionVector;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            opcode = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
            switch(opcode) {
                case 0:
                    expression = parseTypeAble(inputStream, Expression.class);
                    funcIndexVector = parseTypeAble(inputStream, Vector.class, Unsigned128LEB32Value.class);
                    break;
                case 1:
                case 3:
                    funcIndexVector = parseTypeAble(inputStream, Vector.class, Unsigned128LEB32Value.class);
                    break;
                case 2:
                    tableIndex = parseTypeAble(inputStream, Unsigned128LEB32Value.class);
                    expression = parseTypeAble(inputStream, Expression.class);
                    funcIndexVector = parseTypeAble(inputStream, Vector.class, Unsigned128LEB32Value.class);
                    break;
                case 4:
                    expression = parseTypeAble(inputStream, Expression.class);
                    expressionVector = parseTypeAble(inputStream, Vector.class, Expression.class);
                    break;
                case 5:
                case 7:
                    refType = parseTypeAble(inputStream, ReferenceType.class);
                    expressionVector = parseTypeAble(inputStream, Vector.class, Expression.class);
                    break;
                case 6:
                    tableIndex = parseTypeAble(inputStream, Unsigned128LEB32Value.class);
                    expression = parseTypeAble(inputStream, Expression.class);
                    refType = parseTypeAble(inputStream, ReferenceType.class);
                    expressionVector = parseTypeAble(inputStream, Vector.class, Expression.class);
                    break;
                default:
                    throw new IOException(String.format("Unknown opcode: %d", opcode));
            }
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(opcode));
            switch(opcode) {
                case 0:
                    expression.write(outputStream);
                    funcIndexVector.write(outputStream);
                    break;
                case 1:
                case 3:
                    funcIndexVector.write(outputStream);
                    break;
                case 2:
                    tableIndex.write(outputStream);
                    expression.write(outputStream);
                    funcIndexVector.write(outputStream);
                    break;
                case 4:
                    expression.write(outputStream);
                    expressionVector.write(outputStream);
                    break;
                case 5:
                case 7:
                    refType.write(outputStream);
                    expressionVector.write(outputStream);
                    break;
                case 6:
                    tableIndex.write(outputStream);
                    expression.write(outputStream);
                    refType.write(outputStream);
                    expressionVector.write(outputStream);
                    break;
                default:
                    throw new IOException(String.format("Unknown opcode: %d", opcode));
            }
        }

        @Override
        protected Collection<Object> parsedFields() {
            final List<Object> list = new ArrayList<>();
            list.add(opcode);
            if (opcode == 0 || opcode == 2 || opcode == 4 || opcode == 6) {
                list.add(expression);
            }
            if (opcode == 2 || opcode == 6) {
                list.add(tableIndex);
            }
            if (opcode == 5 || opcode == 6 || opcode == 7) {
                list.add(refType);
            }
            if (opcode == 0 || opcode == 1 || opcode == 2 || opcode == 3) {
                list.add(funcIndexVector);
            }
            if (opcode == 4 || opcode == 5 || opcode == 6 || opcode == 7) {
                list.add(expressionVector);
            }
            return list;
        }

        public int getOpcode() {
            return opcode;
        }

        public void setOpcode(int opcode) {
            this.opcode = opcode;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

        public Unsigned128LEB32Value getTableIndex() {
            return tableIndex;
        }

        public void setTableIndex(Unsigned128LEB32Value tableIndex) {
            this.tableIndex = tableIndex;
        }

        public ReferenceType getRefType() {
            return refType;
        }

        public void setRefType(ReferenceType refType) {
            this.refType = refType;
        }

        public Vector<Unsigned128LEB32Value> getFuncIndexVector() {
            return funcIndexVector;
        }

        public void setFuncIndexVector(Vector<Unsigned128LEB32Value> funcIndexVector) {
            this.funcIndexVector = funcIndexVector;
        }

        public Vector<Expression> getExpressionVector() {
            return expressionVector;
        }

        public void setExpressionVector(Vector<Expression> expressionVector) {
            this.expressionVector = expressionVector;
        }
    }
}
