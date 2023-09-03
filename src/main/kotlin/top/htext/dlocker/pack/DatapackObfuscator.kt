package top.htext.dlocker.pack

import top.htext.dlocker.DLocker.Companion.LOGGER
import java.io.File
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

		val regulexList = ArrayList<String>()
		regulexList.add("(?<=(tag=)).+?(?=\\]\\s)")
		regulexList.add("(?<=(tag (@[aerps]|.+) (add|remove|get) )).+\$")

		val files = getAllFiles()

		for (file in files) {
			val lines = file.readLines()
			for (line in lines) {


				// TODO: Obfuscates datapack
			}
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