package com.example.noahr.photoapp.Views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.noahr.photoapp.Domain.FollowRequest;
import com.example.noahr.photoapp.Domain.User;
import com.example.noahr.photoapp.R;
import com.example.noahr.photoapp.Services.FollowNetworkLayer;
import com.example.noahr.photoapp.Views.CentralAcc;

import java.util.ArrayList;


// Fragment that displays to user a list of all the users they follow
public class FollowListFrag extends Fragment {
    private View myView;
    static public ListView listView;
    static public ArrayAdapter arrayAdapter;
    static public Button addFollowButton;
    static public AutoCompleteTextView addFollowEditText;
    static public ArrayList<String> usersIFollow = new ArrayList<>();

    public FollowListFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_friendslist, container, false);
        listView = (ListView) myView.findViewById(R.id.friendsListViewXml);
        addFollowButton = (Button) myView.findViewById(R.id.button);
        addFollowEditText = (AutoCompleteTextView) myView.findViewById(R.id.addFollowEditTextXml);

        // Instantiate the network layer, and start the operation of getting a
        // list of all the people the user follows from the server.
        FollowNetworkLayer followNetworkLayer = new FollowNetworkLayer(new FollowRequest(), getActivity(), addFollowEditText, arrayAdapter, listView);
        followNetworkLayer.getAllWhoIFollow(CentralAcc.myUsername);
        followNetworkLayer.getFriendsOfFriends(CentralAcc.myUsername);

        // When the button is clicked, open a textfield to type the user by name.
        addFollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameToAdd = addFollowEditText.getText().toString();
                // Verify that the textField isn't blank.
                if(usernameToAdd.length() > 0){
                    User userToAdd = new User();
                    userToAdd.setUsername(usernameToAdd);
                    User currUser = new User();
                    currUser.setUsername(CentralAcc.myUsername);
                    FollowRequest addFollowRequest = new FollowRequest(currUser, userToAdd);
                    FollowNetworkLayer followNetworkLayer = new FollowNetworkLayer(addFollowRequest, getActivity(), addFollowEditText, arrayAdapter, listView);
                    followNetworkLayer.addFollow();
                }
                else{
                    Toast.makeText(getActivity(), "Please enter valid username", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Set an OnItemClick listener, so when the user clicks on a user's name, they have the
        // option to delete them.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String userToDelete = (String) parent.getItemAtPosition(position);
                PopupMenu popUp = new PopupMenu(getActivity(), view);
                popUp.getMenuInflater().inflate(R.menu.popup_menu, popUp.getMenu());

                // Set listener for when the user clicks "unfollow" within the popup menu.
                popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        // Create a follownetworklayer, and initiate the operation of deleting a user.
                        FollowNetworkLayer followNetworkLayer = new FollowNetworkLayer(getActivity());
                        followNetworkLayer.deleteUser(CentralAcc.myUsername + " " + userToDelete);
                        usersIFollow.remove(userToDelete);
                        arrayAdapter.notifyDataSetChanged();
                        return true;
                    }
                });

                popUp.show();
            }
        });

        return myView;
    }
}
