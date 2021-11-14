package com.hfad.jokes

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hfad.jokes.api.ApiService
import com.hfad.jokes.data.Jokes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokesFragment : Fragment(R.layout.fragment_jokes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.icndb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)


        val textJoke = view.findViewById<TextView>(R.id.joke)
        val button = view.findViewById<Button>(R.id.button)
        val number = view.findViewById<EditText>(R.id.number)


        button.setOnClickListener {

            val listJokes = mutableListOf<String>()
            val num = number.text.toString().toInt()

            api.fetchJokes(number = num).enqueue(object : Callback<Jokes> {
                override fun onResponse(call: Call<Jokes>, response: Response<Jokes>) {

                    val jokes = response.body()?.value

                    if(jokes.isNullOrEmpty()) {
                        Toast.makeText(context, "Put count more than 0!", Toast.LENGTH_SHORT).show()
                    } else {
                        for(i in 0 until num){
                            listJokes.add(jokes[i].joke)
                        }
                        textJoke.text = listJokes.toString()
                    }
                }

                override fun onFailure(call: Call<Jokes>, t: Throwable) {
                    Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}