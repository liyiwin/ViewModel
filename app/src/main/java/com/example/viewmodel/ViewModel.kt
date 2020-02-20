package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ViewModel: ViewModel() {

    private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()


    val mybook_data = MutableLiveData<Data>()



    fun setValue(){

            val url = "http://104.199.148.167/api/books/109"
            val request = Request.Builder().url(url).build()
            val call = OkHttpClient .newCall(request)

            call.enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val myjson = response.body()?.string()!!.trim()

                    val code = response.code()

                    if (code == 200){

                        val jsonObject = JSONObject(myjson)

                        val data =  jsonObject.getJSONArray("data")

                        val MyObject = data.getJSONObject(0)

                        val title =MyObject.getString("title")

                        val image = MyObject.getString("image")

                        val author = MyObject.getString("author")

                        val intro = MyObject.getString("intro")

                        mybook_data.postValue(Data(title,author,intro,image))



                    }


                }
            })


        }



     fun getValue():LiveData<Data>{


            return  mybook_data


        }





}