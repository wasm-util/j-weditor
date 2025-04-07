package wasm.editor.def.section.type;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;
import wasm.editor.def.type.TableType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class TableSection extends TypeAble {

    private Vector<TableType> tableTypes;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        tableTypes = parseTypeAble(inputStream, Vector.class, TableType.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        tableTypes.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(tableTypes);
    }

    public Vector<TableType> getTableTypes() {
        return tableTypes;
    }
}
