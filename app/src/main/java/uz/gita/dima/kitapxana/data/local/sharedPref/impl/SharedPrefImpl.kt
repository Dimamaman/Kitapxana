package uz.gita.dima.kitapxana.data.local.sharedPref.impl

import android.content.SharedPreferences
import uz.gita.dima.kitapxana.data.local.sharedPref.SharedPref
import javax.inject.Inject
import uz.gita.dima.kitapxana.util.Constant.BOOK_NAME
import uz.gita.dima.kitapxana.util.Constant.TOTAL_PAGE
import uz.gita.dima.kitapxana.util.Constant.SAVED_PAGE

class SharedPrefImpl @Inject constructor(private val pref: SharedPreferences) : SharedPref {

    override var bookName: String?
        get() = pref.getString(BOOK_NAME, "")
        set(value) = pref.edit().putString(BOOK_NAME, value).apply()

    override var savedPage: Int
        get() = pref.getInt(SAVED_PAGE, 0)
        set(value) = pref.edit().putInt(SAVED_PAGE, value).apply()

    override var totalPage: Int
        get() = pref.getInt(TOTAL_PAGE, 0)
        set(value) = pref.edit().putInt(TOTAL_PAGE, value).apply()
}