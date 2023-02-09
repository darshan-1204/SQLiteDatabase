package com.example.sqlitedatabase

class StudentModel {

    var  id = 0
    var name:String
    var surname:String
    var address:String

    constructor(id: Int, name: String, surname: String, address: String) {
        this.id = id
        this.name = name
        this.surname = surname
        this.address = address
    }
}