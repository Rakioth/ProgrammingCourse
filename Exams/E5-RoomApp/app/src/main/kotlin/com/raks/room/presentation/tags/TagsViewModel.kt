package com.raks.room.presentation.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.room.domain.usecase.TagsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagsViewModel @Inject constructor(
    private val tagsUseCases: TagsUseCases
) : ViewModel() {

    val state = tagsUseCases
        .getTagsWithNotes()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onEvent(event: TagsEvent) {
        when (event) {

            is TagsEvent.SaveTag   -> {
                viewModelScope.launch {
                    if (event.tag.tid > 0)
                        tagsUseCases.updateTag(event.tag)
                    else
                        tagsUseCases.insertTag(event.tag)
                }
            }

            is TagsEvent.DeleteTag -> {
                viewModelScope.launch {
                    tagsUseCases.deleteTagAndCrossReferences(event.tag)
                }
            }

        }
    }

}