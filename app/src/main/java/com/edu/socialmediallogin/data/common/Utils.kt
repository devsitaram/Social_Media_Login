package com.edu.socialmediallogin.data.common

import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem
import com.edu.socialmediallogin.domain.model.SubjectModel

//fun SubjectDTO.toDomainModel(): SubjectModel {
//    return SubjectModel(
//        imageUrl = this.previewURL,
//        title = this.tags,
//        urlDescriptions = this.userImageURL
//    )
//}

fun SubjectItem.toDomainModel(): SubjectModel {
    return SubjectModel(
        imageUrl = this.photoUrl,
        title = this.name,
        urlDescriptions = this.description
    )
}

enum class RegistrationState {
    INITIAL, LOADING, SUCCESS, ERROR
}