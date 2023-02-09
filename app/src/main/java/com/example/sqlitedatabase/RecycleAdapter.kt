package com.example.sqlitedatabase

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sqlitedatabase.Database.DataBase

class RecycleAdapter(list: ArrayList<StudentModel>) : Adapter<RecycleAdapter.StudentHolder>() {

    var list = list
    lateinit var context : Context
    lateinit var database: DataBase

    class StudentHolder(itemView: View) : ViewHolder(itemView){

        var txtId: TextView = itemView.findViewById(R.id.tvId)
        var txtName: TextView = itemView.findViewById(R.id.tvName)
        var txtSurname: TextView = itemView.findViewById(R.id.tvSurname)
        var txtAddress: TextView = itemView.findViewById(R.id.tvAddress)
        var btnDelete = itemView.findViewById<ImageView>(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {

        context = parent.context

        return StudentHolder(LayoutInflater.from(parent.context).inflate(R.layout.std_data_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {

        database = DataBase(context)

        holder.txtId.text = list[position].id.toString()
        holder.txtName.text = list[position].name
        holder.txtSurname.text = list[position].surname
        holder.txtAddress.text = list[position].address

        holder.btnDelete.setOnClickListener {

            database.deleteData(list[position].id)
        }

        holder.itemView.setOnClickListener {

            var dialog = Dialog(context)
            dialog.setContentView(R.layout.data_update)

            var did = dialog.findViewById<TextView>(R.id.edtId)
            var dname = dialog.findViewById<EditText>(R.id.edtName)
            var dsurname = dialog.findViewById<EditText>(R.id.edtSurname)
            var daddress = dialog.findViewById<EditText>(R.id.edtAddress)
            var btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)

            did.text = list.get(position).id.toString()
            dname.setText(list.get(position).name)
            dsurname.setText(list.get(position).surname)
            daddress.setText(list.get(position).address)

            btnUpdate.setOnClickListener {

                database.updateData(
                    dname.text.toString(),
                    dsurname.text.toString(),
                    daddress.text.toString(),
                    did.text.toString().toInt()
                )
                dialog.dismiss()
            }
            dialog.show()
        }


    }
}