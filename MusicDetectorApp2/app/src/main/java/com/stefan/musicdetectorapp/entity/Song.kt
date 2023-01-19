//package com.stefan.musicdetectorapp.entity
//
//import android.os.Parcel
//import android.os.Parcelable
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = "songs")
//data class Song(
//    @PrimaryKey(autoGenerate = true) var songId: Long = 0,
//    @ColumnInfo(name = "title") var title: String?,
//    @ColumnInfo(name = "artist") var artist: String?,
//    @ColumnInfo(name = "album") var album: String?,
//    @ColumnInfo(name = "duration") var duration: Int,
//    @ColumnInfo(name = "genre") var genre: String?,
//    @ColumnInfo(name = "released_year") var releasedYear: Int,
//) : Parcelable {
//    constructor(parcel: Parcel) : this(parcel.readLong(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readInt())
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeLong(songId)
//        parcel.writeString(title)
//        parcel.writeString(artist)
//        parcel.writeString(album)
//        parcel.writeInt(duration)
//        parcel.writeString(genre)
//        parcel.writeInt(releasedYear)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Song> {
//        override fun createFromParcel(parcel: Parcel): Song {
//            return Song(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Song?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
