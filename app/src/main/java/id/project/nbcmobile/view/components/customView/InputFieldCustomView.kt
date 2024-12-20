package id.project.nbcmobile.view.components.customView

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import id.project.nbcmobile.R

class InputFieldCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private var customHint: String? = null
    private var errorText: String? = null

    init {
        // Load custom attributes
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.InputFieldCustomView,
            0,
            0
        ).apply {
            try {
                customHint = getString(R.styleable.InputFieldCustomView_customHint)
                errorText = getString(R.styleable.InputFieldCustomView_errorText)
            } finally {
                recycle()
            }
        }

        // Add TextWatcher
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do Nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Do Nothing
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().isEmpty()) {
                    setError(errorText, null)
                } else {
                    error = null
                }
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = customHint
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
}