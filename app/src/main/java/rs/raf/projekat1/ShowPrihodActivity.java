package rs.raf.projekat1;

import androidx.appcompat.app.AppCompatActivity;
import rs.raf.projekat1.fragments.UnosFragment;
import rs.raf.projekat1.models.Prihod;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class ShowPrihodActivity extends AppCompatActivity {

    private Button btnBack;

    private EditText naslov;
    private EditText suma;
    private TextView opis;
    private ImageView playView, pauseView;

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private AudioFocusRequest audioFocusRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_prihod);
        initView();
        initSomething();
    }

    private void initView(){
        btnBack = findViewById(R.id.btn_back);

        naslov=  findViewById(R.id.et_naslov);
        suma = findViewById(R.id.et_kolicina);
        opis = findViewById(R.id.editTextOpis);
        playView = findViewById(R.id.imgvplay);
        pauseView=findViewById(R.id.imgpause);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("naslov");
        String data2 = bundle.getString("suma");
        String data3 = bundle.getString("opis");
        boolean data4 = bundle.getBoolean("audio");

        naslov.setText(data);
        suma.setText(data2);
        if(data4){
            opis.setVisibility(View.GONE);
            playView.setVisibility(View.VISIBLE);
            initPlayer();
            initListeners();

        } else{
            opis.setText(data3);
        }
    }

    private void initSomething(){
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void initListeners(){

        playView.setOnClickListener(v -> play());
        pauseView.setOnClickListener(v -> pause());

        // We have to handle focus changes
        onAudioFocusChangeListener = focusChange -> {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS: {
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a short amount of time.
                    // The AUDIOFOCUS_LOSS case means we've lost audio focus
                    //Timber.e("AUDIOFOCUS_LOSS_TRANSIENT or AUDIOFOCUS_LOSS");
                    pause();
                } break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                    // The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume.
                    //Timber.e("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    playerDuck(true);
                } break;
                case AudioManager.AUDIOFOCUS_GAIN: {
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                    //Timber.e("AUDIOFOCUS_GAIN");
                    playerDuck(false);
                    play();
                } break;
            }
        };

        mediaPlayer.setOnCompletionListener(mp -> {
            // Hide pause button
            pauseView.setVisibility(View.GONE);
            // Show play button
            playView.setVisibility(View.VISIBLE);
            // Set media player to initial position
            mediaPlayer.seekTo(0);
        });

        // Description of the audioFocusRequest
        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAcceptsDelayedFocusGain(true)
                .setWillPauseWhenDucked(true)
                .setOnAudioFocusChangeListener(onAudioFocusChangeListener)
                .build();

    }

    private void initPlayer(){
        if(UnosFragment.file != null){
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(UnosFragment.file));
        }

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    }

    private void play(){
        int result = audioManager.requestAudioFocus(audioFocusRequest);
        if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            playView.setVisibility(View.GONE);
            pauseView.setVisibility(View.VISIBLE);
            mediaPlayer.start();
        }
    }

    private void pause(){
        pauseView.setVisibility(View.GONE);
        playView.setVisibility(View.VISIBLE);
        mediaPlayer.pause();
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
        }

        mediaPlayer=null;
        audioManager.abandonAudioFocusRequest(audioFocusRequest);

    }

    public synchronized void playerDuck(boolean duck) {
        if (mediaPlayer != null) {
            // Reduce the volume when ducking - otherwise play at full volume.
            mediaPlayer.setVolume(duck ? 0.2f : 1.0f, duck ? 0.2f : 1.0f);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Bundle bundle = getIntent().getExtras();
        boolean data4 = bundle.getBoolean("audio");
        if(data4)
        releaseMediaPlayer();
    }
}