package top.htext.dlocker.utils

import com.google.gson.JsonParser
import top.htext.dlocker.exceptions.PackTypeException
import top.htext.dlocker.pack.PackType
import java.nio.file.Path

object PackUtils {
	fun getPackType(path: Path): PackType {
		val treeWalk = path.toFile().walk().maxDepth(1)
		val meta = treeWalk
			.filter { it.isFile }
			.filter { it.name == "pack.mcmeta" }
			.first()

		val content = meta.readText()
		val jsonElement = JsonParser.parseString(content)
		val jsonObject = jsonElement.asJsonObject.getAsJsonObject("pack")

		if (! jsonObject.has("pack_format") && jsonObject.has("description")) throw PackTypeException("Not a datapack.")

		val directories = treeWalk.maxDepth(1).filter { it.isDirectory }

		for (directory in directories) {
			if (directory.name == "data") return PackType.DATAPACK
			if (directory.name == "assets") return PackType.RESOURCEPACK
		}

		throw PackTypeException("Not a datapack.")
	}
}