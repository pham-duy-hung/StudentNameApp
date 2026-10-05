package com.ute.studentnameapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ute.studentnameapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // 1. Khai báo Launcher ở cấp độ thuộc tính lớp
    private lateinit var editNameLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Đăng ký Launcher NGAY TRONG onCreate()
        editNameLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // Khi EditActivity trả kết quả về
            if (result.resultCode == Activity.RESULT_OK) {
                val newName = result.data?.getStringExtra("EXTRA_NEW_NAME")
                if (!newName.isNullOrEmpty()) {
                    // Cập nhật lại giao diện với tên mới
                    binding.tvName.text = newName
                    Toast.makeText(this, "Đã cập nhật họ tên!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 3. Sự kiện khi bấm nút "Chỉnh sửa thông tin"
        binding.btnEdit.setOnClickListener {
            val currentName = binding.tvName.text.toString()

            // Tạo Intent chuyển sang EditActivity kèm tên hiện tại
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("EXTRA_CURRENT_NAME", currentName)
            }
            // Mở EditActivity qua Launcher
            editNameLauncher.launch(intent)
        }
    }
}