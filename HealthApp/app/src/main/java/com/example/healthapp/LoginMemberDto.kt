package com.example.healthapp

import android.os.Parcel
import android.os.Parcelable

class LoginMemberDto(val id:String?, val pwd:String?, val name:String?, val nickname:String?, val gender:String?, val age:Int,
                     val email:String?, val tel:String?, val auth:Int, val regidate:String?): Parcelable{
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
        parcel.readString()
    ) {
    }

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
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginMemberDto> {
        override fun createFromParcel(parcel: Parcel): LoginMemberDto {
            return LoginMemberDto(parcel)
        }

        override fun newArray(size: Int): Array<LoginMemberDto?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "LoginMemberDto(id=$id, pwd=$pwd, name=$name, nickname=$nickname, gender=$gender, age=$age, email=$email, tel=$tel, auth=$auth, regidate=$regidate)"
    }
}