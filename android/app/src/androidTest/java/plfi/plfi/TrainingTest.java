package plfi.plfi;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TrainingTest {
    private static final String PACKAGE_NAME = "plfi.plfi";
    @Rule
    public IntentsTestRule<Training> mainActivityRule = new IntentsTestRule<>(Training.class);

    @Test
    public void bouton_retour_Training() {


        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.my_toolbar_training_rtc),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        intended(AllOf.allOf(hasComponent(hasShortClassName(".MainActivity")), toPackage(PACKAGE_NAME)));
    }

    @Test
    public void bouton_Gomme_Training() {
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


    @Test
    public void button_EnvoitdeForme_Game(){
        //TODO : Faire un groupement de points qui représente toutes les figures et tester que l'image envoyé et donc bien la bonne (Test inutile pour l'instant) cela sera same pour Training enigme
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.save_btn), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

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
