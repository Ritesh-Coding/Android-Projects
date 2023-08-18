package com.example.my_music;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button play;
    private Button play1;


    private SeekBar seekBar;
    private SeekBar seekBar1;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=findViewById(R.id.play);

        seekBar=findViewById(R.id.seekBar);


        //mediaplayer using local source
        //mediaPlayer=MediaPlayer.create(this,R.raw.music1);


        //mediaplayer using web
        mediaPlayer =new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://www.macaronisoup.com/songs/mp3/AsIWasWalkingToTown.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "Ready to play", Toast.LENGTH_SHORT).show();
                mp.start();

                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser)
                        {
                            mediaPlayer.seekTo(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });
        mediaPlayer.prepareAsync();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    play.setText("Play");
                }
                else
                {
                    mediaPlayer.start();

                    play.setText("Pause");
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Toast.makeText(MainActivity.this, "Song is completed", Toast.LENGTH_SHORT).show();
                            mediaPlayer.setLooping(true); //here if the song is repeated

                        }
                    });
                }
            }
        });









        //mediaplayer using web
        mediaPlayer1 =new MediaPlayer();
        try {
            mediaPlayer1.setDataSource("https://www.macaronisoup.com/songs/mp3/AsIWasWalkingToTown.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp1) {
                Toast.makeText(MainActivity.this, "Ready to play", Toast.LENGTH_SHORT).show();
                mp1.start();

                seekBar1.setMax(mediaPlayer1.getDuration());
                seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar1, int progress, boolean fromUser) {
                        if(fromUser)
                        {
                            mediaPlayer1.seekTo(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar1) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar1) {

                    }
                });
            }
        });
        mediaPlayer1.prepareAsync();

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer1.isPlaying())
                {
                    mediaPlayer1.pause();
                    play1.setText("Play");
                }
                else
                {
                    mediaPlayer1.start();

                    play1.setText("Pause");
                    mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Toast.makeText(MainActivity.this, "Song is completed", Toast.LENGTH_SHORT).show();
                            mediaPlayer1.setLooping(true); //here if the song is repeated

                        }
                    });
                }
            }
        });





















    }
}