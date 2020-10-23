package com.hva.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hva.madlevel5task1.R
import com.hva.madlevel5task1.model.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note_add.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddNoteFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveBtn.setOnClickListener {
            saveNote()
        }

        observeNote()
    }

    private fun observeNote() {
        viewModel.note.observe(viewLifecycleOwner, Observer {
            note ->
                note?.let {
                    textInputLayoutTitle.editText?.setText(it.title)
                    textInputLayoutNotes.editText?.setText(it.text)
                }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer {
            success ->
            findNavController().popBackStack()
        })
    }

    private fun saveNote() {
        viewModel.updateNote(textInputLayoutTitle.editText?.text.toString(),
            textInputLayoutNotes.editText?.text.toString())
    }
}