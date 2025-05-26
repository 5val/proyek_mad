package com.example.proyek_mad.ui.detailkelas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.ItemModuleCardBinding

class CourseDetailDiffUtil: DiffUtil.ItemCallback<Module>() {
    override fun areItemsTheSame(oldItem: Module, newItem: Module): Boolean {
        return oldItem.materi_id == newItem.materi_id
    }

    override fun areContentsTheSame(oldItem: Module, newItem: Module): Boolean {
        return oldItem == newItem
    }

}

val courseDetailDiffUtil = CourseDetailDiffUtil()

class CourseDetailAdapter:ListAdapter<Module, CourseDetailAdapter.ViewHolder>(courseDetailDiffUtil) {
    var onItemClickListener:((Module)->Unit)? = null

    class ViewHolder(val binding:ItemModuleCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemModuleCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materiku = getItem(position)
        holder.binding.txtModuleDescCard.setText(materiku.deskripsi_singkat.toString())
        holder.binding.txtModuleTitleCard.setText(materiku.judul_materi.toString())

        holder.binding.root.setOnClickListener{
            MockDB.selectedMateri = materiku.materi_id
            MockDB.selectedKelas = materiku.kelas_id

            onItemClickListener?.invoke(materiku)
        }

    }
}