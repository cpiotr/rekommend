package pl.ciruk.rekommend;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Rek {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://wykop.pl")
                .get()
                .build();
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            System.out.println(response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }
}
