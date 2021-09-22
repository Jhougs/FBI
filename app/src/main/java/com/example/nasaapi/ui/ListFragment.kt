package com.example.nasaapi.ui
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasaapi.databinding.FragmentListBinding
import com.example.nasaapi.server.ApiService
import com.example.nasaapi.ui.model.FBI
import com.example.nasaapi.ui.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListFragment : Fragment() {
    private  var _binding: FragmentListBinding?= null
    private val binding get() = _binding!!
    private lateinit var PhotoAdapter: PhotosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentListBinding.inflate(inflater, container, false)
        val root : View= binding.root

        PhotoAdapter = PhotosAdapter(onItemClicked = {onMovieItemClicked(it)})

        binding.recyclerView.apply {
            layoutManager= LinearLayoutManager(this@ListFragment.context)
            adapter= PhotoAdapter
            setHasFixedSize(false)
        }

        loadPhoto()
        return root


    }

    private fun loadPhoto() {
        val apiKey= "ja4wFodwBYMtqegNJmLhas2nG3TgVDpBpBgcKffj"
        ApiService.create()
            .getTopRated(apiKey)
            .enqueue(object : Callback<FBI> {
                override fun onFailure(call: Call<FBI>, t: Throwable) {
                    Log.d("error", t.message.toString())
                }

                override fun onResponse(call: Call<FBI>, response: Response<FBI>) {
                    if(response.isSuccessful){
                        var listMovies: MutableList<Item> = response.body()?.items as MutableList<Item>
                        PhotoAdapter.appendItems(listMovies)
                    }
                }
            })
    }

    private fun onMovieItemClicked(item: Item) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(item = item))

    }

}