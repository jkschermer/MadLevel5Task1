package com.hva.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hva.madlevel5task1.R
import com.hva.madlevel5task1.model.NoteViewModel
import kotlinx.android.synthetic.main.fragment_notepad.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NotepadFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAddNoteResult()
    }

    private fun observeAddNoteResult() {
        viewModel.note.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                tvNoteTitle.text = it.title
                tvUpdatedTime.text =
                    getString(R.string.notepad_updated_time, it.lastUpdated.toString())
                    tvNoteText.text = it.text
            }
        })
    }
}