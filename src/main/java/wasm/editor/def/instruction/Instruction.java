package wasm.editor.def.instruction;

import wasm.editor.def.TypeAble;
import wasm.editor.def.Vector;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class Instruction extends TypeAble {

    private InstructionSignature signature;
    private final List<TypeAble> arguments;
    private boolean track;

    public Instruction(InstructionSignature signature) {
        this(signature, new ArrayList<>());
    }

    public Instruction(InstructionSignature signature, List<TypeAble> arguments) {
        this.signature = signature;
        this.arguments = arguments;
    }

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        if (signature.getTypeClasses() != null) {
            final Iterator<Class<? extends TypeAble>> iterator = signature.getTypeClasses().iterator();
            while(iterator.hasNext()) {
                final Class<? extends TypeAble> argumentClass = iterator.next();
                if (Vector.class.equals(argumentClass)) {
                    if (!iterator.hasNext()) {
                        throw new IOException("Vector argument without element argument");
                    }
                    arguments.add(parseTypeAble(inputStream, argumentClass, iterator.next()));
                } else {
                    arguments.add(parseTypeAble(inputStream, argumentClass));
                }
            }
        }
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        for (TypeAble arg : arguments) {
            arg.write(outputStream);
        }
    }

    @Override
    protected Collection<Object> parsedFields() {
        return List.of(signature.getOpcode(), signature.toString(), arguments);
    }

    public static boolean equalType(Instruction first, InstructionSignature... others) {
        return equalType(first.getSignature(), others);
    }

    public static boolean equalType(InstructionSignature first, InstructionSignature... others) {
        for (InstructionSignature otherSignature : others) {
            if (otherSignature.getOpcode() == first.getOpcode()) {
                return true;
            }
        }
        return false;
    }

    public List<TypeAble> getArguments() {
        return arguments;
    }

    public void setSignature(InstructionSignature signature) {
        this.signature = signature;
    }

    public InstructionSignature getSignature() {
        return signature;
    }

}
