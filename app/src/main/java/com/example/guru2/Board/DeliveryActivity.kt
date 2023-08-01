package com.example.guru2.Board

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guru2.R
import com.example.guru2.data.PostDataModel
import com.example.guru2.data.PostType
import com.example.guru2.data.Replies
import com.example.guru2.databinding.BoardpageDeliveryBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class DeliveryActivity : AppCompatActivity() {
    private lateinit var mBinding: BoardpageDeliveryBinding
    private lateinit var mAdapter: AdapterBoard
    private lateinit var pList: ArrayList<PostDataModel>

    private val COLLECTION_PATH = "FoodPosts"
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = BoardpageDeliveryBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initVariable() // 변수 초기화 및 RecyclerView 설정 메서드 호출
        getPost() // 게시물 데이터를 Firebase Firestore에서 가져오는 메서드 호출

        // 홈 버튼 클릭 시, HomeActivity로 이동하고 현재 액티비티 종료
        val boardpage_iv1 = findViewById<ImageView>(R.id.boardpage_iv1)
        boardpage_iv1.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 게시물 추가 버튼 클릭 시, AddpostDialog를 띄우고 현재 액티비티 종료
        val btnaddpost = findViewById<Button>(R.id.btnaddpost)
        btnaddpost.setOnClickListener {
            val i = Intent(this, AddpostDialog::class.java)
            startActivity(i)
            finish()
        }
    }

    // 게시물 추가 다이얼로그를 띄우는 메서드
    private fun showAddPostDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.addpost_dialog)
        dialog.show()
    }

    // 변수 초기화 및 RecyclerView 설정 메서드
    private fun initVariable() {
        pList = ArrayList()
        mAdapter = AdapterBoard(pList)
        mBinding.rePosts.layoutManager = LinearLayoutManager(this)
        mBinding.rePosts.adapter = mAdapter
    }

    // Firebase Firestore에서 게시물 데이터를 가져오는 메서드
    fun getPost() {
        db.collection(COLLECTION_PATH).get()
            .addOnSuccessListener(
                OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->

                    if (!queryDocumentSnapshots.isEmpty) {
                        for (snapshot in queryDocumentSnapshots) {
                            // getId() = documentId를 가져온다
                            var res: HashMap<String?, PostDataModel?>? = HashMap()
                            res = snapshot["Posts"] as HashMap<String?, PostDataModel?>?
                            if (res != null) {
                                val type = res["type"] as String
                                Log.i("##INFO", "type = $type");
                                if (type == PostType.Delivery.name) {
                                    val data = PostDataModel()
                                    data.type = if (type == PostType.Delivery.name) PostType.Delivery else PostType.Purchase
                                    data.id = (snapshot.id)
                                    data.title = (java.lang.String.valueOf(res["title"]))
                                    data.content = (java.lang.String.valueOf(res["content"]))
                                    data.password = (java.lang.String.valueOf(res["password"]))
                                    if (res["replies"] != null) {
                                        data.replies =
                                            (java.util.ArrayList<Replies>(res["replies"] as Collection<Replies?>?))
                                    }
                                    if (res["pictures"] != null) {
                                        data.pictures =
                                            (java.util.ArrayList<String>(res["pictures"] as Collection<String>?))
                                    }
                                    pList.add(data)
                                }
                            }
                        }
                    }
                    mAdapter.updatePostList(pList) // 가져온 게시물 데이터로 RecyclerView를 업데이트
                })
    }
}