package vcmsa.ci.musicplaylistmanagerapp //ST10492653_Mohammed Kabeer Rahman_IMAD5112

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private val songs = ArrayList<String>()
    private val artists = ArrayList<String>()
    private val ratings = ArrayList<Int>()
    private val comments = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val titleInput = findViewById<EditText>(R.id.titleInput)
        val artistInput = findViewById<EditText>(R.id.artistInput)
        val ratingInput = findViewById<EditText>(R.id.ratingInput)
        val commentInput = findViewById<EditText>(R.id.commentInput)

        val addBtn = findViewById<Button>(R.id.addBtn)
        val detailBtn = findViewById<Button>(R.id.detailBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)

        addBtn.setOnClickListener {
            val title = titleInput.text.toString()
            val artist = artistInput.text.toString()
            val rating = ratingInput.text.toString().toIntOrNull()
            val comment = commentInput.text.toString()

            if (title.isNotBlank() && artist.isNotBlank() && rating != null && rating in 1..5 && comment.isNotBlank()) {
                songs.add(title)
                artists.add(artist)
                ratings.add(rating)
                comments.add(comment)
                Toast.makeText(this, "Song added", Toast.LENGTH_SHORT).show()

                // Clear inputs
                titleInput.text.clear()
                artistInput.text.clear()
                ratingInput.text.clear()
                commentInput.text.clear()
            } else {
                Toast.makeText(this, "Please fill all fields correctly!", Toast.LENGTH_LONG).show()
            }
        }

        detailBtn.setOnClickListener {
            val intent = Intent(this, DetailedViewScreen::class.java)
            intent.putStringArrayListExtra("songs", songs)
            intent.putStringArrayListExtra("artists", artists)
            intent.putIntegerArrayListExtra("ratings", ratings)
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        exitBtn.setOnClickListener {
            finish()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}