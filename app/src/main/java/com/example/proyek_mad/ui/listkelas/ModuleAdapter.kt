package com.example.proyek_mad.ui.listkelas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.databinding.ItemModuleCardBinding

class ModuleDiffUtil : DiffUtil.ItemCallback<Module>() {
    override fun areItemsTheSame(oldItem: Module, newItem: Module): Boolean {
        return oldItem.kelas_id == newItem.kelas_id
    }

    override fun areContentsTheSame(oldItem: Module, newItem: Module): Boolean {
        return oldItem == newItem
    }
}
val moduleDiffUtil = ModuleDiffUtil()

class ModuleAdapter (
    private val items: List<Module>,
    private val onItemClick: (Module) -> Unit) :
    RecyclerView.Adapter<ModuleAdapter.ViewHolder>() {
    class ViewHolder(val binding:ItemModuleCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemModuleCardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module = items[position]

    }

}
