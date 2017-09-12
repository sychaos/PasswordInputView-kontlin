package cloudist.cc.library.widget

import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import cloudist.cc.library.R
import kotlinx.android.synthetic.main.dialog_input_password.*

/**
 * Created by cloudist on 2017/9/12.
 */
class InputPasswordDialog : DialogFragment() {

    companion object {
        fun newInstance(): InputPasswordDialog {
            val fragment = InputPasswordDialog()
            return fragment
        }
    }

    var textChangeListener: TextChangeListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.attributes.windowAnimations = R.style.AnimBottomPushWindow
        val rootView = inflater!!.inflate(R.layout.dialog_input_password, container)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog.setCanceledOnTouchOutside(true)

        keyboardView.bindTextView(passwordInputView)

        passwordInputView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textChangeListener?.textChange(passwordInputView.text.toString())
            }
        })

    }

    override fun onResume() {
        val window = dialog.window
        val size = Point()
        val display = window.windowManager.defaultDisplay
        display.getSize(size)
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawableResource(R.color.transparent)
        window.setGravity(Gravity.BOTTOM)
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    interface TextChangeListener {
        fun textChange(text: String)
    }
}