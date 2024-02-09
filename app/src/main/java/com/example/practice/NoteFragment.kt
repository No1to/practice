package com.example.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.App
import com.example.practice.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val note = arguments?.getSerializable(FirstFragment.NOTE_EDIT_KEY) as? NotesModel
        if (note != null) {
            binding.btnSave.text = getString(R.string.update)
            binding.etTitle.setText(note.title)
            binding.etDescription.setText(note.description)
        }

        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text!!.isNotEmpty()) {
                if (note != null) {
                    update(note)
                } else save()
                findNavController().navigateUp()
            } else binding.etTitle.error = getString(R.string.title_empty_error)
        }

    }

    private fun update(note: NotesModel) {
        App.db.noteDao().update(
            note.copy(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString()
            )
        )
    }

    private fun save() {
        val data = NotesModel(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        App.db.noteDao().insert(data)
        findNavController().navigateUp()
    }

}