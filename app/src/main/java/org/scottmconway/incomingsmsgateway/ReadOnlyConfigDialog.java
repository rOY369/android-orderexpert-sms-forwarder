package org.scottmconway.incomingsmsgateway;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Objects;

public class ReadOnlyConfigDialog {
    private final Context context;
    private final LayoutInflater layoutInflater;

    public ReadOnlyConfigDialog(Context context, LayoutInflater layoutInflater) {
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = layoutInflater.inflate(R.layout.dialog_config_view, null);

        // Set up read-only fields from StaticConfig
        TextView senderText = view.findViewById(R.id.text_sender);
        senderText.setText(StaticConfig.SENDER);

        TextView urlText = view.findViewById(R.id.text_url);
        urlText.setText("Hidden");

        TextView templateText = view.findViewById(R.id.text_template);
        templateText.setText(StaticConfig.TEMPLATE);

        // Hide headers section
        View headersLabel = view.findViewById(R.id.headers_label);
        View headersText = view.findViewById(R.id.text_headers);
        headersLabel.setVisibility(View.GONE);
        headersText.setVisibility(View.GONE);

        TextView retriesText = view.findViewById(R.id.text_retries);
        retriesText.setText(String.valueOf(StaticConfig.RETRIES));

        TextView sslText = view.findViewById(R.id.text_ignore_ssl);
        sslText.setText(String.valueOf(StaticConfig.IGNORE_SSL));

        TextView chunkedText = view.findViewById(R.id.text_chunked_mode);
        chunkedText.setText(String.valueOf(StaticConfig.CHUNKED_MODE));

        builder.setView(view);
        builder.setPositiveButton("Close", null);

        final AlertDialog dialog = builder.show();
        Objects.requireNonNull(dialog.getWindow())
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
} 