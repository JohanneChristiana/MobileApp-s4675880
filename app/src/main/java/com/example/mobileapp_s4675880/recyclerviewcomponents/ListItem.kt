package com.example.mobileapp_s4675880.recyclerviewcomponents

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mobileapp_s4675880.R

class ListItem(itemView: View) : ViewHolder(itemView) {

    var firstTileText: TextView = itemView.findViewById(R.id.firstTileText)
    var secondTileText: TextView = itemView.findViewById(R.id.secondTileText)
    var button: AppCompatButton = itemView.findViewById(R.id.buttonViewHolderLayout)

}