package top.htext.dlocker.field.command

import top.htext.dlocker.field.Field
import java.security.MessageDigest
import java.util.regex.Pattern


class TagNameField(field: String) : Field(field) {
	companion object {
		private val REGULEX = ArrayList<String>()

		init {
			REGULEX.add("(?<=(tag=))(?!\\.\\.).+?(?=\\])")
			REGULEX.add("(?<=(tag (@[aerps]|.+) (add|remove|get) ))(?!\\.\\.).+$")
		}

		fun handle(line: String): String {
			val input = StringBuilder(line)
			for (r in REGULEX) {
				val pattern = Pattern.compile(r)
				val matcher = pattern.matcher(input)

				while (matcher.find()) {
					val matchedField = TagNameField(matcher.group())
					val obfuscatedField = matchedField.obfuscate()

					input.replace(matcher.start(), matcher.end(), obfuscatedField)
					matcher.reset() // Repeating matching.
				}
			}

			return input.toString()
		}
	}

	private val field: String

	override val fieldKey = "TAG_NAME"

	init {
		this.field = field
	}

	override fun obfuscate(): String {
		val sha1 = MessageDigest.getInstance("SHA-1")
		val textBytes: ByteArray = field.toByteArray()
		sha1.update(textBytes);
		val hashBytes = sha1.digest()

		val stringBuilder = StringBuilder()
		for (hashByte in hashBytes) {
			stringBuilder.append(String.format("%02x", hashByte))
		}

		return "..DLocker." + stringBuilder.toString() + stringBuilder.toString().hashCode()
			.toString() + fieldKey.hashCode().toString()
	}
}