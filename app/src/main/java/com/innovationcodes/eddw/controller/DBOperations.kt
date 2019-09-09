package com.innovationcodes.eddw.controller

import android.content.ContentValues
import android.content.Context
import com.innovationcodes.eddw.model.Note


class DBOperations(var context: Context) {
    fun saveNote(note: String, programmeName: String, id: Int) {
        val myDB = context.openOrCreateDatabase(
            "AppDB.db",
            Context.MODE_PRIVATE, null
        )
        myDB.execSQL("CREATE TABLE IF NOT EXISTS `Notes` (id INTEGER PRIMARY KEY, proId INTEGER NOT NULL, proName TEXT NOT NULL,note TEXT NOT NULL);")
        val values = ContentValues()
        values.put("proId", id)
        values.put("proName", programmeName)
        values.put("note", note)
        if (getNote(id).isEmpty()) {
            myDB.insert("Notes", null, values)
        } else {
            myDB.update("Notes", values, "proId=$id", null)
        }
        myDB.close()

    }

    fun getNote(id: Int): String {
        var note = ""
        val myDB = context.openOrCreateDatabase(
            "AppDB.db",
            Context.MODE_PRIVATE, null
        )
        try {
            val cursor = myDB.rawQuery("SELECT note FROM Notes WHERE proId = $id", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                note = cursor.getString(0)
                cursor.moveToNext()
            }
            cursor.close()
            myDB.close()
        } catch (e: Exception) {
        }

        return note
    }

    fun getAllNote(): ArrayList<Note> {
        val list = arrayListOf<Note>()
        val myDB = context.openOrCreateDatabase(
            "AppDB.db",
            Context.MODE_PRIVATE, null
        )
        try {
            val cursor = myDB.rawQuery("SELECT * FROM Notes", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val note = Note()
                note.proId = cursor.getInt(1)
                note.proName = cursor.getString(2)
                note.body = cursor.getString(3)
                list.add(note)
                cursor.moveToNext()
            }
            cursor.close()
            myDB.close()
        } catch (e: Exception) {
        }


        return list
    }


    fun saveFavorite(programmeId: Int) {
        val myDB = context.openOrCreateDatabase(
            "AppDB.db",
            Context.MODE_PRIVATE, null
        )
        myDB.execSQL("CREATE TABLE IF NOT EXISTS `Favorites` (id INTEGER PRIMARY KEY, proId INTEGER NOT NULL);")
        val values = ContentValues()
        values.put("proId", programmeId)
        myDB.insert("Favorites", null, values)
        myDB.close()
    }

    fun getFavorite(): ArrayList<Int> {
        val list = arrayListOf<Int>()
        val myDB = context.openOrCreateDatabase(
            "AppDB.db",
            Context.MODE_PRIVATE, null
        )
        try {
            val cursor = myDB.rawQuery("SELECT proId FROM Favorites", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                list.add(cursor.getInt(0))
                cursor.moveToNext()
            }
            cursor.close()
            myDB.close()
        } catch (e: Exception) {
        }

        return list
    }


    fun deleteFavorite(programmeId: Int) {
        //DELETE FROM NOTE WHERE ID = 5
        val myDB = context.openOrCreateDatabase(
            "AppDB.db",
            Context.MODE_PRIVATE, null
        )
        myDB.delete("Favorites", "proId=?", arrayOf(programmeId.toString()))

        myDB.close()
    }
}