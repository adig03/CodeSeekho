package com.aditya1010.codeseekho

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.GridLayout
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya1010.codeseekho.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val handler=Handler(Looper.getMainLooper())
    private val typingDelay=100L
    private  var dataBase = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
       // fetchUserData()
        fetchAndAnimateText()
        //display grid
        displayGrid()

        //display recyclerView
        displayRecyclerView()



        val signout:Button = findViewById<Button>(R.id.SignOut)

        signout.setOnClickListener {
            signOut()
        }


    }

    private fun displayRecyclerView(){
        val cardsRecyclerView:RecyclerView =findViewById<RecyclerView>(R.id.MyRecylerView)
        val gridLayoutManager = GridLayoutManager(this , 2 , GridLayoutManager.VERTICAL, false)
        cardsRecyclerView.layoutManager = gridLayoutManager


        val card1 = RecyclerItem("Arrays", "X Questions" , R.drawable.array)
        val card2 = RecyclerItem("Linked List", "Y Questions" , R.drawable.linklist)
        val card3 = RecyclerItem("Binary Trees", "Z Questions" , R.drawable.binarytree)
        val card4 = RecyclerItem("Recursion", "W Questions" , R.drawable.recursion)
        val card5 = RecyclerItem("Functions", "V Questions" , R.drawable.functions)
        val card6= RecyclerItem("Stacks", "L Questions" , R.drawable.stacks)
        val card7 = RecyclerItem("Strings" ,"Z questions" , R.drawable.string)
        val card8 = RecyclerItem("Graphs" ,"v questions" , R.drawable.graph)
        val card9= RecyclerItem("DP" ,"M questions" , R.drawable.dynamicprogramming)
        val card10 = RecyclerItem("Greedy" ,"F questions" , R.drawable.greedy)
        val card11 = RecyclerItem("Heaps" ,"Y questions" , R.drawable.heap)
        val card12 = RecyclerItem("Bit Manipulation" ,"E questions" , R.drawable.bitmanipulation)




        val lowerGrid = ArrayList<RecyclerItem>()


        lowerGrid.add(card1)
        lowerGrid.add(card2)
        lowerGrid.add(card3)
        lowerGrid.add(card4)
        lowerGrid.add(card5)
        lowerGrid.add(card6)
        lowerGrid.add(card7)
        lowerGrid.add(card8)
        lowerGrid.add(card9)
        lowerGrid.add(card10)
        lowerGrid.add(card11)
        lowerGrid.add(card12)

        val adapter = MyRecyclerAdapter(this , lowerGrid)
        cardsRecyclerView.adapter=adapter

    }


    private fun displayGrid(){
        val grid:GridView = findViewById<GridView>(R.id.GridView)

        val gridItem1 = GridItem("Notes" , R.drawable.notes)
        val gridItem2 = GridItem("Quizes" , R.drawable.quiz)
        val gridItem3 = GridItem("Marked Questions" ,R.drawable.markesques)
        val gridItem4 = GridItem("Progress" , R.drawable.progress)


        val allGridItems = ArrayList<GridItem>()

        allGridItems.add(gridItem1)
        allGridItems.add(gridItem2)
        allGridItems.add(gridItem3)
        allGridItems.add(gridItem4)

        var MyAdapter = MyGridAdapter(this , allGridItems)

        grid.adapter = MyAdapter

    }

    private fun signOut(){
        var signout:Button = findViewById<Button>(R.id.SignOut)


            // Show a confirmation dialog before signing out
            AlertDialog.Builder(this)
                .setTitle("Sign Out")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Yes") { dialog, which ->
                    // If the user confirms, proceed with sign out
                    val firebaseAuth = FirebaseAuth.getInstance()
                    firebaseAuth.signOut()

                    // Redirect to the login screen
                    val intent = Intent(this, LoginPage::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish() // Optional: Close the current activity
                }
                .setNegativeButton("No") { dialog, which ->
                    // If the user cancels, just dismiss the dialog
                    dialog.dismiss()
                }
                .show()
        }

    private fun fetchUserData(){
        val userName:TextView = findViewById<TextView>(R.id.mainUserName)
        dataBase.collection(Firebase.auth.currentUser?.uid.toString()).
        document(Firebase.auth.currentUser?.uid.toString()).get().addOnSuccessListener{ it->
            val Name :String? =it.getString("name")

            userName.text = Name.toString()

        }
    }
    private fun fetchAndAnimateText() {
        val db = FirebaseFirestore.getInstance()
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid.toString()

        val userName:TextView = findViewById<TextView>(R.id.mainUserName)
        db.collection(Firebase.auth.currentUser?.uid.toString()).document(Firebase.auth.currentUser?.uid.toString())
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val name = documentSnapshot.getString("name") ?: "Name not available"
                    typeText(name)
                } else {
                    userName.text = "No such document"
                }
            }
            .addOnFailureListener { exception ->
                userName.text = "Error fetching data: ${exception.message}"
            }
    }

    private fun typeText(text: String) {
        val textView: TextView = findViewById<TextView>(R.id.mainUserName)
        val length = text.length
        var index = 0

        val runnable = object : Runnable {
            override fun run() {
                val end = (index + 1).coerceAtMost(length)
                textView.text = text.substring(0, end)
                index = end

                if (index < length) {
                    handler.postDelayed(this, typingDelay)
                }
            }
        }
        handler.post(runnable)
    }
    }
