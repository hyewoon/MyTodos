package com.example.mytodos

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodos.R.id.item_delete
import com.example.mytodos.databinding.ActivityMainBinding
import com.example.mytodos.roomDB.Todo

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TodoViewModel
   private lateinit var  adapter: TodoAdapter
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
         viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
         binding.lifecycleOwner = this
         binding.data = viewModel

         adapter = TodoAdapter(viewModel)
         binding.recycler.adapter = adapter
         binding.recycler.layoutManager = LinearLayoutManager(this)

         viewModel.getAll().observe(this, Observer { todos ->
             adapter.setData(todos!!)
         })

         binding.floatingButton.setOnClickListener {
             val id = 0
             val item: String = binding.editText.text.toString()
             val todo: Todo = Todo(id, item)
             viewModel.insert(todo)
             binding.editText.setText("")

         }
         adapter.itemClick = object : TodoAdapter.ItemClick {
             override fun onClick(view: View, position: Int) {
                 when (view.id) {
                     R.id.item_container -> itemUpdate(position)
                 }

             }
         }

     }
    fun itemUpdate(position : Int){
        val dialog = EditText(this)
       AlertDialog.Builder(this)
           .setTitle("UPDATE")
           .setView(dialog)
           .setPositiveButton("update", object : OnClickListener {
               override fun onClick(p0: DialogInterface?, p1: Int) {
                   val data = dialog.text.toString()
                   viewModel.update(Todo(position, data))
               }

           }).create()
           .show()

    }


}

