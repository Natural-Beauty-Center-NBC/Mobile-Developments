package id.project.nbcmobile.view.components.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import id.project.nbcmobile.authentication.LoginActivity
import id.project.nbcmobile.databinding.ActivitySplashBinding
import id.project.nbcmobile.view.components.utils.ViewModelFactory
import id.project.nbcmobile.view.customer.main.MainActivity
import id.project.nbcmobile.view.pegawai.main.PegawaiActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel by viewModels<SplashViewModel> {
        ViewModelFactory.getInstance(this@SplashActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start the animations after the layout is loaded
        startSplashAnimation()
    }

    private fun startSplashAnimation() {
        // Logo animation: fade in and slide up
        val logoFadeIn = ObjectAnimator.ofFloat(binding.nbcLogoCompact, "alpha", 0f, 1f)
        val logoSlideUp = ObjectAnimator.ofFloat(binding.nbcLogoCompact, "translationY", 300f, 0f)

        // Title animation: fade in and slide up
        val titleFadeIn = ObjectAnimator.ofFloat(binding.splashTitle, "alpha", 0f, 1f)
        val titleSlideUp = ObjectAnimator.ofFloat(binding.splashTitle, "translationY", 100f, 0f)

        // Message animation: fade in
        val messageFadeIn = ObjectAnimator.ofFloat(binding.splashMessage, "alpha", 0f, 1f)

        // Set durations and delays
        logoFadeIn.duration = 1500
        logoSlideUp.duration = 1500

        titleFadeIn.duration = 1500
        titleSlideUp.duration = 1500
        titleFadeIn.startDelay = 500
        titleSlideUp.startDelay = 500

        messageFadeIn.duration = 1000
        messageFadeIn.startDelay = 500

        val animatorSet = AnimatorSet()
        animatorSet.apply {
            playTogether(logoFadeIn, logoSlideUp) // Play logo animations together
            playTogether(titleFadeIn, titleSlideUp) // Play title animations together
            play(messageFadeIn).after(titleSlideUp) // Play message after title animations

            // A listener to execute an action when animation finish
            addListener(object : android.animation.AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)

                    // Checking "is the user already login?"
                    viewModel.getSession().observe(this@SplashActivity) { user ->
                        if (user.isLogin && user.type == "customer") {
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                            finish()
                        }

                        if (user.isLogin && user.type == "pegawai") {
                            startActivity(Intent(this@SplashActivity, PegawaiActivity::class.java))
                            finish()
                        }

                        if (!user.isLogin) {
                            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                            finish()
                        }
                    }
                }
            })

            start()
        }
    }
}
