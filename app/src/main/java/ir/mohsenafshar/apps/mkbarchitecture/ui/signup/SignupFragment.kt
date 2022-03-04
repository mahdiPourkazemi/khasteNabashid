package ir.mohsenafshar.apps.mkbarchitecture.ui.signup


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ir.mohsenafshar.apps.mkbarchitecture.R
import ir.mohsenafshar.apps.mkbarchitecture.data.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.databinding.FragmentSignupBinding

class SignupFragment : Fragment(R.layout.fragment_signup) {
    private val hobbies = listOf("Movie", "Game", "Sport")

    private val viewModelHome: ViewModelHome by viewModels()
    lateinit var binding: FragmentSignupBinding
    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)
        init()
        initObserving()
        goToListUser()
    }

    private fun goToListUser() {
        binding.getListUsers.setOnClickListener {
            navController.navigate(R.id.action_signupFragment_to_userFragment)
        }
    }

    private fun init() {
        with(binding) {
            homeBtn.setOnClickListener {
                val user = UserReqBody(
                    homeName.text.toString(),
                    hobbies,
                    homeFamily.text.toString(),
                    homeNationalCode.text.toString()
                )
                viewModelHome.createUser(user)

            }
        }
    }

    private fun initObserving() {
        viewModelHome.userId.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        })

        viewModelHome.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}
