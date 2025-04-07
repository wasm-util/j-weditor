package wasm.editor.def.section;

import wasm.editor.def.TypeAble;
import wasm.editor.def.section.type.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public enum SectionEnum {

    CUSTOM(0x0, CustomSection.class),
    TYPE(0x1, TypeSection.class),
    IMPORT(0x2, ImportSection.class),
    FUNCTION(0x3, FunctionSection.class),
    TABLE(0x4, TableSection.class),
    MEMORY(0x5, MemorySection.class),
    GLOBAL(0x6, GlobalSection.class),
    EXPORT(0x7, ExportSection.class),
    START(0x8, StartSection.class),
    ELEMENT(0x9, ElementSection.class),
    CODE(0xA, CodeSection.class),
    DATA(0xB, DataSection.class),
    DATA_COUNT(0xC, DataCountSection.class);

    private final int flag;
    private final Class<? extends TypeAble> parserClass;
    private static final HashMap<Integer, Class<? extends TypeAble>> map = new HashMap<>();

    static {
        for (SectionEnum type : values()) {
            map.put(type.flag, type.parserClass);
        }
    }

    SectionEnum(int flag, Class<? extends TypeAble> parserClass) {
        this.flag = flag;
        this.parserClass = parserClass;
    }

    public static Class<? extends TypeAble> getParserClass(int flag) {
        return map.get(flag);
    }

    public static int getFlag(Class<? extends TypeAble> parserClass) {
        return map.entrySet().stream().filter(entry -> entry.getValue() == parserClass)
                .map(Map.Entry::getKey).findAny().orElse(-1);
    }
}
