package top.htext.dlocker.field

import top.htext.dlocker.DLocker
import top.htext.dlocker.field.command.TagNameField

object MainTest {
	@JvmStatic
	fun main(args: Array<String>) {
		val processed = TagNameField.handle("execute @a[tag=a] at @s[tag=bc] run tag @s add a")
		DLocker.LOGGER.debug(processed)
	}
}