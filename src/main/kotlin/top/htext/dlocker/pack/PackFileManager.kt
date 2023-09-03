package top.htext.dlocker.pack

import java.io.File

interface PackFileManager {
	fun getAllFiles(): ArrayList<File>
}