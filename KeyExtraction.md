The easiest way for me was to hook into MultiBit HD's signing capabilities and log the private key being used to the console:

```
diff --git a/mbhd-core/src/main/java/org/multibit/hd/core/managers/WalletManager.java b/mbhd-core/src/main/java/org/multibit/hd/core/managers/WalletManager.java
index 61979a3..e8a0de1 100644
--- a/mbhd-core/src/main/java/org/multibit/hd/core/managers/WalletManager.java
+++ b/mbhd-core/src/main/java/org/multibit/hd/core/managers/WalletManager.java
@@ -1753,6 +1753,8 @@ public enum WalletManager implements WalletEventListener {
           if (signingKey.getKeyCrypter() != null) {
             KeyParameter aesKey = signingKey.getKeyCrypter().deriveKey(walletPassword);
             ECKey decryptedSigningKey = signingKey.decrypt(aesKey);
+            log.info("HACK address: " + signingAddress.toString());
+            log.info("HACK private key: " + decryptedSigningKey.getPrivateKeyAsWiF(networkParameters));

             String signatureBase64 = decryptedSigningKey.signMessage(messageText);
             return new SignMessageResult(Optional.of(signatureBase64), true, CoreMessageKey.SIGN_MESSAGE_SUCCESS, null);
```

I have tested this with v0.5 following these steps:

1) Clone the MultiBit HD Git repository
2) Apply the patch above (The current repo where you find this file has been pre-patched)
3) Build the project (Skipping tests to speed things up): mvn clean dependency:sources install
4) Start the application: java -jar mbhd-swing/target/multibit-hd.jar
5) Go to Tools -> Sign message and enter a Bitcoin address from your wallet for which you want to recover the private key. Press finish.
6) Read the Bitcoin address and corresponding private key from the console output