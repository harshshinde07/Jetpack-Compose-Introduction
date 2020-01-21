package com.apps.harsh.jetpackcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.ExpandedHeight
import androidx.ui.layout.Spacing
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface {
                    Greeting(name = "Android")
                }
            }
        }
    }
}
// OLD Config
//setContent {
//    MyApp {
//        MyScreenContent()
//    }
//}

@Composable
fun MyApp(children: @Composable() () -> Unit) {
    MaterialTheme {
        Surface(color = Color.Yellow) {
            children()
        }
    }
}

//@Composable
//fun MyScreenContent() {
//    Column {
//        Greeting(name = "Android")
//        Divider(color = Color.Black)
//        Greeting(name = "World!")
//    }
//}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "there"), counterState: CounterState = CounterState()) {
    Column(modifier = ExpandedHeight) {
    Column(modifier = Flexible(1f)) {
        for (name in names) {
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
        Divider(color = Color.Transparent, height = 32.dp)
        Counter(state = counterState)
    }
}

@Model
class CounterState(var count: Int = 0)

@Composable
fun Counter(state: CounterState) {
    Button(
        text = "I've been clicked ${state.count} times!",
        onClick = {
            state.count++
        },
//        style = OutlinedButtonStyle()
    style = ContainedButtonStyle(color = if (state.count > 5) Color.Green else Color.White)
    )
}

/**
 * Whenever you want to query a color or a text style from your Theme, use +MaterialTheme.colors() or +MaterialTheme.typography() in a Composable function.

    e.g. textStyle = (+MaterialTheme.typography()).body1

    e.g. color = (+MaterialTheme.colors()).surface

You can modify a predefined style by using the copy function — they're regular Kotlin data classes!

    e.g. textStyle = (+MaterialTheme.typography()).body1.copy(color = Color.Yellow)

 */

@Composable
fun Greeting(name: String) {
        Text(
            text = "Hello $name!",
            modifier = Spacing(all = 24.dp),
            style = (+MaterialTheme.typography()).h1
        )
}

@Preview("MyScreen Preview")
@Composable
fun DefaultPreview() {
    MyAppTheme {
        Surface {
            Greeting(name = "Android")
        }
    }
}

/**
 * we're saving the state of a Checkbox to a data structure with @Model (so it propagates changes to the Composables that read its value).
 */
@Model
class FormState(var optionChecked: Boolean)

@Composable
fun Form(formState: FormState) {
    Checkbox(
        checked = formState.optionChecked,
        onCheckedChange = { newState -> formState.optionChecked = newState })
}
/*
OTHER Example
Checkbox doesn't store whether it is checked or not. Whenever the user interacts with the Checkbox, it notifies this via the onCheckedChange callback. In our case, we're updating the FormState instance that manages the state of Form. When that happens, since Checkbox is reading the optionChecked variable, it will be recomposed and display the new value. If we did not provide an onCheckedChange lambda, the Checkbox would not change.

Since Form hoists its state, everything flows down from the given FormState parameter. This makes the component easier to test, reuseable in different contexts, and reduces possible bugs from state duplication.
 */