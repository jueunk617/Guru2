package com.example.guru2.Post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R

class PostAdapter(private val postList: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTv1: TextView = itemView.findViewById(R.id.post_tv1)
        val postTv2: TextView = itemView.findViewById(R.id.post_tv2)
        val postTv3: TextView = itemView.findViewById(R.id.post_tv3)
        val postTv4: TextView = itemView.findViewById(R.id.post_tv4)
        val hashtag1: TextView = itemView.findViewById(R.id.hashteg1)
        val hashtag2: TextView = itemView.findViewById(R.id.hashteg2)
        val hashtag3: TextView = itemView.findViewById(R.id.hashteg3)
        val needPeople: TextView = itemView.findViewById(R.id.needpeople)
        val views: TextView = itemView.findViewById(R.id.views)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_postlist, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.itemView.apply {
            holder.postTv1.text = post.category
            holder.postTv2.text = post.time
            holder.postTv3.text = post.title
            holder.postTv4.text = post.content

            if (post.hashtags.size > 0) {
                holder.hashtag1.text = "#" + post.hashtags[0]
                holder.hashtag1.visibility = View.VISIBLE
            } else {
                holder.hashtag1.visibility = View.GONE
            }

            if (post.hashtags.size > 1) {
                holder.hashtag2.text = "#" + post.hashtags[1]
                holder.hashtag2.visibility = View.VISIBLE
            } else {
                holder.hashtag2.visibility = View.GONE
            }

            if (post.hashtags.size > 2) {
                holder.hashtag3.text = "#" + post.hashtags[2]
                holder.hashtag3.visibility = View.VISIBLE
            } else {
                holder.hashtag3.visibility = View.GONE
            }

            holder.needPeople.text = "최소인원: ${post.participants}"
            holder.views.text = "조회수: ${post.views}"
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}