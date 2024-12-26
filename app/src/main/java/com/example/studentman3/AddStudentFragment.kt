// AddStudentFragment.kt
package com.example.studentman3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AddStudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_save).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.edit_text_name).text.toString()
            val id = view.findViewById<EditText>(R.id.edit_text_id).text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                StudentDatabase.addStudent(StudentModel(name, id))
                findNavController().navigateUp()
            }
        }
    }
}