package com.shist.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shist.domain.BuildingItemImage
import com.shist.domain.ScientistItem
import com.shist.ui.databinding.ScientistItemBinding

class ScientistsAdapter(val onScientistClick: (ScientistItem) -> Unit) : ListAdapter<ScientistItem, ScientistsAdapter.ItemViewHolder>(ScientistItemDiffCallback()){

    class ItemViewHolder(itemBinding: ScientistItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        val image: ImageView
        val fullName: TextView

        init {
            image = itemBinding.image
            fullName = itemBinding.fullName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ScientistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val scientist = getItem(position)
        holder.fullName.text = scientist.fullName
        holder.itemView.setOnClickListener { onScientistClick(scientist) }
        //add logic to image
    }

}

class ScientistItemDiffCallback : DiffUtil.ItemCallback<ScientistItem>() {
    override fun areItemsTheSame(oldItem: ScientistItem, newItem: ScientistItem): Boolean {
        return oldItem.fullName == newItem.fullName
    }

    override fun areContentsTheSame(oldItem: ScientistItem, newItem: ScientistItem): Boolean {
        return oldItem == newItem
    }
}