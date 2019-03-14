package org.twodee.kanasan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class QuizActivity : Activity() {
  private lateinit var alphabet: Array<String>
  private lateinit var kanaText: TextView
  private lateinit var romajiBox: EditText

  private var round: Int = 0
  private var currentIndex: Int = 0
  private var score: Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_quiz)
    alphabet = intent.getStringArrayExtra("alphabet")
    kanaText = findViewById(R.id.kanaText)
    romajiBox = findViewById(R.id.romajiBox)

    round = 0
    next()

    romajiBox.setOnEditorActionListener { _, i, _ ->
      if (i == EditorInfo.IME_ACTION_DONE) {
        if (romajiBox.text.toString() == Japanese.romaji[currentIndex]) {
          next()
          Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show()
          score += 10
        } else {
          Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
          score -= 5
        }
        true
      } else {
        false
      }
    }
  }

  fun next() {
    if (round > 5) {
      val result = Intent()
      result.putExtra("score", score)
      setResult(Activity.RESULT_OK, result)
      finish()
    } else {
      romajiBox.setText("")
      romajiBox.requestFocus()
      currentIndex = Random.nextInt(alphabet.size)
      kanaText.text = alphabet[currentIndex]
      round += 1
    }
  }
}
