package com.example.goolemap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.goolemap.Authorization.Login;
import com.example.goolemap.Authorization.Registration;
import com.example.goolemap.Fragment.FragmentFlat;
import com.example.goolemap.Fragment.FragmentHostel;
import com.example.goolemap.Fragment.FragmentRoom;
import com.example.goolemap.Fragment.FragmentHome;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdepter pageAdepter;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();

        tabLayout = findViewById(R.id.tabLayoutId);
        viewPager = findViewById(R.id.viewPagerId);
        pageAdepter = new ViewPageAdepter(getSupportFragmentManager());
        pageAdepter.addFragment(new FragmentHome(),"");
        pageAdepter.addFragment(new FragmentFlat(),"Flat");
        pageAdepter.addFragment(new FragmentRoom(),"Room");
        pageAdepter.addFragment(new FragmentHostel(),"Hostel");
        viewPager.setAdapter(pageAdepter);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolBarId);
        toolbar.setTitle("Easy Rent");
        setSupportActionBar(toolbar);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_user);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF0000"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);

        MenuItem registration = menu.findItem(R.id.reg);
        MenuItem login = menu.findItem(R.id.login);
        MenuItem logout = menu.findItem(R.id.logout);

        if (user == null)
        {
            registration.setVisible(true);
            login.setVisible(true);
            logout.setVisible(false);
        }else
        {
            logout.setVisible(true);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {

            case R.id.login:
                Intent intent2 = new Intent(MainActivity.this,Login.class);
                intent2.putExtra("loginPage","loginPage");
                startActivity(intent2);
                return true;

            case R.id.reg:
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
                return true;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
