package wasm.editor.def.type;

import wasm.editor.def.TypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class TableType extends TypeAble {

    private ReferenceType refType;
    private LimitType limit;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        refType = parseTypeAble(inputStream, ReferenceType.class);
        limit = parseTypeAble(inputStream, LimitType.class);
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        refType.write(outputStream);
        limit.write(outputStream);
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(refType, limit);
    }
}
