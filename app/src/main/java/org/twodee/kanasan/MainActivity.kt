package org.twodee.kanasan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val QUIZ = 100

class MainActivity : Activity() {
  private lateinit var scoreText: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    scoreText = findViewById(R.id.scoreText)
    val katakanaButton: Button = findViewById(R.id.katakana)
    val hiraganaButton: Button = findViewById(R.id.hiragana)

    katakanaButton.setOnClickListener {
      val intent = Intent(this, QuizActivity::class.java)
      intent.putExtra("alphabet", Japanese.katakana)
      startActivityForResult(intent, QUIZ)
    }

    hiraganaButton.setOnClickListener {
      val intent = Intent(this, QuizActivity::class.java)
      intent.putExtra("alphabet", Japanese.hiragana)
      startActivityForResult(intent, QUIZ)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == QUIZ) {
      scoreText.text = "Last score: ${data.getIntExtra("score", 0)}"
    }
  }
}
