package edu.wmdd.webviewexample_oct_3_2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);


        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("WEBVIEW", "Finished loading "+url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.d("WEBVIEW", message);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
            }
        });

        Button b = findViewById(R.id.button);
        b.setOnClickListener(v -> {
            String input = ((TextView)findViewById(R.id.editText)).getText().toString();
            webView.evaluateJavascript("foo("+input+")", value -> {
                Log.d("WEVIEW", "returned from webview is: "+value);

            });
        });
        webView.loadUrl("file:///android_asset/test.html");
    }
}
