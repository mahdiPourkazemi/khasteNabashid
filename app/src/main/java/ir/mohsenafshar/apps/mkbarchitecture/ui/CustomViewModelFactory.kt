package ir.mohsenafshar.apps.mkbarchitecture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.mohsenafshar.apps.mkbarchitecture.data.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.RemoteDataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.RetrofitApiProvider
import ir.mohsenafshar.apps.mkbarchitecture.ui.users.UserViewModel

class CustomViewModelFactory(/*TODO: private val userRepository: Repository*/): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            val dataSource = RemoteDataSource(RetrofitApiProvider.userApi)
            val userRepository = UserRepository(dataSource)
            UserViewModel(userRepository) as T
        } else {
            modelClass.newInstance()
        }
    }
}