package wasm.editor.def.instruction;

import wasm.editor.def.TypeAble;

import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public interface InstructionSignature {

    int getOpcode();

    List<Class<? extends TypeAble>> getTypeClasses();
}
