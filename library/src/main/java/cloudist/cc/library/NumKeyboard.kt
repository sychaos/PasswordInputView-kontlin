package cloudist.cc.library

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.layout_keyboard.view.*
import kotlin.properties.Delegates
import android.support.v7.widget.GridLayoutManager



/**
 * Created by cloudist on 2017/9/10.
 */
class NumKeyboard(val mContext: Context, val attrs: AttributeSet, val defStyleAttr: Int = 0) : RelativeLayout(mContext, attrs, defStyleAttr) {

    private val mAdapter by lazy {
        KeyboardAdapter(mContext)
    }
    private var mEditText: EditText? = null

    /**
     * 初始化键盘的点击事件
     */
    private fun initEvent() {
        getItems()
        mAdapter.onItemClick {
            (itemType, value) ->
            when (itemType) {
                KeyboardAdapter.TYPE_NUM -> mEditText?.apply {
                    text.append(value.toChar())
                }
                KeyboardAdapter.TYPE_DELETE -> mEditText?.apply {
                    text.dropLastWhile { text.isNotEmpty() }
                }
            }
        }
        gv_keyboard.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        gv_keyboard.adapter = mAdapter
    }

    /**
     * 初始化KeyboardView
     */
    fun initKeyboardView() {
        initEvent()
    }

    fun getItems() {
        for (i in 1..10) {
            mAdapter.addData(KeyBoardItem(KeyboardAdapter.TYPE_NUM, i))
        }
        mAdapter.addData(KeyBoardItem(KeyboardAdapter.TYPE_EMPTY))
        mAdapter.addData(KeyBoardItem(KeyboardAdapter.TYPE_NUM, 0))
        mAdapter.addData(KeyBoardItem(KeyboardAdapter.TYPE_DELETE))
    }

    fun bindEditText(editText: EditText) {
        mEditText = editText
    }

}