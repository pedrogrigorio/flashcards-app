package com.example.flashcards_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashcards_app.models.User;
import com.example.flashcards_app.repositories.AddUserRepository;

import java.util.ArrayList;
import java.util.List;

public class AddUserViewModel extends ViewModel {

    private MutableLiveData<List<User>> addFriendsLiveData = new MutableLiveData<>();
    private AddUserRepository addUserRepository;

    public AddUserViewModel() {
        addUserRepository = new AddUserRepository();
    }


    public void getSearchRequest(String request) {

        //        if (addFriendsLiveData.getValue() == null || addFriendsLiveData.getValue().isEmpty()) {
//            addFriendsLiveData = addFriendRepository.getNewFriendsToAdd();
//        }

        MutableLiveData<List<User>> tempDataLive = new MutableLiveData<>();

        List<User> tempData = new ArrayList<>();

        tempData.add(new User("Em busca do shape inexplicavel", "@ShapeBolado"));
        tempData.add(new User("Maromberio raiz", "@EmBuscaDos140KG"));
        tempData.add(new User("Buscando 170kg", "@PeitroalArnold"));

        tempDataLive.setValue(tempData);
        addFriendsLiveData = tempDataLive;


    }


    public LiveData<List<User>> getAddFriends() {

        return addFriendsLiveData;
    }
    public void insertFriend(User user) {
        List<User> currentFriends = addFriendsLiveData.getValue();

        if (currentFriends == null) {
            currentFriends = new ArrayList<>();
        }

        currentFriends.add(user);

        addFriendsLiveData.setValue(currentFriends);
    }



//    public void deleteFriend(int position) {
//        Adicionar funcionalidade em breve
//    }



}
