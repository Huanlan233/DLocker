package top.htext.dlocker

import org.apache.commons.cli.*
import org.apache.commons.io.FileUtils
import org.apache.logging.log4j.LogManager.getLogger
import org.apache.logging.log4j.Logger
import top.htext.dlocker.pack.DatapackObfuscator
import top.htext.dlocker.pack.PackObfuscator
import top.htext.dlocker.pack.PackType
import top.htext.dlocker.pack.ResourcepackObfuscator
import top.htext.dlocker.utils.PackUtils
import java.io.File
import kotlin.system.exitProcess

class DLocker {
	companion object {
		val LOGGER: Logger = getLogger()

		@JvmStatic
		fun main(args: Array<String>) {
			val options = initOptions()
			val cmd = parseOptions(options, args)

			val pack = File(cmd.getOptionValue("p", null)).toPath()
			val output = File(cmd.getOptionValue("o", "./output")).toPath()
			val seed = cmd.getOptionValue("s", "DLocker by Huanlan233")

			output.toFile().deleteOnExit()
			FileUtils.copyDirectory(pack.toFile(), output.toFile())

			var packObfuscator: PackObfuscator = DatapackObfuscator(output, seed)
			if (PackUtils.getPackType(output) == PackType.RESOURCEPACK) packObfuscator =
				ResourcepackObfuscator(output, seed)

			packObfuscator.obfuscate()
		}

		private fun initOptions(): Options {
			LOGGER.debug("initOptions() was called.")

			val options = Options()
			val packOption = Option.builder("p").longOpt("pack")
				.hasArg().argName("path")
				.desc("Path of datapack which will be confused.")
				.required(true).build()
			val outputOption = Option.builder("o").longOpt("output")
				.hasArg().argName("path")
				.desc("Path of output.")
				.build()
			val seedOption = Option.builder("s").longOpt("seed")
				.hasArg().argName("str")
				.desc("Seed.")
				.build()

			return options
				.addOption(packOption)
				.addOption(outputOption)
				.addOption(seedOption)
		}

		private fun parseOptions(options: Options, args: Array<String>): CommandLine {
			LOGGER.debug("parseOptions() was called.")

			val parser = DefaultParser()
			val helpFormatter = HelpFormatter()

			try {
				return parser.parse(options, args)
			} catch (e: ParseException) {
				println(e.message)
				helpFormatter.printHelp("Please pass a argument.", options)
				exitProcess(- 1)
			}
		}
	}
}