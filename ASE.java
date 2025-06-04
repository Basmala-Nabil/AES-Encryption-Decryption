import java.util.Arrays;
import java.util.Scanner;

 class AES {


     // AES S-Box
     static final byte[] S_BOX = {
             (byte) 0x63, (byte) 0x7c, (byte) 0x77, (byte) 0x7b, (byte) 0xf2, (byte) 0x6b, (byte) 0x6f, (byte) 0xc5,
             (byte) 0x30, (byte) 0x01, (byte) 0x67, (byte) 0x2b, (byte) 0xfe, (byte) 0xd7, (byte) 0xab, (byte) 0x76,
             (byte) 0xca, (byte) 0x82, (byte) 0xc9, (byte) 0x7d, (byte) 0xfa, (byte) 0x59, (byte) 0x47, (byte) 0xf0,
             (byte) 0xad, (byte) 0xd4, (byte) 0xa2, (byte) 0xaf, (byte) 0x9c, (byte) 0xa4, (byte) 0x72, (byte) 0xc0,
             (byte) 0xb7, (byte) 0xfd, (byte) 0x93, (byte) 0x26, (byte) 0x36, (byte) 0x3f, (byte) 0xf7, (byte) 0xcc,
             (byte) 0x34, (byte) 0xa5, (byte) 0xe5, (byte) 0xf1, (byte) 0x71, (byte) 0xd8, (byte) 0x31, (byte) 0x15,
             (byte) 0x04, (byte) 0xc7, (byte) 0x23, (byte) 0xc3, (byte) 0x18, (byte) 0x96, (byte) 0x05, (byte) 0x9a,
             (byte) 0x07, (byte) 0x12, (byte) 0x80, (byte) 0xe2, (byte) 0xeb, (byte) 0x27, (byte) 0xb2, (byte) 0x75,
             (byte) 0x09, (byte) 0x83, (byte) 0x2c, (byte) 0x1a, (byte) 0x1b, (byte) 0x6e, (byte) 0x5a, (byte) 0xa0,
             (byte) 0x52, (byte) 0x3b, (byte) 0xd6, (byte) 0xb3, (byte) 0x29, (byte) 0xe3, (byte) 0x2f, (byte) 0x84,
             (byte) 0x53, (byte) 0xd1, (byte) 0x00, (byte) 0xed, (byte) 0x20, (byte) 0xfc, (byte) 0xb1, (byte) 0x5b,
             (byte) 0x6a, (byte) 0xcb, (byte) 0xbe, (byte) 0x39, (byte) 0x4a, (byte) 0x4c, (byte) 0x58, (byte) 0xcf,
             (byte) 0xd0, (byte) 0xef, (byte) 0xaa, (byte) 0xfb, (byte) 0x43, (byte) 0x4d, (byte) 0x33, (byte) 0x85,
             (byte) 0x45, (byte) 0xf9, (byte) 0x02, (byte) 0x7f, (byte) 0x50, (byte) 0x3c, (byte) 0x9f, (byte) 0xa8,
             (byte) 0x51, (byte) 0xa3, (byte) 0x40, (byte) 0x8f, (byte) 0x92, (byte) 0x9d, (byte) 0x38, (byte) 0xf5,
             (byte) 0xbc, (byte) 0xb6, (byte) 0xda, (byte) 0x21, (byte) 0x10, (byte) 0xff, (byte) 0xf3, (byte) 0xd2,
             (byte) 0xcd, (byte) 0x0c, (byte) 0x13, (byte) 0xec, (byte) 0x5f, (byte) 0x97, (byte) 0x44, (byte) 0x17,
             (byte) 0xc4, (byte) 0xa7, (byte) 0x7e, (byte) 0x3d, (byte) 0x64, (byte) 0x5d, (byte) 0x19, (byte) 0x73,
             (byte) 0x60, (byte) 0x81, (byte) 0x4f, (byte) 0xdc, (byte) 0x22, (byte) 0x2a, (byte) 0x90, (byte) 0x88,
             (byte) 0x46, (byte) 0xee, (byte) 0xb8, (byte) 0x14, (byte) 0xde, (byte) 0x5e, (byte) 0x0b, (byte) 0xdb,
             (byte) 0xe0, (byte) 0x32, (byte) 0x3a, (byte) 0x0a, (byte) 0x49, (byte) 0x06, (byte) 0x24, (byte) 0x5c,
             (byte) 0xc2, (byte) 0xd3, (byte) 0xac, (byte) 0x62, (byte) 0x91, (byte) 0x95, (byte) 0xe4, (byte) 0x79,
             (byte) 0xe7, (byte) 0xc8, (byte) 0x37, (byte) 0x6d, (byte) 0x8d, (byte) 0xd5, (byte) 0x4e, (byte) 0xa9,
             (byte) 0x6c, (byte) 0x56, (byte) 0xf4, (byte) 0xea, (byte) 0x65, (byte) 0x7a, (byte) 0xae, (byte) 0x08,
             (byte) 0xba, (byte) 0x78, (byte) 0x25, (byte) 0x2e, (byte) 0x1c, (byte) 0xa6, (byte) 0xb4, (byte) 0xc6,
             (byte) 0xe8, (byte) 0xdd, (byte) 0x74, (byte) 0x1f, (byte) 0x4b, (byte) 0xbd, (byte) 0x8b, (byte) 0x8a,
             (byte) 0x70, (byte) 0x3e, (byte) 0xb5, (byte) 0x66, (byte) 0x48, (byte) 0x03, (byte) 0xf6, (byte) 0x0e,
             (byte) 0x61, (byte) 0x35, (byte) 0x57, (byte) 0xb9, (byte) 0x86, (byte) 0xc1, (byte) 0x1d, (byte) 0x9e,
             (byte) 0xe1, (byte) 0xf8, (byte) 0x98, (byte) 0x11, (byte) 0x69, (byte) 0xd9, (byte) 0x8e, (byte) 0x94,
             (byte) 0x9b, (byte) 0x1e, (byte) 0x87, (byte) 0xe9, (byte) 0xce, (byte) 0x55, (byte) 0x28, (byte) 0xdf,
             (byte) 0x8c, (byte) 0xa1, (byte) 0x89, (byte) 0x0d, (byte) 0xbf, (byte) 0xe6, (byte) 0x42, (byte) 0x68,
             (byte) 0x41, (byte) 0x99, (byte) 0x2d, (byte) 0x0f, (byte) 0xb0, (byte) 0x54, (byte) 0xbb, (byte) 0x16
     };

     // AES Inverse S-Box
     static final byte[] INV_S_BOX = {
             (byte) 0x52, (byte) 0x09, (byte) 0x6a, (byte) 0xd5, (byte) 0x30, (byte) 0x36, (byte) 0xa5, (byte) 0x38,
             (byte) 0xbf, (byte) 0x40, (byte) 0xa3, (byte) 0x9e, (byte) 0x81, (byte) 0xf3, (byte) 0xd7, (byte) 0xfb,
             (byte) 0x7c, (byte) 0xe3, (byte) 0x39, (byte) 0x82, (byte) 0x9b, (byte) 0x2f, (byte) 0xff, (byte) 0x87,
             (byte) 0x34, (byte) 0x8e, (byte) 0x43, (byte) 0x44, (byte) 0xc4, (byte) 0xde, (byte) 0xe9, (byte) 0xcb,
             (byte) 0x54, (byte) 0x7b, (byte) 0x94, (byte) 0x32, (byte) 0xa6, (byte) 0xc2, (byte) 0x23, (byte) 0x3d,
             (byte) 0xee, (byte) 0x4c, (byte) 0x95, (byte) 0x0b, (byte) 0x42, (byte) 0xfa, (byte) 0xc3, (byte) 0x4e,
             (byte) 0x08, (byte) 0x2e, (byte) 0xa1, (byte) 0x66, (byte) 0x28, (byte) 0xd9, (byte) 0x24, (byte) 0xb2,
             (byte) 0x76, (byte) 0x5b, (byte) 0xa2, (byte) 0x49, (byte) 0x6d, (byte) 0x8b, (byte) 0xd1, (byte) 0x25,
             (byte) 0x72, (byte) 0xf8, (byte) 0xf6, (byte) 0x64, (byte) 0x86, (byte) 0x68, (byte) 0x98, (byte) 0x16,
             (byte) 0xd4, (byte) 0xa4, (byte) 0x5c, (byte) 0xcc, (byte) 0x5d, (byte) 0x65, (byte) 0xb6, (byte) 0x92,
             (byte) 0x6c, (byte) 0x70, (byte) 0x48, (byte) 0x50, (byte) 0xfd, (byte) 0xed, (byte) 0xb9, (byte) 0xda,
             (byte) 0x5e, (byte) 0x15, (byte) 0x46, (byte) 0x57, (byte) 0xa7, (byte) 0x8d, (byte) 0x9d, (byte) 0x84,
             (byte) 0x90, (byte) 0xd8, (byte) 0xab, (byte) 0x00, (byte) 0x8c, (byte) 0xbc, (byte) 0xd3, (byte) 0x0a,
             (byte) 0xf7, (byte) 0xe4, (byte) 0x58, (byte) 0x05, (byte) 0xb8, (byte) 0xb3, (byte) 0x45, (byte) 0x06,
             (byte) 0xd0, (byte) 0x2c, (byte) 0x1e, (byte) 0x8f, (byte) 0xca, (byte) 0x3f, (byte) 0x0f, (byte) 0x02,
             (byte) 0xc1, (byte) 0xaf, (byte) 0xbd, (byte) 0x03, (byte) 0x01, (byte) 0x13, (byte) 0x8a, (byte) 0x6b,
             (byte) 0x3a, (byte) 0x91, (byte) 0x11, (byte) 0x41, (byte) 0x4f, (byte) 0x67, (byte) 0xdc, (byte) 0xea,
             (byte) 0x97, (byte) 0xf2, (byte) 0xcf, (byte) 0xce, (byte) 0xf0, (byte) 0xb4, (byte) 0xe6, (byte) 0x73,
             (byte) 0x96, (byte) 0xac, (byte) 0x74, (byte) 0x22, (byte) 0xe7, (byte) 0xad, (byte) 0x35, (byte) 0x85,
             (byte) 0xe2, (byte) 0xf9, (byte) 0x37, (byte) 0xe8, (byte) 0x1c, (byte) 0x75, (byte) 0xdf, (byte) 0x6e,
             (byte) 0x47, (byte) 0xf1, (byte) 0x1a, (byte) 0x71, (byte) 0x1d, (byte) 0x29, (byte) 0xc5, (byte) 0x89,
             (byte) 0x6f, (byte) 0xb7, (byte) 0x62, (byte) 0x0e, (byte) 0xaa, (byte) 0x18, (byte) 0xbe, (byte) 0x1b,
             (byte) 0xfc, (byte) 0x56, (byte) 0x3e, (byte) 0x4b, (byte) 0xc6, (byte) 0xd2, (byte) 0x79, (byte) 0x20,
             (byte) 0x9a, (byte) 0xdb, (byte) 0xc0, (byte) 0xfe, (byte) 0x78, (byte) 0xcd, (byte) 0x5a, (byte) 0xf4,
             (byte) 0x1f, (byte) 0xdd, (byte) 0xa8, (byte) 0x33, (byte) 0x88, (byte) 0x07, (byte) 0xc7, (byte) 0x31,
             (byte) 0xb1, (byte) 0x12, (byte) 0x10, (byte) 0x59, (byte) 0x27, (byte) 0x80, (byte) 0xec, (byte) 0x5f,
             (byte) 0x60, (byte) 0x51, (byte) 0x7f, (byte) 0xa9, (byte) 0x19, (byte) 0xb5, (byte) 0x4a, (byte) 0x0d,
             (byte) 0x2d, (byte) 0xe5, (byte) 0x7a, (byte) 0x9f, (byte) 0x93, (byte) 0xc9, (byte) 0x9c, (byte) 0xef,
             (byte) 0xa0, (byte) 0xe0, (byte) 0x3b, (byte) 0x4d, (byte) 0xae, (byte) 0x2a, (byte) 0xf5, (byte) 0xb0,
             (byte) 0xc8, (byte) 0xeb, (byte) 0xbb, (byte) 0x3c, (byte) 0x83, (byte) 0x53, (byte) 0x99, (byte) 0x61,
             (byte) 0x17, (byte) 0x2b, (byte) 0x04, (byte) 0x7e, (byte) 0xba, (byte) 0x77, (byte) 0xd6, (byte) 0x26,
             (byte) 0xe1, (byte) 0x69, (byte) 0x14, (byte) 0x63, (byte) 0x55, (byte) 0x21, (byte) 0x0c, (byte) 0x7d
     };

     // Round constants
     static final byte[] RCON = {
             0x00, 0x01, 0x02, 0x04,
             0x08, 0x10, 0x20, 0x40,
             (byte) 0x80, 0x1B, 0x36
     };

     // -------- Helper Methods --------

     // Converts a 16-byte array input block into a 4x4 state matrix in column-major order
     static void arrayToState(byte[] input, byte[][] state) {
         for (int i = 0; i < 16; i++) {
             state[i % 4][i / 4] = input[i];
         }
     }

     // Converts a 4x4 state matrix back into a 16-byte array in column-major order
     static void stateToArray(byte[][] state, byte[] output) {
         for (int i = 0; i < 16; i++) {
             output[i] = state[i % 4][i / 4];
         }
     }

     // Galois Field multiplication
     static byte gmul(byte a, byte b) {
         byte p = 0;
         byte hi_bit_set;
         for (int i = 0; i < 8; i++) {
             if ((b & 1) != 0) {
                 p ^= a;
             }
             hi_bit_set = (byte) (a & 0x80);
             a <<= 1;
             if (hi_bit_set != 0) {
                 a ^= 0x1b;
             }
             b >>= 1;
         }
         return p;
     }

     // SubBytes transformation
     static void subBytes(byte[][] state) {
         for (int r = 0; r < 4; r++) {
             for (int c = 0; c < 4; c++) {
                 state[r][c] = S_BOX[state[r][c] & 0xFF];
             }
         }
     }

     // Inverse SubBytes transformation
     static void invSubBytes(byte[][] state) {
         for (int r = 0; r < 4; r++) {
             for (int c = 0; c < 4; c++) {
                 state[r][c] = INV_S_BOX[state[r][c] & 0xFF];
             }
         }
     }

     // ShiftRows transformation, direction specified by inv flag
     static void shiftRows(byte[][] state, boolean inv) {
         byte temp;
         if (!inv) {
             // Shift left
             // Row 1 - shift left by 1
             temp = state[1][0];
             for (int i = 0; i < 3; i++)
                 state[1][i] = state[1][i + 1];
             state[1][3] = temp;

             // Row 2 - shift left by 2
             temp = state[2][0];
             byte temp2 = state[2][1];
             state[2][0] = state[2][2];
             state[2][1] = state[2][3];
             state[2][2] = temp;
             state[2][3] = temp2;

             // Row 3 - shift left by 3
             temp = state[3][3];
             for (int i = 3; i > 0; i--)
                 state[3][i] = state[3][i - 1];
             state[3][0] = temp;
         } else {
             // Shift right
             // Row 1 - shift right by 1
             temp = state[1][3];
             for (int i = 3; i > 0; i--)
                 state[1][i] = state[1][i - 1];
             state[1][0] = temp;

             // Row 2 - shift right by 2
             temp = state[2][0];
             byte temp2 = state[2][1];
             state[2][0] = state[2][2];
             state[2][1] = state[2][3];
             state[2][2] = temp;
             state[2][3] = temp2;

             // Row 3 - shift right by 3
             temp = state[3][0];
             for (int i = 0; i < 3; i++)
                 state[3][i] = state[3][i + 1];
             state[3][3] = temp;
         }
     }

     // MixColumns transformation
     static void mixColumns(byte[][] state, boolean inv) {
         for (int c = 0; c < 4; c++) {
             byte[] col = new byte[4];
             for (int i = 0; i < 4; i++) col[i] = state[i][c];
             if (!inv) {
                 state[0][c] = (byte) (gmul((byte) 0x02, col[0]) ^ gmul((byte) 0x03, col[1]) ^ col[2] ^ col[3]);
                 state[1][c] = (byte) (col[0] ^ gmul((byte) 0x02, col[1]) ^ gmul((byte) 0x03, col[2]) ^ col[3]);
                 state[2][c] = (byte) (col[0] ^ col[1] ^ gmul((byte) 0x02, col[2]) ^ gmul((byte) 0x03, col[3]));
                 state[3][c] = (byte) (gmul((byte) 0x03, col[0]) ^ col[1] ^ col[2] ^ gmul((byte) 0x02, col[3]));
             } else {
                 state[0][c] = (byte) (gmul((byte) 0x0e, col[0]) ^ gmul((byte) 0x0b, col[1]) ^ gmul((byte) 0x0d, col[2]) ^ gmul((byte) 0x09, col[3]));
                 state[1][c] = (byte) (gmul((byte) 0x09, col[0]) ^ gmul((byte) 0x0e, col[1]) ^ gmul((byte) 0x0b, col[2]) ^ gmul((byte) 0x0d, col[3]));
                 state[2][c] = (byte) (gmul((byte) 0x0d, col[0]) ^ gmul((byte) 0x09, col[1]) ^ gmul((byte) 0x0e, col[2]) ^ gmul((byte) 0x0b, col[3]));
                 state[3][c] = (byte) (gmul((byte) 0x0b, col[0]) ^ gmul((byte) 0x0d, col[1]) ^ gmul((byte) 0x09, col[2]) ^ gmul((byte) 0x0e, col[3]));
             }
         }
     }

     // AddRoundKey
     static void addRoundKey(byte[][] state, byte[][] roundKey) {
         for (int row = 0; row < 4; row++) {
             for (int col = 0; col < 4; col++) {
                 state[row][col] ^= roundKey[row][col];
             }
         }
     }

     // KeyExpansion
     static byte[] KeyExpansion(byte[] key) {
         byte[] expandedKeys = new byte[176]; // 11 * 16 bytes
         System.arraycopy(key, 0, expandedKeys, 0, 16);

         byte[] temp = new byte[4];
         int bytesGenerated = 16;
         int rconIteration = 1;

         while (bytesGenerated < 176) {
             System.arraycopy(expandedKeys, bytesGenerated - 4, temp, 0, 4);

             if (bytesGenerated % 16 == 0) {
                 // RotWord
                 temp = new byte[]{temp[1], temp[2], temp[3], temp[0]};
                 // SubWord
                 for (int i = 0; i < 4; i++) {
                     temp[i] = S_BOX[temp[i] & 0xFF];
                 }
                 // Rcon
                 temp[0] ^= RCON[rconIteration];
                 rconIteration++;
             }

             for (int i = 0; i < 4; i++) {
                 expandedKeys[bytesGenerated] = (byte) (expandedKeys[bytesGenerated - 16] ^ temp[i]);
                 bytesGenerated++;
             }
         }
         return expandedKeys;
     }

     // CopyRoundKey
     static void copyRoundKey(byte[] expandedKeys, int round, byte[][] roundKey) {
         for (int i = 0; i < 16; i++) {
             roundKey[i % 4][i / 4] = expandedKeys[round * 16 + i];
         }
     }

     // Encryption
     static byte[] AES_Encrypt(byte[] input, byte[] key) {
         byte[][] state = new byte[4][4];
         byte[][] roundKey = new byte[4][4];
         byte[] expandedKeys = KeyExpansion(key);

         arrayToState(input, state);

         copyRoundKey(expandedKeys, 0, roundKey);
         addRoundKey(state, roundKey);

         for (int round = 1; round <= 9; round++) {
             subBytes(state);
             shiftRows(state, false);
             mixColumns(state, false);
             copyRoundKey(expandedKeys, round, roundKey);
             addRoundKey(state, roundKey);
         }

         subBytes(state);
         shiftRows(state, false);
         copyRoundKey(expandedKeys, 10, roundKey);
         addRoundKey(state, roundKey);

         byte[] output = new byte[16];
         stateToArray(state, output);
         return output;
     }

     // Decryption
     static byte[] AES_Decrypt(byte[] input, byte[] key) {
         byte[][] state = new byte[4][4];
         byte[][] roundKey = new byte[4][4];
         byte[] expandedKeys = KeyExpansion(key);

         arrayToState(input, state);
         copyRoundKey(expandedKeys, 10, roundKey);
         addRoundKey(state, roundKey);

         for (int round = 9; round >= 1; round--) {
             shiftRows(state, true);
             invSubBytes(state);
             copyRoundKey(expandedKeys, round, roundKey);
             addRoundKey(state, roundKey);
             mixColumns(state, true);
         }

         shiftRows(state, true);
         invSubBytes(state);
         copyRoundKey(expandedKeys, 0, roundKey);
         addRoundKey(state, roundKey);

         byte[] output = new byte[16];
         stateToArray(state, output);
         return output;
     }

     // Converts string to 16-byte block with padding
     static byte[] stringToBlock(String input) {
         byte[] block = new byte[16];
         Arrays.fill(block, (byte) 0);
         int len = Math.min(input.length(), 16);
         for (int i = 0; i < len; i++) {
             block[i] = (byte) input.charAt(i);
         }
         return block;
     }

     // Convert bytes to hex string
     static String bytesToHex(byte[] bytes) {
         StringBuilder sb = new StringBuilder();
         for (byte b : bytes) {
             sb.append(String.format("%02x ", b & 0xFF));
         }
         return sb.toString();
     }
 }

     class Main {
         public static void main(String[] args) {
             Scanner scanner = new Scanner(System.in);

             // Sample input
             String userInput = "basmala nabil BN";
             String userKey = "keykeykeykeykeyk";

             // Uncomment the following lines to take input from the user
             // System.out.print("Enter plaintext (max 16 characters): ");
             // userInput = scanner.nextLine();
             // System.out.print("Enter key (16 characters): ");
             // userKey = scanner.nextLine();

             System.out.println("Plaintext: " + userInput);
             System.out.println("Key: " + userKey);

             if (userKey.length() != 16) {
                 System.err.println("Error: Key must be exactly 16 characters.");
                 return;
             }

             byte[] plaintext = stringToBlock(userInput);
             byte[] key = stringToBlock(userKey);
             byte[] encrypted = AES.AES_Encrypt(plaintext, key);
             byte[] decrypted = AES.AES_Decrypt(encrypted, key);

             System.out.print("Plaintext (hex): ");
             printHex(plaintext);
             System.out.print("Encrypted (hex): ");
             printHex(encrypted);
             System.out.print("Decrypted (hex): ");
             printHex(decrypted);

             System.out.print("Decrypted as text: ");
             for (byte b : decrypted) {
                 if (b == 0) break; // Stop at first zero byte
                 System.out.print((char) b);
             }
             System.out.println();
         }

         // Converts string to 16-byte block with padding
         static byte[] stringToBlock(String input) {
             byte[] block = new byte[16];
             for (int i = 0; i < Math.min(input.length(), 16); i++) {
                 block[i] = (byte) input.charAt(i);
             }
             return block;
         }

         // Print byte array in hex format
         static void printHex(byte[] bytes) {
             for (byte b : bytes) {
                 System.out.printf("%02x ", b & 0xFF);
             }
             System.out.println();
         }
     }
