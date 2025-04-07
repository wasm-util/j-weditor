package wasm.editor.def;

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
public class Vector<T extends TypeAble> extends TypeAble {

    private final List<T> elements = new ArrayList<>();
    private final Class<T> elementClass;

    public Vector(Class<T> elementClass) {
        this.elementClass = elementClass;
    }

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        final int size = parseTypeAble(inputStream, Unsigned128LEB32Value.class).get().intValue();
        for (int i = 0; i < size; i++) {
            elements.add(parseTypeAble(inputStream, elementClass));
        }
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        writeValueType(outputStream, Unsigned128LEB32Value.class, BigInteger.valueOf(elements.size()));
        for (TypeAble element : elements) {
            element.write(outputStream);
        }
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(String.format("Class[%s]", elementClass.getSimpleName()), elements);
    }

    public List<T> getElements() {
        return elements;
    }

    public T getElement(int index) {
        return elements.get(index);
    }

    public Vector<T> getDuplicate() {
        final Vector<T> duplicate = new Vector<T>(elementClass);
        duplicate.elements.addAll(elements);
        return duplicate;
    }
}
