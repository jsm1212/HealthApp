package com.example.healthapp.bbs

import android.os.Parcel
import android.os.Parcelable

class BbsDto(val seq:Int?, val id:String?, val nickname:String?, val title:String?, val content:String?, val wdate:String?,
             val ref:Int?, val step:Int?, val depth:Int?, val del:Int?, val readcount:Int?, val bbsLike:Int?, val bbsImage:String?) : Parcelable {

    constructor(p: Parcel) : this(
        p.readInt(), p.readString(), p.readString(), p.readString(), p.readString(), p.readString(),
        p.readInt(), p.readInt(), p.readInt(), p.readInt(), p.readInt(), p.readInt(), p.readString()
    ) { }

    override fun writeToParcel(p: Parcel, flags: Int) {
        p.writeInt(seq!!)
        p.writeString(id)
        p.writeString(nickname)
        p.writeString(title)
        p.writeString(content)
        p.writeString(wdate)
        p.writeInt(ref!!)
        p.writeInt(step!!)
        p.writeInt(depth!!)
        p.writeInt(del!!)
        p.writeInt(readcount!!)
        p.writeInt(bbsLike!!)
        p.writeString(bbsImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BbsDto> {
        override fun createFromParcel(parcel: Parcel): BbsDto {
            return BbsDto(parcel)
        }

        override fun newArray(size: Int): Array<BbsDto?> {
            return arrayOfNulls(size)
        }
    }
}