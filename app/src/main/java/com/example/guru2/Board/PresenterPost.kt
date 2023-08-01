package com.app.pcestimate.view.board

import android.util.Log
import com.example.guru2.data.PostDataModel
import com.example.guru2.data.Replies
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class PresenterPost private constructor() {
    private val db = FirebaseFirestore.getInstance()

    fun setPost(postInfo: PostDataModel?, postId: String): Boolean {
        if (postInfo == null) return false
        if (postInfo.title.isEmpty() || postInfo.content.isEmpty() || postInfo.password.isEmpty()) return false

        if (!postId.isEmpty()) {
            // case -> 게시글 수정
            db.collection(COLLECTION_PATH).document(postId).update("Posts", postInfo)
                .addOnSuccessListener { }
                .addOnFailureListener { e: Exception ->
                    Log.e(
                        TAG,
                        "setPost: error >>$e"
                    )
                }
        } else {
            // case -> 게시글 첫 작성
            val posts: MutableMap<String, PostDataModel> = HashMap<String, PostDataModel>()
            posts["Posts"] = postInfo
            db.collection(COLLECTION_PATH).add(posts)
                .addOnSuccessListener { documentReference: DocumentReference? ->
                    Log.i("##INFO", "success = ${documentReference?.id}(): ");
                }
                .addOnFailureListener { e: Exception ->
                    Log.e(
                        TAG,
                        "setPost: error >>$e"
                    )
                }
        }
        return true
    }

    fun getPost(callback: IPostsResultCallback): Boolean {
        db.collection(COLLECTION_PATH).get().addOnSuccessListener { queryDocumentSnapshots ->
            val postsList: ArrayList<PostDataModel> = ArrayList<PostDataModel>()
            if (!queryDocumentSnapshots.isEmpty) {
                for (snapshot in queryDocumentSnapshots) {
                    // getId() = documentId를 가져옴
                    var res: HashMap<String?, PostDataModel?>? =
                        HashMap<String?, PostDataModel?>()
                    res = snapshot["Posts"] as HashMap<String?, PostDataModel?>?
                    if (res != null) {
                        val data = PostDataModel()
                        data.id = snapshot.id
                        data.title = java.lang.String.valueOf(res["title"])
                        data.content = java.lang.String.valueOf(res["content"])
                        data.password = java.lang.String.valueOf(res["password"])
                        data.replies = java.util.ArrayList<Replies>(res["replies"] as Collection<Replies?>?)
                        data.pictures = ArrayList(res["pictures"] as Collection<String>?)

                        // TODO: 로케이션 위치 저장 형식까지 지정하였고 게시글 새로 작성해서 불러와서 지도에 입혀주어야 함.
                        postsList.add(data)
                        callback.onResult(postsList)
                    }
                }
            }
        }
        return true
    }

    fun setReply(postInfo: PostDataModel): Boolean {
        postInfo.id.let {
            db.collection(COLLECTION_PATH).document(it).update("Posts", postInfo)
                .addOnSuccessListener {
                    Log.i(
                        "##INFO",
                        "setReply(): success"
                    )

                }.addOnFailureListener { e -> Log.i("##INFO", "setReply(): e = " + e.message) }
        }
        return true
    }

    fun deleteReply(postInfo: PostDataModel): Boolean {
        postInfo.id?.let {
            db.collection(COLLECTION_PATH).document(it).update("Posts", postInfo)
                .addOnSuccessListener {
                    Log.i(
                        "##INFO",
                        "deleteReply(): success"
                    )
                }.addOnFailureListener { e ->
                    Log.i(
                        "##INFO",
                        "deleteReply(): e = " + e.message
                    )
                }
        }
        return true
    }

    fun deletePost(postInfo: PostDataModel): Boolean {
        val updates = hashMapOf<String, Any>(
            "Posts" to FieldValue.delete(),
        )

        postInfo.id.let {
            db.collection(COLLECTION_PATH).document(it).delete().addOnSuccessListener {
                Log.i(
                    "##INFO",
                    "deletePost(): success"
                )
            }.addOnFailureListener { e ->
                Log.i(
                    "##INFO",
                    "deletePost(): e = " + e.message
                )
            }
        }
        return true
    }

    interface IPostsResultCallback {
        fun onResult(list: ArrayList<PostDataModel>?)
        fun onError(erMsg: String?)
    }

    companion object {
        private const val TAG = "##H"
        private const val COLLECTION_PATH = "FoodPosts"
        private var INSTANCE: PresenterPost? = null


        val instance: PresenterPost?
            get() {
                if (INSTANCE == null) {
                    INSTANCE = PresenterPost()
                }
                return INSTANCE
            }
    }
}