package cloudist.cc.library

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.EditText
import kotlin.properties.Delegates
import android.text.InputFilter
import android.text.InputType


/**
 * Created by cloudist on 2017/7/31.
 */
class PasswordInputView(context: Context, attrs: AttributeSet) : EditText(context, attrs) {

    private var borderColor by Delegates.notNull<Int>()
    private var borderRespondingColor by Delegates.notNull<Int>()
    private var borderWidth by Delegates.notNull<Float>()
    private var borderRadius by Delegates.notNull<Float>()

    private var passwordLength by Delegates.notNull<Int>()
    private var passwordColor by Delegates.notNull<Int>()
    private var passwordWidth by Delegates.notNull<Float>()

    private var itemPadding by Delegates.notNull<Float>()
    private var itemHeight by Delegates.notNull<Float>()
    private var normalInput by Delegates.notNull<Boolean>()

    private val passwordPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var textLength: Int = 0
    private var rect = RectF()
    private var rectIn = RectF()

    init {
        //TODO 宽度问题
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.PasswordInputView, 0, 0)
        borderColor = a.getColor(R.styleable.PasswordInputView_borderColor, ContextCompat.getColor(context, R.color.textMedium))
        borderRespondingColor = a.getColor(R.styleable.PasswordInputView_borderRespondingColor, ContextCompat.getColor(context, R.color.colorBlue))
        borderWidth = a.getDimension(R.styleable.PasswordInputView_borderWidth, 0.5f)
        borderRadius = a.getDimension(R.styleable.PasswordInputView_borderRadius, 2f)

        passwordColor = a.getColor(R.styleable.PasswordInputView_passwordColor, ContextCompat.getColor(context, R.color.textDark))
        passwordWidth = a.getDimension(R.styleable.PasswordInputView_passwordWidth, 6f)
        passwordLength = a.getInteger(R.styleable.PasswordInputView_passwordLength, 6)

        itemPadding = a.getDimension(R.styleable.PasswordInputView_itemPadding, 8f)
        itemHeight = a.getDimension(R.styleable.PasswordInputView_itemHeight, 36f)

        normalInput = a.getBoolean(R.styleable.PasswordInputView_normalInput, true)

        passwordPaint.style = Paint.Style.FILL
        passwordPaint.color = passwordColor

        isFocusable = normalInput
        isCursorVisible = false
        filters = arrayOf<InputFilter>(InputFilter.LengthFilter(passwordLength))
        setSingleLine(true)
        inputType = InputType.TYPE_CLASS_NUMBER
    }

    override fun onDraw(canvas: Canvas) {
        // 外边框
        val paddingVertical = (height - itemHeight) / 2
        for (i in 0..passwordLength - 1) {
            rect.set((itemHeight + itemPadding) * i + itemPadding, paddingVertical,
                    (itemHeight + itemPadding) * (i + 1), paddingVertical + itemHeight)
            if (i == textLength && (!normalInput || hasFocus())) {
                borderPaint.color = borderRespondingColor
            } else {
                borderPaint.color = borderColor
            }
            borderPaint.strokeWidth = 1f
            canvas.drawRoundRect(rect, borderRadius, borderRadius, borderPaint)

            // 内容区
            rectIn.set(rect.left + borderWidth, rect.top + borderWidth,
                    rect.right - borderWidth, rect.bottom - borderWidth)
            borderPaint.color = ContextCompat.getColor(context, R.color.white)
            canvas.drawRoundRect(rectIn, borderRadius, borderRadius, borderPaint)
        }

        // 密码
        var cx: Float
        val cy = itemHeight / 2 + paddingVertical
        val half = (itemHeight) / 2 + itemPadding
        for (i in 0..textLength - 1) {
            cx = (itemHeight + itemPadding) * i + half
            canvas.drawCircle(cx, cy, passwordWidth, passwordPaint)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(itemHeight.toInt() * passwordLength + itemPadding.toInt() * (passwordLength + 1)
                , (itemHeight + 2 * itemPadding).toInt())
    }

    override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        this.textLength = text.toString().length
        invalidate()
    }

}