package ar.com.scacchipa.providerapp

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.provider.DocumentsProvider
import android.widget.CursorAdapter


class MiContentProvider : ContentProvider() {

    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("ar.com.scacchipa.providerapp", "easyKey", 1)
        addURI("ar.com.scacchipa.providerapp", "mediumKey", 2)
        addURI("ar.com.scacchipa.providerapp", "hardKey", 3)
    }

    private val myDatabase = MyDatabase()

    override fun onCreate(): Boolean {
        println("onCreate")
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        println("query")
        println(uri)
        println(projection)
        println(selection)
        println(selectionArgs)
        println(sortOrder)

        return when (sUriMatcher.match(uri)) {
            1 -> myDatabase.getEasyKey()
            2 -> myDatabase.getMediumKey()
            3 -> myDatabase.getHardKey()
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return Cursor.FIELD_TYPE_STRING.toString()
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}
