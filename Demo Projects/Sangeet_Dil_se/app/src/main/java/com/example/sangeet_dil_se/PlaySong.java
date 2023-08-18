package com.example.sangeet_dil_se;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlaySong extends AppCompatActivity {
    TextView firstDuration;
    MediaPlayer mediaPlayer;
    ImageView next;
    ImageView play;
    int position;
    ImageView previous;
    TextView secondDuration;
    SeekBar seekBar;
    ArrayList<File> songs;
    boolean stopThread;
    String textContent;
    TextView textView;
    Thread updateSeek;

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.stopThread = true;
        this.mediaPlayer.stop();
        this.mediaPlayer.reset();
        this.mediaPlayer.release();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0468R.layout.activity_play_song);
        this.textView = (TextView) findViewById(C0468R.C0471id.textView);
        this.firstDuration = (TextView) findViewById(C0468R.C0471id.firstDuration);
        this.secondDuration = (TextView) findViewById(C0468R.C0471id.secondDuration);
        this.play = (ImageView) findViewById(C0468R.C0471id.play);
        this.previous = (ImageView) findViewById(C0468R.C0471id.previous);
        this.next = (ImageView) findViewById(C0468R.C0471id.next);
        this.seekBar = (SeekBar) findViewById(C0468R.C0471id.seekBar);
        Intent intent = getIntent();
        this.songs = (ArrayList)intent.getExtras().getParcelableArrayList("songList");
        String stringExtra = intent.getStringExtra("currentSong");
        this.textContent = stringExtra;
        this.textView.setText(stringExtra);
        this.textView.setSelected(true);
        int intExtra = intent.getIntExtra("position", 0);
        this.position = intExtra;
        MediaPlayer create = MediaPlayer.create(this, Uri.parse(this.songs.get(intExtra).toString()));
        this.mediaPlayer = create;
        create.start();
        this.seekBar.setMax(this.mediaPlayer.getDuration());
        this.stopThread = false;
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                Log.d("duration", PlaySong.this.mediaPlayer.getCurrentPosition() + " and " + PlaySong.this.mediaPlayer.getDuration());
                if (PlaySong.this.mediaPlayer.getCurrentPosition() >= PlaySong.this.mediaPlayer.getDuration() - 200) {
                    PlaySong.this.next.callOnClick();
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                PlaySong.this.mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        this.updateSeek = new Thread() {
            public void run() {
                while (!PlaySong.this.stopThread) {
                    try {
                        if (PlaySong.this.mediaPlayer != null) {
                            long currentPosition = (long) PlaySong.this.mediaPlayer.getCurrentPosition();
                            final String format = String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(currentPosition)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(currentPosition) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentPosition)))});
                            PlaySong.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    PlaySong.this.firstDuration.setText(format);
                                }
                            });
                            PlaySong.this.seekBar.setProgress(PlaySong.this.mediaPlayer.getCurrentPosition());
                            sleep(200);
                            Log.d("threadCode", "Updating Success");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("threadCode", "Updating Failed");
                    }
                }
            }
        };
        this.play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PlaySong.this.mediaPlayer.isPlaying()) {
                    PlaySong.this.play.setImageResource(C0468R.C0470drawable.play);
                    PlaySong.this.mediaPlayer.pause();
                    return;
                }
                PlaySong.this.play.setImageResource(C0468R.C0470drawable.pause);
                PlaySong.this.mediaPlayer.start();
            }
        });
        this.previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PlaySong.this.mediaPlayer.stop();
                PlaySong.this.mediaPlayer.release();
                if (PlaySong.this.position != 0) {
                    PlaySong.this.position--;
                } else {
                    PlaySong playSong = PlaySong.this;
                    playSong.position = playSong.songs.size() - 1;
                }
                Uri parse = Uri.parse(PlaySong.this.songs.get(PlaySong.this.position).toString());
                PlaySong playSong2 = PlaySong.this;
                playSong2.mediaPlayer = MediaPlayer.create(playSong2.getApplicationContext(), parse);
                PlaySong.this.mediaPlayer.start();
                PlaySong.this.play.setImageResource(C0468R.C0470drawable.pause);
                PlaySong.this.seekBar.setMax(PlaySong.this.mediaPlayer.getDuration());
                PlaySong playSong3 = PlaySong.this;
                playSong3.textContent = playSong3.songs.get(PlaySong.this.position).getName().toString();
                PlaySong.this.textView.setText(PlaySong.this.textContent);
                long duration = (long) PlaySong.this.mediaPlayer.getDuration();
                PlaySong.this.secondDuration.setText(String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(duration)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)))}));
            }
        });
        this.next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PlaySong.this.mediaPlayer.stop();
                PlaySong.this.mediaPlayer.release();
                if (PlaySong.this.position != PlaySong.this.songs.size() - 1) {
                    PlaySong.this.position++;
                } else {
                    PlaySong.this.position = 0;
                }
                Uri parse = Uri.parse(PlaySong.this.songs.get(PlaySong.this.position).toString());
                PlaySong playSong = PlaySong.this;
                playSong.mediaPlayer = MediaPlayer.create(playSong.getApplicationContext(), parse);
                PlaySong.this.mediaPlayer.start();
                PlaySong.this.play.setImageResource(C0468R.C0470drawable.pause);
                PlaySong.this.seekBar.setMax(PlaySong.this.mediaPlayer.getDuration());
                PlaySong playSong2 = PlaySong.this;
                playSong2.textContent = playSong2.songs.get(PlaySong.this.position).getName().toString();
                PlaySong.this.textView.setText(PlaySong.this.textContent);
                long duration = (long) PlaySong.this.mediaPlayer.getDuration();
                PlaySong.this.secondDuration.setText(String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(duration)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)))}));
            }
        });
        long duration = (long) this.mediaPlayer.getDuration();
        this.secondDuration.setText(String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(duration)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)))}));
        this.updateSeek.start();
    }
}
