package ir.mohsenafshar.apps.mkbarchitecture.di

import android.app.Application
import ir.mohsenafshar.apps.mkbarchitecture.data.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.local.LocalDataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.local.db.AppDataBase
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.RemoteDataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.RetrofitApiProvider
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ServiceLocator(application: Application) {
    private val remoteDataSource = RemoteDataSource(RetrofitApiProvider.userApi)
    private val localDataSource = LocalDataSource(AppDataBase.getDatabase(application).userDao())
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    val userRepository = UserRepository(executorService, remoteDataSource, localDataSource)
}