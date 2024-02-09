package com.example.practice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.practice.databinding.ItemNoteBinding

class NoteAdapter(
    private val onLongClick: (NotesModel) -> Unit,
    private val onClick: (NotesModel) -> Unit
) : ListAdapter<NotesModel, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        fun bind(note: NotesModel) = with(binding) {
            tvTitle.text = note.title
            tvDescription.text = note.description
            itemNote.setOnLongClickListener {
                onLongClick(note)
                false
            }
            itemNote.setOnClickListener {
                onClick(note)
            }
        }
    }

}

class NoteDiffCallback : DiffUtil.ItemCallback<NotesModel>() {

    override fun areItemsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
        return oldItem.noteId == newItem.noteId
    }

    override fun areContentsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
        return oldItem == newItem
    }

}