package plfi.plfi;

import commun.Point;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AllOf;
import org.junit.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;

import androidx.test.runner.AndroidJUnit4;

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
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TrainingEnigmeTest {
    private static final String PACKAGE_NAME = "plfi.plfi";
    @Rule
    public IntentsTestRule<TrainingEnigme> mainActivityRule = new IntentsTestRule<>(TrainingEnigme.class);
    private boolean dessinEnvoye = false;

    @Test
    public void boutonRetourTrainingEnigme() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Revenir en haut de la page"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_enigme),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        intended(AllOf.allOf(hasComponent(hasShortClassName(".MainActivity")), toPackage(PACKAGE_NAME)));
    }



    @Test
    public void gommeTrainingEnigme() {

        Point[] pointsCercle = new Point[180];
        for (int i = 0; i < 180; i++) {
            pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 180.0) * 50 + 125,
                    (float) Math.sin(i * 2 * Math.PI / 180.0) * 50 + 125);
        }
        for (Point p : pointsCercle)
            mainActivityRule.getActivity().gamePrint.getPoints().add(p);

        assertFalse(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.enigme_new_btn), withContentDescription("New"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        assertTrue(mainActivityRule.getActivity().gamePrint.getPoints().isEmpty());

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
