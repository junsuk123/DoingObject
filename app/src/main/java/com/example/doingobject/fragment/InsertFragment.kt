package com.example.doingobject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doingobject.BaseFragment
import com.example.doingobject.databinding.FragmentInsertBinding
import com.example.doingobject.model.entry.EntryAdapter
import com.example.doingobject.model.entry.EntryDao
import com.example.doingobject.model.entry.EntryDatabase
import com.example.doingobject.model.entry.EntryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertFragment : BaseFragment<FragmentInsertBinding>() {

    private lateinit var entryDao: EntryDao
    private lateinit var entryAdapter: EntryAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInsertBinding {
        return FragmentInsertBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Submit 버튼 클릭 리스너 설정
        binding.submitButton.setOnClickListener {
            val updatedEntries = entryAdapter.getItems() // 수정된 항목들 가져오기
            updateEntries(updatedEntries) // 수정된 항목들을 데이터베이스에 업데이트
        }

        // Previous 버튼 클릭 리스너 설정
        binding.previousButton.setOnClickListener {
            // 이전 버튼을 누르면 프래그먼트를 닫고 액티비티로 돌아가기
            parentFragmentManager.popBackStack()
        }
    }

    private fun updateEntries(updatedEntries: List<EntryEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            entryDao.insertEntry(updatedEntries)
        }
    }
}
