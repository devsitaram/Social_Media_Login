package com.edu.socialmediallogin.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.domain.use_case.GetSearchSubjectUseCase
import com.edu.socialmediallogin.presentation.state.SubjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(private val getSearchSubjectUseCase: GetSearchSubjectUseCase) : ViewModel() {

    private val _imageList = mutableStateOf(SubjectState())
    val imageList: State<SubjectState> get() = _imageList

    private val _query = MutableStateFlow("")

    init {
        getSearchImage("flower")
        viewModelScope.launch {
            _query.debounce(1000).collectLatest {
                getSearchImage(query = it)
            }
        }
    }

    fun updateQuery(query: String) {
        _query.value = query
    }

    private fun getSearchImage(query: String) {
        getSearchSubjectUseCase(query).onEach {
            when (it) {
                is Resource.Loading -> {
                    _imageList.value = SubjectState(isLoading = true)
                }

                is Resource.Success -> {
                    _imageList.value = SubjectState(data = it.data)
                }

                is Resource.Error -> {
                    _imageList.value = SubjectState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}