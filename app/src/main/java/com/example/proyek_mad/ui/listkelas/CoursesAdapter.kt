package com.example.proyek_mad.ui.listkelas

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.databinding.ItemCourseCardBinding

class CourseDiffUtil : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.kelas_id == newItem.kelas_id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}
val courseDiffUtil = CourseDiffUtil()

class CoursesAdapter ():ListAdapter<Course, CoursesAdapter.ViewHolder>(courseDiffUtil) {
    var onItemClickListener:((Course)->Unit)? = null
    class ViewHolder(val binding:ItemCourseCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCourseCardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = getItem(position)
        holder.binding.txtCourseTitleCard.setText(course.nama_kelas.toString())
        holder.binding.txtCourseDescCard.setText(course.deskripsi_kelas.toString())

        holder.binding.root.setOnClickListener{
            onItemClickListener?.invoke(course)
        }
        holder.binding.txtCourseProgressCard.text = course.progress.toString()+"%"
        holder.binding.courseProgressBarCard.progress = course.progress
        if (course.attended == "belum"){
            Log.e("debug", course.nama_kelas+"is debug" )
            holder.binding.root.alpha = 0.5f
        }else {
            Log.e("debug", course.nama_kelas+" - "+course.attended )
            holder.binding.root.alpha = 1.0f
        }
        holder.binding.root.post{
            holder.binding.root.invalidate()
            holder.binding.root.requestLayout()
        }
    }

}
