package wasm.editor.def;

import wasm.Constants;
import wasm.editor.Parser;
import wasm.editor.def.section.SectionEnum;
import wasm.editor.def.value.type.UnInterpretedInteger32Value;
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
public class WasmModule extends TypeAble {

    private final List<TypeAble> sections = new ArrayList<>();

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        if (parseTypeAble(inputStream, UnInterpretedInteger32Value.class).get() != Constants.WASM_BINARY_MAGIC_NUMBER) {
            throw new IOException("Invalid WASM_BINARY_MAGIC from wasm input.");
        }
        if (parseTypeAble(inputStream, UnInterpretedInteger32Value.class).get() != Constants.WASM_BINARY_VERSION_NUMBER) {
            throw new IOException("Invalid WASM_BINARY_VERSION from wasm input.");
        }
        int sectionCode;
        while((sectionCode = inputStream.read()) != -1) {
            final BigInteger sectionSize = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get();
            if (sectionSize.intValue() < 0) {
                throw new IOException("Incorrect section size of 0");
            }
            final byte[] sectionBytes = new byte[sectionSize.intValue()];
            if (inputStream.read(sectionBytes) < sectionSize.intValue()) {
                throw new IOException(String.format("Failed to read section (code: %s) bytes with size %d", Parser.toHex(sectionCode), sectionSize));
            }
            final Class<? extends TypeAble> sectionClass = SectionEnum.getParserClass(sectionCode);
            if (sectionClass == null) {
                throw new IOException(String.format("Cannot parse section, unsupported section id: %s", Parser.toHex(sectionCode)));
            }
            final TypeAble section = parseTypeAble(new ByteArrayInputStream(sectionBytes), sectionClass);
            sections.add(section);
        }

    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, UnInterpretedInteger32Value.class, Constants.WASM_BINARY_MAGIC_NUMBER);
        writeValueType(outputStream, UnInterpretedInteger32Value.class, Constants.WASM_BINARY_VERSION_NUMBER);
        for (TypeAble section : sections) {
            final int flag = SectionEnum.getFlag(section.getClass());
            if (flag == -1) {
                throw new IOException(String.format("Cannot write section, unsupported section class: %s", section.getClass().getSimpleName()));
            }
            outputStream.write(flag);
            final ByteArrayOutputStream sectionOutputStream = new ByteArrayOutputStream();
            section.write(sectionOutputStream);
            writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(sectionOutputStream.size()));
            sectionOutputStream.writeTo(outputStream);
        }
    }

    public <T extends TypeAble> T getSection(Class<T> sectionClass) {
        for (TypeAble section : sections) {
            if (section.getClass().equals(sectionClass)) {
                return unsafeCast(section, sectionClass);
            }
        }
        return null;
    }

    @Override
    public Collection<Object> parsedFields() {
        return List.of(sections);
    }

}