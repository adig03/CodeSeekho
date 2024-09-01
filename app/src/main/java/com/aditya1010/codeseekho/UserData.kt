package com.aditya1010.codeseekho

class UserData {

    var name :String? = null
    var email:String?  =null
    var phoneNumber:String? = null
    var password:String? =null

    constructor(name:String? , email:String? ,password:String?){
        this.name = name
        this.email= email
        this.password = password

    }
    constructor(name:String? , email:String? ,password:String?,phoneNumber: String?){
        this.name = name
        this.email= email
        this.password = password
        this.phoneNumber = phoneNumber

    }

    constructor(email:String? , password: String?){
        this.email=email
        this.password = password
    }
}