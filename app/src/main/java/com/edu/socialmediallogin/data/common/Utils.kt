package com.edu.socialmediallogin.data.common


// check the username validation
fun emailValidation(email: String): Boolean {
    // get text fields text
    val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+.+[a-z]+")
    return email.matches(emailPattern)
}

// check the username validation
fun nameValidation(username: String): Boolean {
    val nameRegex = Regex("[A-Za-z\\s]+")
    return username.matches(nameRegex)
}

// check the username validation
fun passwordValidation(password: String): Boolean {
    val nameRegex = Regex("[a-zA-Z0-9]")
    return password.matches(nameRegex)
}


//fun SubjectDTO.toDomainModel(): SubjectModel {
//    return SubjectModel(
//        imageUrl = this.previewURL,
//        title = this.tags,
//        urlDescriptions = this.userImageURL
//    )
//}

enum class RegistrationState {
    INITIAL, LOADING, SUCCESS, ERROR
}