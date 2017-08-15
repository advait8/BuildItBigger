package com.udacity.nanodegree.advait.builditbiggerv4;


import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.udacity.nanodegree.advait.builditbiggerv4.paid.MainActivity;
import com.udacity.nanodegree.advait.jokeandroidlib.JokeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



/**
 * Created by Advait on 8/10/2017.
 */
@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onButtonClicked() {
        Intents.init();
        onView(withId(R.id.jokeButton)).perform(click());
        intended(hasComponent(JokeActivity.class.getName()));
        Intents.release();
    }
}
