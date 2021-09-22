package com.example.nasaapi.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.nasaapi.R
import com.example.nasaapi.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private  val args: DetailFragmentArgs by navArgs()
    private  var _binding: FragmentDetailBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailBinding.inflate(inflater,container,false)
        val root: View= binding.root



        with(binding){
            val item= args.item
            val context: Context = binding.root.context

            NameTextView.text= context.getString(R.string.alias_info,item?.title.toString())
            hairTextView.text=context.getString(R.string.hair_info,item?.hair.toString())
            eyesTextview.text=context.getString(R.string.eyes_info,item?.eyes.toString())
            nationalityTextView.text=context.getString(R.string.nationality_info, item?.nationality.toString())

            var foto= item?.images?.get(0)?.large.toString()
            if(foto !=null){
                Picasso.get().load(foto.toString()).into(imageView);
            }



        }

        return root

    }


}