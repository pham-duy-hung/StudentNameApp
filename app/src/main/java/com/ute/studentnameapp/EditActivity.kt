package com.ute.studentnameapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ute.studentnameapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Nhận tên hiện tại từ Intent và điền sẵn vào EditText
        val currentName = intent.getStringExtra("EXTRA_CURRENT_NAME") ?: ""
        binding.edtName.setText(currentName)

        // 2. Sự kiện bấm nút "Lưu & Quay lại"
        binding.btnSave.setOnClickListener {
            val newName = binding.edtName.text.toString().trim()

            if (newName.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Đóng gói tên mới vào Intent phản hồi
            val resultIntent = Intent().apply {
                putExtra("EXTRA_NEW_NAME", newName)
            }

            // Gán kết quả RESULT_OK kèm Intent
            setResult(Activity.RESULT_OK, resultIntent)

            // Đóng EditActivity để quay về MainActivity
            finish()
        }
    }
}