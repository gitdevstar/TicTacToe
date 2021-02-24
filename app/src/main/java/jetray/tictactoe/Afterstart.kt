package jetray.tictactoe

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import jetray.tictactoe.Afterstart
import java.util.*

class Afterstart : AppCompatActivity() {
    var easy = false
    var medium = false
    var hard = false
    var impossible = false
    var r = Random()
    var flag = 0
    var ax = 10
    var zero = 1
    var sensorflag = 0
    var win = 0
    var i = 0
    var game = 1
    var prevrow = 0
    var prevcol = 0
    var summ = 0
    var ctrflag = 0
    var night = 0
    var resetchecker = 1
    var currentgamedonechecker = 0
    var score1 = 0
    var score2 = 0
    var drawchecker = 0
    var sum = IntArray(8)
    var player1ax = false
    var selectedsingleplayer = false
    var p1: TextView? = null
    var p2: TextView? = null
    var player1: CharSequence = "Player 1"
    var player2: CharSequence = "Player 2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_afterstart)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        val players = intent.getCharSequenceArrayExtra("playersnames")
        player1ax = intent.getBooleanExtra("player1ax", true)
        selectedsingleplayer = intent.getBooleanExtra("selectedsingleplayer", true)
        easy = intent.getBooleanExtra("easy", false)
        medium = intent.getBooleanExtra("medium", false)
        hard = intent.getBooleanExtra("hard", false)
        impossible = intent.getBooleanExtra("impossible", false)
        if (player1ax) {
            ax = 1
            zero = 10
        }
        player1 = players[0]
        player2 = players[1]
        p1 = findViewById<View>(R.id.playerone) as TextView
        p2 = findViewById<View>(R.id.playertwo) as TextView
        p1!!.text = player1
        p2!!.text = player2
        Toast.makeText(this, "$player1\'s turn", Toast.LENGTH_SHORT).show()
    }

    fun kzz(view: View?) {
        if (win == 0 && buttonpressed[0][0] == 0) {
            if (flag % 2 == 0) tracker[0][0] = ax else tracker[0][0] = zero
            printBoard()
            winchecker()
            cpuplay()
            flag++
            buttonpressed[0][0]++
        }
    }

    fun kzo(view: View?) {
        if (win == 0 && buttonpressed[0][1] == 0) {
            if (flag % 2 == 0) tracker[0][1] = ax else tracker[0][1] = zero
            printBoard()
            winchecker()
            cpuplay()
            buttonpressed[0][1]++
            flag++
        }
    }

    fun kzt(view: View?) {
        if (win == 0 && buttonpressed[0][2] == 0) {
            if (flag % 2 == 0) tracker[0][2] = ax else tracker[0][2] = zero
            printBoard()
            winchecker()
            cpuplay()
            buttonpressed[0][2]++
            flag++
        }
    }

    fun koz(v: View?) {
        if (win == 0 && buttonpressed[1][0] == 0) {
            if (flag % 2 == 0) tracker[1][0] = ax else tracker[1][0] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[1][0]
            flag++
        }
    }

    fun koo(v: View?) {
        if (win == 0 && buttonpressed[1][1] == 0) {
            if (flag % 2 == 0) tracker[1][1] = ax else tracker[1][1] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[1][1]
            flag++
        }
    }

    fun kot(v: View?) {
        if (win == 0 && buttonpressed[1][2] == 0) {
            if (flag % 2 == 0) tracker[1][2] = ax else tracker[1][2] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[1][2]
            flag++
        }
    }

    fun ktz(v: View?) {
        if (win == 0 && buttonpressed[2][0] == 0) {
            if (flag % 2 == 0) tracker[2][0] = ax else tracker[2][0] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[2][0]
            flag++
        }
    }

    fun kto(v: View?) {
        if (win == 0 && buttonpressed[2][1] == 0) {
            if (flag % 2 == 0) tracker[2][1] = ax else tracker[2][1] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[2][1]
            flag++
        }
    }

    fun ktt(v: View?) {
        if (win == 0 && buttonpressed[2][2] == 0) {
            if (flag % 2 == 0) tracker[2][2] = ax else tracker[2][2] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[2][2]
            flag++
        }
    }

    fun cpuplay() {
        if (selectedsingleplayer && win == 0) {
            if (ifcpuwin()) ; else if (ifopowin()) ; else if (emptycentre()) ; else if (emptycorner()) ; else emptyany()
            /***  final Handler handler = new Handler();
             * Timer t = new Timer();
             * t.schedule(new TimerTask() {
             * public void run() {
             * handler.post(new Runnable() {
             * public void run() {
             *
             * //add code to be executed after a pause
             *
             * }
             * });
             * }
             * }, 80); */
            printBoard()
            winchecker()
            flag++
            return
        }
    }

    fun ifcpuwin(): Boolean {
        if (!easy) {
            i = 0
            while (i < 8) {
                if (sum[i] == 2 * zero) {
                    if (i == 0) {
                        for (x in 0..2) if (tracker[0][x] == 0) tracker[0][x] = zero
                    }
                    if (i == 1) {
                        for (x in 0..2) if (tracker[1][x] == 0) tracker[1][x] = zero
                    }
                    if (i == 2) {
                        for (x in 0..2) if (tracker[2][x] == 0) tracker[2][x] = zero
                    }
                    if (i == 3) {
                        for (x in 0..2) if (tracker[x][0] == 0) tracker[x][0] = zero
                    }
                    if (i == 4) {
                        for (x in 0..2) if (tracker[x][1] == 0) tracker[x][1] = zero
                    }
                    if (i == 5) {
                        for (x in 0..2) if (tracker[x][2] == 0) tracker[x][2] = zero
                    }
                    if (i == 6) {
                        for (y in 0..2) for (x in 0..2) if (x == y) if (tracker[x][y] == 0) tracker[x][y] = zero
                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0) tracker[0][2] = zero else if (tracker[1][1] == 0) tracker[1][1] = zero else tracker[2][0] = zero
                    }
                    return true
                }
                i++
            }
        }
        return false
    }

    fun ifopowin(): Boolean {
        if (!easy || !medium) {
            i = 0
            while (i < 8) {
                if (sum[i] == 2 * ax) {
                    if (i == 0) {
                        for (x in 0..2) if (tracker[0][x] == 0) {
                            tracker[0][x] = zero
                            buttonpressed[0][x]++
                        }
                    }
                    if (i == 1) {
                        for (x in 0..2) if (tracker[1][x] == 0) {
                            tracker[1][x] = zero
                            buttonpressed[1][x]++
                        }
                    }
                    if (i == 2) {
                        for (x in 0..2) if (tracker[2][x] == 0) {
                            tracker[2][x] = zero
                            buttonpressed[2][x]++
                        }
                    }
                    if (i == 3) {
                        for (x in 0..2) if (tracker[x][0] == 0) {
                            tracker[x][0] = zero
                            buttonpressed[x][0]++
                        }
                    }
                    if (i == 4) {
                        for (x in 0..2) if (tracker[x][1] == 0) {
                            tracker[x][1] = zero
                            buttonpressed[x][1]++
                        }
                    }
                    if (i == 5) {
                        for (x in 0..2) if (tracker[x][2] == 0) {
                            tracker[x][2] = zero
                            buttonpressed[x][2]++
                        }
                    }
                    if (i == 6) {
                        for (y in 0..2) for (x in 0..2) if (x == y) if (tracker[x][y] == 0) {
                            tracker[x][y] = zero
                            buttonpressed[x][y]++
                        }
                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0) {
                            tracker[0][2] = zero
                            buttonpressed[0][2]++
                        } else if (tracker[1][1] == 0) {
                            tracker[1][1] = zero
                            buttonpressed[1][1]++
                        } else {
                            tracker[2][0] = zero
                            buttonpressed[2][0]++
                        }
                    }
                    return true
                }
                i++
            }
        }
        return false
    }

    fun emptycentre(): Boolean {
        if (impossible || hard) {
            if (tracker[1][1] == 0) {
                tracker[1][1] = zero
                buttonpressed[1][1]++
                return true
            }
        }
        return false
    }

    fun emptycorner(): Boolean {
        if (hard || impossible) if (tracker[0][0] + tracker[2][2] == 2 * ax || tracker[0][2] + tracker[2][0] == 2 * ax) {
            for (k in 0..2) for (j in 0..2) if ((k + j) % 2 == 1) {
                if (tracker[k][j] == 0) tracker[k][j] = zero
                buttonpressed[k][j]++
                return true
            }
        }
        if (impossible) if (sum[6] == zero || sum[7] == zero) {
            if (sum[6] == zero) {
                if (sum[0] + sum[3] > sum[2] + sum[5]) {
                    tracker[0][0] = zero
                    buttonpressed[0][0]++
                } else {
                    tracker[2][2] = zero
                    buttonpressed[2][2]++
                }
                return true
            }
            if (sum[7] == zero) {
                if (sum[0] + sum[5] > sum[3] + sum[2]) {
                    tracker[0][2] = zero
                    buttonpressed[0][2]++
                } else {
                    tracker[2][0] = zero
                    buttonpressed[2][0]++
                }
                return true
            }
        }
        for (i in 0..2) {
            if (tracker[0][i] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero
                    buttonpressed[0][0]++
                    return true
                }
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero
                    buttonpressed[0][2]++
                    return true
                }
            }
        }
        for (i in 0..2) {
            if (tracker[2][i] == ax) {
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero
                    buttonpressed[2][0]++
                    return true
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero
                    buttonpressed[2][2]++
                    return true
                }
            }
        }
        for (i in 0..2) {
            if (tracker[i][0] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero
                    buttonpressed[0][0]++
                    return true
                }
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero
                    buttonpressed[2][0]++
                    return true
                }
            }
        }
        for (i in 0..2) {
            if (tracker[i][2] == ax) {
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero
                    buttonpressed[0][2]++
                    return true
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero
                    buttonpressed[2][2]++
                    return true
                }
            }
        }
        return false
    }

    fun emptyany() {
        if (ctrflag == 0) while (true) {
            val x = rand()
            val y = rand()
            if (tracker[x][y] == 0) {
                tracker[x][y] = zero
                buttonpressed[x][y]++
                return
            }
        }
        for (x in 0..2) for (y in 0..2) if (tracker[x][y] == 0) {
            tracker[x][y] = zero
            buttonpressed[x][y]++
            return
        }
    }

    fun rand(): Int {
        return r.nextInt(3)
    }

    fun printBoard() {
        val q1: ImageView
        val q2: ImageView
        val q3: ImageView
        val q4: ImageView
        val q5: ImageView
        val q6: ImageView
        val q7: ImageView
        val q8: ImageView
        val q9: ImageView
        q1 = findViewById<View>(R.id.tzz) as ImageView
        q2 = findViewById<View>(R.id.tzo) as ImageView
        q3 = findViewById<View>(R.id.tzt) as ImageView
        q4 = findViewById<View>(R.id.toz) as ImageView
        q5 = findViewById<View>(R.id.too) as ImageView
        q6 = findViewById<View>(R.id.tot) as ImageView
        q7 = findViewById<View>(R.id.ttz) as ImageView
        q8 = findViewById<View>(R.id.tto) as ImageView
        q9 = findViewById<View>(R.id.ttt) as ImageView
        if (tracker[0][0] == 1) q1.setImageResource(R.drawable.x)
        if (tracker[0][0] == 10) q1.setImageResource(R.drawable.oo)
        if (tracker[0][1] == 1) q2.setImageResource(R.drawable.x)
        if (tracker[0][1] == 10) q2.setImageResource(R.drawable.oo)
        if (tracker[0][2] == 1) q3.setImageResource(R.drawable.x)
        if (tracker[0][2] == 10) q3.setImageResource(R.drawable.oo)
        if (tracker[1][0] == 1) q4.setImageResource(R.drawable.x)
        if (tracker[1][0] == 10) q4.setImageResource(R.drawable.oo)
        if (tracker[1][1] == 1) q5.setImageResource(R.drawable.x)
        if (tracker[1][1] == 10) q5.setImageResource(R.drawable.oo)
        if (tracker[1][2] == 1) q6.setImageResource(R.drawable.x)
        if (tracker[1][2] == 10) q6.setImageResource(R.drawable.oo)
        if (tracker[2][0] == 1) q7.setImageResource(R.drawable.x)
        if (tracker[2][0] == 10) q7.setImageResource(R.drawable.oo)
        if (tracker[2][1] == 1) q8.setImageResource(R.drawable.x)
        if (tracker[2][1] == 10) q8.setImageResource(R.drawable.oo)
        if (tracker[2][2] == 1) q9.setImageResource(R.drawable.x)
        if (tracker[2][2] == 10) q9.setImageResource(R.drawable.oo)
        resetchecker++
    }

    fun showDialog(whoWon: String?, scoreWon: String?, whoLose: String?, scoreLose: String?) {
        val dialog = Dialog(this@Afterstart)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_layout)
        //        TextView playerOneScore = dialog.findViewById(R.id.player_one_score);
//        TextView playerTwoScore = dialog.findViewById(R.id.player_two_score);
        val titleText = dialog.findViewById<TextView>(R.id.title_text)
        dialog.setCancelable(false)
        dialog.show()
        titleText.text = whoWon
        //        playerOneScore.setText(whoWon+" Score -> "+scoreWon);
//        playerTwoScore.setText(whoLose+"Score -> "+scoreLose);
        val resetButton = dialog.findViewById<Button>(R.id.reset_button)
        val playAgainButton = dialog.findViewById<Button>(R.id.play_again_button)
        resetButton.setOnClickListener {
            dialog.dismiss()
            doreset()
        }
        playAgainButton.setOnClickListener {
            dialog.dismiss()
            playmore()
        }
    }

    fun winchecker() {
        ctrflag++
        sum[0] = tracker[0][0] + tracker[0][1] + tracker[0][2]
        sum[1] = tracker[1][0] + tracker[1][1] + tracker[1][2]
        sum[2] = tracker[2][0] + tracker[2][1] + tracker[2][2]
        sum[3] = tracker[0][0] + tracker[1][0] + tracker[2][0]
        sum[4] = tracker[0][1] + tracker[1][1] + tracker[2][1]
        sum[5] = tracker[0][2] + tracker[1][2] + tracker[2][2]
        sum[6] = tracker[0][0] + tracker[1][1] + tracker[2][2]
        sum[7] = tracker[0][2] + tracker[1][1] + tracker[2][0]
        currentgamedonechecker++
        resetchecker++
        for (i in 0..7) if (sum[i] == 3 || sum[i] == 30) {
            win++
            if (sum[i] == 3 && ax == 1) {
                score1++
                val q1 = findViewById<View>(R.id.p1score) as TextView
                q1.text = "" + score1
                showDialog("$player1 won!", "" + score1, "" + player2, "" + score2)
            }
            if (sum[i] == 3 && zero == 1) {
                score2++
                val q1 = findViewById<View>(R.id.p2score) as TextView
                q1.text = "" + score2
                showDialog("$player2 won!", "" + score2, "" + player1, "" + score1)
            }
            if (sum[i] == 30 && ax == 10) {
                score1++
                val q1 = findViewById<View>(R.id.p1score) as TextView
                q1.text = "" + score1
                showDialog("$player1 won!", "" + score1, "" + player2, "" + score2)
            }
            if (sum[i] == 30 && zero == 10) {
                score2++
                val q1 = findViewById<View>(R.id.p2score) as TextView
                q1.text = "" + score2
                showDialog("$player2 won!", "" + score2, "" + player1, "" + score1)
            }
        }
        if (ctrflag == 9 && win == 0) {
            showDialog("This is a draw !", "" + score1, "" + player2, "" + score2)
            drawchecker++
        }
    } //end winchecker()

    private fun playmore() {
        if (drawchecker > 0 || win > 0) {
            game++
            val qq = findViewById<View>(R.id.gamenumber) as TextView
            qq.text = "" + game
            for (i in 0..7) sum[i] = 0
            drawchecker = 0
            val q1: ImageView
            val q2: ImageView
            val q3: ImageView
            val q4: ImageView
            val q5: ImageView
            val q6: ImageView
            val q7: ImageView
            val q8: ImageView
            val q9: ImageView
            q1 = findViewById<View>(R.id.tzz) as ImageView
            q2 = findViewById<View>(R.id.tzo) as ImageView
            q3 = findViewById<View>(R.id.tzt) as ImageView
            q4 = findViewById<View>(R.id.toz) as ImageView
            q5 = findViewById<View>(R.id.too) as ImageView
            q6 = findViewById<View>(R.id.tot) as ImageView
            q7 = findViewById<View>(R.id.ttz) as ImageView
            q8 = findViewById<View>(R.id.tto) as ImageView
            q9 = findViewById<View>(R.id.ttt) as ImageView
            q1.setImageDrawable(null)
            q2.setImageDrawable(null)
            q3.setImageDrawable(null)
            q4.setImageDrawable(null)
            q5.setImageDrawable(null)
            q6.setImageDrawable(null)
            q7.setImageDrawable(null)
            q8.setImageDrawable(null)
            q9.setImageDrawable(null)
            for (i in 0..2) for (j in 0..2) buttonpressed[i][j] = 0
            for (i in 0..2) for (j in 0..2) tracker[i][j] = 0
            if ((game + 1) % 2 == 0) Toast.makeText(this, "$player1\'s turn", Toast.LENGTH_SHORT).show() else Toast.makeText(this, "$player2\'s turn", Toast.LENGTH_SHORT).show()
            win = 0
            summ = 0
            ctrflag = 0
            flag = (game + 1) % 2
            currentgamedonechecker = 0
            if (selectedsingleplayer && game % 2 == 0) cpuplay()
        }
    }

    fun doreset() {
        val qq = findViewById<View>(R.id.gamenumber) as TextView
        qq.text = "" + 1
        for (i in 0..2) for (j in 0..2) tracker[i][j] = 0
        for (i in 0..2) for (j in 0..2) buttonpressed[i][j] = 0
        val q1: ImageView
        val q2: ImageView
        val q3: ImageView
        val q4: ImageView
        val q5: ImageView
        val q6: ImageView
        val q7: ImageView
        val q8: ImageView
        val q9: ImageView
        q1 = findViewById<View>(R.id.tzz) as ImageView
        q2 = findViewById<View>(R.id.tzo) as ImageView
        q3 = findViewById<View>(R.id.tzt) as ImageView
        q4 = findViewById<View>(R.id.toz) as ImageView
        q5 = findViewById<View>(R.id.too) as ImageView
        q6 = findViewById<View>(R.id.tot) as ImageView
        q7 = findViewById<View>(R.id.ttz) as ImageView
        q8 = findViewById<View>(R.id.tto) as ImageView
        q9 = findViewById<View>(R.id.ttt) as ImageView
        q1.setImageDrawable(null)
        q2.setImageDrawable(null)
        q3.setImageDrawable(null)
        q4.setImageDrawable(null)
        q5.setImageDrawable(null)
        q6.setImageDrawable(null)
        q7.setImageDrawable(null)
        q8.setImageDrawable(null)
        q9.setImageDrawable(null)
        win = 0
        drawchecker = 0
        summ = 0
        resetchecker = 0
        ctrflag = 0
        score1 = 0
        score2 = 0
        game = 1
        flag = 0
        currentgamedonechecker = 0
        val qqq = findViewById<View>(R.id.p1score) as TextView
        qqq.text = "" + score1
        val qqqq = findViewById<View>(R.id.p2score) as TextView
        qqqq.text = "" + score2
        Toast.makeText(this, "$player1\'s turn", Toast.LENGTH_SHORT).show()
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
    private fun showExitDialog() {
        val dialog = Dialog(this@Afterstart)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_layout_exit)
        dialog.setCancelable(false)
        dialog.show()
        val exit = dialog.findViewById<Button>(R.id.yes_button)
        val dismiss = dialog.findViewById<Button>(R.id.no_button)
        exit.setOnClickListener {
            doreset()
            finish()
        }
        dismiss.setOnClickListener { dialog.dismiss() }
    }

    override fun onBackPressed() {
        showExitDialog()
    } //    @Override

    //    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.exit) {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("EXIT", true);
//            doreset();
//            startActivity(intent);
//        }
//
//        if (id == R.id.daynightmode) {
//
//            if (night % 2 == 0) {
//                View view = this.getWindow().getDecorView();
//                view.setBackgroundColor(Color.parseColor("#000000"));
//                item.setTitle("Day Mode");
//            } else {
//                View view = this.getWindow().getDecorView();
//                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                item.setTitle("Night Mode");
//            }
//            night++;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
    companion object {
        var tracker = Array(3) { IntArray(3) }
        var buttonpressed = Array(3) { IntArray(3) }
    }
}