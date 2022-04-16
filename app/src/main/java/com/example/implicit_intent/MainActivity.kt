package com.example.implicit_intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.implicit_intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnExe.setOnClickListener {
            when {
                binding.url.isChecked -> {
                    startActivity(
                        Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https:" + binding.textView.text.toString())
                    )
                    )
                }
                binding.map.isChecked -> {
                    startActivity(Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:" + binding.textView.text.toString())
                    ))
                }
                binding.phone.isChecked -> {
                    startActivity(Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("tel:${binding.textView.text}")
                    ))
                }
                else -> {
                    when {
                        (binding.textView.text.toString().matches(Regex("""([+]79[0-9]{9})|(89[0-9]{9})"""))) -> {
                            startActivity(Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("tel:" + binding.textView.text.toString())
                            ))
                        }
                        (binding.textView.text.toString().matches(Regex("""(-[0-90])|[0-90],(-[0-180])|[0-180]"""))) -> {
                            startActivity(Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("geo:" + binding.textView.text.toString())
                            ))
                        }
                        else -> {
                            startActivity(Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https:" + binding.textView.text.toString())
                            ))
                        }
                    }
                }
            }
        }
    }
}