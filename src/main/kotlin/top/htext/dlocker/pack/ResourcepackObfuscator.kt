package top.htext.dlocker.pack

import java.io.File
import java.nio.file.Path

class ResourcepackObfuscator(path: Path, seed: String) : PackObfuscator, PackFileManager {
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
		// TODO: Obfuscates Resourcepack.
	}

	override fun getAllFiles(): ArrayList<File> {
		val path = getPath()
		val treeWalk = path.toFile().walk()
		val files = treeWalk
			.filter { it.isFile }
			.filter { it.extension in arrayOf("png", "json", "mcmeta") }

		return files.toList() as ArrayList<File>
	}
}