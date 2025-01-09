package ar.com.scacchipa.providerapp

import android.content.ContentResolver
import android.database.CharArrayBuffer
import android.database.ContentObserver
import android.database.Cursor
import android.database.Cursor.FIELD_TYPE_STRING
import android.database.DataSetObserver
import android.net.Uri
import android.os.Bundle


class MyCursor(
    val myDatabase: MyDatabase,
    val keyList: List<String>,
    var cursorPosition: Int
) : Cursor {

    private val columnNames = arrayOf("key")
    private val columnTypes = intArrayOf(FIELD_TYPE_STRING)
    private val contentObserverList = mutableListOf<ContentObserver>()
    private var close = false
    private var notificationUri: Uri? = null
    private var extras = Bundle()

    override fun close() {
        myDatabase.destroyCursor(this)
        close = true
    }

    override fun getCount(): Int  = keyList.size


    override fun getPosition(): Int = cursorPosition

    override fun move(offset: Int): Boolean {

        if (offset in 0 .. getPosition() + count) {
            cursorPosition = cursorPosition.plus(offset)
            return true
        }

        return false
    }

    override fun moveToPosition(position: Int): Boolean {

        if (position in 0..<count) {
            this.cursorPosition = position
            return true
        }

        return false
    }

    override fun moveToFirst(): Boolean {

        if (count > 0) {
            cursorPosition = 0
            return true
        }

        return false
    }

    override fun moveToLast(): Boolean {

        if (count > 0) {
            cursorPosition = count - 1
            return true
        }

        return false
    }

    override fun moveToNext(): Boolean {

        if (cursorPosition < count - 1) {
            cursorPosition = cursorPosition.plus(1)
            return true
        }

        return false
    }

    override fun moveToPrevious(): Boolean {
        if (cursorPosition > 0) {
            cursorPosition = cursorPosition.minus(1)
            return true
        }

        return false
    }

    override fun isFirst(): Boolean = cursorPosition == 0

    override fun isLast(): Boolean = cursorPosition == count - 1

    override fun isBeforeFirst(): Boolean = cursorPosition == -1

    override fun isAfterLast(): Boolean = cursorPosition == count

    override fun getColumnIndex(columnName: String?): Int =
        columnNames.indexOfFirst { name -> name == columnName }

    override fun getColumnIndexOrThrow(columnName: String?): Int {

        val columnIndex = columnNames.indexOfFirst { name -> name == columnName }

        if (columnIndex != -1) {
            throw IllegalArgumentException("No such column")
        }

       return columnIndex
    }

    override fun getColumnName(columnIndex: Int): String = columnNames[columnIndex]

    override fun getColumnNames(): Array<String> = columnNames

    override fun getColumnCount(): Int = columnNames.size

    override fun getBlob(columnIndex: Int): ByteArray {
        TODO("Not yet implemented")
    }

    override fun getString(columnIndex: Int): String = keyList[cursorPosition]

    override fun copyStringToBuffer(columnIndex: Int, buffer: CharArrayBuffer?) {
        buffer?.apply {
            val str = getString(columnIndex)
            data = str.toCharArray()
            sizeCopied = str.length
        }
    }

    override fun getShort(columnIndex: Int): Short {
        TODO("Not yet implemented")
    }

    override fun getInt(columnIndex: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(columnIndex: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(columnIndex: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getDouble(columnIndex: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getType(columnIndex: Int): Int {
        return columnTypes[columnIndex]
    }

    override fun isNull(columnIndex: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun deactivate() {
        TODO("Not yet implemented")
    }

    override fun requery(): Boolean {
        throw UnsupportedOperationException("Requery is not supported")
    }

    override fun isClosed(): Boolean {
        return isClosed
    }

    override fun registerContentObserver(observer: ContentObserver?) {
        observer?.let {
            contentObserverList.add(it)
        }
    }

    override fun unregisterContentObserver(observer: ContentObserver?) {
        contentObserverList.remove(observer)
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun setNotificationUri(cr: ContentResolver?, uri: Uri?) {
        notificationUri = uri
    }

    override fun getNotificationUri(): Uri? {
        return notificationUri
    }

    override fun getWantsAllOnMoveCalls(): Boolean {
        return false
    }

    override fun setExtras(extras: Bundle?) {
        this.extras = extras ?: Bundle()
    }

    override fun getExtras(): Bundle {
        return extras
    }

    override fun respond(extras: Bundle?): Bundle {
        return extras ?: Bundle()
    }
}