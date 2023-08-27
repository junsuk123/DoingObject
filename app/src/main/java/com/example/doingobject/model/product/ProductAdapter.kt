package com.example.doingobject.model.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doingobject.R

class ProductAdapter(private val items: MutableList<ProductEntity>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder 내부의 뷰들을 초기화하는 코드 추가

        // currentItem에서 데이터를 가져와서 뷰에 설정하는 메소드 추가
        fun bind(item: ProductEntity) {
            // 여기서 각 뷰에 데이터를 설정하는 작업을 수행하면 됩니다.
            // 예시:
            itemView.findViewById<TextView>(R.id.productName).text = item.name
            itemView.findViewById<TextView>(R.id.productDescription).text = item.description
            itemView.findViewById<TextView>(R.id.productQuantity).text = item.quantity.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem) // ViewHolder의 bind 메소드를 호출하여 데이터를 설정하는 코드 추가
    }

    override fun getItemCount(): Int {
        return items.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<ProductEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
