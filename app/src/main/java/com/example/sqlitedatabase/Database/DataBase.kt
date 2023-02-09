package com.example.sqlitedatabase.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlitedatabase.StudentModel

class DataBase(context: Context?) : SQLiteOpenHelper(context, "MyData.db", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {

        val sql ="CREATE TABLE students(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT,address TEXT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addData(name: String, surname: String, address: String) {

        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("surname", surname)
        values.put("address", address)
        db.insert("students", null, values)
    }

    fun showData(): ArrayList<StudentModel> {

        var stdList = ArrayList<StudentModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM students"
        var cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        for (i in 0 until cursor.count) {
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var surname = cursor.getString(2)
            var address = cursor.getString(3)

            var std = StudentModel(id, name, surname, address)
            stdList.add(std)
            cursor.moveToNext()

        }
        return stdList
    }

    fun updateData(name: String, surname: String, address: String, id: Int) {

        var db = writableDatabase
        var values = ContentValues()
        values.put("name", name)
        values.put("surname", surname)
        values.put("address", address)
        db.update("students", values, "id=$id", null)

    }

    fun deleteData(id:Int){

        var db = writableDatabase
        db.delete("students","id=$id",null)
    }
}