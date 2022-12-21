package com.example.retrofitwithpost

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitwithpost.databinding.ActivityCreateNewUserBinding
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.RequestBody.Companion.toRequestBody

class CreateNewUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateNewUserBinding
    lateinit var viewModel: CreateNewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val user_id = intent.getStringExtra("user_id")
        initViewModel()
        createUserObservable()

//        if (user_id != null) {
//            loadUserData(user_id)
//        }
        binding.createButton.setOnClickListener {
            createUser()
        }
        binding.deleteButton.setOnClickListener {
//            deleteUser(user_id)
        }

        OnClickTime()
    }

    private fun OnClickTime() {
        val textView = binding.textView
        val timePicker = binding.editTextDate
        timePicker.setOnTimeChangedListener { _, hour, minute ->
            var hour = hour
            if (textView != null) {
                val hour = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                val msg = "$hour:$min"
                textView.text = msg
                textView.visibility = ViewGroup.VISIBLE
            }
        }
    }

//    private fun deleteUser(user_id: String?) {
//        viewModel.getDeleteUserObservable().observe(this) {
//            if (it == null) {
//                Toast.makeText(this@CreateNewUserActivity, "Failed to delete user...", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this@CreateNewUserActivity, "Successfully delete user...", Toast.LENGTH_SHORT).show()
//                finish()
//            }
//        }
//        viewModel.deleteUser(user_id)
//    }
//
//    private fun loadUserData(user_id: String?) {
//        viewModel.getLoadUserObservable().observe(this) {
//            if (it != null) {
//
////                binding.editTextDate.setText(it.datetime)
////                binding.editTextType.setText(it.type)
//                binding.editTextDuration.setText(it.duration)
//                binding.editTextComment.setText(it.comment)
//
//                binding.createButton.text = "Update"
//                binding.deleteButton.visibility = View.VISIBLE
//            }
//        }
//        viewModel.getUserData(user_id)
//    }

    @SuppressLint("SuspiciousIndentation")
    private fun createUser() {

        val user = ResponseItem(
            1,
            binding.textView.text.toString(),
            binding.editTextType.text.toString(),
            binding.editTextDuration.text.toString(),
            binding.editTextComment.text.toString()
        )

        val date = user.datetime
        val type = user.type
        val duration = user.duration
        val comment = user.comment


//        val jsonObject = JSONObject()
//
////        jsonObject = user.agent_id
//        jsonObject.put("agent_id", 1)
//        jsonObject.put("datetime", binding.textView.text.toString())
//        jsonObject.put("type",  binding.editTextType.text.toString())
//        jsonObject.put("duration",  binding.editTextDuration.text.toString())
//        jsonObject.put("comment",  binding.editTextComment.text.toString())
//
//        Log.d("@@@", user)


        viewModel.createUser(user)
//        else
//            viewModel.updateUser(requestBody, user)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreateNewUserViewModel::class.java)
    }

    private fun createUserObservable() {
        viewModel.getCreateNewUserObservable().observe(this) {
            if (it == null) {
                Toast.makeText(
                    this@CreateNewUserActivity,
                    "Failed to create/update new user...",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@CreateNewUserActivity,
                    "Successfully created/updated user...",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}