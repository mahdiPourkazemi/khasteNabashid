package ir.mohsenafshar.apps.mkbarchitecture.data.model

data class UserResponse(
    val _id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String>? = null,
)