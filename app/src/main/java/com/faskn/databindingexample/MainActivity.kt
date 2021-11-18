package com.faskn.databindingexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.faskn.databindingexample.databinding.ActivityMainBinding
import com.faskn.databindingexample.ext.afterTextChanged
import com.faskn.databindingexample.model.FormValidationErrorTags

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.initData()

        binding.buttonContinue.setOnClickListener {
            viewModel.submitForm()
        }

        viewModel.getFormViewStateLiveData().observe(this, { viewState ->
            setFormViewState(viewState)
        })

        binding.textInputEditTextName.afterTextChanged {
            viewModel.clearFormErrors(
                FormValidationErrorTags.INVALID_NAME
            )
        }
        binding.textInputEditTextSurname.afterTextChanged {
            viewModel.clearFormErrors(
                FormValidationErrorTags.INVALID_SURNAME
            )
        }
        binding.textInputEditTextAge.afterTextChanged {
            viewModel.clearFormErrors(
                FormValidationErrorTags.INVALID_AGE
            )
        }
    }

    private fun setFormViewState(viewState: MainFormViewState) {
        binding.formViewState = viewState
        binding.executePendingBindings()
    }
}
