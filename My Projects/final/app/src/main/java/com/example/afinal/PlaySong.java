package com.example.afinal;   //activity playsong.java

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlaySong extends AppCompatActivity {
    TextView firstDuration;
    TextView secondDuration;
    boolean stopThread;  //to stop the song
    TextView textView;
    ImageView play,previous,next;
    SeekBar seekBar;
    ArrayList<File>  songs; //it comes from the intent
    MediaPlayer mediaPlayer;
    String textContent;//name of the songs
    int position;
    Thread updateSeek;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopThread=true;
        mediaPlayer.stop();
        mediaPlayer.reset();  //it  means mediaplayer is just created or after pressing the back from the playing music it is in the idle condition
        mediaPlayer.release(); //to release the memory
        //means after clicking on the back button then the current thread is stopped and updateseek is also stopped
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        textView=findViewById(R.id.textView);
        play=findViewById(R.id.play);
        seekBar=findViewById(R.id.seekBar);
        previous=findViewById(R.id.previous);
        next=findViewById(R.id.next);
        firstDuration=findViewById(R.id.firstDuration);
        secondDuration=findViewById(R.id.secondDuration);

        Intent intent =getIntent();//get the intent that we have sent
        Bundle bundle=intent.getExtras();
        songs=(ArrayList) bundle.getParcelableArrayList("songList");//here we have to typecast in the ArrayList

        String stringExtra = intent.getStringExtra("currentSong");
        textContent = stringExtra; //*******************//here we set the text of the currentSong
        textView.setText(stringExtra);//***************
          //here we set the text
        textView.setSelected(true);


        //********************
        int intExtra=intent.getIntExtra("position",0);//it takes 2 argument
        position=intExtra;
        //Uri uri = Uri.parse(songs.get(position).toString());//here we get the songs from the position that we have passed
//        mediaPlayer=MediaPlayer.create(this,uri);//takes 2 argument
//        mediaPlayer.start();

        //********************
        MediaPlayer create = MediaPlayer.create(this, Uri.parse(this.songs.get(intExtra).toString()));
        mediaPlayer = create;
        create.start();

        seekBar.setMax(mediaPlayer.getDuration());
        stopThread=false;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //this method gives the notification that progress level has been changed
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                Log.d("duration", PlaySong.this.mediaPlayer.getCurrentPosition() + " and " + PlaySong.this.mediaPlayer.getDuration());
                if (mediaPlayer.getCurrentPosition() >= mediaPlayer.getDuration() - 200) {
                  next.callOnClick(); //here it means as the song ends then automatically play next song
                }
            }

            @Override
            //notification that user has stared to touch a seekbar
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }

            @Override
            //notification that user has stop touching a seekbar
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        //updateSeek is a method that contains the updated value
        updateSeek = new Thread() {
            public void run() {
                while (!stopThread) {  //here boolean variable comes into play
                    try {
                        if (mediaPlayer != null) {
                            long currentPosition = (long) mediaPlayer.getCurrentPosition();
                            final String format = String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(currentPosition)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(currentPosition) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentPosition)))});
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    firstDuration.setText(format);
                                }
                            });
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
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
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    play.setImageResource(R.drawable.play);
                    mediaPlayer.pause();
                    return;
                }
                play.setImageResource(R.drawable.pause);
                mediaPlayer.start();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();//the instance associated with the mediaplayer instance is gets relesed
                if (position != 0) {
                    PlaySong.this.position--;
                } else {
                    PlaySong playSong = PlaySong.this;
                    playSong.position = playSong.songs.size() - 1;
                }
                Uri parse = Uri.parse(PlaySong.this.songs.get(PlaySong.this.position).toString());//here  we get the songs from the position that we have passed
                PlaySong playSong2 = PlaySong.this;
                playSong2.mediaPlayer = MediaPlayer.create(playSong2.getApplicationContext(), parse);
                PlaySong.this.mediaPlayer.start(); //start the previous song
                PlaySong.this.play.setImageResource(R.drawable.pause);//set the play image
                PlaySong.this.seekBar.setMax(PlaySong.this.mediaPlayer.getDuration());//to find the seekbar maximum position
                PlaySong playSong3 = PlaySong.this;
                playSong3.textContent = playSong3.songs.get(PlaySong.this.position).getName().toString();//from the position get the name of the song
                PlaySong.this.textView.setText(PlaySong.this.textContent);//here we set the text
                long duration = (long) PlaySong.this.mediaPlayer.getDuration(); //now we get the secondsuration
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
                PlaySong.this.play.setImageResource(R.drawable.pause);
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
