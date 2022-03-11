package ir.mohsenafshar.apps.mkbarchitecture.data

import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import kotlin.random.Random

object Mapper {
    fun transformToUser(userResponse: UserResponse): User {
        return User(
            id = userResponse._id,
            firstName = userResponse.firstName,
            lastName = userResponse.lastName,
            nationalCode = userResponse.nationalCode
        )
    }
    fun transformToHobie(userResponse: UserResponse): List<Hobie> {
        return userResponse.hobbies?.map {
            Hobie(Random.nextInt(),it,userResponse._id)
        }?: emptyList()
    }

    fun transformToUserResponse(user:User, hobbies: List<Hobie>): UserResponse {
        return UserResponse(
            _id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            nationalCode = user.nationalCode,
            hobbies.map {
                it.name
            }
        )
    }
}