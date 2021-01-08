package aqura.ujjwal.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public void dowloadImage(View view){
        Log.i("button tap","ok");
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try {
            myImage= task.execute("https://specials-images.forbesimg.com/imageserve/5d3703e2f1176b00089761a6/960x0.jpg?cropX1=836&cropX2=5396&cropY1=799&cropY2=3364").get();
                    imageView.setImageBitmap(myImage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url= new URL(strings[0]);
                HttpURLConnection connection= (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(in);
         return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}