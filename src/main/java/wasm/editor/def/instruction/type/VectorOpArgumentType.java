package wasm.editor.def.instruction.type;

import wasm.editor.def.TypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class VectorOpArgumentType extends TypeAble {

    private int opcode;
    private TypeAble[] arguments;

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        //TODO support SIMD opcodes
        throw new IOException("Vector instruction argument type not supported yet");
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        //TODO support SIMD opcodes
        throw new IOException("Vector instruction argument type not supported yet");
    }

    @Override
    protected Collection<Object> parsedFields() {
        final List<Object> list = new ArrayList<>();
        list.add(opcode);
        if (arguments != null) {
            list.add(arguments);
        }
        return list;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public TypeAble[] getArguments() {
        return arguments;
    }

    public void setArguments(TypeAble[] arguments) {
        this.arguments = arguments;
    }
}
