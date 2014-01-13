package ajeetmurty.reference.java.crypt;

import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringJasypt {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
	private String plainText = "IN9UT_#$tr1ng-.2o14";
	private String symettricEncryptionKey = "WbuFnjTsoiCftdmX_UeJRMHS9";

	public static void main(String[] args) {
		new StringJasypt();
	}

	private StringJasypt() {
		logp.info("start");
		doJasypt();
		logp.info("stop");
	}

	private void doJasypt() {
		logp.info("input plain text: " + plainText);
		logp.info("encryption/decryption key: " + symettricEncryptionKey);
		String encryptedText = null;
		String decryptedText = null;

		if (plainText != null && !plainText.isEmpty()) {
			encryptedText = crypString(plainText, symettricEncryptionKey, true);
		} else {
			logp.error("input plain text is null or empty");
		}

		logp.info("encrypted version of plain text: " + encryptedText);

		if (encryptedText != null && !encryptedText.isEmpty()) {
			decryptedText = crypString(encryptedText, symettricEncryptionKey, false);
		} else {
			logp.error("encrypted text is null or empty");
		}

		logp.info("decrypted version of encrypted plain text: " + decryptedText);
	}

	private String crypString(String inputText, String key, boolean encrypt) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(key);
		if (encrypt) {
			return textEncryptor.encrypt(inputText);
		} else {
			return textEncryptor.decrypt(inputText);
		}
	}
}
