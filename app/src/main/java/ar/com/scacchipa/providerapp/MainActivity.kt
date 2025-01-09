package ar.com.scacchipa.providerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ar.com.scacchipa.providerapp.ui.theme.ProviderAppTheme

/**
 * How to test the Key Content Provider on terminal
 * ./adb -s emulator-5554 shell content query --uri content://ar.com.scacchipa.providerapp/easyKey
 * ./adb -s emulator-5554 shell content query --uri content://ar.com.scacchipa.providerapp/mediumKey
 * ./adb -s emulator-5554 shell content query --uri content://ar.com.scacchipa.providerapp/hardKey
 *
 */


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ProviderAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProviderAppTheme {
        Greeting("Android")
    }
}