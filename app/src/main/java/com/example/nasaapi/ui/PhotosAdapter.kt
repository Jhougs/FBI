package com.example.nasaapi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapi.R
import com.example.nasaapi.databinding.PhotoListItemBinding
import com.example.nasaapi.ui.model.Item
import com.squareup.picasso.Picasso

class PhotosAdapter (
    private val onItemClicked: (Item) -> Unit,
    ): RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

        private  var listPhoto: MutableList<Item> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PhotosAdapter.ViewHolder {
            val view= LayoutInflater.from(parent.context).inflate(R.layout.photo_list_item, parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder:PhotosAdapter.ViewHolder, position: Int) {

            holder.bind((listPhoto[position]))
            holder.itemView.setOnClickListener { onItemClicked(listPhoto[position]) }
        }

        override fun getItemCount(): Int =  listPhoto.size


        fun appendItems(newItems: MutableList<Item>){
            listPhoto.clear()
            listPhoto.addAll(newItems)
            notifyDataSetChanged()
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val binding = PhotoListItemBinding.bind(view)
            private val context: Context = binding.root.context
            fun bind(item: Item){
                with(binding){
                    //nameTextView.text= item.files?.get(0)?.name.toString()
                    nameTextView.text= context.getString(R.string.alias_info,item.title.toString())
                    if(item.rewardText != null){
                    edadTextView.text= context.getString(R.string.date_info,item.rewardText)
                    }else{
                        edadTextView.text= context.getString(R.string.no_description)
                    }
                    var foto= item.images?.get(0)?.large.toString()
                    if(foto !=null){
                      Picasso.get().load(foto.toString()).into(image);
                    }
                }

            }
        }
}