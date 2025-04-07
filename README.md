## j-weditor
Simple wasm-editor that is able to decode and encode WebAssembly (wasm) binaries. Built using Java 17, but may be used with lower versions.

This tool follows the WebAssembly core 2.0 specification and, with the exception of vector instructions, has fully implemented the specification. 

### TODO
- Vector instructions
- Validation (optional)

### Example
```
// Read a module
final WasmModule module = Parser.parseModule(inputBytes);

// Add new memory type
module.getSection(MemorySection.class).getMemoryTypes().getElements().add(...)

// Write a module
final byte[] outputBytes = Parser.writeModule(module);
```