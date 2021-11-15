package com.hfad.jokes.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.jokes.R
import com.hfad.jokes.api.buildApiService
import com.hfad.jokes.data.Jokes
import com.hfad.jokes.databinding.FragmentJokesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class JokesFragment : Fragment(R.layout.fragment_jokes) {

    companion object {
        const val SAVE_LIST = "SAVE_LIST"
    }

    private val api = buildApiService()
    private var listJokes: ArrayList<String> = ArrayList()
    private var _binding: FragmentJokesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            savedInstanceState.getStringArrayList(SAVE_LIST)?.let { showJokes(it) }
        }

        binding.button.setOnClickListener {
            val imm: InputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            fetchJokes(binding.number.text.toString().toInt())

        }
    }

    private fun fetchJokes(number: Int) {

        api.fetchJokes(number = number).enqueue(object : Callback<Jokes> {
            override fun onResponse(call: Call<Jokes>, response: Response<Jokes>) {

                val jokes = response.body()?.value

                if (jokes.isNullOrEmpty()) {
                    Toast.makeText(context, "Put count more than 0!", Toast.LENGTH_SHORT).show()
                } else {
                    listJokes.clear()

                    for (i in 0 until number) {
                        listJokes.add(jokes[i].joke)
                    }
                    showJokes(listJokes)
                }
            }

            override fun onFailure(call: Call<Jokes>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showJokes(list: MutableList<String>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = JokesAdapter(list)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(SAVE_LIST, listJokes)
    }
}