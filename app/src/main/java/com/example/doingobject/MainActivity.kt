package com.example.doingobject

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doingobject.databinding.ActivityMainBinding
import com.example.doingobject.fragment.InsertFragment
import com.example.doingobject.model.product.ProductAdapter
import com.example.doingobject.model.product.ProductDao
import com.example.doingobject.model.product.ProductDatabase
import com.example.doingobject.model.product.ProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productDao: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        // 데이터베이스 초기화
        val database = ProductDatabase.getDatabase(this)
        productDao = database.productDao()

        // 리사이클러뷰 설정
        productAdapter = ProductAdapter(mutableListOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = productAdapter

        // 버튼 클릭 리스너 설정
        binding.addItemButton.setOnClickListener {
            addNewItem()
        }

        binding.submitButton.setOnClickListener {
            openInsertFragment() // 인설트 프래그먼트로 이동
        }
    }

    private fun addNewItem() {
        val newItem = ProductEntity(
            name = "",
            description = "",
            quantity = 0
        )
        CoroutineScope(Dispatchers.IO).launch {
            productDao.insertProduct(newItem)
        }
    }

    private fun openInsertFragment() {
        val fragment = InsertFragment()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment) // 전체 화면으로 설정
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()

        // 데이터베이스에서 아이템 가져와서 어댑터에 설정
        CoroutineScope(Dispatchers.Main).launch {
            val products = productDao.getAllProducts()
            productAdapter.setItems(products)
        }
    }
}
