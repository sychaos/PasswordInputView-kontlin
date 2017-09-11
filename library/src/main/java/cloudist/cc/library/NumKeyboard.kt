package cloudist.cc.library

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.GridView
import android.widget.RelativeLayout
import cloudist.cc.library.adapter.KeyboardAdapter
import kotlin.properties.Delegates

/**
 * Created by cloudist on 2017/9/11.
 */
class NumKeyboard : RelativeLayout {

    var mContext by Delegates.notNull<Context>()

    private var gvKeyboard by Delegates.notNull<GridView>()
    private var onClickKeyboardListener: OnClickKeyboardListener? = null
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

    /**
     * 初始化键盘的点击事件
     */
    private fun initEvent() {
//        gvKeyboard.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            if (onClickKeyboardListener != null && position >= 0) {
//                onClickKeyboardListener.onKeyClick(position, key[position])
//            }
//        }
    }

    /**
     * 初始化KeyboardView
     */
    private fun initKeyboardView() {
        val view = View.inflate(context, R.layout.view_keyboard, this)
        gvKeyboard = view.findViewById<View>(R.id.gv_keyboard) as GridView
        mAdapter.mSelectedData = convertKeyBoardItem()
        gvKeyboard.adapter = mAdapter
        initEvent()
    }

    interface OnClickKeyboardListener {
        fun onKeyClick(position: Int, value: String)
    }

    /**
     * 对外开放的方法

     * @param onClickKeyboardListener
     */
    fun setOnClickKeyboardListener(onClickKeyboardListener: OnClickKeyboardListener) {
        this.onClickKeyboardListener = onClickKeyboardListener
    }

    /**
     * 设置键盘所显示的内容
     */
    fun init() {
        initKeyboardView()
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