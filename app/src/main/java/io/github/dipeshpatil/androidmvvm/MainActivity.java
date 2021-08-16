package io.github.dipeshpatil.androidmvvm;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.github.dipeshpatil.androidmvvm.fragments.PhotosFragment;
import io.github.dipeshpatil.androidmvvm.fragments.PostsFragment;
import io.github.dipeshpatil.androidmvvm.fragments.TodosFragment;
import io.github.dipeshpatil.androidmvvm.fragments.UsersFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainBottomNavigationView;

    private Fragment postsFragment;
    private Fragment todosFragment;
    private Fragment photosFragment;
    private Fragment usersFragment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBottomNavigationView = findViewById(R.id.bottom_navigation_view_main);

        postsFragment = new PostsFragment();
        todosFragment = new TodosFragment();
        photosFragment = new PhotosFragment();
        usersFragment = new UsersFragment();

        // Calling this method to set PostsFragment as default on start
        replaceFragment(postsFragment);

        mainBottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_posts:
                    replaceFragment(postsFragment);
                    return true;
                case R.id.menu_item_images:
                    replaceFragment(photosFragment);
                    return true;
                case R.id.menu_item_todos:
                    replaceFragment(todosFragment);
                    return true;
                case R.id.menu_item_users:
                    replaceFragment(usersFragment);
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
