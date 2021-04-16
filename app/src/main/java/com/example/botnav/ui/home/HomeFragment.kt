package com.example.botnav.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.botnav.R
import com.example.botnav.service.CommentApi
import com.example.botnav.ui.model.Comment
import com.example.botnav.utils.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
getCommentApi()
    }
    fun getCommentApi(){
        val retro = Retro().getRetroClientInstance().create(CommentApi::class.java)
        retro.getComments().enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
          val comment = response.body()
              for (c in comment !!){
                  Log.e("result", c.email.toString())
              }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
              Log.e("Failed",t.message.toString())
            }

        })
    }
}