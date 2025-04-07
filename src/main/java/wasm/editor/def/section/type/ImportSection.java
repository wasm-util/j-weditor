package wasm.editor.def.section.type;

import wasm.editor.def.Index;
import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.type.GlobalType;
import wasm.editor.def.type.LimitType;
import wasm.editor.def.type.TableType;
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
public class ImportSection extends TypeAble {

    private Vector<Import> imports;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        imports = parseTypeAble(inputStream, Vector.class, Import.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        imports.write(outputStream);
    }

    public Vector<Import> getImports() {
        return imports;
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(imports);
    }

    public static class Import extends TypeAble {

        public static final int LIMIT_FLAG = 0x02;

        private String module;
        private String name;
        private int flag;
        private TypeAble desc;

        @Override
        public void parse(ByteArrayInputStream inputStream) throws IOException {
            module = parseTypeAble(inputStream, NameValue.class).get();
            name = parseTypeAble(inputStream, NameValue.class).get();
            flag = parseTypeAble(inputStream, ByteValue.class).get();
            switch(flag) {
                case 0x00:
                    desc = parseTypeAble(inputStream, Index.class);
                    break;
                case 0x01:
                    desc = parseTypeAble(inputStream, TableType.class);
                    break;
                case LIMIT_FLAG:
                    desc = parseTypeAble(inputStream, LimitType.class);
                    break;
                case 0x03:
                    desc = parseTypeAble(inputStream, GlobalType.class);
                    break;
                default:
                    throw new IOException(String.format("Unsupported import desc flag %d", flag));
            }
        }

        @Override
        public void write(ByteArrayOutputStream outputStream) throws IOException {
            writeValueType(outputStream, NameValue.class, module);
            writeValueType(outputStream, NameValue.class, name);
            writeValueType(outputStream, ByteValue.class, flag);
            desc.write(outputStream);
        }

        @Override
        protected Collection<Object> parsedFields() {
            return List.of(module, name, flag, desc);
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
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

        public TypeAble getDesc() {
            return desc;
        }

        public void setDesc(TypeAble desc) {
            this.desc = desc;
        }
    }

}