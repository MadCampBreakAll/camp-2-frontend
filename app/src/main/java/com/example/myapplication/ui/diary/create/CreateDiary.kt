package com.example.myapplication.ui.diary.create

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.api.diary.dto.CreateDiaryDto
import com.example.myapplication.api.diary.dto.CreateDiaryRequestDto
import com.example.myapplication.api.friend.dto.FriendDto

class CreateDiary {
    private var title: String = ""
    private var friends: MutableList<FriendDto>
    private var selectedFriends: MutableList<FriendDto?>

    constructor(friends: MutableList<FriendDto>){
        this.friends = friends
        this.selectedFriends = MutableList(CreateDiary.MAX_CAPACTIRY) {
            null
        }
    }

    fun getCanSelectedFriends() : List<FriendDto>{
        return this.friends.filter {
            !selectedFriends.contains(it)
        }
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

    companion object {
        val MAX_CAPACTIRY = 4
    }

}