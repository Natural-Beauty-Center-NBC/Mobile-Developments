package id.project.nbcmobile.authentication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import id.project.nbcmobile.view.customer.main.MainActivity
import id.project.nbcmobile.R
import id.project.nbcmobile.databinding.ActivityLoginBinding
import id.project.nbcmobile.view.pegawai.main.PegawaiActivity
import id.project.nbcmobile.view.components.customView.AlertDialogCustom
import id.project.nbcmobile.view.components.utils.ViewModelFactory
import id.project.nbcmobile.view.guest.HomeGuestActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<AuthenticationViewModel> {
        ViewModelFactory.getInstance(this@LoginActivity)
    }
    private val alertDialog = AlertDialogCustom(this@LoginActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isLoading.observe(this@LoginActivity) {
            showLoading(it)
        }

        viewModel.loginResponse.observe(this@LoginActivity) { response ->
            alertDialog.apply {
                if (response.user != null) {
                    create(
                        title = getString(R.string.hi_welcome_back),
                        message = "${response.user.nama} is login as ${response.userType}",
                        hasNegativeBtn = false,
                        onPositiveButtonClick = {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        },
                        onNegativeButtonClick = { /* Do Nothing */ }
                    )
                    show()
                } else {
                    create(
                        title = getString(R.string.hi_welcome_back),
                        message = "${response.pegawai?.nama} is login as ${response.userType}",
                        hasNegativeBtn = false,
                        onPositiveButtonClick = {
                            startActivity(Intent(this@LoginActivity, PegawaiActivity::class.java))
                        },
                        onNegativeButtonClick = { /* Do Nothing */ }
                    )
                    show()
                }
            }
        }

        viewModel.errorResponse.observe(this@LoginActivity) { response ->
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
            loginButton.setOnClickListener {
                val email = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()) {
                    alertDialog.apply {
                        create(
                            title = "Error",
                            message = getString(R.string.error_input_message),
                            hasNegativeBtn = false,
                            onPositiveButtonClick = { /* Do Nothing */ },
                            onNegativeButtonClick = { /* Do Nothing */ }
                        )
                        show()
                    }
                } else {
                    viewModel.login(email = email, password = password)
                }
            }

            continueAsGuest.setOnClickListener {
                startActivity(Intent(this@LoginActivity, HomeGuestActivity::class.java))
                finish()
            }

            registerButton.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun playAnimation() {
        binding.apply {
            val title = ObjectAnimator.ofFloat(titleLogin, View.ALPHA, 1f).setDuration(100)
            val subMessage =
                ObjectAnimator.ofFloat(messageTextView, View.ALPHA, 1f).setDuration(100)
            val emailTitle = ObjectAnimator.ofFloat(emailTextView, View.ALPHA, 1f).setDuration(100)
            val emailInputField =
                ObjectAnimator.ofFloat(emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val passwordTitle =
                ObjectAnimator.ofFloat(passwordTextView, View.ALPHA, 1f).setDuration(100)
            val passwordInputField =
                ObjectAnimator.ofFloat(passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
            val loginButton = ObjectAnimator.ofFloat(loginButton, View.ALPHA, 1f).setDuration(100)
            val registration =
                ObjectAnimator.ofFloat(registrationWrapper, View.ALPHA, 1f).setDuration(100)

            AnimatorSet().apply {
                playSequentially(
                    title,
                    subMessage,
                    emailTitle,
                    emailInputField,
                    passwordTitle,
                    passwordInputField,
                    loginButton,
                    registration
                )
                startDelay = 100
            }.start()
        }
    }
}