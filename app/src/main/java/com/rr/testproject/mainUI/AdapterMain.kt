package com.rr.testproject.mainUI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.rr.testproject.data.UserData
import com.rr.testproject.databinding.ItemUsersBinding

class AdapterMain : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    var dataList: MutableList<UserData> = ArrayList()
    var selectedItem: MutableLiveData<String> = MutableLiveData()
    var selectedItemDelete: MutableLiveData<UserData> = MutableLiveData()
    var userId: String = ""

    fun setData(data: List<UserData>) {
        this.dataList = data as MutableList<UserData>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataViewHolder.create(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as DataViewHolder
        vh.binding.data = dataList[position]
        vh.binding.llItem.setOnClickListener {
            selectedItem.value = dataList[position].id
            selectedItemDelete.value = dataList[position]
            userId = dataList[position].id
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    class DataViewHolder(var binding: ItemUsersBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(inflater: LayoutInflater?, parent: ViewGroup?): DataViewHolder {
                val itemUsers = ItemUsersBinding.inflate(inflater!!, parent, false)
                return DataViewHolder(itemUsers)
            }
        }
    }

}