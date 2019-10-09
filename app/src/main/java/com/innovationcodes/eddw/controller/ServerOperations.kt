package com.innovationcodes.eddw.controller

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.edit
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Method
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.google.gson.reflect.TypeToken
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.model.*
import okhttp3.Response

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

    internal fun showHideLoadingView() {
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

    private fun getId(): String {
        return shared.getString("id", "") ?: ""
    }

    fun loginSpeaker(username: String, callback: (logged: Boolean) -> Unit) {

        dynamicRequest<Speaker>(url = "Speaker/Authenticate/$username") {
            if (it != null) {
                shared.edit {
                    putString("name", it.fullname)
                    putString("token", it.token)
                    putString("username", it.username)
                    putInt("title", it.title)
                    putString("id", it.id)
                }
                Toast.makeText(context, "Welcome, ${it.fullname}", Toast.LENGTH_LONG).show()
                callback(true)
            } else {
                callback(false)
            }
        }
    }

    fun loginEmployee(username: String, callback: (logged: Boolean) -> Unit) {
        dynamicRequest<Employee>(url = "Employee/Authenticate/$username") {
            if (it != null) {
                shared.edit {
                    putString("name", it.fullname)
                    putString("token", it.token)
                    putString("username", it.username)
                    putInt("title", it.title)
                    putString("id", it.id)
                }
                Toast.makeText(context, "Welcome, ${it.fullname}", Toast.LENGTH_LONG).show()
                callback(true)
            } else {
                callback(false)
            }
        }

    }

    fun loginGuest(username: String, password: String, callback: (logged: Boolean) -> Unit) {
        dynamicRequest<Guest>(url = "Guest/Authenticate/$username/$password") {
            if (it != null) {
                shared.edit {
                    putString("name", it.fullname)
                    putString("token", it.accessToken)
                    putString("username", it.username)
                    putInt("title", it.title)
                    putString("id", it.id)
                }
                Toast.makeText(context, "Welcome, ${it.fullname}", Toast.LENGTH_LONG).show()
                callback(true)
            } else {
                callback(false)
            }
        }

    }


    fun registerGuest(guest: Guest, callback: (logged: Boolean) -> Unit) {
        dynamicRequest(Method.POST, url = "Guest", body = guest) {
            if (it != null) {
                shared.edit {
                    putString("name", it.fullname)
                    putString("token", it.accessToken)
                    putString("username", it.username)
                    putInt("title", it.title)
                    putString("id", it.id)
                }

                callback(true)
            } else {
                callback(false)
            }
        }

    }

    fun createMetaAssist(meta: MetaAssist, callback: (MetaAssist: MetaAssist) -> Unit) {
        meta.user = getId()

        dynamicRequest(Method.POST, url = "MetaAssist", body = meta) {
            if (it != null) {
                callback(it)
            } else {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }


    }


    fun retrieveProgramme(callback: (prog: ArrayList<Programme>) -> Unit) {
        dynamicRequest<ArrayList<Programme>>(url = "Programme") {
            if (it.isNullOrEmpty()) {
                return@dynamicRequest
            } else {
                println("Basem ${it.first().status}")
                callback(it)
            }
        }

    }


    fun retrieveTimeline(callback: (times: ArrayList<Timeline>) -> Unit) {
        dynamicRequest<ArrayList<Timeline>>(url = "Timeline/${getId()}") {
            if (it.isNullOrEmpty()) {
                return@dynamicRequest
            } else {
                println("Basem ${it.first().title}")
                callback(it)
            }
        }

    }

    fun retrieveSponsors(callback: (times: ArrayList<Sponsor>) -> Unit) {
        dynamicRequest<ArrayList<Sponsor>>(url = "Sponsor") {
            if (it.isNullOrEmpty()) {
                return@dynamicRequest
            } else {

                callback(it)
            }
        }

    }

    fun retriveSpeakers(callback: (speakers: ArrayList<Speaker>) -> Unit) {

        dynamicRequest<ArrayList<Speaker>>(url = "Speaker") {
            if (it.isNullOrEmpty()) {
                return@dynamicRequest
            } else {
                callback(it)
            }
        }

    }

    fun saveAttendance(code: String, programmeId: Int) {
        val att = Attendance()
        att.userId = getId()
        att.programmeId = programmeId

        dynamicRequest(Method.POST, url = "Attendance/$code", body = att) {
            if (it != null) {
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "wrong attendance code", Toast.LENGTH_SHORT).show()
            }

        }

    }


    fun saveQuestion(question: Question) {
        question.userId = getId()

        dynamicRequest(Method.POST, url = "Question", body = question) {
            if (it != null) {
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private inline fun <reified T : Any> dynamicRequest(
        method: Int = Method.GET,
        url: String,
        body: T? = null,
        query: Map<String, Any>? = null,
        crossinline callback: (res: T?) -> Unit
    ) {
        showHideLoadingView()
        AndroidNetworking.request("${host}${url}", method)
            .setPriority(Priority.MEDIUM)
            .addApplicationJsonBody(body)
            .addQueryParameter(query)
            .build()
            .getAsOkHttpResponseAndParsed(object : TypeToken<T>() {},
                object : OkHttpResponseAndParsedRequestListener<T> {
                    override fun onResponse(okHttpResponse: Response?, response: T) {
                        showHideLoadingView()
                        if (okHttpResponse != null && okHttpResponse.isSuccessful) {
                            callback(response)
                        } else {
                            println("Basem API Good with error")
                        }
                    }

                    override fun onError(anError: ANError?) {
                        showHideLoadingView()
                        callback(null)
                        println("Basem API ${anError?.errorCode} ${anError?.errorBody} ${anError?.errorDetail} ${anError?.message},${anError?.response}")
                    }
                })
    }


}