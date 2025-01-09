package ar.com.scacchipa.providerapp

class MyDatabase  {

    val keyMap = mapOf(
        "easyKey" to listOf("easyKey"),
        "mediumKey" to listOf("meeddiiuummKKeeyy"),
        "hardKey" to listOf(
            "fvbayvryfqb_MUY_DIFÍCIL_uyebyugyeugasud",
            "oidIIUh$$^@#_MUY_MUY_DIFÍCIL_oihuv^%#")
    )

    private val cursorList = mutableListOf<MyCursor>()

    fun createCursor(keyType: String, position: Int) : MyCursor? {

        val keyList = keyMap[keyType]

        if (keyList.isNullOrEmpty() || keyList.size <= position) {
            return null
        }

        val cursor = MyCursor(
            myDatabase = this,
            keyList = keyList,
            cursorPosition = position
        )

        cursorList.add(cursor)
        return cursor
    }

    fun getEasyKey(): MyCursor? = createCursor("easyKey", 0)
    fun getMediumKey(): MyCursor? = createCursor("mediumKey", 0)
    fun getHardKey(): MyCursor? = createCursor("hardKey", 0)

//    fun createCursor(myCursor: MyCursor, position: Int): MyCursor? {
//        if (myCursor.keyList.isEmpty() || myCursor.keyList.size <= position) {
//            return null
//        }
//        val newCursor = MyCursor(
//            myDatabase = this,
//            keyList = myCursor.keyList,
//            position = position
//        )
//        cursorList.add(newCursor)
//
//        return newCursor
//    }
//
    fun destroyCursor(cursor: MyCursor) {
        cursorList.remove(cursor)
    }
//
//    fun moveCursor(cursor: MyCursor, position: Int): MyCursor? {
//        return createCursor(cursor, position)
//    }
}