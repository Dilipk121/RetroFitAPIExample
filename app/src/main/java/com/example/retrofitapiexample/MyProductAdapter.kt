package com.example.retrofitapiexample

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyProductAdapter(val context:Activity, val productList:List<Product>) :
    RecyclerView.Adapter<MyProductAdapter.myViewHolder>() {

        class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            var title : TextView
            var price : TextView
            var description : TextView
            var thumbnail : ImageView

            init {

                title = itemView.findViewById(R.id.tv_title)
                price = itemView.findViewById(R.id.tv_price)
                description = itemView.findViewById(R.id.tv_description)
                thumbnail = itemView.findViewById(R.id.thumbnail)

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.each_items,parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.title.text = currentItem.title
        holder.price.text = currentItem.price.toString()
        holder.description.text = currentItem.description

        //image in string URL, now use 3rd party library picasso
        Picasso.get().load(currentItem.thumbnail).into(holder.thumbnail);
    }


}