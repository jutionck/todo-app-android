package com.example.sample_retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sample_retrofit.data.config.ApiClient
import com.example.sample_retrofit.data.api.response.LoginResponse
import com.example.sample_retrofit.data.model.LoginModel
import com.example.sample_retrofit.databinding.FragmentLoginBinding
import com.example.sample_retrofit.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiClient = ApiClient()
        sessionManager = SessionManager(requireContext())
        binding.apply {
            btnLogin.setOnClickListener {
                apiClient
                    .getApiService(requireContext())
                    .login(
                        LoginModel(teUsername.text.toString(), tePassword.text.toString()))
                    .enqueue(object : Callback<LoginResponse> {

                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val loginResponse = response.body()
                            Log.i("TODOS", "loginResponse: ${loginResponse.toString()}")
//                            if (loginResponse != null) {
//                                Log.i("TODOS", loginResponse.token.token)
//                                sessionManager
//                                    .saveAuthToken(
//                                        loginResponse.token.token,
//                                        teUsername.text.toString()
//                                    )
//                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Log.i("TODOS", "Login failure ${t.localizedMessage}")
                        }

                    })
//                findNavController().navigate(R.id.action_loginFragment_to_todoFragment)
            }
        }
    }

}