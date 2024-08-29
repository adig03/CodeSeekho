package com.aditya1010.codeseekho

import android.os.Bundle
import android.widget.GridLayout
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        //display grid
        displayGrid()

        //display recyclerView
        displayRecyclerView()






    }

    private fun displayRecyclerView(){
        val cardsRecyclerView:RecyclerView =findViewById<RecyclerView>(R.id.MyRecylerView)
        val gridLayoutManager = GridLayoutManager(this , 2 , GridLayoutManager.HORIZONTAL , false)
        cardsRecyclerView.layoutManager = gridLayoutManager


        val card1 = RecyclerItem("Array", "X Questions" , R.drawable.array)
        val card2 = RecyclerItem("LinkList", "Y Questions" , R.drawable.linklist)
        val card3 = RecyclerItem("Binary Trees", "Z Questions" , R.drawable.binarytree)
        val card4 = RecyclerItem("Recursion", "W Questions" , R.drawable.recursion)
        val card5 = RecyclerItem("Functions", "V Questions" , R.drawable.functions)
        val card6= RecyclerItem("Stacks", "L Questions" , R.drawable.stacks)


        val lowerGrid = ArrayList<RecyclerItem>()


        lowerGrid.add(card1)
        lowerGrid.add(card2)
        lowerGrid.add(card3)
        lowerGrid.add(card4)
        lowerGrid.add(card5)
        lowerGrid.add(card6)

        val adapter = MyRecyclerAdapter(this , lowerGrid)
        cardsRecyclerView.adapter=adapter

    }


    private fun displayGrid(){
        val grid:GridView = findViewById<GridView>(R.id.GridView)

        val gridItem1 = GridItem("Notes" , R.drawable.notes)
        val gridItem2 = GridItem("History" , R.drawable.history)
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
}