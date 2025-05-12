package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

data class Recipe(val title: String, val description: String, val image: Int)

class MainActivity : AppCompatActivity() {

    private val recipeList = listOf(
        Recipe("Maggi", "1. Boil water\n2. Add noodles\n3. Add tastemaker\n4. Cook for 2 mins", R.drawable.maggi_image),
        Recipe("Tea", "1. Boil water\n2. Add tea leaves\n3. Add milk and sugar\n4. Strain and serve", R.drawable.tea_image),
        Recipe("Sandwich", "1. Take bread\n2. Add veggies and cheese\n3. Grill and serve", R.drawable.sandwich_image)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.recipeListView)

        val adapter = object : ArrayAdapter<Recipe>(this, R.layout.list_item_recipe, recipeList) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val inflater = LayoutInflater.from(context)
                val view = convertView ?: inflater.inflate(R.layout.list_item_recipe, parent, false)

                val recipe = getItem(position)
                val imageView = view.findViewById<ImageView>(R.id.recipeImage)
                val textView = view.findViewById<TextView>(R.id.recipeTitle)

                imageView.setImageResource(recipe?.image ?: R.drawable.default_image)
                textView.text = recipe?.title

                return view
            }
        }

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            val selectedRecipe = recipeList[position]
            intent.putExtra("title", selectedRecipe.title)
            intent.putExtra("description", selectedRecipe.description)
            startActivity(intent)
        }
    }
}
