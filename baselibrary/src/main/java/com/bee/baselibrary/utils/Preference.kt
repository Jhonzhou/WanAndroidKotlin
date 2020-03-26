package com.bee.baselibrary.utils

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *  sp工具类,使用属性委托方便使用
 */
class Preference<T>(private val key: String, private var value: T) : ReadWriteProperty<Any?, T> {
    companion object {
        private lateinit var spInstance: SharedPreferences

        fun initContext(context: Context) {
            spInstance = context.applicationContext.getSharedPreferences(
                    "sp_config${context.applicationContext.packageName}",
                    Context.MODE_PRIVATE)
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = getSp(key, value)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putSp(key, value)

    private fun <U> putSp(key: String, value: U) = with(spInstance.edit()) {
        when (value) {
            is String -> putString(key, value)
            is Long -> putLong(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            else -> throw Exception("this $value is not support")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <U> getSp(key: String, value: U) = with(spInstance) {
        when (value) {
            is String -> getString(key, value)
            is Long -> getLong(key, value)
            is Boolean -> getBoolean(key, value)
            is Float -> getFloat(key, value)
            is Int -> getInt(key, value)
            else -> throw Exception("This type can not be saved into Preferences")
        } as U
    }
}