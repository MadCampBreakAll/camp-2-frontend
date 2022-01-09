package com.example.myapplication.ui.diary.create

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.api.diary.dto.CreateDiaryDto
import com.example.myapplication.api.diary.dto.CreateDiaryRequestDto
import com.example.myapplication.api.friend.dto.FriendDto
import com.example.myapplication.api.page.dto.UserDto
import com.google.gson.annotations.SerializedName

class CreateDiary {
    private var title: String = ""
    private var friends: MutableList<FriendDto> = mutableListOf()
    private var selectedFriends: MutableList<FriendDto?> = mutableListOf(null, null, null, null)

    fun setFriends(friends: List<FriendDto>){
        this.friends.addAll(friends)
    }

    fun setMe(userDto: com.example.myapplication.api.user.dto.UserDto) {
        val (id, nickname, body, bodyColor, blushColor, item, _) = userDto
        try {
            this.selectedFriends[0] = FriendDto(
                id!!,
                nickname!!,
                body!!,
                bodyColor!!,
                blushColor!!,
                item!!
            )
        } catch (e: Throwable) {
            Log.d("DEBUG", "SET ME FAIL")
            e.printStackTrace()
        }
    }

    fun getCanSelectedFriends() : List<FriendDto>{
        return this.friends.filter {
            !selectedFriends.contains(it)
        }
    }

    fun getSelectedFriend(): List<FriendDto?> {
        return this.selectedFriends.toList()
    }



    @RequiresApi(Build.VERSION_CODES.N)
    fun selectFriend(index: Int, friendDto: FriendDto){
        selectedFriends.replaceAll {
            if(it?.id == friendDto.id){
                null
            }
            it
        }
        selectedFriends[index] = friendDto
    }

    fun selectedFriendSize() : Int{
        return this.selectedFriends.size
    }

    fun setTitle(title: String){
        this.title = title
    }

    fun toCreateDiaryRequestDto() : CreateDiaryRequestDto {
        return CreateDiaryRequestDto(
            title,
            userIds = selectedFriends.filter {
                it != null
            }.map { it!!.id }
        )
    }

    fun remove(friendId: Int) {
        this.selectedFriends[friendId] = null
    }

    companion object {
        val MAX_CAPACTIRY = 4
    }

}