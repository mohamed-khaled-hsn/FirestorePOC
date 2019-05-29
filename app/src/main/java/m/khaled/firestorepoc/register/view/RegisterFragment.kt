package m.khaled.firestorepoc.register.view


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_register.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.helpers.extensions.getProgressDialog
import m.khaled.firestorepoc.helpers.extensions.navigate
import m.khaled.firestorepoc.helpers.extensions.showToast
import m.khaled.firestorepoc.register.model.RegistrationFields
import m.khaled.firestorepoc.register.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        subscribeToDataLoadingEvent()
        subscribeToErrorMessageEvent()
        subscribeToRegistrationCompletedEvent()
        return view
    }

    private fun subscribeToRegistrationCompletedEvent() {
        viewModel.registrationCompletedLiveEvent.observe(viewLifecycleOwner, Observer {
            navigate(R.id.action_cars)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_register.setOnClickListener {
            if (::viewModel.isInitialized) {
                viewModel.register(
                    RegistrationFields(
                        edt_name.text.toString(),
                        edt_email.text.toString(),
                        edt_password.text.toString()
                    )
                )
            } else
                Log.i("RegisterFragment", "ViewModel not initialized")
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
    }
}
