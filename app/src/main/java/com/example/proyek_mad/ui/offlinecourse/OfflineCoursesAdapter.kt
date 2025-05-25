package com.example.proyek_mad.ui.offlineofflineCourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.databinding.ItemOfflinecourseCardBinding

class OfflineCourseDiffUtil : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.kelas_id == newItem.kelas_id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}
val offlineCourseDiffUtil = OfflineCourseDiffUtil()

class OfflineCoursesAdapter ():ListAdapter<Course, OfflineCoursesAdapter.ViewHolder>(offlineCourseDiffUtil) {
    var onItemClickListener:((Course)->Unit)? = null
    var onDeleteClickListener:((Course)->Unit)? = null
    class ViewHolder(val binding:ItemOfflinecourseCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOfflinecourseCardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offlineCourse = getItem(position)
        holder.binding.txtCourseTitleCard.setText(offlineCourse.nama_kelas.toString())
        holder.binding.txtCourseDescCard.setText(offlineCourse.deskripsi_kelas.toString())
        holder.binding.root.setOnClickListener{
            MockDB.selectedKelas = offlineCourse.kelas_id
            onItemClickListener?.invoke(offlineCourse)
        }
        holder.binding.ibDeleteBtn.setOnClickListener{
            onDeleteClickListener?.invoke(offlineCourse)
        }
    }

}
