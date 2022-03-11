package ir.mohsenafshar.apps.mkbarchitecture.data

import ir.mohsenafshar.apps.mkbarchitecture.data.model.User
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse

object Mapper {
    fun transformToUser(userResponse: UserResponse): User {
        return User(
            id = userResponse._id,
            firstName = userResponse.firstName,
            lastName = userResponse.lastName,
            nationalCode = userResponse.nationalCode
        )
    }
}