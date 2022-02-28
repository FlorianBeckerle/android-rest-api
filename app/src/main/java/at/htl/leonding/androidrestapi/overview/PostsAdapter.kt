package at.htl.leonding.androidrestapi.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import at.htl.leonding.androidrestapi.databinding.FragmentPostOverviewBinding
import at.htl.leonding.androidrestapi.databinding.PostItemBinding
import at.htl.leonding.androidrestapi.Model.Post
import java.text.FieldPosition

class PostsAdapter : ListAdapter<Post, PostsAdapter.PostViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder{
        Log.d("Posts-Adapter", "onCreateViewHolder")
        return PostViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsAdapter.PostViewHolder, position: Int){
        val post = getItem(position)
        Log.d("Posts-Adapter", "Post: " + post.toString() + " onBindViewHolder")
        holder.bind(post)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Post>(){
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    class PostViewHolder(private var binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.post = post
            binding.executePendingBindings()
        }
    }
}