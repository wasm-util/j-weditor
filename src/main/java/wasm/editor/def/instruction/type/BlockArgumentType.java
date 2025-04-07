package wasm.editor.def.instruction.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.instruction.Expression;
import wasm.editor.def.value.type.ByteValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class BlockArgumentType extends TypeAble {

    private BlockType blockType;
    private Expression innerExpression;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        blockType = parseTypeAble(inputStream, BlockType.class);
        innerExpression = parseTypeAble(inputStream, Expression.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        blockType.write(outputStream);
        innerExpression.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(blockType);
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public Expression getInnerExpression() {
        return innerExpression;
    }

    public void setInnerExpression(Expression innerExpression) {
        this.innerExpression = innerExpression;
    }

    public static class BlockType extends TypeAble {

        public static final int EMPTY_TYPE_FLAG = 0x40;
        private TypeAble type;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            inputStream.mark(1);
            final int firstByte = inputStream.read();
            if (firstByte == EMPTY_TYPE_FLAG) {
                type = new ByteValue().set(firstByte);
            } else {
                throw new IOException("Unsupported block type flag: " + firstByte);
                /**
                 * TODO:
                 * inputStream.reset();
                 * read either valtype or s33
                 */
            }
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
           type.write(outputStream);
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(type);
        }
    }
}
