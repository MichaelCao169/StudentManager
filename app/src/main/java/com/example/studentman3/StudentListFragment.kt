// StudentListFragment.kt
package com.example.studentman3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {
    private lateinit var studentList: MutableList<StudentModel>
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentList = StudentDatabase.getStudents()

        val listView = view.findViewById<ListView>(R.id.listview_students)
        adapter = StudentAdapter(requireContext(), studentList)
        listView.adapter = adapter

        listView.setOnItemLongClickListener { _, view, position, _ ->
            showContextMenu(view, position)
            true
        }
    }

    private fun showContextMenu(view: View, position: Int) {
        PopupMenu(requireContext(), view).apply {
            menuInflater.inflate(R.menu.context_menu, menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_edit -> {
                        val bundle = Bundle().apply {
                            putInt("position", position)
                            putString("name", studentList[position].studentName)
                            putString("id", studentList[position].studentId)
                        }
                        findNavController().navigate(
                            R.id.action_studentListFragment_to_editStudentFragment,
                            bundle
                        )
                        true
                    }
                    R.id.action_remove -> {
                        studentList.removeAt(position)
                        adapter.notifyDataSetChanged()
                        true
                    }
                    else -> false
                }
            }
            show()
        }
    }
}
