// EditStudentFragment.kt
package com.example.studentman3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class EditStudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("position") ?: return
        val name = arguments?.getString("name") ?: return
        val id = arguments?.getString("id") ?: return

        view.findViewById<EditText>(R.id.edit_text_name).setText(name)
        view.findViewById<EditText>(R.id.edit_text_id).setText(id)

        view.findViewById<Button>(R.id.button_update).setOnClickListener {
            val newName = view.findViewById<EditText>(R.id.edit_text_name).text.toString()
            val newId = view.findViewById<EditText>(R.id.edit_text_id).text.toString()

            if (newName.isNotEmpty() && newId.isNotEmpty()) {
                StudentDatabase.updateStudent(position, StudentModel(newName, newId))
                findNavController().navigateUp()
            }
        }
    }
}
