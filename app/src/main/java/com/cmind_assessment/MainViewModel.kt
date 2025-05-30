package com.cmind_assessment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val inputText = MutableLiveData<String>("")
    private val _outputText = MutableLiveData<String>("")
    val outputText: LiveData<String> = _outputText

    private val _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> = _errorMessage

    private fun isValidInput(text: String?): Boolean {
        if (text.isNullOrEmpty()) return true // empty allowed, no error
        // Regex for alphanumeric and spaces only
        val regex = Regex("^[a-zA-Z0-9 ]*$")
        return regex.matches(text)
    }

    private fun validateInput(): Boolean {
        val text = inputText.value ?: ""
        return if (isValidInput(text)) {
            _errorMessage.value = ""
            true
        } else {
            _errorMessage.value = "Invalid input: only letters, numbers and spaces allowed"
            false
        }
    }

    fun onLengthClick() {
        if (!validateInput()) return

        val text = inputText.value?.trim() ?: ""
        _outputText.value = if (text.isBlank()) "0" else text.length.toString()
    }

    fun onReverseClick() {
        if (!validateInput()) return

        val text = inputText.value ?: ""
        _outputText.value = if (text.isBlank()) "" else text.reversed()
    }

    fun onAppendClick() {
        if (!validateInput()) return

        val text = inputText.value ?: ""
        _outputText.value = "Hello $text"
    }

    fun onNumericClick() {
        if (!validateInput()) return

        val text = inputText.value ?: ""
        _outputText.value = if (text.matches(Regex("^\\d+$"))) "Yes" else "No"
    }

    fun onClearClick() {
        inputText.value = ""
        _outputText.value = ""
        _errorMessage.value = ""
    }
}

