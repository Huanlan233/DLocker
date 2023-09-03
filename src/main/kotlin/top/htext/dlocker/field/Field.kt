package top.htext.dlocker.field

abstract class Field(field: String) : Confusable {
	abstract val fieldKey: String

	private val field: String

	init {
		this.field = field
	}

	abstract override fun obfuscate(): String

	open fun getKey(): String {
		return fieldKey
	}

	open fun getField(): String {
		return field
	}
}