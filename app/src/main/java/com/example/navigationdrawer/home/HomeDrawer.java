package com.example.navigationdrawer.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.chat.ChatHomeActivity;
import com.example.navigationdrawer.notifications.NotificationsActivity;
import com.example.navigationdrawer.people.PeopleActivity;
import com.example.navigationdrawer.youtubechannel.YoutubeActivity;

public class HomeDrawer extends AppCompatActivity {

    //initializing
    DrawerLayout homeDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);

        homeDrawer=findViewById(R.id.homeNav_drawer);
    }
public void ClickMenu(View view){
        //on clicking menu picture on the top bottom bar open drawer
        openDrawer(homeDrawer);
}

    private void openDrawer(DrawerLayout homeDrawer) {
        //open drawer layout
        homeDrawer.openDrawer(GravityCompat.START);

    }

    public void ClickLogo(View view) {
        //On clicking logo,close the drawer
        closeDrawer(homeDrawer);

    }

    private void closeDrawer(DrawerLayout homeDrawer) {
        //close the drawer layout
        if(homeDrawer.isDrawerOpen(GravityCompat.START)){
        //This is to check if the drawer is open just to make sure
            //we are closing whats opened
            homeDrawer.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view) {
        recreate();
    }

    public void ClickNotifications(View view) {
        redirectActivity(this, NotificationsActivity.class);
    }

    private static void redirectActivity(Activity activity,Class clas) {
        Intent intent=new Intent(activity,clas);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    public void ClickChat(View view) {
        redirectActivity(this, ChatHomeActivity.class);
    }

    public void ClickPeople(View view) {
        redirectActivity(this, PeopleActivity.class);
    }

    public void ClickLive(View view) {
        redirectActivity(this, YoutubeActivity.class);
    }

    public void ClickLogout(View view) {
        //close 
        logout(this);
    }

    private static void logout(Activity activity) {
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Do you really want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //finishing
                activity.finishAffinity();
                //exit
                System.exit(0);
            }
        });
        //if not ready to logout
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //display some message of the dismissal
        builder.show();
    }
    protected  void onPause(){
        super.onPause();
        closeDrawer(homeDrawer);

    }
}