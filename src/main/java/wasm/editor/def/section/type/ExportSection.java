package wasm.editor.def.section.type;

import wasm.editor.def.Index;
import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.value.type.ByteValue;
import wasm.editor.def.value.type.NameValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class ExportSection extends TypeAble {

    private Vector<Export> exports;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        exports = parseTypeAble(inputStream, Vector.class, Export.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        exports.write(outputStream);
    }

    public Vector<Export> getExports() {
        return exports;
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(exports);
    }

    public static class Export extends TypeAble {

        public static final int FUNC_REF_INDEX = 0x00;
        public static final int TABLE_REF_INDEX = 0x01;
        public static final int MEMORY_REF_INDEX = 0x02;
        public static final int GLOBAL_REF_INDEX = 0x03;

        private String name;
        private int flag;
        private Index desc;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            name = parseTypeAble(inputStream, NameValue.class).get();
            flag = parseTypeAble(inputStream, ByteValue.class).get();
            desc = parseTypeAble(inputStream, Index.class);
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            writeValueType(outputStream, NameValue.class, name);
            writeValueType(outputStream, ByteValue.class, flag);
            desc.write(outputStream);
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(name, flag, desc);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public Index getDesc() {
            return desc;
        }

        public void setDesc(Index desc) {
            this.desc = desc;
        }
    }
}
