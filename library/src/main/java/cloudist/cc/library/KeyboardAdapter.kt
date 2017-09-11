package cloudist.cc.library

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by cloudist on 2017/9/11.
 */
class KeyboardAdapter(val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mSelectedData = mutableListOf<KeyBoardItem>()

    companion object {
        val TYPE_NUM = 1001
        val TYPE_DELETE = 1002
        val TYPE_EMPTY = 1003
    }

    override fun getItemCount(): Int {
        return mSelectedData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val mInflater = LayoutInflater.from(mContext)
        val viewGroup: View = when (viewType) {
            TYPE_NUM -> mInflater.inflate(R.layout.item_num, parent, false)
            TYPE_DELETE -> mInflater.inflate(R.layout.item_empty, parent, false)
            else -> {
                mInflater.inflate(R.layout.item_num, parent, false)
            }
        }
        return ViewHolder(viewGroup)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder?.let {
            val item = mSelectedData[position]
            val view = holder.itemView
            when (holder.itemViewType) {
                TYPE_NUM -> {
//                    val docCbChoose = view.findViewById(R.id.cb_choose) as CheckBox
//                    val docName = view.findViewById(R.id.tv_name) as TextView
//                    docCbChoose.isChecked = mSelectedData.contains(fileItem)
//                    docName.setText(fileItem.getName())
                }
                TYPE_DELETE -> {
//                    val folderName = view.findViewById(R.id.tv_name) as TextView
//                    folderName.setText(fileItem.getName())
                }
                else -> {
                }
            }
            mOnItemClickListener?.invoke(item)
        }
    }

    var mOnItemClickListener: ((item: KeyBoardItem) -> Unit)? = null

    fun onItemClick(listener: (((item: KeyBoardItem) -> Unit))) {
        mOnItemClickListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return mSelectedData[position].itemType
    }

    fun addData(mData: List<KeyBoardItem>?) {
        mData?.let {
            mSelectedData.addAll(mData)
        }
    }

    fun addData(mData: KeyBoardItem?) {
        mData?.let {
            mSelectedData.add(mData)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}