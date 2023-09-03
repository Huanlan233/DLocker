package top.htext.dlocker.pack

import java.nio.file.Path

/**
 * This class is only used to record the path and seed of the pack and obfuscate the pack.
 * */
interface PackObfuscator {
	/**
	 * @return The seed of the pack.
	 * */
	fun getSeed(): String

	/**
	 * @return The path of the pack.
	 * */
	fun getPath(): Path

	fun obfuscate()
}