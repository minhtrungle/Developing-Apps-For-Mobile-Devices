package com.navis.internetexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startClick(View v)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://vnexpress.net/rss/tin-moi-nhat.rss");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                    {
                        InputStream is = connection.getInputStream();
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document xmlDoc = builder.parse(is);
                        NodeList itemNodes = xmlDoc.getElementsByTagName("item");
                        for (int i = 0;i < itemNodes.getLength();i++)
                        {
                            Node node = itemNodes.item(i);
                            Node title = ((Element)node).getElementsByTagName("title").item(0);
                            String strTitle = title.getTextContent();
                            Node desc = ((Element)node).getElementsByTagName("description").item(0);
                            String strDesc = desc.getTextContent();
                            Node link = ((Element)node).getElementsByTagName("link").item(0);
                            String strLink = link.getTextContent();
                            Log.d("MYTAG", strTitle);
                        }
                        is.close();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
