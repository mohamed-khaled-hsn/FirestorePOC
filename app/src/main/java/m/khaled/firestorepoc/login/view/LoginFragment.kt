package m.khaled.firestorepoc.login.view


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.helpers.extensions.getProgressDialog
import m.khaled.firestorepoc.helpers.extensions.navigate
import m.khaled.firestorepoc.helpers.extensions.showToast
import m.khaled.firestorepoc.login.viewmodel.LoginViewModel


class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (FirebaseAuth.getInstance().currentUser != null) {
            navigate(R.id.action_loginFragment_to_carsFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_register))

        btn_login.setOnClickListener { viewModel.login(edt_email.text.toString(), edt_password.text.toString()) }

        subscribeToLoggedInEvent()
        subscribeToErrorMessageEvent()
        subscribeToDataLoadingEvent()
    }

    private fun subscribeToLoggedInEvent() {
        viewModel.signedInLiveEvent.observe(viewLifecycleOwner, Observer {
            navigate(R.id.action_loginFragment_to_carsFragment)
        })
    }

    private fun subscribeToErrorMessageEvent() {
        viewModel.errorMessageLiveEvent.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })
    }

    private fun subscribeToDataLoadingEvent() {
        val progressDialog = getProgressDialog()
        viewModel.dataLoading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading)
                progressDialog.show()
            else
                progressDialog.hide()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear() //hide menu
    }
}
