package com.edu.socialmediallogin.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.domain.use_case.GetSubjectUseCase
import com.edu.socialmediallogin.presentation.state.SubjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(private val getSubjectUseCase: GetSubjectUseCase) :
    ViewModel() {

    private var _subjectList = mutableStateOf(SubjectState())
    val subjectList: State<SubjectState> get() = _subjectList

    init {
        getSubject()
    }

    private fun getSubject() {
        getSubjectUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _subjectList.value = SubjectState(isLoading = true)
                }

                is Resource.Success -> {
                    _subjectList.value = SubjectState(isData = it.data)
                }

                is Resource.Error -> {
                    _subjectList.value = SubjectState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertSubject(
        photoUrl: String?,
        name: String?,
        description: String?,
        isIvy: Boolean?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            getSubjectUseCase(photoUrl, name, description, isIvy).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _subjectList.value = SubjectState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _subjectList.value = SubjectState(isSuccess = true)
                    }

                    is Resource.Error -> {
                        _subjectList.value = SubjectState(isError = it.message.toString())
                    }
                }
            }.launchIn(viewModelScope)
        }
        getSubject()
    }

    fun deleteSubject(subject: SubjectEntity) {
        getSubjectUseCase(subject).onEach {
            when (it) {
                is Resource.Loading -> {
                    _subjectList.value = SubjectState(isLoading = true)
                }

                is Resource.Success -> {
                    _subjectList.value = SubjectState(isSuccess = true)
                }

                is Resource.Error -> {
                    _subjectList.value = SubjectState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
//    init {
//        getSearchImage("flower")
//        viewModelScope.launch {
//            _query.debounce(1000).collectLatest {
//                getSearchImage(query = it)
//            }
//        }
//    }
//
//    fun updateQuery(query: String) {
//        _query.value = query
//    }
//
//    private fun getSearchImage(query: String) {
//        getSearchSubjectUseCase(query).onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _imageList.value = SubjectState(isLoading = true)
//                }
//
//                is Resource.Success -> {
//                    _imageList.value = SubjectState(data = it.data)
//                }
//
//                is Resource.Error -> {
//                    _imageList.value = SubjectState(error = it.message.toString())
//                }
//            }
//        }.launchIn(viewModelScope)
//    }