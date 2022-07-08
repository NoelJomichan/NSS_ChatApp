package com.example.si_chatapp

class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null
    var choice: Boolean = false

    constructor(){}

    constructor(name: String?, email: String?, uid: String?, choice: Boolean){
        this.name = name
        this.email = email
        this.uid = uid
        this.choice = choice
    }
}