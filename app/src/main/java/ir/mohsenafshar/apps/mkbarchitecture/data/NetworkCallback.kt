package ir.mohsenafshar.apps.mkbarchitecture.data

interface NetworkCallback<T> {
    fun onResponse(data: T)
    fun onFailure(t: Throwable)
}