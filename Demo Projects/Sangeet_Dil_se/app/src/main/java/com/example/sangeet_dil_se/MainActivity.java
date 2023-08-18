package com.example.sangeet_dil_se;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.karumi.dexter.BuildConfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0468R.layout.activity_main);
        final ListView listView = (ListView) findViewById(C0468R.C0471id.listView);
        Dexter.withContext(this).withPermission("android.permission.READ_EXTERNAL_STORAGE").withListener(new PermissionListener() {
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
            }

            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                final ArrayList<File> fetchSongs = MainActivity.this.fetchSongs(Environment.getExternalStorageDirectory());
                String[] strArr = new String[fetchSongs.size()];
                for (int i = 0; i < fetchSongs.size(); i++) {
                    strArr[i] = fetchSongs.get(i).getName().replace(".mp3", BuildConfig.FLAVOR);
                }
                listView.setAdapter(new ArrayAdapter(MainActivity.this, 17367043, strArr));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(MainActivity.this, PlaySong.class);
                        String obj = listView.getItemAtPosition(i).toString();
                        intent.putExtra("songList", fetchSongs);
                        intent.putExtra("currentSong", obj);
                        intent.putExtra("position", i);
                        MainActivity.this.startActivity(intent);
                    }
                });
            }

            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    public ArrayList<File> fetchSongs(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (!file2.isHidden() && file2.isDirectory()) {
                    arrayList.addAll(fetchSongs(file2));
                } else if (file2.getName().endsWith(".mp3") && !file2.getName().startsWith(".")) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }
}
