package com.example.mobileapp_s4675880.recyclerviewcomponents

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mobileapp_s4675880.R
import com.example.mobileapp_s4675880.models.ListItemDataModel

class MyRecyclerviewAdapter(val context: Context, val dataList: List<ListItemDataModel>): Adapter<com.example.mobileapp_s4675880.recyclerviewcomponents.ListItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.mobileapp_s4675880.recyclerviewcomponents.ListItem {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_viewholder, parent, false)

        return ListItem(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(listItem: com.example.mobileapp_s4675880.recyclerviewcomponents.ListItem, position: Int) {
        dataList.get(position).let { data ->
            listItem.firstTileText.text = data.firstTile
            listItem.secondTileText.text = data.secondTile
            listItem.button.text = data.button

            listItem.button.setOnClickListener {
                Toast.makeText(context, data.button, Toast.LENGTH_LONG).show()
            }
        }
    }
}