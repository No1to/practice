package com.example.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.App
import com.example.practice.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val adapter = NoteAdapter(this::onLongClick, this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        setData()
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.noteFragment)
        }
    }

    private fun setData() {
        val data = App.db.noteDao().getAll()
        adapter.submitList(data)
    }

    private fun onLongClick(note: NotesModel) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Удалить?")
        alertDialog.setMessage("Вы уверены, что хотите удалить задачу?")
        alertDialog.setNegativeButton("Нет") { dialog, _ ->
            dialog.cancel()
        }
        alertDialog.setPositiveButton("Да") { dialog, _ ->
            App.db.noteDao().delete(note)
            setData()
            dialog.dismiss()
        }
        alertDialog.create().show()
    }

    private fun onClick(note: NotesModel) {
        findNavController().navigate(R.id.noteFragment, bundleOf(NOTE_EDIT_KEY to note))
    }

    companion object {
        const val NOTE_EDIT_KEY = "task.edit.key"
    }

}