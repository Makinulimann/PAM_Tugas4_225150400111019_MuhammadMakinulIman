package com.example.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var itemList: MutableList<Item>
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Data statis awal
        itemList = mutableListOf(
            Item("Muhammad Makinul Iman", "Universitas Brawijaya", R.drawable.ic_launcher_foreground),
            Item("Allisa Villah", "Universitas Gadjah Mada", R.drawable.ic_launcher_foreground),
            Item("Abdul", "Institut Teknologi Bandung", R.drawable.ic_launcher_foreground)
        )

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        adapter = ItemAdapter(itemList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Tombol untuk menambah item baru
        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            showAddItemDialog() // Tampilkan dialog untuk menambah item
        }
    }

    // Fungsi untuk menampilkan dialog input data
    private fun showAddItemDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.inputName)
        val universityEditText = dialogView.findViewById<EditText>(R.id.inputUniversity)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add New Item")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEditText.text.toString()
                val university = universityEditText.text.toString()

                if (name.isNotEmpty() && university.isNotEmpty()) {
                    addItem(name, university, R.drawable.ic_launcher_foreground) // Gambar default
                } else {
                    Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    // Fungsi untuk menambah item baru ke daftar
    private fun addItem(name: String, university: String, imageResId: Int) {
        val newItem = Item(name, university, imageResId)
        itemList.add(newItem)
        adapter.notifyItemInserted(itemList.size - 1)
        recyclerView.scrollToPosition(itemList.size - 1) // Scroll ke item baru
    }
}

