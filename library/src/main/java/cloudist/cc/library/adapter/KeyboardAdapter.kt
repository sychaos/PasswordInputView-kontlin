package cloudist.cc.library.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cloudist.cc.library.KeyBoardItem
import cloudist.cc.library.R
import kotlin.properties.Delegates

/**
 * Created by cloudist on 2017/9/11.
 */
class KeyboardAdapter(val mContext: Context) : BaseAdapter() {

    companion object {
        val TYPE_NUM = 1001
        val TYPE_DELETE = 1002
        val TYPE_EMPTY = 1003
    }

    var mSelectedData = mutableListOf<KeyBoardItem>()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder by Delegates.notNull<ViewHolder>()
        var mConvertView by Delegates.notNull<View>()
        if (convertView == null) {
            when (mSelectedData[position].itemType) {
                TYPE_NUM -> {
                    //数字键
                    mConvertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_keyboard, parent, false)
                    viewHolder = ViewHolder(mConvertView)
                    viewHolder.tvKey.text = mSelectedData[position].value.toString()

                }
                TYPE_DELETE -> {
                    //删除键
                    mConvertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_keyboard_delete, parent, false)
                }
                TYPE_EMPTY -> {
                    //空白键
                    mConvertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_keyboard_empty, parent, false)
                }
            }
        } else {
            mConvertView = convertView
        }

        return mConvertView
    }

    override fun getItem(position: Int): Any {
        return mSelectedData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mSelectedData.size
    }

    override fun getItemViewType(position: Int): Int {
        return mSelectedData[position].itemType
    }
}

/**
 * ViewHolder,view缓存
 */
class ViewHolder(view: View) {
    val tvKey: TextView = view.findViewById<View>(R.id.tv_keyboard_keys) as TextView

    init {
        view.tag = this
    }
}