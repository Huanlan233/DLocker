package top.htext.dlocker.field

@FunctionalInterface
interface Confusable {
	/**
	 * To obfuscates the field.
	 * @return Confused field.
	 * */
	fun obfuscate(): String
}