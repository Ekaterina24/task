package com.example.retrofitwithpost

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitwithpost.databinding.ActivityMainBinding
import okhttp3.ResponseBody

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
//        searchUser()

        binding.createUserButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateNewUserActivity::class.java))
        }
    }

//    private fun searchUser() {
//        binding.searchButton.setOnClickListener {
//            if (!TextUtils.isEmpty(binding.searchEditText.text.toString())) {
//                viewModel.searchUser(binding.searchEditText.text.toString())
//            } else {
//                viewModel.getUsersList()
//            }
//        }
//    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getUserListObservable().observe(this) {
            Log.d("@@@", it.toString())
            if (it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_SHORT).show()
            } else {
                recyclerViewAdapter.userList = it as MutableList<UserItem>
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
        viewModel.getUsersList()
    }

    override fun onItemEditClick(user: UserItem) {
        val intent = Intent(this@MainActivity, CreateNewUserActivity::class.java)
        intent.putExtra("user_id", user.agent_id)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000) {
            viewModel.getUsersList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}