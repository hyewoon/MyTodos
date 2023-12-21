package com.example.mytodos

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mytodos.databinding.TodoItemBinding
import com.example.mytodos.roomDB.Todo

class TodoAdapter(val viewModel: TodoViewModel): RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

     var items = emptyList<Todo>()



    interface ItemClick {  //클릭이벤트추가부분
        fun onClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null  //클릭이벤트추가부분



    inner class MyViewHolder(var binding: TodoItemBinding) : ViewHolder(binding.root){
        fun bind(item : Todo){
            binding.todo = item
            //1. delete처리
           binding.itemDelete.setOnClickListener {
                viewModel.delete(item)
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = TodoItemBinding.inflate(inflater, parent,false )
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TodoAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { //클릭이벤트추가부분
             itemClick?.onClick(it, position)
          /*  holder.binding.delete.setOnClickListener {
              Log.d("delete", items.get(position).todoList.toString())

                viewModel.delete(Todo(position, items[position].todoList))
            }
*/
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
    fun setData(todo : List<Todo>){
        this.items = todo
        notifyDataSetChanged()
    }




}