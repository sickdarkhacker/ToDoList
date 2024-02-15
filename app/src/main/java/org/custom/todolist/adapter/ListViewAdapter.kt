package org.custom.todolist.adapter

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.custom.todolist.MainActivity
import org.custom.todolist.properties.ListItem

class ListViewAdapter(private val context: MainActivity, private val itemList: List<ListItem>) :RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,

            )
            orientation = LinearLayout.HORIZONTAL

            setPadding(16, 16, 16, 16)

            val imageView = ImageView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                scaleType = ImageView.ScaleType.CENTER_CROP
                setPadding(32,32,32,32)
            }

            val textLayout = LinearLayout(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER_VERTICAL // 여기서 중앙 정렬을 설정해야 합니다.

                val titleTextView = TextView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    textSize = 18f
                    setTextColor(Color.BLACK)
                    setPadding(0,22,0,8)
                }

                val commentTextView = TextView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    textSize = 14f
                    setTextColor(Color.BLACK)
                }

                addView(titleTextView)
                addView(commentTextView)
            }

            addView(imageView)
            addView(textLayout)
        }
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.imageView.setImageBitmap(item.itemImageSource)
        holder.titleTextView.text = item.itemTitle
        holder.commentTextView.text = item.itemComment
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = (itemView as LinearLayout).getChildAt(0) as ImageView
        val titleTextView: TextView = ((itemView as LinearLayout).getChildAt(1) as LinearLayout).getChildAt(0) as TextView
        val commentTextView: TextView = ((itemView as LinearLayout).getChildAt(1) as LinearLayout).getChildAt(1) as TextView
    }
}
