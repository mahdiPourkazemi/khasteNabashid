package ir.mohsenafshar.apps.mkbarchitecture.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.mohsenafshar.apps.mkbarchitecture.data.model.Hobie
import ir.mohsenafshar.apps.mkbarchitecture.data.model.User
import java.util.concurrent.ExecutorService

class UserRepository(
    private val executorService: ExecutorService,
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource
) {

    companion object {
        const val TAG = "Repository"
    }

    fun saveHobieList(hobieList:List<Hobie>) {
        executorService.submit {
            localDataSource.saveHobieList(hobieList)
        }
    }
fun saveUserList(userList: List<User>){
    executorService.submit {
        localDataSource.saveUserList(userList)
    }

}
    fun getUserList(): LiveData<List<User>> {
        val liveData = MutableLiveData<List<User>>()

        executorService.submit {
            val remoteData: List<User> = remoteDataSource.getUserList()
            localDataSource.saveUserList(remoteData)
            liveData.postValue(remoteData)
        }

        return liveData
    }

}