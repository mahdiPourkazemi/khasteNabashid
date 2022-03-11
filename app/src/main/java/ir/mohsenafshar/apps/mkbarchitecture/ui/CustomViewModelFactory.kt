package ir.mohsenafshar.apps.mkbarchitecture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.mohsenafshar.apps.mkbarchitecture.data.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.local.LocalDataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.local.db.AppDataBase
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.RemoteDataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.RetrofitApiProvider
import ir.mohsenafshar.apps.mkbarchitecture.ui.users.UserViewModel
import java.util.concurrent.Executors

class CustomViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel(userRepository) as T
        } else {
            modelClass.newInstance()
        }
    }
}