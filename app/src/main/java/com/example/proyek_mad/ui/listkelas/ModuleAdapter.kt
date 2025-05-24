package com.example.proyek_mad.ui.listkelas

import androidx.recyclerview.widget.DiffUtil

class ModuleDiffUtil : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.nrp == newItem.nrp
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }
}
val studentDiffUtil = StudentDiffUtil()
