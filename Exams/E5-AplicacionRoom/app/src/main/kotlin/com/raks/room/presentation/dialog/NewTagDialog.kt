package com.raks.room.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.raks.room.R
import com.raks.room.databinding.DialogNewTagBinding

class NewTagDialog : DialogFragment() {

    interface NewTagDialogListener {
        fun onDialogOk(newTag: String)
    }

    private lateinit var listener: NewTagDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogNewTagBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext())
            .setMessage(R.string.manage_tags)
            .setView(binding.root)
            .setNegativeButton(getString(R.string.cancel), null)
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                listener.onDialogOk(binding.tag.text.toString())
            }
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as NewTagDialogListener
    }

}