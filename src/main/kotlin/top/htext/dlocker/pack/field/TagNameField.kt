package top.htext.dlocker.pack.field

class TagNameField : Matchable {
	companion object {
		private val REGULEX = ArrayList<String>()

		init {
			REGULEX.add("(?<=(tag=)).+?(?=\\]\\s)")
			REGULEX.add("(?<=(tag (@[aerps]|.+) (add|remove|get) )).+\$")
		}
	}

	override fun match() {

	}
}