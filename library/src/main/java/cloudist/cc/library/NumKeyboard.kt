package cloudist.cc.library

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.RelativeLayout
import android.widget.TextView
import cloudist.cc.library.adapter.KeyboardAdapter
import kotlin.properties.Delegates

/**
 * Created by cloudist on 2017/9/11.
 */
class NumKeyboard : RelativeLayout {

    var mContext by Delegates.notNull<Context>()

    private var gvKeyboard by Delegates.notNull<GridView>()
    private var mTextView: TextView? = null

    val mAdapter by lazy { KeyboardAdapter(mContext) }

    constructor(context: Context?) : super(context) {
        mContext = context!!
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context!!
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mContext = context!!
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initKeyboardView()
    }

    fun bindTextView(textView: TextView) {
        mTextView = textView
    }

    /**
     * 初始化KeyboardView
     */
    private fun initKeyboardView() {
        val view = View.inflate(context, R.layout.view_keyboard, this)
        gvKeyboard = view.findViewById<View>(R.id.gv_keyboard) as GridView
        mAdapter.mSelectedData = convertKeyBoardItem()
        gvKeyboard.adapter = mAdapter

        gvKeyboard.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            when (mAdapter.mSelectedData[position].itemType) {
                KeyboardAdapter.TYPE_NUM -> mTextView?.apply {
                    append(mAdapter.mSelectedData[position].value.toString())
                }
                KeyboardAdapter.TYPE_DELETE -> mTextView?.apply {
                    if (text.isEmpty()) {
                        return@OnItemClickListener
                    }
                    text = text.subSequence(0, text.lastIndex)
                }
            }
        }
    }

    fun convertKeyBoardItem(): MutableList<KeyBoardItem> {
        val KeyBoardItems = mutableListOf<KeyBoardItem>()
        (1..9).mapTo(KeyBoardItems) { KeyBoardItem(KeyboardAdapter.TYPE_NUM, it) }
        KeyBoardItems.add(KeyBoardItem(KeyboardAdapter.TYPE_EMPTY))
        KeyBoardItems.add(KeyBoardItem(KeyboardAdapter.TYPE_NUM, 0))
        KeyBoardItems.add(KeyBoardItem(KeyboardAdapter.TYPE_DELETE))
        return KeyBoardItems
    }
}