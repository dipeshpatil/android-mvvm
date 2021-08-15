package io.github.dipeshpatil.androidmvvm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainBottomNavigationView;
    private Fragment postsFragment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBottomNavigationView = findViewById(R.id.bottom_navigation_view_main);

        postsFragment = new PostsFragment();

        // Calling this method to set PostsFragment as default on start
        replaceFragment(postsFragment);

        mainBottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_posts:
                    replaceFragment(postsFragment);
                    Toast.makeText(MainActivity.this, "postsFragment", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_item_images:
                    Toast.makeText(MainActivity.this, "imagesFragment", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_item_todos:
                    Toast.makeText(MainActivity.this, "todosFragment", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_item_users:
                    Toast.makeText(MainActivity.this, "usersFragment", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_main, fragment);
        transaction.commit();
    }
}
