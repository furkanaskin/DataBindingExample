package com.faskn.databindingexample

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.faskn.databindingexample.model.FormValidationErrorModel
import com.faskn.databindingexample.model.FormValidationErrorTags

@Suppress("ConstructorParameterNaming")
data class MainFormViewState(
    private var _name: String = "",
    private var _surname: String = "",
    private var _age: String = "",
    var formErrors: MutableList<FormValidationErrorModel> = mutableListOf()
) : BaseObservable() {

    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }
    var surname: String
        @Bindable get() = _surname
        set(value) {
            _surname = value
            notifyPropertyChanged(BR.surname)
        }
    var age: String
        @Bindable get() = _age
        set(value) {
            _age = value
            notifyPropertyChanged(BR.age)
        }

    @Bindable
    fun isContinueButtonEnabled(): Boolean {
        notifyPropertyChanged(BR.continueButtonEnabled)
        return name.isEmpty().not() &&
                surname.isEmpty().not() &&
                age.isEmpty().not() &&
                formErrors.isEmpty()
    }
}