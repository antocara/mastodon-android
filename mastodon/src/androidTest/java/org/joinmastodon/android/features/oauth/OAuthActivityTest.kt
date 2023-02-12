package org.joinmastodon.android.features.oauth

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(
    AndroidJUnit4::class
)
class OAuthActivityTest {


    // @get:Rule
    // val rule = activityScenarioRule<OAuthActivity>(intent)

    @Before
    fun setup(){
        // launch(DummyActivity::class.java)
    }

    @Test
    fun myTest() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.kdfjghd.com?code=fakeCode"),
            ApplicationProvider.getApplicationContext(),
            OAuthActivity::class.java
        )

        launch<OAuthActivity>(intent).use { scenario ->
            // Your test code goes here
            print("")
        }
    }

    @After
    fun cleanup() {
        //scenario.close()
    }
}

class DummyActivity: AppCompatActivity(){}