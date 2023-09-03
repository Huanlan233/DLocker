package top.htext.dlocker.pack

import top.htext.dlocker.DLocker.Companion.LOGGER
import top.htext.dlocker.field.command.TagNameField
import java.io.File
import java.nio.file.Files
import java.nio.file.Path


class DatapackObfuscator(path: Path, seed: String) : PackObfuscator, PackFileManager {
	private val path: Path
	private val seed: String

	init {
		this.path = path
		this.seed = seed
	}

	override fun getSeed(): String {
		return seed
	}

	override fun getPath(): Path {
		return path
	}

	override fun obfuscate() {
		LOGGER.debug("obfuscate() was called.")

		val files = getAllFiles()

		for (file in files) {
			val lines = file.readLines()
			val obfuscatedLines = ArrayList<String>()

			if (file.extension != "mcmeta") for (line in lines) {
				val obfuscatedLine = TagNameField.handle(line)
				obfuscatedLines.add(obfuscatedLine)

				LOGGER.info(line.replace(line, obfuscatedLine))
			}

			Files.write(file.toPath(), obfuscatedLines)
		}
	}

	override fun getAllFiles(): ArrayList<File> {
		val path = getPath()
		val treeWalk = path.toFile().walk()
		val files = treeWalk
			.filter { it.isFile }
			.filter { it.extension in arrayOf("mcfunction", "json", "mcmeta") }

		return files.toList() as ArrayList<File>
	}
}