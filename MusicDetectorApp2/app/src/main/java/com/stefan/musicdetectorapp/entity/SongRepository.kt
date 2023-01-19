//package com.stefan.musicdetectorapp.entity
//
//import android.net.Uri
//import android.util.Log
//import androidx.lifecycle.LiveData
//import com.google.firebase.storage.FirebaseStorage
//import com.stefan.musicdetectorapp.apiSearchEntities.SearchResult
//import java.io.File
//
//class SongRepository(private val songDao: SongDao) {
//    val allSongs: LiveData<List<Song>> = songDao.getAllSongs()
//    fun insert(song: Song) { songDao.insert(song) }
//    fun update(song: Song) { songDao.update(song) }
//    fun delete(song: Song) { songDao.delete(song) } }
//// Add a method for uploading a music file to Firebase Storage
//fun uploadMusicFile(song: Song, file: File) {
//    // Create a FirebaseStorage instance and a StorageReference
//    val storage = FirebaseStorage.getInstance()
//    val storageRef = storage.reference
//
//    // Create a reference to the music file in Firebase Storage
//    val musicRef = storageRef.child("music/${song.songId}.mp3")
//
//    // Create an UploadTask to upload the music file to Firebase Storage
//    val uploadTask = musicRef.putFile(Uri.fromFile(file))
//
//    // Register listeners to track the progress of the upload
//    uploadTask.addOnProgressListener { taskSnapshot ->
//        val progress = (100.0 * taskSnapshot.bytesTransferred) / taskSnapshot.totalByteCount
//        Log.d("SongRepository", "Upload is $progress% done")
//    }.addOnPausedListener {
//        Log.d("SongRepository", "Upload is paused")
//    }.addOnSuccessListener {
//        // Music file successfully uploaded
//    }.addOnFailureListener {
//        // An error occurred while uploading the music file
//    }
//}
//
//// Add a method for downloading a music file from Firebase Storage
//fun downloadMusicFile(song: Song): File {
//    // Create a FirebaseStorage instance and a StorageReference
//    val storage = FirebaseStorage.getInstance()
//    val storageRef = storage.reference
//
//    // Create a reference to the music file in Firebase Storage
//    val musicRef = storageRef.child("music/${song.songId}.mp3")
//
//    // Create a local file to store the downloaded music file
//    val musicFile = File.createTempFile("music", "mp3")
//
//    // Create a DownloadTask to download the music file from Firebase Storage
//    val downloadTask = musicRef .getFile(musicFile)
//// Register listeners to track the progress of the download downloadTask.addOnProgressListener { taskSnapshot -> val progress = (100.0 * taskSnapshot.bytesTransferred) / taskSnapshot.totalByteCount Log.d("SongRepository", "Download is $progress% done") }.addOnPausedListener { Log.d("SongRepository", "Download is paused") }.addOnSuccessListener { // Music file successfully downloaded }.addOnFailureListener { // An error occurred while downloading the music file }
//    return musicFile }
