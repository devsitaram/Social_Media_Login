package com.edu.socialmediallogin.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.domain.use_case.SubjectUseCase
import com.edu.socialmediallogin.presentation.state.SubjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(private val subjectUseCase: SubjectUseCase) :
    ViewModel() {

    private var _subjectList = mutableStateOf(SubjectState())
    val subjectList: State<SubjectState> get() = _subjectList

    fun getSubject() {
        subjectUseCase().onEach {
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

    fun insertSubject(subjectEntity: List<SubjectEntity>) {
        subjectUseCase(listOfSubject = subjectEntity).launchIn(viewModelScope)
    }

    fun deleteSubject(id: Int) {
        subjectUseCase(id).launchIn(viewModelScope)
    }
}

//fun insertSubject(
//    photoUrl: String?,
//    name: String?,
//    description: String?,
//    isIvy: Boolean?
//) {
//    getSubjectUseCase(photoUrl, name, description, isIvy).onEach {
//        when (it) {
//            is Resource.Loading -> {
//                _subjectList.value = SubjectState(isLoading = true)
//            }
//
//            is Resource.Success -> {
//                _subjectList.value = SubjectState(isData = it.data)
//            }
//
//            is Resource.Error -> {
//                _subjectList.value = SubjectState(isError = it.message.toString())
//            }
//        }
//    }.launchIn(viewModelScope)
//}
//
//
//fun deleteSubject(id: Int) {
//    getSubjectUseCase(id).onEach {
//        when (it) {
//            is Resource.Loading -> {
//                _subjectList.value = SubjectState(isLoading = true)
//            }
//
//            is Resource.Success -> {
//                _subjectList.value = SubjectState(isSuccess = true)
//            }
//
//            is Resource.Error -> {
//                _subjectList.value = SubjectState(isError = it.message.toString())
//            }
//        }
//    }.launchIn(viewModelScope)
//}