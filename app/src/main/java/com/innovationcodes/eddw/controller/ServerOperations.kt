package com.innovationcodes.eddw.controller

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.edit
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.model.Employee
import com.innovationcodes.eddw.model.Programme
import com.innovationcodes.eddw.model.Speaker

class ServerOperations(var context: Context) {
    private var dialog: AlertDialog
    private val host = "http://eddw.innovationcodes.com/api/"
    private var shared: SharedPreferences

    init {
        AndroidNetworking.initialize(context)
        AndroidNetworking.enableLogging()
        shared = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        dialog = AlertDialog.Builder(context).create()
        @SuppressLint("InflateParams")
        val inflated = LayoutInflater.from(context).inflate(R.layout.loading_view, null)

        dialog.setView(inflated)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun showHideLoadingView() {
        if (dialog.isShowing) {
            dialog.dismiss()
        } else {
            dialog.show()
        }

    }

    fun isLogged(): Boolean {
        return shared.contains("id")
    }

    fun getFullName(): String {
        return shared.getString("name", "") ?: ""
    }


    fun loginSpeaker(username: String, callback: (logged: Boolean) -> Unit) {
        baseRequest("Speaker/Authenticate/$username", Speaker::class.java) { speaker: Speaker? ->
            if (speaker != null) {
                shared.edit {
                    putString("name", speaker.fullname)
                    putString("token", speaker.token)
                    putString("username", speaker.username)
                    putInt("title", speaker.title)
                    putString("id", speaker.id)
                }
                Toast.makeText(context, "Welcome, ${speaker.fullname}", Toast.LENGTH_LONG).show()
                callback(true)
            } else {
                callback(false)
            }


        }
    }

    fun loginEmployee(username: String, callback: (logged: Boolean) -> Unit) {
        baseRequest(
            "Employee/Authenticate/$username",
            Employee::class.java
        ) { employee: Employee? ->
            if (employee != null) {
                shared.edit {
                    putString("name", employee.fullname)
                    putString("token", employee.token)
                    putString("username", employee.username)
                    putInt("title", employee.title)
                    putString("id", employee.id)
                }
                Toast.makeText(context, "Welcome, ${employee.fullname}", Toast.LENGTH_LONG).show()
                callback(true)
            } else {
                callback(false)
            }


        }
    }


    fun retrieveProgramme(callback: (prog: ArrayList<Programme>) -> Unit) {
        baseArrayRequest("Programme", Programme::class.java) { progs: ArrayList<Programme>? ->
            if (progs.isNullOrEmpty()) {
                return@baseArrayRequest
            } else {
                println("Basem ${progs.first().status}")
                callback(progs)
            }

        }
    }


    private fun <T, C> baseRequest(url: String, c: Class<C>, callback: (res: T?) -> Unit) {
        showHideLoadingView()
        AndroidNetworking.get("${host}${url}")
            .setPriority(Priority.HIGH)
            .build()
            .getAsObject(c, object : ParsedRequestListener<T> {
                override fun onResponse(response: T) {
                    showHideLoadingView()
                    callback(response)
                }

                override fun onError(anError: ANError?) {
                    showHideLoadingView()
                    callback(null)
                    println("Basem ${anError?.errorBody} ${anError?.errorDetail}")
                }
            })
    }

    private fun <T, C> baseArrayRequest(url: String, c: Class<C>, callback: (res: T?) -> Unit) {
        showHideLoadingView()
        AndroidNetworking.get("${host}${url}")
            .setPriority(Priority.HIGH)
            .build()
            .getAsObjectList(c, object : ParsedRequestListener<T> {
                override fun onResponse(response: T) {
                    showHideLoadingView()
                    callback(response)
                }

                override fun onError(anError: ANError?) {
                    showHideLoadingView()
                    callback(null)
                    println("Basem ${anError?.errorBody} ${anError?.errorDetail}")
                }
            })
    }


}