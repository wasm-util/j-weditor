package wasm.editor.def.value.type;

import wasm.editor.def.value.ValueTypeAble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 */
public class Signed128LEB32Value extends ValueTypeAble<BigInteger> {

    @Override
    public void parse(ByteArrayInputStream inputStream) throws IOException {
        value = BigInteger.ZERO;
        int shift = 0;
        int nextByte;
        do {
            nextByte = inputStream.read();
            value = value.or(BigInteger.valueOf(nextByte & 0x7F).shiftLeft(shift));
            shift += 7;
        } while((nextByte & 0x80) != 0);
        if (shift < 32 && (nextByte & 0x40) != 0) {
            value = value.or(BigInteger.valueOf(-1).shiftLeft(shift));
        }
    }

    @Override
    public void write(ByteArrayOutputStream outputStream) throws IOException {
        if (value.equals(BigInteger.ZERO)) {
            outputStream.write(0);
            return;
        }
        BigInteger value = this.value;
        boolean more = true;

        while (more) {
            int nextByte = value.and(BigInteger.valueOf(0x7F)).byteValue();
            value = value.shiftRight(7);
            if ((value.compareTo(BigInteger.ZERO) == 0 && (nextByte & 0x40) == 0) ||
                    (value.compareTo(BigInteger.ZERO) < 0 && (nextByte & 0x40) != 0)) {
                more = false;
            } else {
                nextByte |= 0x80;
            }
            outputStream.write(nextByte);
        }
    }
}
