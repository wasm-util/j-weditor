package wasm.editor;

import wasm.editor.def.WasmModule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Alan Massom <https://github.com/SavionsSw>
 *
 * Following https://webassembly.github.io/spec/core/binary/modules.html#binary-module as of 11/2024
 */
public class Parser {

    public static byte[] readFromInput(String inputPath) {
        try {
            return Files.readAllBytes(Paths.get(inputPath));
        } catch (IOException e) {
            System.out.printf("Error reading from input: %s\n", inputPath);
            throw new RuntimeException(e);
        }
    }

    public static WasmModule parseModule(byte[] inputBytes) {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes);
        final WasmModule wasmModule = new WasmModule();
        try {
            wasmModule.parse(inputStream);
        } catch (IOException e) {
            System.out.printf("Error parsing module: %s\n", e.getMessage());
            throw new RuntimeException(e);
        }
        return wasmModule;
    }

    public static void writeToInput(String outputPath, byte[] outputBytes) {
        try {
            final Path directoryPath = Paths.get(outputPath).getParent();
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
            fileOutputStream.write(outputBytes);
        } catch (IOException e) {
            System.out.printf("Error writing module to output: %s\n", outputPath);
            throw new RuntimeException(e);
        }
    }

    public static byte[] writeModule(WasmModule wasmModule) {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            wasmModule.write(outputStream);
        } catch (IOException e) {
            System.out.printf("Error writing module: %s\n", e.getMessage());
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }

    public static String toHex(int i) {
        return String.format("0x%s", Integer.toHexString(i));
    }
}