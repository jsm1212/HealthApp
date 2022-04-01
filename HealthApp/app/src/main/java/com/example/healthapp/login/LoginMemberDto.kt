package com.example.healthapp.login

import android.os.Parcel
import android.os.Parcelable

class LoginMemberDto(val id:String?, val pwd:String?, val name:String?, val nickname:String?, val gender:String?, val age:Int,
                     val email:String?, val tel:String?, val auth:Int, val regidate:String?, val trainer:String?, val del:Int): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(pwd)
        parcel.writeString(name)
        parcel.writeString(nickname)
        parcel.writeString(gender)
        parcel.writeInt(age)
        parcel.writeString(email)
        parcel.writeString(tel)
        parcel.writeInt(auth)
        parcel.writeString(regidate)
        parcel.writeString(trainer)
        parcel.writeInt(del)
    }
    override fun describeContents(): Int {
        return 0
    }
    override fun toString(): String {
        return "LoginMemberDto(id=$id, pwd=$pwd, name=$name, nickname=$nickname, gender=$gender, age=$age, email=$email, tel=$tel, auth=$auth, regidate=$regidate, trainer=$trainer, del=$del)"
    }
    companion object CREATOR : Parcelable.Creator<LoginMemberDto> {
        override fun createFromParcel(parcel: Parcel): LoginMemberDto {
            return LoginMemberDto(parcel)
        }

        override fun newArray(size: Int): Array<LoginMemberDto?> {
            return arrayOfNulls(size)
        }
    }
}