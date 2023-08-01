package com.example.guru2.Board

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.example.guru2.data.PostDataModel

class AdapterBoard(list: ArrayList<PostDataModel>) :
    RecyclerView.Adapter<AdapterBoard.ViewHolderMainBoard>() {
    private var pList: ArrayList<PostDataModel>

    init {
        // Adapter를 PostDataModel 객체의 리스트로 초기화
        pList = list
    }

    // 새로운 ViewHolder 인스턴스를 생성, RecyclerView에 사용할 아이템 레이아웃 인플레이션
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMainBoard {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_board_post, parent, false)
        return ViewHolderMainBoard(view)
    }

    // ViewHolder에 데이터 바인딩
    // 해당 PostDataModel에 대한 제목과 댓글 수 설정
    override fun onBindViewHolder(holder: ViewHolderMainBoard, position: Int) {
        holder.title.setText(pList[position].title)
        holder.replayCount.text = "[${pList[position].replies.size}]"
        holder.onItemClick()
    }

    // 리스트의 총 아이템 수 반환
    override fun getItemCount(): Int {
        return pList.size
    }

    fun updatePostList(list: ArrayList<PostDataModel>) {
        pList = list
        notifyItemChanged(0, pList.size)
    }

    fun resetPostList(list: ArrayList<PostDataModel>) {
        pList = list
        notifyDataSetChanged()
    }

    // RecyclerView 아이템용 ViewHolder 클래스 정의
    inner class ViewHolderMainBoard(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val replayCount: TextView

        init {
            // 아이템 레이아웃에서 제목과 댓글 수 초기화
            title = itemView.findViewById<TextView>(R.id.tv_title_item_post)
            replayCount = itemView.findViewById<TextView>(R.id.tv_re_count_item_post)
            onItemClick() // 아이템 클릭 리스너를 설정합니다.
        }

        fun onItemClick() {
            itemView.setOnClickListener { v: View? ->
                val i = Intent(
                    itemView.context,
                    DetailPostActivity::class.java
                )
                i.putExtra("PostInfo", pList[adapterPosition])
                itemView.context.startActivity(i)
            }
        }
    }
}