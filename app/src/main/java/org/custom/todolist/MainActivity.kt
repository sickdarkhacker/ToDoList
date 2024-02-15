package org.custom.todolist

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.custom.todolist.adapter.ListViewAdapter
import org.custom.todolist.properties.ListItem

class MainActivity : ComponentActivity() {
    private val itemList: ArrayList<ListItem> = ArrayList<ListItem>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val linearLayout = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val textFormRealativeLayout = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT // 높이를 WRAP_CONTENT로 변경
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val titleEditText: EditText = EditText(this).apply {
            id = View.generateViewId() // EditText에 고유한 ID를 부여합니다.
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            hint = "Enter text here" // EditText에 힌트를 추가합니다.
        }
        val commentEditText: EditText = EditText(this).apply {
            id = View.generateViewId() // EditText에 고유한 ID를 부여합니다.
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                320
            )
            hint = "Enter text here" // EditText에 힌트를 추가합니다.
        }

        val buttonSubmit: Button = Button(this).apply {
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            text = "Submit" // Button에 텍스트를 설정합니다.
        }



        val recyclerView = RecyclerView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ListViewAdapter(this@MainActivity, itemList)
        }

        textFormRealativeLayout.addView(titleEditText)
        textFormRealativeLayout.addView(commentEditText)
        textFormRealativeLayout.addView(buttonSubmit)

        linearLayout.addView(textFormRealativeLayout)
        linearLayout.addView(recyclerView) // RecyclerView를 RelativeLayout에 추가합니다.
        buttonSubmit.setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/a90244e9-9c2c-47bd-843b-5b7e8e51fa6d/dafv5be-2ee545ec-511b-48c4-a758-8a96cbaa827c.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2E5MDI0NGU5LTljMmMtNDdiZC04NDNiLTViN2U4ZTUxZmE2ZFwvZGFmdjViZS0yZWU1NDVlYy01MTFiLTQ4YzQtYTc1OC04YTk2Y2JhYTgyN2MucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.gLozzXzKVdPx4dxz0Miw1_3Kz_CYlfqCRhoDdrOSI00")
                .apply(RequestOptions().override(-1))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        // 이미지 로드 완료 시 처리할 작업
                        itemList.add(ListItem(resource, titleEditText.text.toString(), commentEditText.text.toString()))
                        recyclerView.adapter?.notifyDataSetChanged() // 어댑터에 데이터가 변경되었음을 알립니다.
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // 이미지가 로드되지 않았거나 클리어되었을 때 처리할 작업
                        throw IllegalStateException("이미지를 처리하는데 문제가 있습니다.\n 코드를 점검 해주세요.");
                    }
                })
        }


        setContentView(linearLayout)
    }


}
