// StudentAdapter.kt
package com.example.studentman3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentAdapter(
    context: Context,
    private val students: List<StudentModel>
) : ArrayAdapter<StudentModel>(context, R.layout.item_student, students) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_student, parent, false)

        val student = students[position]
        view.findViewById<TextView>(R.id.text_view_name).text = student.studentName
        view.findViewById<TextView>(R.id.text_view_id).text = student.studentId

        return view
    }
}