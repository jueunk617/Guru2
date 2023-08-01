package com.example.guru2.Board

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pcestimate.view.board.PresenterPost
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.guru2.R
import com.example.guru2.data.PostDataModel
import com.example.guru2.data.Replies
import com.example.guru2.databinding.ActivityDetailPostBinding

class DetailPostActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityDetailPostBinding
    private lateinit var postInfo: PostDataModel
    private lateinit var replyList: ArrayList<Replies>
    private lateinit var mAdapter: AdapterReplay
    private var dlg: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initVariable()
        getPostItem()
        onViewClick()
    }

    private fun initVariable() {
        replyList = ArrayList<Replies>()
        mAdapter = AdapterReplay(replyList)
    }

    private fun getPostItem() {
        // 넘어온 데이터가 있을 경우
        if (intent.getSerializableExtra("PostInfo") != null) {
            postInfo = intent.getSerializableExtra("PostInfo") as PostDataModel
            replyList = postInfo.replies
            mBinding.tvTitleDetailPost.setText(postInfo.title)
            mBinding.tvContentDetailPost.setText(postInfo.content)
            mBinding.tvRepliesCountDetailPost.setText(postInfo.replies.size.toString())

            if (postInfo.pictures.size == 1) {
                Glide.with(this).load(postInfo.pictures.get(0)).into(mBinding.imOneDetailPost)
            }

            if (postInfo.pictures.size == 2) {
                Glide.with(this).load(postInfo.pictures.get(0)).into(mBinding.imOneDetailPost)
                Glide.with(this).load(postInfo.pictures.get(1)).into(mBinding.imTwoDetailPost)
                mBinding.imTwoDetailPost.setVisibility(View.VISIBLE)
            }
            mBinding.imOneDetailPost.setVisibility(View.VISIBLE)
        }

        setReplyData()
    }

    private fun setReplyData() {
        Log.i("##INFO", "testmessage(): ");
        mBinding.reRepliesDetail.setAdapter(mAdapter)
        mBinding.reRepliesDetail.setLayoutManager(LinearLayoutManager(this))
        Log.i("##INFO", "replayList = ${replyList.size}(): ");
        mAdapter!!.updateReplyList(replyList)
    }

    private fun onViewClick() {
        mAdapter.onItemClickListener(object : AdapterReplay.OnItemClick {
            override fun clickDelete(reply: String?, position: Int) {
                managePasswordDialog()

                // 상단에 취소키를 눌렀을때 다이얼로그창 종료
                dlg!!.findViewById<View>(R.id.im_cancel_dialog)
                    .setOnClickListener { v: View? -> dlg!!.dismiss() }

                // 댓글 삭제버튼 클릭시
                dlg!!.findViewById<View>(R.id.bt_ok_dialog).setOnClickListener { v: View? ->
                    val password: String
                    val a: Any = postInfo.replies.get(position)
                    if (a is HashMap<*, *>) {
                        val h =
                            a as HashMap<String, String>
                        h["replayPassword"]
                        password = h["replayPassword"].toString()
                    } else {
                        val h: Replies = a as Replies
                        password = java.lang.String.valueOf(h.replayPassword)
                    }
                    val inputPassword =
                        (dlg!!.findViewById<View>(R.id.ed_password_dialog) as EditText).text
                            .toString()
                    if (inputPassword == password) {
                        replyList.removeAt(position)
                        mAdapter.resetReplyList(replyList)
                        postInfo.replies = replyList
                        PresenterPost.instance?.deleteReply(postInfo)
                        dlg!!.dismiss()
                        mBinding.tvRepliesCountDetailPost.setText(replyList!!.size.toString() + "")
                        Toast.makeText(this@DetailPostActivity, "댓글이 삭제되었습니다", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this@DetailPostActivity, "비밀번호가 틀립니다", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
        mBinding.imBackDetailPost.setOnClickListener { v ->
//            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // 댓글 작성 후 보내기 버튼 클릭시 발생 이벤트
        mBinding.imSendDetail.setOnClickListener { v ->
            managePasswordDialog()
            dlg!!.findViewById<View>(R.id.bt_ok_dialog)
                .setOnClickListener { t: View? ->
                    val reply: String = mBinding.edReplyDetail.getText().toString()
                    val inputPassword =
                        (dlg!!.findViewById<View>(R.id.ed_password_dialog) as EditText).text
                            .toString()
                    val re = Replies(reply, inputPassword.toInt())
                    Log.i("##INFO", "onViewClick(): re.getReply() = $reply")
                    replyList.add(re)
                    Log.i("##INFO", "onViewClick(): replayList.size = " + replyList!!.size)
                    postInfo.replies = replyList
                    PresenterPost.instance?.setReply(postInfo)
                    dlg!!.dismiss()
                    mAdapter.updateReplyList(replyList)
                    mBinding.edReplyDetail.setText("")
                    mBinding.tvRepliesCountDetailPost.text = replyList.size.toString()
                }
            Log.i("##INFO", "onViewClick(): replayList.size = " + replyList!!.size)
            //            mBinding.tvRepliesCountDetailPost.setText(replyList.size() + "");

            // 댓글 입력시 자동으로 키보드 내림
            val view = this.currentFocus
            if (view != null) {
                val imm =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        // 삭제버튼 클릭시 게시글 삭제
        mBinding.tvDeleteContent.setOnClickListener { v ->
            managePasswordDialog()

            //상단에 취소키를 눌렀을때 다이얼로그창 종료
            dlg!!.findViewById<View>(R.id.im_cancel_dialog)
                .setOnClickListener { t: View? -> dlg!!.dismiss() }
            dlg!!.findViewById<View>(R.id.bt_ok_dialog)
                .setOnClickListener { t: View? ->
                    val password: String = postInfo.password
                    val inputPassword =
                        (dlg!!.findViewById<View>(R.id.ed_password_dialog) as EditText).text
                            .toString()
                    if (inputPassword == password) {
                        PresenterPost.instance?.deletePost(postInfo)
                        dlg!!.dismiss()
                        Toast.makeText(this@DetailPostActivity, "게시글이 삭제되었습니다", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@DetailPostActivity, "비밀번호가 틀립니다", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }

    private fun managePasswordDialog() {
        dlg = Dialog(this@DetailPostActivity, R.style.theme_dialog)
        dlg!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg!!.setCanceledOnTouchOutside(false)
        dlg!!.setCancelable(false)
        dlg!!.setContentView(R.layout.dialog_check_password)
        dlg!!.show()
        dlg!!.findViewById<View>(R.id.im_cancel_dialog)
            .setOnClickListener { t: View? -> dlg!!.dismiss() }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, DetailPostActivity::class.java))
        finish()
        super.onBackPressed()
    }
}