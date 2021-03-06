package ir.mohsenafshar.apps.mkbarchitecture.ui.users

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.mohsenafshar.apps.mkbarchitecture.App
import ir.mohsenafshar.apps.mkbarchitecture.R
import ir.mohsenafshar.apps.mkbarchitecture.data.Mapper
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.databinding.FragmentUserBinding
import ir.mohsenafshar.apps.mkbarchitecture.di.ServiceLocator
import ir.mohsenafshar.apps.mkbarchitecture.ui.CustomViewModelFactory

class UserFragment : Fragment(R.layout.fragment_user) {

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.userRepository)
    })

    private var listUsers = mutableListOf<String>()
    private var search = mutableListOf<String>()

    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)

        initViews()
        initClickListeners()
        getUserWithHobbies()


        val listUserResponse: List<UserResponse> = readFromAsset(requireContext())
        val userList = listUserResponse.map {
            Mapper.transformToUser(it)
        }
        viewModel.saveUserList(userList)
       val hobieList = listUserResponse.flatMap {
           Mapper.transformToHobie(it)
       }
        viewModel.saveHobie(hobieList)
//        initObserving()
//        getUserData()


    }

    private fun getUserWithHobbies() {
        viewModel.getAllUserWithHobbies().observe(viewLifecycleOwner){
            Log.d("HOBBIES", it.toString())
        }
    }


    private fun getUserData() {
        viewModel.getUsers()
    }

    private fun initViews() {
        recyclerAdapter = RecyclerAdapter(listUsers)
        binding.rc.layoutManager = LinearLayoutManager(requireContext())
        binding.rc.adapter = recyclerAdapter
    }

    private fun initClickListeners() {
        binding.btsearch.setOnClickListener {
            viewModel.getUserFromFirstName(binding.edsearch.text.toString())
        }
    }

    private fun initObserving() {
        viewModel.listUsers.observe(viewLifecycleOwner) {
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdapter.notifyItemRangeInserted(0, it.size)
        }

        viewModel.searchResult.observe(viewLifecycleOwner) {
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdapter.notifyItemRangeInserted(0, it.size)
        }
    }
}