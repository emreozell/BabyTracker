package com.example.babytracker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.babytracker.R
import com.example.babytracker.data.entity.CalenderItem
import com.example.babytracker.databinding.CalenderRowBinding


class CalenderAdapter(val myDataList: ArrayList<CalenderItem>) : RecyclerView.Adapter<CalenderAdapter.DataVH>() {

    class DataVH(itemView: View): RecyclerView.ViewHolder(itemView){

        private lateinit var binding: CalenderRowBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVH {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.calender_row,parent,false)
        return DataVH(itemView)
    }

    override fun getItemCount(): Int {
        return myDataList.size
    }

    override fun onBindViewHolder(holder: DataVH, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.itemTime_text)
        val textView2= holder.itemView.findViewById<TextView>(R.id.itemname_text)
        val textView3= holder.itemView.findViewById<TextView>(R.id.date_text)
        val image= holder.itemView.findViewById<ImageView>(R.id.status_image_view)
        textView.text=myDataList[position].itemTime
        textView2.text=myDataList[position].itemName
        textView3.text=myDataList[position].itemDate

        if (myDataList[position].itemName=="Sleep"){
            image.setImageResource(R.drawable.vector_selected_sleep)
        }else if(myDataList[position].itemName=="Diaper Change"){
            image.setImageResource(R.drawable.vector_selected_diaper)
        }else {
            image.setImageResource(R.drawable.vector_selected_feeding)
        }


    }

}