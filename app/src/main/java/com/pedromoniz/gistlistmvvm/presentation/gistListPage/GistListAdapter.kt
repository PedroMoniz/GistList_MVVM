package com.pedromoniz.gistlistmvvm.presentation.gistListPage


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedromoniz.gistlistmvvm.databinding.GistListPageFragmentBinding
import com.pedromoniz.gistlistmvvm.databinding.GistTemplateRowBinding
import com.pedromoniz.gistlistmvvm.databinding.GistTemplateWithImageRowBinding
import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity
import kotlinx.android.synthetic.main.gist_template_with_image_row.view.*

class GistListAdapter(
    private val viewModel: GistListPageViewModel,
    private var data: List<GistEntity> = emptyList()
) : RecyclerView.Adapter<GistListAdapter.BaseGistListViewHolder<*>>() {

    companion object {
        private const val TYPE_SIMPLE = 0
        private const val TYPE_IMAGE = 1
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseGistListViewHolder<*> =
        when (viewType) {
            TYPE_SIMPLE -> {
                val binding = GistTemplateRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SimpleGistViewHolder(binding)
            }
            TYPE_IMAGE -> {
                val binding = GistTemplateWithImageRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SimpleGistWithImageViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }


    override fun onBindViewHolder(holder: BaseGistListViewHolder<*>, position: Int) {
        val element = data[position]
        when (holder) {
            is SimpleGistViewHolder -> holder.bind(element as GistEntity)
            is SimpleGistWithImageViewHolder -> holder.bind(element as GistEntity)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val element = data[position]
        return if(element.hasImage())
            TYPE_IMAGE
        else
            TYPE_SIMPLE
    }


    fun setData(newData: List<GistEntity>) {
        data = newData
        notifyDataSetChanged()
    }

    abstract class BaseGistListViewHolder<T>(root: View) : RecyclerView.ViewHolder(root) {
        abstract fun bind(item: T)
    }

    inner class SimpleGistViewHolder(val binding: GistTemplateRowBinding) :
        BaseGistListViewHolder<GistEntity>(binding.root) {

        override fun bind(item: GistEntity) {
            binding.viewmodel = viewModel
            binding.gist = item
            binding.executePendingBindings()
        }
    }

    inner class SimpleGistWithImageViewHolder(val binding: GistTemplateWithImageRowBinding) :
        BaseGistListViewHolder<GistEntity>(binding.root) {

        override fun bind(item: GistEntity) {

            Glide.with(itemView.context)
                .load(item.imageUrl)
                .centerCrop()
                .thumbnail()
                .into(itemView.gistTemplateImageView)


            binding.viewmodel = viewModel
            binding.gist = item
            binding.executePendingBindings()
        }
    }
}