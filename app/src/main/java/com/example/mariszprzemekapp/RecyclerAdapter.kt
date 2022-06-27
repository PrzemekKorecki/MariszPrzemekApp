package com.example.mariszprzemekapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.mycardview.view.*
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapter(private val arrayData: ArrayList<MessageApp>): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val contactRow = layoutInflater.inflate(R.layout.mycardview, parent, false)
        return MyViewHolder(contactRow)
    }
    override fun getItemCount(): Int {
        return arrayData.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.lblMessage.text = arrayData[holder.adapterPosition].message
        holder.lblSender.text = arrayData[holder.adapterPosition].senderName
        holder.lblZonk.text = arrayData[holder.adapterPosition].zonk

    }
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val lblMessage = view.lblMessage
        val lblSender = view.lblSender
        val lblZonk = view.lblZonk

    }
}