package wasm.editor.def.value;

import wasm.editor.def.TypeAble;

import java.util.Collection;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public abstract class ValueTypeAble<T> extends TypeAble {

    protected T value;

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(value);
    }

    public T get() {
        return value;
    }

    public ValueTypeAble<T> set(T value) {
        this.value = value;
        return this;
    }
}