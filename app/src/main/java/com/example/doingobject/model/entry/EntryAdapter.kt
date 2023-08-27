package com.example.doingobject.model.entry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doingobject.R

class EntryAdapter(private val items: MutableList<EntryEntity>) :
    RecyclerView.Adapter<EntryAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder 내부의 뷰들을 초기화하는 코드 추가

        // currentItem에서 데이터를 가져와서 뷰에 설정하는 메소드 추가
        fun bind(item: EntryEntity) {
            // 여기서 각 뷰에 데이터를 설정하는 작업을 수행하면 됩니다.
            // 예시:
            itemView.findViewById<TextView>(R.id.entryId).text = item.id
            itemView.findViewById<TextView>(R.id.entryQuantity).text = item.quantity.toString()


            // 다른 뷰 설정도 위와 비슷하게 처리하면 됩니다.
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
    fun addItem(item: EntryEntity) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun getItems(): List<EntryEntity> {
        return items
    }
    fun setItems(newItems: List<EntryEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
