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
public class GlobalType extends TypeAble {

    private ValueType valType;
    private Mut mut;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        valType = parseTypeAble(inputStream, ValueType.class);
        mut = parseTypeAble(inputStream, Mut.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        valType.write(outputStream);
        mut.write(outputStream);
    }

    public Mut getMut() {
        return mut;
    }

    public void setMut(Mut mut) {
        this.mut = mut;
    }

    public ValueType getValType() {
        return valType;
    }

    public void setValType(ValueType valType) {
        this.valType = valType;
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(valType, mut);
    }

    public static class Mut extends TypeAble {

        public static final int NON_MUTATBLE = 0x0;
        public static final int MUTATABLE = 0x1;

        private int flag;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            flag = parseTypeAble(inputStream, ByteValue.class).get();
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            writeValueType(outputStream, ByteValue.class, flag);
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(flag);
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
