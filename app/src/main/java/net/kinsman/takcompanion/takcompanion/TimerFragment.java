package net.kinsman.takcompanion.takcompanion;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;


public class TimerFragment extends Fragment implements View.OnClickListener {

    public class Player
    {
        public long millisecondsLeft;
        public TextView timerTextView;
        public ToggleButton goButton;
    }

    private View view;
    private int baseTime;
    private int turnTime;
    private boolean gameStarted;
    private boolean gameOver;
    private long lastFrameTime;
    private ToggleButton pauseToggle;
    private Button bttnReset;
    public Player playerOne = new Player();
    public Player playerTwo = new Player();
    public Player activePlayer;

    private void initializeTimerValues()
    {
        timerHandler.removeCallbacks(timerRunnable);
        activePlayer = null;
        gameStarted = false;
        gameOver = false;
        pauseToggle.setEnabled(false);
        pauseToggle.setChecked(false);
        playerOne.millisecondsLeft = baseTime;
        showPlayerTimeRemaining(playerOne);
        playerOne.goButton.setEnabled(true);
        playerOne.goButton.setChecked(false);
        playerOne.goButton.setText(R.string.start_turn);
        playerTwo.millisecondsLeft = baseTime;
        showPlayerTimeRemaining(playerTwo);
        playerTwo.goButton.setEnabled(true);
        playerTwo.goButton.setChecked(false);
        playerTwo.goButton.setText(R.string.start_turn);

    }

    private void startGame(Player startingPlayer) {
        gameStarted = true;
        pauseToggle.setEnabled(true);
        playerOne.goButton.setText(R.string.pass_turn);
        playerTwo.goButton.setText(R.string.pass_turn);
        playerOne.goButton.setTextOn(getString(R.string.pass_turn));
        playerTwo.goButton.setTextOn(getString(R.string.pass_turn));
        playerOne.goButton.setTextOff(getString(R.string.pass_turn));
        playerTwo.goButton.setTextOff(getString(R.string.pass_turn));
        playerOne.goButton.setEnabled(false);
        playerTwo.goButton.setEnabled(false);
        showPlayerTimeRemaining(playerOne);
        showPlayerTimeRemaining(playerTwo);
        lastFrameTime = System.currentTimeMillis();
        activePlayer = startingPlayer;
        activePlayer.millisecondsLeft += turnTime;
        activePlayer.goButton.setEnabled(true);
        timerHandler.postDelayed(timerRunnable, 500);
    }

    private void endGame()
    {
        gameOver = true;
        playerOne.goButton.setEnabled(false);
        playerTwo.goButton.setEnabled(false);
        pauseToggle.setEnabled(false);
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            updateTimeLeft(activePlayer);
            showPlayerTimeRemaining(activePlayer);
            if (!gameOver)
            {
                timerHandler.postDelayed(timerRunnable, 500);
            }
        }
    };

    private void changePlayer(Player newPlayer) {
        updateTimeLeft(activePlayer);
        activePlayer.goButton.setEnabled(false);
        activePlayer = newPlayer;
        activePlayer.goButton.setEnabled(true);
        activePlayer.goButton.setChecked(true);
        activePlayer.millisecondsLeft += turnTime;
    }

    private void updateTimeLeft(Player updatePlayer) {
        updatePlayer.millisecondsLeft -= System.currentTimeMillis() - lastFrameTime;
        lastFrameTime = System.currentTimeMillis();

        if (updatePlayer.millisecondsLeft < 0)
        {
            endGame();
        }
    }

    private void showPlayerTimeRemaining(Player player) {
        int minutes = (int) (player.millisecondsLeft / 1000) / 60;
        int seconds = (int) (player.millisecondsLeft / 1000) % 60;

        player.timerTextView.setText(String.format("%d:%02d", minutes, seconds));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bttn_pause:
                pauseClick(v);
                break;
            case R.id.bttn_reset:
                resetClick(v);
                break;
            case R.id.player1Go:
                player1Click(v);
                break;
            case R.id.player2Go:
                player2Click(v);
                break;
        }
    }

    public void player1Click(View v) {
        if (!gameStarted)
        {
            startGame(playerOne);
        }
        else
        {
            changePlayer(playerTwo);
        }
    }

    public void player2Click(View v){
        if (!gameStarted)
        {
            startGame(playerTwo);
        }
        else
        {
            changePlayer(playerOne);
        }
    }

    public void pauseClick(View v){
        if(pauseToggle.isChecked())
        {
            timerHandler.removeCallbacks(timerRunnable);
            activePlayer.goButton.setEnabled(false);
        }
        else
        {
            activePlayer.goButton.setEnabled(true);
            lastFrameTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
        }

    }

    public void resetClick(View v){
        initializeTimerValues();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timer, container, false);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("timerSettings", Context.MODE_PRIVATE);
        baseTime = sharedPref.getInt("baseTimeMillis", R.integer.baseTimeMillis);
        turnTime = sharedPref.getInt("turnTimeMillis", R.integer.turnTimeMillis);

        pauseToggle = (ToggleButton) view.findViewById(R.id.bttn_pause);
        pauseToggle.setOnClickListener(this);
        pauseToggle.setTransformationMethod(null);
        ImageSpan pauseSpan = new ImageSpan(TimerFragment.this.getActivity(), R.drawable.ic_pause_black_24dp);
        SpannableString pauseContent = new SpannableString("X");
        pauseContent.setSpan(pauseSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pauseToggle.setTextOff(pauseContent);
        pauseToggle.setText(pauseContent);
        ImageSpan playSpan = new ImageSpan(TimerFragment.this.getActivity(), R.drawable.ic_play_arrow_black_24dp);
        SpannableString playContent = new SpannableString("X");
        playContent.setSpan(playSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        pauseToggle.setTextOn(playContent);

        bttnReset = (Button) view.findViewById(R.id.bttn_reset);
        bttnReset.setOnClickListener(this);
        bttnReset.setTransformationMethod(null);
        ImageSpan resetSpan = new ImageSpan(TimerFragment.this.getActivity(), R.drawable.ic_refresh_black_24dp);
        SpannableString resetContent = new SpannableString("X");
        resetContent.setSpan(resetSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bttnReset.setText(resetContent);

        playerOne.timerTextView = (TextView) view.findViewById(R.id.player1Timer);
        playerOne.goButton = (ToggleButton) view.findViewById(R.id.player1Go);
        playerOne.goButton.setOnClickListener(this);
        playerTwo.timerTextView = (TextView) view.findViewById(R.id.player2Timer);
        playerTwo.goButton = (ToggleButton) view.findViewById(R.id.player2Go);
        playerTwo.goButton.setOnClickListener(this);

        initializeTimerValues();
        return view;
    }

}
