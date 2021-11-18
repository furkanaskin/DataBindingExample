package com.faskn.databindingexample

import android.util.Log
import androidx.lifecycle.*
import com.faskn.databindingexample.ext.orFalse
import com.faskn.databindingexample.model.FormValidationErrorModel
import com.faskn.databindingexample.model.FormValidationErrorTags

class MainViewModel : ViewModel() {

    private val formViewStateLiveData: MutableLiveData<MainFormViewState> = MutableLiveData()

    fun getFormViewStateLiveData(): LiveData<MainFormViewState> =
        formViewStateLiveData

    private fun setFormViewStateLiveData(viewState: MainFormViewState) {
        formViewStateLiveData.value = viewState
    }

    fun initData() {
        setFormViewStateLiveData(MainFormViewState())
    }

    private fun setFormError(error: FormValidationErrorModel) {
        formViewStateLiveData.value?.let { viewState ->
            val errors = viewState.formErrors.toMutableList().apply {
                if (this.contains(error).not()) {
                    add(error)
                }
            }
            setFormViewStateLiveData(viewState.copy(formErrors = errors))
        }
    }

    fun clearFormErrors(
        tag: FormValidationErrorTags
    ) {
        formViewStateLiveData.value?.let { viewState ->
            val current = viewState.formErrors
            val newList = current.toMutableList()
            if (newList.map { it.tags }.contains(tag)) {
                val index = newList.map { it.tags }.indexOf(tag)
                newList.removeAt(index)
            }
            setFormViewStateLiveData(viewState.copy(formErrors = newList))
        }
    }

    fun submitForm() {
        val formValues = formViewStateLiveData.value
        validateForm()
        Log.v("FormSample", "Name : ${formValues?.name}")
        Log.v("FormSample", "Surname : ${formValues?.surname}")
        Log.v("FormSample", "Age : ${formValues?.age}")
    }

    private fun validateForm() {
        val formValues = formViewStateLiveData.value
        when {
            formValues?.name?.contains(Regex("[^A-Za-z]")).orFalse() -> setFormError(
                FormValidationErrorModel(
                    FormValidationErrorTags.INVALID_NAME,
                    "Invalid name"
                )
            )
            formValues?.surname?.contains(Regex("[^A-Za-z]")).orFalse() -> setFormError(
                FormValidationErrorModel(
                    FormValidationErrorTags.INVALID_SURNAME,
                    "Invalid surname"
                )
            )
            formValues?.age?.toIntOrNull() ?: 0 <= 0 -> setFormError(
                FormValidationErrorModel(
                    FormValidationErrorTags.INVALID_AGE,
                    "Invalid age"
                )
            )
        }
    }
}