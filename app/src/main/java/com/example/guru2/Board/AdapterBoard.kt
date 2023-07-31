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
        pList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMainBoard {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_board_post, parent, false)
        return ViewHolderMainBoard(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMainBoard, position: Int) {
        holder.title.setText(pList[position].title)
        holder.replayCount.text = "[${pList[position].replies.size}]"
        holder.onItemClick()
    }

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

    inner class ViewHolderMainBoard(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val replayCount: TextView

        init {
            title = itemView.findViewById<TextView>(R.id.tv_title_item_post)
            replayCount = itemView.findViewById<TextView>(R.id.tv_re_count_item_post)
            onItemClick()
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
