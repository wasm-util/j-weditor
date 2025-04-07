package wasm.editor.def;

import wasm.editor.Parser;
import wasm.editor.def.value.ValueTypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public abstract class TypeAble {

    public abstract void parse(ByteArrayInputStream inputStream) throws IOException;

    public abstract void write(ByteArrayOutputStream outputStream) throws IOException;

    protected abstract Collection<Object> parsedFields();

    @SafeVarargs
    protected static <T extends TypeAble> T parseTypeAble(ByteArrayInputStream inputStream, Class<T> typeClass, Class<? extends TypeAble>... typeClasses) {
        try {
            T typeAble;
            if (typeClasses.length > 0) {
                typeAble = typeClass.getDeclaredConstructor(Class.class).newInstance((Object[]) typeClasses);
            } else {
                typeAble = typeClass.getConstructor().newInstance();
            }
            typeAble.parse(inputStream);
            return typeAble;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static <T> void writeValueType(ByteArrayOutputStream outputStream, Class<? extends ValueTypeAble<T>> valueClass, T value) {
        try {
            final ValueTypeAble<T> typeAble = valueClass.getConstructor().newInstance();
            typeAble.set(value);
            typeAble.write(outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected static <T extends TypeAble> T unsafeCast(final TypeAble typeAble, final Class<T> typeClass) {
        return (T) typeAble;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        final Collection<Object> parsedFields = parsedFields();
        if (parsedFields != null && parsedFields.size() > 0) {
            if (getClass() == WasmModule.class) {
                stringBuilder.append(":\n");
                parsedFields.forEach(element -> {
                    if (element instanceof Iterable<?>) {
                        ((Iterable<?>) element).forEach(e -> stringBuilder.append("-> ").append(valueString(e)).append("\n"));
                    } else {
                        stringBuilder.append("-> ").append(valueString(element)).append("\n");
                    }
                });
            } else {
                stringBuilder.append("(");
                stringBuilder.append(parsedFields.stream().map(this::valueString).collect(Collectors.joining(", ")));
                stringBuilder.append(")");
            }
        }
        return stringBuilder.toString();
    }

    private String valueString(Object object) {
        return object instanceof Integer ? Parser.toHex((Integer) object) : object.toString();
    }
}
