package MarvellousPackerUnpacker;

public class MarvellousSecurity {
    
    /**
     * Performs a simple XOR encryption/decryption on a byte array using a user-provided key.
     * The key is derived from the first byte of the provided key string.
     * @param data The byte array to be encrypted or decrypted.
     * @param length The number of bytes to process.
     * @param strKey The String key provided by the user.
     */
    public static void xorCipher(byte[] data, int length, String strKey) {
        if (strKey == null || strKey.isEmpty()) {
            // Use a default fixed key if the user-provided key is invalid or empty
            strKey = "DefaultKey"; 
        }
        
        // Use the first character's byte value as the cipher key
        byte KEY = strKey.getBytes()[0]; 
        
        for (int i = 0; i < length; i++) {
            data[i] = (byte) (data[i] ^ KEY);
        }
    }

    /**
     * Encrypts/Decrypts the 100-byte header.
     * @param headerBuffer The 100-byte buffer containing the header.
     * @param strKey The String key provided by the user.
     */
    public static void xorCipherHeader(byte[] headerBuffer, String strKey) {
        // Encrypt/Decrypt the entire 100-byte header buffer
        xorCipher(headerBuffer, headerBuffer.length, strKey);
    }
}