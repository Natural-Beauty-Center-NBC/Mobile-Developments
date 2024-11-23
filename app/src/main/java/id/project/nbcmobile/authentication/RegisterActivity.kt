package id.project.nbcmobile.authentication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import id.project.nbcmobile.databinding.ActivityRegisterBinding
import id.project.nbcmobile.view.components.customView.AlertDialogCustom
import id.project.nbcmobile.view.components.utils.ViewModelFactory
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<AuthenticationViewModel> {
        ViewModelFactory.getInstance(this@RegisterActivity)
    }
    private val alertDialog = AlertDialogCustom(this@RegisterActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isLoading.observe(this@RegisterActivity) {
            showLoading(it)
        }

        viewModel.registerResponse.observe(this@RegisterActivity) { response ->
            alertDialog.apply {
                create(
                    title = response.status,
                    message = response.message,
                    hasNegativeBtn = false,
                    onPositiveButtonClick = {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    },
                    onNegativeButtonClick = { /* Do Nothing */ }
                )
                show()
            }
        }

        viewModel.errorResponse.observe(this@RegisterActivity) { response ->
            alertDialog.apply {
                create(
                    title = response.status.toString(),
                    message = response.message.toString(),
                    hasNegativeBtn = false,
                    onPositiveButtonClick = { /* Do Nothing */ },
                    onNegativeButtonClick = { /* Do Nothing */ }
                )
                show()
            }
        }

        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding.apply {
            dateEditText.setOnClickListener {
                // Get current date
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // Show DatePicker
                val datePickerDialog = DatePickerDialog(this@RegisterActivity, { _, selectedYear, selectedMonth, selectedDay ->
                    // Format and set the selected date
                    val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    dateEditText.setText(selectedDate)
                }, year, month, day)
                datePickerDialog.show()
            }

            registerButton.setOnClickListener {
                binding.apply {
                    val name = namaEditText.text.toString().trim()
                    val email = emailEditText.text.toString().trim()
                    val dateOfBirth = dateEditText.text.toString().trim()
                    val address = addressEditText.text.toString().trim()
                    val numPhone = phoneEditText.text.toString().trim()
                    val allergy = allergyEditText.text.toString().trim()
                    val password = passworEditText.text.toString().trim()

                    val selectedRadioButton = findViewById<RadioButton>(genderRadioGroup.checkedRadioButtonId)
                    val gender = selectedRadioButton?.text.toString()

                    viewModel.register(
                        name = name,
                        email = email,
                        dateOfBirth = dateOfBirth,
                        gender = gender,
                        address = address,
                        numPhone = numPhone,
                        allergy = allergy,
                        password = password
                    )
                }
            }

            loginButton.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun playAnimation() {
        binding.apply {
            val title = ObjectAnimator.ofFloat(titleRegister, View.ALPHA, 1f).setDuration(100)
            val subMessage = ObjectAnimator.ofFloat(messageTextView, View.ALPHA, 1f).setDuration(100)
            val nameTitle = ObjectAnimator.ofFloat(nameTextView, View.ALPHA, 1f).setDuration(100)
            val nameInputField = ObjectAnimator.ofFloat(nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val emailTitle = ObjectAnimator.ofFloat(emailTextView, View.ALPHA, 1f).setDuration(100)
            val emailInputField = ObjectAnimator.ofFloat(emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val dateTitle = ObjectAnimator.ofFloat(dateTextView, View.ALPHA, 1f).setDuration(100)
            val dateInputField = ObjectAnimator.ofFloat(dateEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val genderTitle = ObjectAnimator.ofFloat(genderTextView, View.ALPHA, 1f).setDuration(100)
            val genderInputRadio = ObjectAnimator.ofFloat(genderRadioGroup, View.ALPHA, 1f).setDuration(100)
            val addressTitle = ObjectAnimator.ofFloat(addressTextView, View.ALPHA, 1f).setDuration(100)
            val addressInputField = ObjectAnimator.ofFloat(addressEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val numPhoneTitle = ObjectAnimator.ofFloat(phoneTextView, View.ALPHA, 1f).setDuration(100)
            val numPhoneInputField = ObjectAnimator.ofFloat(phoneEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val allergyTitle = ObjectAnimator.ofFloat(allergyTextView, View.ALPHA, 1f).setDuration(100)
            val allergyInputField = ObjectAnimator.ofFloat(allergyEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val passwordTitle = ObjectAnimator.ofFloat(passwordTextView, View.ALPHA, 1f).setDuration(100)
            val passwordInputField = ObjectAnimator.ofFloat(passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val registrationButton = ObjectAnimator.ofFloat(registerButton, View.ALPHA, 1f).setDuration(100)
            val login = ObjectAnimator.ofFloat(loginWrapper, View.ALPHA, 1f).setDuration(100)

            AnimatorSet().apply {
                playSequentially(
                    title,
                    subMessage,
                    nameTitle,
                    nameInputField,
                    emailTitle,
                    emailInputField,
                    dateTitle,
                    dateInputField,
                    genderTitle,
                    genderInputRadio,
                    addressTitle,
                    addressInputField,
                    numPhoneTitle,
                    numPhoneInputField,
                    allergyTitle,
                    allergyInputField,
                    passwordTitle,
                    passwordInputField,
                    registrationButton,
                    login
                )
                startDelay = 100
            }.start()
        }
    }
}