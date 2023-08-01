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
import com.example.guru2.databinding.BoardpagePurchaseBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class PurchaseActivity: AppCompatActivity() {
    private lateinit var mBinding: BoardpagePurchaseBinding
    private lateinit var mAdapter: AdapterBoard
    private lateinit var pList: ArrayList<PostDataModel>
    private val db = FirebaseFirestore.getInstance()
    private val COLLECTION_PATH = "FoodPosts"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = BoardpagePurchaseBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initVariable()
        getPost()

        val boardpage_iv1 = findViewById<ImageView>(R.id.boardpage_iv1)
        val btnaddpost = findViewById<Button>(R.id.btnaddpost)

        boardpage_iv1.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnaddpost.setOnClickListener {
            val i = Intent(this, AddpostDialog::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun showAddPostDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.addpost_dialog)
        dialog.show()
    }

    private fun initVariable() {
        pList = ArrayList()
        mAdapter = AdapterBoard(pList)
        mBinding.rePosts.layoutManager = LinearLayoutManager(this)
        mBinding.rePosts.adapter = mAdapter
    }

    fun getPost() {
        db.collection(COLLECTION_PATH).get()
            .addOnSuccessListener(
                OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->

                    if (!queryDocumentSnapshots.isEmpty) {
                        for (snapshot in queryDocumentSnapshots) {
                            //getId() = documentId를 가져온다
                            var res: HashMap<String?, PostDataModel?>? = HashMap()
                            res = snapshot["Posts"] as HashMap<String?, PostDataModel?>?
                            if (res != null) {
                                val type = res["type"] as String
                                Log.i("##INFO", "type = $type");
                                if (type == PostType.Purchase.name) {
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
                    mAdapter.updatePostList(pList)
                })

    }
}