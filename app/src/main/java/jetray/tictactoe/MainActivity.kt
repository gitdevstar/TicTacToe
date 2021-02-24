package jetray.tictactoe

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import jetray.tictactoe.Afterstart
import java.util.*

class MainActivity : AppCompatActivity() {
    var plyr1: EditText? = null
    var plyr2: EditText? = null
    var difficulty: Spinner? = null
    var player1: CharSequence = "Player 1"
    var player2: CharSequence = "Player 2"
    var cloneplayer2: CharSequence? = null
    var player1ax = true
    var selectedSinglePlayer = false
    var easy = true
    var medium = false
    var hard = false
    var impossible = false
    var p1x: CheckBox? = null
    var p1o: CheckBox? = null
    var p2x: CheckBox? = null
    var p2o: CheckBox? = null
    var singleplayer: CheckBox? = null
    var twoplayer: CheckBox? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //apply the animation ( fade In ) to your LAyout
        if (intent.getBooleanExtra("EXIT", false)) {
            finish()
        }
        addItemToDifficultySpinner()
        plyr1 = findViewById<View>(R.id.playerone) as EditText
        plyr2 = findViewById<View>(R.id.playertwo) as EditText
        p1x = findViewById<View>(R.id.player1x) as CheckBox
        p1o = findViewById<View>(R.id.player1o) as CheckBox
        p2x = findViewById<View>(R.id.player2x) as CheckBox
        p2o = findViewById<View>(R.id.player2o) as CheckBox
        singleplayer = findViewById<View>(R.id.splayer) as CheckBox
        twoplayer = findViewById<View>(R.id.tplayer) as CheckBox
        p1x!!.setOnClickListener(checkboxClickListener)
        p1o!!.setOnClickListener(checkboxClickListener)
        p2x!!.setOnClickListener(checkboxClickListener)
        p2o!!.setOnClickListener(checkboxClickListener)
        singleplayer!!.setOnClickListener(checkboxClickListener)
        twoplayer!!.setOnClickListener(checkboxClickListener)
        difficulty!!.isEnabled = false
        p1x!!.isChecked = true
        p2o!!.isChecked = true
        twoplayer!!.isChecked = true
        plyr1!!.addTextChangedListener(object : TextWatcher {
            /*this code take player1's name characterwise i.e it takes one character at a time and
                                                                                         saved to string variable player1*/
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                player1 = s.toString()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        plyr2!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                player2 = s.toString()
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun addItemToDifficultySpinner() {
        difficulty = findViewById<View>(R.id.difficulty) as Spinner
        val list: MutableList<String> = ArrayList()
        list.add("Easy")
        list.add("Medium")
        list.add("Hard")
        list.add("Impossible")
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        difficulty!!.adapter = dataAdapter
        difficulty!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val temp = parent.getItemAtPosition(position).toString()
                when (temp) {
                    "Easy" -> {
                        easy = true
                        medium = false
                        hard = false
                        impossible = false
                    }
                    "Medium" -> {
                        easy = false
                        medium = true
                        hard = false
                        impossible = false
                    }
                    "Hard" -> {
                        easy = false
                        medium = false
                        hard = true
                        impossible = false
                    }
                    "Impossible" -> {
                        easy = false
                        medium = false
                        hard = false
                        impossible = true
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                medium = true
                easy = false
                hard = false
                impossible = false
            }
        }
    }

    var checkboxClickListener = View.OnClickListener { view ->
        val checked = (view as CheckBox).isChecked
        if (checked) {
            when (view.getId()) {
                R.id.player1x -> {
                    p1o!!.isChecked = false
                    p2x!!.isChecked = false
                    p2o!!.isChecked = true
                    player1ax = true
                }
                R.id.player1o -> {
                    p1x!!.isChecked = false
                    p2o!!.isChecked = false
                    p2x!!.isChecked = true
                    player1ax = false
                }
                R.id.player2x -> {
                    p2o!!.isChecked = false
                    p1x!!.isChecked = false
                    p1o!!.isChecked = true
                    player1ax = false
                }
                R.id.player2o -> {
                    p2x!!.isChecked = false
                    p1o!!.isChecked = false
                    p1x!!.isChecked = true
                    player1ax = true
                }
                R.id.splayer -> {
                    twoplayer!!.isChecked = false
                    selectedSinglePlayer = true
                    cloneplayer2 = player2
                    plyr2!!.setText("CPU")
                    plyr1!!.imeOptions = EditorInfo.IME_ACTION_DONE
                    plyr1!!.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE)
                    difficulty!!.isEnabled = true
                }
                R.id.tplayer -> {
                    singleplayer!!.isChecked = false
                    selectedSinglePlayer = false
                    plyr2!!.setText(cloneplayer2)
                    plyr1!!.imeOptions = EditorInfo.IME_ACTION_NEXT
                    plyr1!!.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_NEXT)
                    difficulty!!.isEnabled = false
                }
            }
        } else {
            when (view.getId()) {
                R.id.player1x -> {
                    p1o!!.isChecked = true
                    p2x!!.isChecked = true
                    p2o!!.isChecked = false
                    player1ax = false
                }
                R.id.player1o -> {
                    p1x!!.isChecked = true
                    p2o!!.isChecked = true
                    p2x!!.isChecked = false
                    player1ax = true
                }
                R.id.player2x -> {
                    p2o!!.isChecked = true
                    p1x!!.isChecked = true
                    p1o!!.isChecked = false
                    player1ax = true
                }
                R.id.player2o -> {
                    p2x!!.isChecked = true
                    p1o!!.isChecked = true
                    p1x!!.isChecked = false
                    player1ax = false
                }
                R.id.splayer -> {
                    twoplayer!!.isChecked = true
                    selectedSinglePlayer = false
                    plyr2!!.setText(cloneplayer2)
                    difficulty!!.isEnabled = false
                    plyr1!!.imeOptions = EditorInfo.IME_ACTION_NEXT
                    plyr1!!.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_NEXT)
                }
                R.id.tplayer -> {
                    singleplayer!!.isChecked = true
                    selectedSinglePlayer = true
                    plyr2!!.setText("CPU")
                    plyr1!!.imeOptions = EditorInfo.IME_ACTION_DONE
                    plyr1!!.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE)
                    difficulty!!.isEnabled = true
                }
            }
        }
    }

    fun startgame(view: View?) {
        if (!selectedSinglePlayer) if (player2.length == 0) player2 = "player 2"
        if (player1.length == 0) player1 = "player 1"
        val players = arrayOf(player1, player2)
        val i = Intent(this, Afterstart::class.java)
        i.putExtra("easy", easy)
        i.putExtra("medium", medium)
        i.putExtra("hard", hard)
        i.putExtra("impossible", impossible)
        i.putExtra("playersnames", players)
        i.putExtra("player1ax", player1ax)
        i.putExtra("selectedsingleplayer", selectedSinglePlayer)
        startActivity(i)
    }
}