🔐 AES Encryption & Decryption in Java

📘 What is AES?

AES (Advanced Encryption Standard) is a widely-used symmetric encryption algorithm standardized by NIST in 2001. It is designed to secure sensitive data by converting plaintext into ciphertext using the same secret key for encryption and decryption.

AES is a block cipher operating on 128-bit blocks.

It supports three key sizes: 128, 192, and 256 bits.

The encryption process includes several rounds of transformations: SubBytes, ShiftRows, MixColumns, and AddRoundKey.

Decryption applies the inverse operations in reverse order to recover the original plaintext.

🧠 How AES Works

AES encryption works through multiple rounds:

Initial round: AddRoundKey

Main rounds: SubBytes → ShiftRows → MixColumns → AddRoundKey

Final round: SubBytes → ShiftRows → AddRoundKey (without MixColumns)

The number of rounds depends on the key size:

10 rounds for 128-bit keys

12 rounds for 192-bit keys

14 rounds for 256-bit keys

🧪 Project Overview

This project implements AES encryption and decryption in Java with:

AES S-Box and Inverse S-Box tables

Basic AES transformations (SubBytes, ShiftRows, MixColumns, AddRoundKey)

User input support for plaintext and key (planned)

Educational purpose implementation (not optimized for production use)

📥 Future Features

Input plaintext and secret key from user

Encrypt plaintext to ciphertext

Decrypt ciphertext back to original plaintext
