package com.edu.socialmediallogin.data.common

import com.edu.socialmediallogin.data.source.remote.pojo.SubjectDTO
import com.edu.socialmediallogin.domain.model.SubjectModel

fun SubjectDTO.toDomainModel(): SubjectModel {
    return SubjectModel(
        imageUrl = this.previewURL
    )
}