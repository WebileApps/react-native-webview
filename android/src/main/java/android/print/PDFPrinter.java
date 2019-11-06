package android.print;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.webkit.WebView;

import java.io.File;

public class PDFPrinter {

    private static String TAG = "PDFPrinter";

    public static void createWebPrintJob(final WebView webView) {
        Handler handler = new Handler(webView.getContext().getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                _createWebPrintJob(webView);
            }
        });
    }

    private static void _createWebPrintJob(WebView webView) {
        try {
            PrintManager printManager = (PrintManager) webView.getContext()
                    .getSystemService(Context.PRINT_SERVICE);
        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter("Webview Document");

        printManager.print("Webview Document", printAdapter,
                    new PrintAttributes.Builder().build());
        } catch (Exception e) {
            Log.d(TAG, "Failed to open ParcelFileDescriptor", e);
        }
    }
}
