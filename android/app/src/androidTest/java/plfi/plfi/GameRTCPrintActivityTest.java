package plfi.plfi;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AllOf;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import commun.Dessin;
import commun.Point;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GameRTCPrintActivityTest {
    private static final String PACKAGE_NAME = "plfi.plfi";
    @Rule
    public IntentsTestRule<gameRTCPrint> mainActivityRule = new IntentsTestRule<>(gameRTCPrint.class);
    public boolean testEnvoye= false;

    @Test
    public void button_retour_GameRTCPrint() {

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Revenir en haut de la page"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_rtc_print),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        intended(AllOf.allOf(hasComponent(hasShortClassName(".MainActivity")), toPackage(PACKAGE_NAME)));
    }


    @Test
    public void button_online_GameRTCPrint() {
        // Test que ca change bien de offline à online

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_rtc_offline), withText("OFFLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_rtc_print),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.menu_rtc_online), withText("ONLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_toolbar_training_rtc),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());
        intended(AllOf.allOf(hasComponent(hasShortClassName(".gameRTCPrint")), toPackage(PACKAGE_NAME)));
    }

    @Test
    public void button_offline_GameRTCPrint() {


        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_rtc_offline), withText("OFFLINE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_rtc_print),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView.perform(click());
        intended(AllOf.allOf(hasComponent(hasShortClassName(".Training")), toPackage(PACKAGE_NAME)));
    }

    @Test
    public void button_Gomme_Game(){
        // On insert un Point
        mainActivityRule.getActivity().gamePrint.getPoints().add(new Point(5,6));
        // On vérifie que le canvas est pas vite
        assertFalse(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.new_btn), withContentDescription("New"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());
        // On vérifie que la liste n'est pas vide
        assertTrue(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());

    }





    public void envoye(){
        testEnvoye=true;
    }

    @Test
    public void button_EnvoitServeur_Game(){
        
        assertFalse(testEnvoye);
        mainActivityRule.getActivity().gamePrint.getPoints().add(new Point(5,6));
        mainActivityRule.getActivity().connexion = new Connexion("", "", mainActivityRule.getActivity().controleur) {
            public void sendForme(List<Point> points, String emit) {
                envoye();
        }

        };

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.save_btn), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());
        assertTrue(testEnvoye);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
